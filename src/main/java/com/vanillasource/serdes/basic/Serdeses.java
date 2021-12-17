/**
 * Copyright (C) 2019 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.basic;

import java.util.List;
import java.io.UncheckedIOException;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;

import com.vanillasource.serdes.tuple.Tuple2;
import static com.vanillasource.serdes.tuple.Tuples.*;
import static com.vanillasource.serdes.seq.Sequences.*;
import static com.vanillasource.serdes.seq.SerdesFactory.*;
import com.vanillasource.serdes.Serdes;
import com.vanillasource.serdes.io.LimitedInputStream;
import com.vanillasource.serdes.io.LimitedOutputStream;
import java.util.Optional;

public final class Serdeses {
   private Serdeses() {
   }

   public static <T> Serdes<List<T>> fixedListSerdes(int size, Serdes<T> itemSerializer) {
      return Serdes.of(itemSerializer.maxLength().map(itemMaxLength -> itemMaxLength * size),
            (list, output) -> {
               for (T item: list) {
                  itemSerializer.serialize(item, output);
               }
            },
            (input) -> {
               List<T> list = new ArrayList<>(size);
               for (int i=0; i<size; i++) {
                  list.add(itemSerializer.deserialize(input));
               }
               return list;
            });
   }

   public static <T> Serdes<List<T>> listSerdes(Serdes<T> itemSerializer) {
      return seq(
            intSerdes(),
            withoutMaxLength(size -> fixedListSerdes(size, itemSerializer)))
         .map(o -> tuple(o.size(), o),
              t -> t.b);
   }

   public static <T> Serdes<List<T>> listSerdes(int maxSize, Serdes<T> itemSerializer) {
      return seq(
            intSerdes(),
            optionalMaxLength(itemSerializer.maxLength().map(itemMaxLength -> itemMaxLength*maxSize), size -> fixedListSerdes(size, itemSerializer)))
         .map(o -> {
            if (o.size() > maxSize) {
               throw new IllegalArgumentException("tried to serialize a list that was "+o.size()+" long, but maximum was given as "+maxSize);
            }
            return tuple(o.size(), o);
         }, t -> t.b);
   }

   public static Serdes<byte[]> fixedByteArraySerdes(int size) {
      return data(size, DataOutputStream::write, input -> {
         byte[] array = new byte[size];
         input.readFully(array);
         return array;
      });
   }

   public static <T> Serdes<byte[]> byteArraySerdes() {
      return seq(
            intSerdes(),
            withoutMaxLength(Serdeses::fixedByteArraySerdes))
         .map(o -> tuple(o.length, o),
              t -> t.b);
   }

   public static <T> Serdes<byte[]> byteArraySerdes(int maxLength) {
      return seq(
            intSerdes(),
            withMaxLength(maxLength, Serdeses::fixedByteArraySerdes))
         .map((bytes) -> {
            if (bytes.length > maxLength) {
               throw new IllegalArgumentException("tried to serialize a byte array that was "+bytes.length+" long, but maximum was given as "+maxLength);
            }
            return tuple(bytes.length, bytes);
         }, tuple -> tuple.b);
   }

   public static Serdes<Byte> byteSerdes() {
      return data(1, (output, b) -> output.writeByte(b), DataInputStream::readByte);
   }

   public static Serdes<Short> shortSerdes() {
      return data(2, (output, s) -> output.writeShort(s), DataInputStream::readShort);
   }

   public static Serdes<Integer> intSerdes() {
      return data(4, DataOutputStream::writeInt, DataInputStream::readInt);
   }

   public static Serdes<Long> longSerdes() {
      return data(8, DataOutputStream::writeLong, DataInputStream::readLong);
   }

   public static Serdes<Date> dateSerdes() {
       return longSerdes()
           .map(d -> d.getTime(), t -> new Date(t));
   }

   public static Serdes<Boolean> booleanSerdes() {
      return data(1, DataOutputStream::writeBoolean, DataInputStream::readBoolean);
   }

   public static Serdes<String> stringSerdes() {
      return data(null, DataOutputStream::writeUTF, input -> input.readUTF());
   }

   public static Serdes<String> stringSerdes(int maxLength) {
      return data(maxLength+2, DataOutputStream::writeUTF, input -> input.readUTF());
   }

   public static Serdes<BitSet> bitsSerdes(int maxCount) {
      int length = (maxCount+7)/8;
      return data(maxCount, (output, bitset) -> {
         byte[] bytes = new byte[length];
         byte[] bitBytes = bitset.toByteArray();
         System.arraycopy(bitBytes, 0, bytes, 0, bitBytes.length);
         output.write(bytes);
      },
      (input) -> {
         byte[] bytes = new byte[length];
         input.readFully(bytes);
         return BitSet.valueOf(bytes);
      });
   }

   public static <T> Serdes<T> empty() {
      return Serdes.of(0, (object, output) -> {}, input -> null);
   }

   public static <T> Serdes<T> nullable(Serdes<T> serdes) {
       return seq(
           booleanSerdes(),
           optionalMaxLength(serdes.maxLength(), present -> present ? serdes : empty()))
               .map((value) -> tuple(value!=null, value),
                    tuple -> tuple.b);
   }

   private static <T> Serdes<T> data(Integer maxLength, DataSerializer<T> serializer, DataDeserializer<T> deserializer) {
      return data(maxLength, new DataSerdes<T>() {
         @Override
         public void serialize(T object, DataOutputStream output) throws IOException {
            serializer.serialize(output, object);
         }

         @Override
         public T deserialize(DataInputStream input) throws IOException {
            return deserializer.deserialize(input);
         }
      });
   }
   
   private static <T> Serdes<T> data(Integer maxLength, DataSerdes<T> serdes) {
      return new Serdes<T>() {
         @Override
         public Optional<Integer> maxLength() {
            return Optional.ofNullable(maxLength);
         }

         @Override
         public void serialize(T object, OutputStream output) {
            try {
               if (maxLength == null) {
                  serdes.serialize(object, new DataOutputStream(output));
               } else {
                  serdes.serialize(object, new DataOutputStream(new LimitedOutputStream(output, maxLength)));
               }
            } catch (IOException e) {
               throw new UncheckedIOException(e);
            }
         }

         @Override
         public T deserialize(InputStream input) {
            try {
               if (maxLength == null) {
                  return serdes.deserialize( new DataInputStream(input));
               } else {
                  return serdes.deserialize( new DataInputStream(new LimitedInputStream(input, maxLength)));
               }
            } catch (IOException e) {
               throw new UncheckedIOException(e);
            }
         }
      };
   }

   private interface DataSerdes<T> {
      void serialize(T object, DataOutputStream output) throws IOException;

      T deserialize(DataInputStream input) throws IOException;
   }

   private interface DataSerializer<T> {
      void serialize(DataOutputStream output, T object) throws IOException;
   }

   private interface DataDeserializer<T> {
      T deserialize(DataInputStream input) throws IOException;
   }
}
