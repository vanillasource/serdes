/**
 * Copyright (C) 2021 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.seq;

import com.vanillasource.serdes.Serdes;
import java.util.function.Function;
import java.util.Optional;

public final class SerdesFactory<P, T> {
   private final Optional<Integer> maxLength;
   private final Function<P, Serdes<T>> serdesFactory;

   private SerdesFactory(Optional<Integer> maxLength, Function<P, Serdes<T>> serdesFactory) {
      this.maxLength = maxLength;
      this.serdesFactory = serdesFactory;
   }

   public Serdes<T> createFor(P value) {
      return serdesFactory.apply(value);
   }

   public Optional<Integer> maxLength() {
      return maxLength;
   }

   public static <P, T> SerdesFactory<P, T> independent(Serdes<T> serdes) {
      return new SerdesFactory<>(serdes.maxLength(), nothing -> serdes);
   }

   public static <P, T> SerdesFactory<P, T> withoutMaxLength(Function<P, Serdes<T>> serdesFactory) {
      return new SerdesFactory<>(Optional.empty(), serdesFactory);
   }

   public static <P, T> SerdesFactory<P, T> withMaxLength(int maxLength, Function<P, Serdes<T>> serdesFactory) {
      return new SerdesFactory<>(Optional.of(maxLength), serdesFactory);
   }

   public static <P, T> SerdesFactory<P, T> optionalMaxLength(Optional<Integer> maxLength, Function<P, Serdes<T>> serdesFactory) {
      return new SerdesFactory<>(maxLength, serdesFactory);
   }
}
