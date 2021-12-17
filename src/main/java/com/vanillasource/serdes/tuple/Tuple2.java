/**
 * Copyright (C) 2021 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.tuple;

import java.util.stream.Stream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static java.util.Arrays.asList;

public final class Tuple2<A, B> implements Tuple {
   public final A a;
   public final B b;

   public Tuple2(A a, B b) {
      this.a = a;
      this.b = b;
   }

   @Override
   public Stream<Object> stream() {
      return Stream.of(a, b);
   }

   public List<Tuple2<A, B>> explode() {
      return asList(
            this.a==null?null:new Tuple2<A, B>(a, null),
            this.b==null?null:new Tuple2<A, B>(null, b)
      ).stream().filter(Objects::nonNull).collect(Collectors.toList());
   }

   public Tuple2<A, B> merge(Tuple2<A, B> other) {
      return new Tuple2<A, B>(
            other.a==null?this.a:other.a,
            other.b==null?this.b:other.b
      );
   }

   public static <A, B> Tuple2<A, B> merge(List<Tuple2<A, B>> list) {
      return list.stream().reduce(new Tuple2<A, B>(null, null), Tuple2::merge);
   }

   public static <A, B> Tuple2<A, B> liftA(A a) {
      return new Tuple2<A, B>(a, null);
   }

   public static <A, B> Tuple2<A, B> liftB(B b) {
      return new Tuple2<A, B>(null, b);
   }
}
