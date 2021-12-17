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

public final class Tuple1<A> implements Tuple {
   public final A a;

   public Tuple1(A a) {
      this.a = a;
   }

   @Override
   public Stream<Object> stream() {
      return Stream.of(a);
   }

   public List<Tuple1<A>> explode() {
      return asList(
            this.a==null?null:new Tuple1<A>(a)
      ).stream().filter(Objects::nonNull).collect(Collectors.toList());
   }

   public Tuple1<A> merge(Tuple1<A> other) {
      return new Tuple1<A>(
            other.a==null?this.a:other.a
      );
   }

   public static <A, B> Tuple1<A> merge(List<Tuple1<A>> list) {
      return list.stream().reduce(new Tuple1<A>(null), Tuple1::merge);
   }

   public static <A> Tuple1<A> liftA(A a) {
      return new Tuple1<A>(a);
   }
}
