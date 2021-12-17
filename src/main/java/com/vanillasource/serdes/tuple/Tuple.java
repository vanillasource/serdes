/**
 * Copyright (C) 2021 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.tuple;

import java.util.stream.Stream;
import java.util.Objects;

public interface Tuple {
   Stream<Object> stream();

   default int size() {
      return (int) stream().count();
   }

   default boolean isEmpty() {
      return stream().allMatch(Objects::isNull);
   }
}
