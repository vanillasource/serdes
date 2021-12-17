/**
 * Copyright (C) 2021 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.tuple;

public final class Tuples {
   private Tuples() {
   }

   public static <A> Tuple1<A> tuple(A a) {
      return new Tuple1<>(a);
   }

   public static <A, B> Tuple2<A, B> tuple(A a, B b) {
      return new Tuple2<>(a, b);
   }

   public static <A, B, C> Tuple3<A, B, C> tuple(A a, B b, C c) {
      return new Tuple3<>(a, b, c);
   }

   public static <A, B, C, D> Tuple4<A, B, C, D> tuple(A a, B b, C c, D d) {
      return new Tuple4<>(a, b, c, d);
   }

   public static <A, B, C, D, E> Tuple5<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) {
      return new Tuple5<>(a, b, c, d, e);
   }

   public static <A, B, C, D, E, F> Tuple6<A, B, C, D, E, F> tuple(A a, B b, C c, D d, E e, F f) {
      return new Tuple6<>(a, b, c, d, e, f);
   }

   public static <A, B, C, D, E, F, G> Tuple7<A, B, C, D, E, F, G> tuple(A a, B b, C c, D d, E e, F f, G g) {
      return new Tuple7<>(a, b, c, d, e, f, g);
   }

   public static <A, B, C, D, E, F, G, H> Tuple8<A, B, C, D, E, F, G, H> tuple(A a, B b, C c, D d, E e, F f, G g, H h) {
      return new Tuple8<>(a, b, c, d, e, f, g, h);
   }

   public static <A, B, C, D, E, F, G, H, I> Tuple9<A, B, C, D, E, F, G, H, I> tuple(A a, B b, C c, D d, E e, F f, G g, H h, I i) {
      return new Tuple9<>(a, b, c, d, e, f, g, h, i);
   }
}

