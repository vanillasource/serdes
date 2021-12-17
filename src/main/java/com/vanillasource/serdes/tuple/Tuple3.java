/**
 * Copyright (C) 2021 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.tuple;

import java.util.stream.Stream;
import java.util.Objects;
import java.util.stream.Collectors;
import static java.util.Arrays.asList;
import java.util.List;

public final class Tuple3<A, B, C> implements Tuple {
   public final A a;
   public final B b;
   public final C c;

   public Tuple3(A a, B b, C c) {
      this.a = a;
      this.b = b;
      this.c = c;
   }

   @Override
   public Stream<Object> stream() {
      return Stream.of(a, b, c);
   }

   public List<Tuple3<A, B, C>> explode() {
      return asList(
            this.a==null?null:new Tuple3<A, B, C>(a, null, null),
            this.b==null?null:new Tuple3<A, B, C>(null, b, null),
            this.c==null?null:new Tuple3<A, B, C>(null, null, c)
      ).stream().filter(Objects::nonNull).collect(Collectors.toList());
   }

   public Tuple3<A, B, C> merge(Tuple3<A, B, C> other) {
      return new Tuple3<A, B, C>(
            other.a==null?this.a:other.a,
            other.b==null?this.b:other.b,
            other.c==null?this.c:other.c
      );
   }

   public static <A, B, C> Tuple3<A, B, C> merge(List<Tuple3<A, B, C>> list) {
      return list.stream().reduce(new Tuple3<A, B, C>(null, null, null), Tuple3::merge);
   }

   public static <A, B, C> Tuple3<A, B, C> liftA(A a) {
      return new Tuple3<A, B, C>(a, null, null);
   }

   public static <A, B, C> Tuple3<A, B, C> liftB(B b) {
      return new Tuple3<A, B, C>(null, b, null);
   }

   public static <A, B, C> Tuple3<A, B, C> liftC(C c) {
      return new Tuple3<A, B, C>(null, null, c);
   }

}
