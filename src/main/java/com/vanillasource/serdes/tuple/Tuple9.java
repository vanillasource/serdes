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

public final class Tuple9<A, B, C, D, E, F, G, H, I> implements Tuple {
   public final A a;
   public final B b;
   public final C c;
   public final D d;
   public final E e;
   public final F f;
   public final G g;
   public final H h;
   public final I i;

   public Tuple9(A a, B b, C c, D d, E e, F f, G g, H h, I i) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
      this.e = e;
      this.f = f;
      this.g = g;
      this.h = h;
      this.i = i;
   }

   @Override
   public Stream<Object> stream() {
      return Stream.of(a, b, c, d, e, f, g, h, i);
   }

   public List<Tuple9<A, B, C, D, E, F, G, H, I>> explode() {
      return asList(
            this.a==null?null:new Tuple9<A, B, C, D, E, F, G, H, I>(a, null, null, null, null, null, null, null, null),
            this.b==null?null:new Tuple9<A, B, C, D, E, F, G, H, I>(null, b, null, null, null, null, null, null, null),
            this.c==null?null:new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, c, null, null, null, null, null, null),
            this.d==null?null:new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, d, null, null, null, null, null),
            this.e==null?null:new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, e, null, null, null, null),
            this.f==null?null:new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, null, f, null, null, null),
            this.g==null?null:new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, null, null, g, null, null),
            this.h==null?null:new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, null, null, null, h, null),
            this.i==null?null:new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, null, null, null, null, i)
      ).stream().filter(Objects::nonNull).collect(Collectors.toList());
   }

   public Tuple9<A, B, C, D, E, F, G, H, I> merge(Tuple9<A, B, C, D, E, F, G, H, I> other) {
      return new Tuple9<A, B, C, D, E, F, G, H, I>(
            other.a==null?this.a:other.a,
            other.b==null?this.b:other.b,
            other.c==null?this.c:other.c,
            other.d==null?this.d:other.d,
            other.e==null?this.e:other.e,
            other.f==null?this.f:other.f,
            other.g==null?this.g:other.g,
            other.h==null?this.h:other.h,
            other.i==null?this.i:other.i
      );
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> merge(List<Tuple9<A, B, C, D, E, F, G, H, I>> list) {
      return list.stream().reduce(new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, null, null, null, null, null), Tuple9::merge);
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> liftA(A a) {
      return new Tuple9<A, B, C, D, E, F, G, H, I>(a, null, null, null, null, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> liftB(B b) {
      return new Tuple9<A, B, C, D, E, F, G, H, I>(null, b, null, null, null, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> liftC(C c) {
      return new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, c, null, null, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> liftD(D d) {
      return new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, d, null, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> liftE(E e) {
      return new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, e, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> liftF(F f) {
      return new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, null, f, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> liftG(G g) {
      return new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, null, null, g, null, null);
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> liftH(H h) {
      return new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, null, null, null, h, null);
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> liftI(I i) {
      return new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, null, null, null, null, i);
   }
}
