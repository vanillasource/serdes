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

public final class Tuple8<A, B, C, D, E, F, G, H> implements Tuple {
   public final A a;
   public final B b;
   public final C c;
   public final D d;
   public final E e;
   public final F f;
   public final G g;
   public final H h;

   public Tuple8(A a, B b, C c, D d, E e, F f, G g, H h) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
      this.e = e;
      this.f = f;
      this.g = g;
      this.h = h;
   }

   @Override
   public Stream<Object> stream() {
      return Stream.of(a, b, c, d, e, f, g, h);
   }

   public List<Tuple8<A, B, C, D, E, F, G, H>> explode() {
      return asList(
            this.a==null?null:new Tuple8<A, B, C, D, E, F, G, H>(a, null, null, null, null, null, null, null),
            this.b==null?null:new Tuple8<A, B, C, D, E, F, G, H>(null, b, null, null, null, null, null, null),
            this.c==null?null:new Tuple8<A, B, C, D, E, F, G, H>(null, null, c, null, null, null, null, null),
            this.d==null?null:new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, d, null, null, null, null),
            this.e==null?null:new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, null, e, null, null, null),
            this.f==null?null:new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, null, null, f, null, null),
            this.g==null?null:new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, null, null, null, g, null),
            this.h==null?null:new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, null, null, null, null, h)
      ).stream().filter(Objects::nonNull).collect(Collectors.toList());
   }

   public Tuple8<A, B, C, D, E, F, G, H> merge(Tuple8<A, B, C, D, E, F, G, H> other) {
      return new Tuple8<A, B, C, D, E, F, G, H>(
            other.a==null?this.a:other.a,
            other.b==null?this.b:other.b,
            other.c==null?this.c:other.c,
            other.d==null?this.d:other.d,
            other.e==null?this.e:other.e,
            other.f==null?this.f:other.f,
            other.g==null?this.g:other.g,
            other.h==null?this.h:other.h
      );
   }

   public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> merge(List<Tuple8<A, B, C, D, E, F, G, H>> list) {
      return list.stream().reduce(new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, null, null, null, null, null), Tuple8::merge);
   }

   public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> liftA(A a) {
      return new Tuple8<A, B, C, D, E, F, G, H>(a, null, null, null, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> liftB(B b) {
      return new Tuple8<A, B, C, D, E, F, G, H>(null, b, null, null, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> liftC(C c) {
      return new Tuple8<A, B, C, D, E, F, G, H>(null, null, c, null, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> liftD(D d) {
      return new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, d, null, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> liftE(E e) {
      return new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, null, e, null, null, null);
   }

   public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> liftF(F f) {
      return new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, null, null, f, null, null);
   }

   public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> liftG(G g) {
      return new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, null, null, null, g, null);
   }

   public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> liftH(H h) {
      return new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, null, null, null, null, h);
   }
}
