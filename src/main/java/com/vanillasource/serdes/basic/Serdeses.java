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
import com.vanillasource.serdes.Serdes;
import java.util.Optional;

public final class Serdeses {
   private Serdeses() {
   }

   public static <T> Serdes<List<T>> fixedListSerdes(int size, Serdes<T> itemSerializer) {
      return Serdes.of(
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
            size -> fixedListSerdes(size, itemSerializer))
         .map(o -> tuple(o.size(), o),
              t -> t.b);
   }

   public static Serdes<byte[]> fixedByteArraySerdes(int size) {
      return data(DataOutputStream::write, input -> {
         byte[] array = new byte[size];
         input.readFully(array);
         return array;
      });
   }

   public static <T> Serdes<byte[]> byteArraySerdes() {
      return seq(
            intSerdes(),
            Serdeses::fixedByteArraySerdes)
         .map(o -> tuple(o.length, o),
              t -> t.b);
   }

   public static Serdes<Byte> byteSerdes() {
      return data((output, b) -> output.writeByte(b), DataInputStream::readByte);
   }

   public static Serdes<Short> shortSerdes() {
      return data((output, s) -> output.writeShort(s), DataInputStream::readShort);
   }

   public static Serdes<Integer> intSerdes() {
      return data(DataOutputStream::writeInt, DataInputStream::readInt);
   }

   public static Serdes<Long> longSerdes() {
      return data(DataOutputStream::writeLong, DataInputStream::readLong);
   }

   public static Serdes<Date> dateSerdes() {
       return longSerdes()
           .map(d -> d.getTime(), t -> new Date(t));
   }

   public static Serdes<Boolean> booleanSerdes() {
      return data(DataOutputStream::writeBoolean, DataInputStream::readBoolean);
   }

   public static Serdes<String> stringSerdes() {
      return data(DataOutputStream::writeUTF, input -> input.readUTF());
   }

   public static Serdes<BitSet> bitsSerdes(int maxCount) {
      int length = (maxCount+7)/8;
      return data((output, bitset) -> {
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
      return Serdes.of((object, output) -> {}, input -> null);
   }

   public static <T> Serdes<T> nullable(Serdes<T> serdes) {
       return seq(
           booleanSerdes(),
           present -> present ? serdes : empty())
               .map((value) -> tuple(value!=null, value),
                    tuple -> tuple.b);
   }

   private static <T> Serdes<T> data(DataSerializer<T> serializer, DataDeserializer<T> deserializer) {
      return new Serdes<T>() {
         @Override
         public void serialize(T object, OutputStream output) {
            try {
               serializer.serialize(new DataOutputStream(output), object);
            } catch (IOException e) {
               throw new UncheckedIOException(e);
            }
         }

         @Override
         public T deserialize(InputStream input) {
            try {
               return deserializer.deserialize(new DataInputStream(input));
            } catch (IOException e) {
               throw new UncheckedIOException(e);
            }
         }
      };
   }
   
   private interface DataSerializer<T> {
      void serialize(DataOutputStream output, T object) throws IOException;
   }

   private interface DataDeserializer<T> {
      T deserialize(DataInputStream input) throws IOException;
   }
}
