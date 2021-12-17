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

public final class Tuple5<A, B, C, D, E> implements Tuple {
   public final A a;
   public final B b;
   public final C c;
   public final D d;
   public final E e;

   public Tuple5(A a, B b, C c, D d, E e) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
      this.e = e;
   }

   @Override
   public Stream<Object> stream() {
      return Stream.of(a, b, c, d, e);
   }

   public List<Tuple5<A, B, C, D, E>> explode() {
      return asList(
            this.a==null?null:new Tuple5<A, B, C, D, E>(a, null, null, null, null),
            this.b==null?null:new Tuple5<A, B, C, D, E>(null, b, null, null, null),
            this.c==null?null:new Tuple5<A, B, C, D, E>(null, null, c, null, null),
            this.d==null?null:new Tuple5<A, B, C, D, E>(null, null, null, d, null),
            this.e==null?null:new Tuple5<A, B, C, D, E>(null, null, null, null, e)
      ).stream().filter(Objects::nonNull).collect(Collectors.toList());
   }

   public Tuple5<A, B, C, D, E> merge(Tuple5<A, B, C, D, E> other) {
      return new Tuple5<A, B, C, D, E>(
            other.a==null?this.a:other.a,
            other.b==null?this.b:other.b,
            other.c==null?this.c:other.c,
            other.d==null?this.d:other.d,
            other.e==null?this.e:other.e
      );
   }

   public static <A, B, C, D, E> Tuple5<A, B, C, D, E> merge(List<Tuple5<A, B, C, D, E>> list) {
      return list.stream().reduce(new Tuple5<A, B, C, D, E>(null, null, null, null, null), Tuple5::merge);
   }

   public static <A, B, C, D, E> Tuple5<A, B, C, D, E> liftA(A a) {
      return new Tuple5<A, B, C, D, E>(a, null, null, null, null);
   }

   public static <A, B, C, D, E> Tuple5<A, B, C, D, E> liftB(B b) {
      return new Tuple5<A, B, C, D, E>(null, b, null, null, null);
   }

   public static <A, B, C, D, E> Tuple5<A, B, C, D, E> liftC(C c) {
      return new Tuple5<A, B, C, D, E>(null, null, c, null, null);
   }

   public static <A, B, C, D, E> Tuple5<A, B, C, D, E> liftD(D d) {
      return new Tuple5<A, B, C, D, E>(null, null, null, d, null);
   }

   public static <A, B, C, D, E> Tuple5<A, B, C, D, E> liftE(E e) {
      return new Tuple5<A, B, C, D, E>(null, null, null, null, e);
   }
}
