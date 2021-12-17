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

public final class Tuple7<A, B, C, D, E, F, G> implements Tuple {
   public final A a;
   public final B b;
   public final C c;
   public final D d;
   public final E e;
   public final F f;
   public final G g;

   public Tuple7(A a, B b, C c, D d, E e, F f, G g) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
      this.e = e;
      this.f = f;
      this.g = g;
   }

   @Override
   public Stream<Object> stream() {
      return Stream.of(a, b, c, d, e, f, g);
   }

   public List<Tuple7<A, B, C, D, E, F, G>> explode() {
      return asList(
            this.a==null?null:new Tuple7<A, B, C, D, E, F, G>(a, null, null, null, null, null, null),
            this.b==null?null:new Tuple7<A, B, C, D, E, F, G>(null, b, null, null, null, null, null),
            this.c==null?null:new Tuple7<A, B, C, D, E, F, G>(null, null, c, null, null, null, null),
            this.d==null?null:new Tuple7<A, B, C, D, E, F, G>(null, null, null, d, null, null, null),
            this.e==null?null:new Tuple7<A, B, C, D, E, F, G>(null, null, null, null, e, null, null),
            this.f==null?null:new Tuple7<A, B, C, D, E, F, G>(null, null, null, null, null, f, null),
            this.g==null?null:new Tuple7<A, B, C, D, E, F, G>(null, null, null, null, null, null, g)
      ).stream().filter(Objects::nonNull).collect(Collectors.toList());
   }

   public Tuple7<A, B, C, D, E, F, G> merge(Tuple7<A, B, C, D, E, F, G> other) {
      return new Tuple7<A, B, C, D, E, F, G>(
            other.a==null?this.a:other.a,
            other.b==null?this.b:other.b,
            other.c==null?this.c:other.c,
            other.d==null?this.d:other.d,
            other.e==null?this.e:other.e,
            other.f==null?this.f:other.f,
            other.g==null?this.g:other.g
      );
   }

   public static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> merge(List<Tuple7<A, B, C, D, E, F, G>> list) {
      return list.stream().reduce(new Tuple7<A, B, C, D, E, F, G>(null, null, null, null, null, null, null), Tuple7::merge);
   }

   public static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> liftA(A a) {
      return new Tuple7<A, B, C, D, E, F, G>(a, null, null, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> liftB(B b) {
      return new Tuple7<A, B, C, D, E, F, G>(null, b, null, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> liftC(C c) {
      return new Tuple7<A, B, C, D, E, F, G>(null, null, c, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> liftD(D d) {
      return new Tuple7<A, B, C, D, E, F, G>(null, null, null, d, null, null, null);
   }

   public static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> liftE(E e) {
      return new Tuple7<A, B, C, D, E, F, G>(null, null, null, null, e, null, null);
   }

   public static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> liftF(F f) {
      return new Tuple7<A, B, C, D, E, F, G>(null, null, null, null, null, f, null);
   }

   public static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> liftG(G g) {
      return new Tuple7<A, B, C, D, E, F, G>(null, null, null, null, null, null, g);
   }
}
