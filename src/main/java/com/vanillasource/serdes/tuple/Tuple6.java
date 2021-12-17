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

public final class Tuple6<A, B, C, D, E, F> implements Tuple {
   public final A a;
   public final B b;
   public final C c;
   public final D d;
   public final E e;
   public final F f;

   public Tuple6(A a, B b, C c, D d, E e, F f) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
      this.e = e;
      this.f = f;
   }

   @Override
   public Stream<Object> stream() {
      return Stream.of(a, b, c, d, e, f);
   }

   public List<Tuple6<A, B, C, D, E, F>> explode() {
      return asList(
            this.a==null?null:new Tuple6<A, B, C, D, E, F>(a, null, null, null, null, null),
            this.b==null?null:new Tuple6<A, B, C, D, E, F>(null, b, null, null, null, null),
            this.c==null?null:new Tuple6<A, B, C, D, E, F>(null, null, c, null, null, null),
            this.d==null?null:new Tuple6<A, B, C, D, E, F>(null, null, null, d, null, null),
            this.e==null?null:new Tuple6<A, B, C, D, E, F>(null, null, null, null, e, null),
            this.f==null?null:new Tuple6<A, B, C, D, E, F>(null, null, null, null, null, f)
      ).stream().filter(Objects::nonNull).collect(Collectors.toList());
   }

   public Tuple6<A, B, C, D, E, F> merge(Tuple6<A, B, C, D, E, F> other) {
      return new Tuple6<A, B, C, D, E, F>(
            other.a==null?this.a:other.a,
            other.b==null?this.b:other.b,
            other.c==null?this.c:other.c,
            other.d==null?this.d:other.d,
            other.e==null?this.e:other.e,
            other.f==null?this.f:other.f
      );
   }

   public static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> merge(List<Tuple6<A, B, C, D, E, F>> list) {
      return list.stream().reduce(new Tuple6<A, B, C, D, E, F>(null, null, null, null, null, null), Tuple6::merge);
   }

   public static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> liftA(A a) {
      return new Tuple6<A, B, C, D, E, F>(a, null, null, null, null, null);
   }

   public static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> liftB(B b) {
      return new Tuple6<A, B, C, D, E, F>(null, b, null, null, null, null);
   }

   public static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> liftC(C c) {
      return new Tuple6<A, B, C, D, E, F>(null, null, c, null, null, null);
   }

   public static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> liftD(D d) {
      return new Tuple6<A, B, C, D, E, F>(null, null, null, d, null, null);
   }

   public static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> liftE(E e) {
      return new Tuple6<A, B, C, D, E, F>(null, null, null, null, e, null);
   }

   public static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> liftF(F f) {
      return new Tuple6<A, B, C, D, E, F>(null, null, null, null, null, f);
   }
}
