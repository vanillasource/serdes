/**
 * Copyright (C) 2019 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.field;

import com.vanillasource.serdes.Serdes;
import com.vanillasource.serdes.tuple.*;
import static com.vanillasource.serdes.union.Unions.*;
import static com.vanillasource.serdes.basic.Serdeses.*;

public final class Fields {
   private Fields() {
   }

   public static <A> Serdes<Tuple1<A>> fields(
         Serdes<A> aSerdes
   ) {
      return listSerdes(1, union(aSerdes))
         .map(Tuple1::explode, Tuple1::merge);
   }
   
   public static <A, B> Serdes<Tuple2<A, B>> fields(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes
   ) {
      return listSerdes(2, union(aSerdes, bSerdes))
         .map(Tuple2::explode, Tuple2::merge);
   }

   public static <A, B, C> Serdes<Tuple3<A, B, C>> fields(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes
   ) {
      return listSerdes(3, union(aSerdes, bSerdes, cSerdes))
         .map(Tuple3::explode, Tuple3::merge);
   }

   public static <A, B, C, D> Serdes<Tuple4<A, B, C, D>> fields(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes
   ) {
      return listSerdes(4, union(aSerdes, bSerdes, cSerdes, dSerdes))
         .map(Tuple4::explode, Tuple4::merge);
   }

   public static <A, B, C, D, E> Serdes<Tuple5<A, B, C, D, E>> fields(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes,
         Serdes<E> eSerdes
   ) {
      return listSerdes(5, union(aSerdes, bSerdes, cSerdes, dSerdes, eSerdes))
         .map(Tuple5::explode, Tuple5::merge);
   }

   public static <A, B, C, D, E, F> Serdes<Tuple6<A, B, C, D, E, F>> fields(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes,
         Serdes<E> eSerdes,
         Serdes<F> fSerdes
   ) {
      return listSerdes(6, union(aSerdes, bSerdes, cSerdes, dSerdes, eSerdes, fSerdes))
         .map(Tuple6::explode, Tuple6::merge);
   }

   public static <A, B, C, D, E, F, G> Serdes<Tuple7<A, B, C, D, E, F, G>> fields(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes,
         Serdes<E> eSerdes,
         Serdes<F> fSerdes,
         Serdes<G> gSerdes
   ) {
      return listSerdes(7, union(aSerdes, bSerdes, cSerdes, dSerdes, eSerdes, fSerdes, gSerdes))
         .map(Tuple7::explode, Tuple7::merge);
   }

   public static <A, B, C, D, E, F, G, H> Serdes<Tuple8<A, B, C, D, E, F, G, H>> fields(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes,
         Serdes<E> eSerdes,
         Serdes<F> fSerdes,
         Serdes<G> gSerdes,
         Serdes<H> hSerdes
   ) {
      return listSerdes(8, union(aSerdes, bSerdes, cSerdes, dSerdes, eSerdes, fSerdes, gSerdes, hSerdes))
         .map(Tuple8::explode, Tuple8::merge);
   }

   public static <A, B, C, D, E, F, G, H, I> Serdes<Tuple9<A, B, C, D, E, F, G, H, I>> fields(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes,
         Serdes<E> eSerdes,
         Serdes<F> fSerdes,
         Serdes<G> gSerdes,
         Serdes<H> hSerdes,
         Serdes<I> iSerdes
   ) {
      return listSerdes(9, union(aSerdes, bSerdes, cSerdes, dSerdes, eSerdes, fSerdes, gSerdes, hSerdes, iSerdes))
         .map(Tuple9::explode, Tuple9::merge);
   }
}
