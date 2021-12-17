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
      return of(
            (o, output) -> serialize(unpack.apply(o), output),
            (input)     -> pack.apply(deserialize(input)));
   }

   public static <A> Serdes<A> of(BiConsumer<A, OutputStream> serializer, Function<InputStream, A> deserializer) {
      return new Serdes<A>() {
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
