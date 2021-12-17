/**
 * Copyright (C) 2019 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes;

import java.io.OutputStream;
import java.io.InputStream;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.Optional;

public interface Serdes<A> extends Serializer<A>, Deserializer<A> {
   default <O> Serdes<O> map(Function<O, A> unpack, Function<A, O> pack) {
      Serdes<A> self = this;
      return new Serdes<O>() {
         @Override
         public Optional<Integer> maxLength() {
            return self.maxLength();
         }

         @Override
         public void serialize(O object, OutputStream output) {
            self.serialize(unpack.apply(object), output);
         }

         @Override
         public O deserialize(InputStream input) {
            return pack.apply(self.deserialize(input));
         }
      };
   }

   public static <A> Serdes<A> of(BiConsumer<A, OutputStream> serializer, Function<InputStream, A> deserializer) {
      return of(Optional.empty(), serializer, deserializer);
   }

   public static <A> Serdes<A> of(int maxLength, BiConsumer<A, OutputStream> serializer, Function<InputStream, A> deserializer) {
      return of(Optional.of(maxLength), serializer, deserializer);
   }

   public static <A> Serdes<A> of(Optional<Integer> maxLength, BiConsumer<A, OutputStream> serializer, Function<InputStream, A> deserializer) {
      return new Serdes<A>() {
         @Override
         public Optional<Integer> maxLength() {
            return maxLength;
         }

         @Override
         public void serialize(A object, OutputStream output) {
            serializer.accept(object, output);
         }

         @Override
         public A deserialize(InputStream input) {
            return deserializer.apply(input);
         }
      };
   }
}
