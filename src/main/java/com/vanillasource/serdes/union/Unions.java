/**
 * Copyright (C) 2021 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.union;

import static com.vanillasource.serdes.tuple.Tuples.*;
import com.vanillasource.serdes.tuple.*;
import com.vanillasource.serdes.Serdes;
import static java.lang.Math.*;
import static com.vanillasource.serdes.basic.Serdeses.*;
import static com.vanillasource.serdes.seq.Sequences.*;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.function.Function;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.List;
import static java.util.Arrays.asList;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@SuppressWarnings("unchecked")
public final class Unions {
   private Unions() {
   }

   public static <A> Serdes<Tuple1<A>> union(
         Serdes<A> aSerdes) {
      return (Serdes<Tuple1<A>>)(Object) union(
            asList(aSerdes),
            asList(Tuple1::liftA),
            new Tuple1<A>(null));
   }

   public static <A, B> Serdes<Tuple2<A, B>> union(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes) {
      return (Serdes<Tuple2<A, B>>)(Object) union(
            asList(aSerdes, bSerdes),
            asList(Tuple2::liftA, Tuple2::liftB),
            new Tuple2<A, B>(null, null));
   }

   public static <A, B, C> Serdes<Tuple3<A, B, C>> union(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes
   ) {
      return (Serdes<Tuple3<A, B, C>>)(Object) union(
            asList(aSerdes, bSerdes, cSerdes),
            asList(Tuple3::liftA, Tuple3::liftB, Tuple3::liftC),
            new Tuple3<A, B, C>(null, null, null));
   }

   public static <A, B, C, D> Serdes<Tuple4<A, B, C, D>> union(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes
   ) {
      return (Serdes<Tuple4<A, B, C, D>>)(Object) union(
            asList(aSerdes, bSerdes, cSerdes, dSerdes),
            asList(Tuple4::liftA, Tuple4::liftB, Tuple4::liftC, Tuple4::liftD),
            new Tuple4<A, B, C, D>(null, null, null, null));
   }

   public static <A, B, C, D, E> Serdes<Tuple5<A, B, C, D, E>> union(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes,
         Serdes<E> eSerdes
   ) {
      return (Serdes<Tuple5<A, B, C, D, E>>)(Object) union(
            asList(aSerdes, bSerdes, cSerdes, dSerdes, eSerdes),
            asList(Tuple5::liftA, Tuple5::liftB, Tuple5::liftC, Tuple5::liftD, Tuple5::liftE),
            new Tuple5<A, B, C, D, E>(null, null, null, null, null));
   }

   public static <A, B, C, D, E, F> Serdes<Tuple6<A, B, C, D, E, F>> union(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes,
         Serdes<E> eSerdes,
         Serdes<F> fSerdes
   ) {
      return (Serdes<Tuple6<A, B, C, D, E, F>>)(Object) union(
            asList(aSerdes, bSerdes, cSerdes, dSerdes, eSerdes, fSerdes),
            asList(Tuple6::liftA, Tuple6::liftB, Tuple6::liftC, Tuple6::liftD, Tuple6::liftE, Tuple6::liftF),
            new Tuple6<A, B, C, D, E, F>(null, null, null, null, null, null));
   }

   public static <A, B, C, D, E, F, G> Serdes<Tuple7<A, B, C, D, E, F, G>> union(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes,
         Serdes<E> eSerdes,
         Serdes<F> fSerdes,
         Serdes<G> gSerdes
   ) {
      return (Serdes<Tuple7<A, B, C, D, E, F, G>>)(Object) union(
            asList(aSerdes, bSerdes, cSerdes, dSerdes, eSerdes, fSerdes, gSerdes),
            asList(Tuple7::liftA, Tuple7::liftB, Tuple7::liftC, Tuple7::liftD, Tuple7::liftE, Tuple7::liftF, Tuple7::liftG),
            new Tuple7<A, B, C, D, E, F, G>(null, null, null, null, null, null, null));
   }

   public static <A, B, C, D, E, F, G, H> Serdes<Tuple8<A, B, C, D, E, F, G, H>> union(
         Serdes<A> aSerdes,
         Serdes<B> bSerdes,
         Serdes<C> cSerdes,
         Serdes<D> dSerdes,
         Serdes<E> eSerdes,
         Serdes<F> fSerdes,
         Serdes<G> gSerdes,
         Serdes<H> hSerdes
   ) {
      return (Serdes<Tuple8<A, B, C, D, E, F, G, H>>)(Object) union(
            asList(aSerdes, bSerdes, cSerdes, dSerdes, eSerdes, fSerdes, gSerdes, hSerdes),
            asList(Tuple8::liftA, Tuple8::liftB, Tuple8::liftC, Tuple8::liftD, Tuple8::liftE, Tuple8::liftF, Tuple8::liftG, Tuple8::liftH),
            new Tuple8<A, B, C, D, E, F, G, H>(null, null, null, null, null, null, null, null));
   }

   public static <A, B, C, D, E, F, G, H, I> Serdes<Tuple9<A, B, C, D, E, F, G, H, I>> union(
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
      return (Serdes<Tuple9<A, B, C, D, E, F, G, H, I>>)(Object) union(
            asList(aSerdes, bSerdes, cSerdes, dSerdes, eSerdes, fSerdes, gSerdes, hSerdes, iSerdes),
            asList(Tuple9::liftA, Tuple9::liftB, Tuple9::liftC, Tuple9::liftD, Tuple9::liftE, Tuple9::liftF, Tuple9::liftG, Tuple9::liftH, Tuple9::liftI),
            new Tuple9<A, B, C, D, E, F, G, H, I>(null, null, null, null, null, null, null, null, null));
   }

   private static Serdes<Tuple> union(List<Serdes<? extends Object>> rawSerdeses, List<Function<Object, Tuple>> lifts, Tuple empty) {
      List<Serdes<Object>> serdeses = (List<Serdes<Object>>)(Object) rawSerdeses;
      if (serdeses.size() != lifts.size()) {
         throw new IllegalArgumentException("mismatched parameters");
      }
      return seq(
            codeLengthSerdes(),
            codeLength -> fixedByteArraySerdes(codeLength.b))
         .map(o -> {
            Object[] items = o.stream().toArray();
            byte code = IntStream.range(0, o.size()).filter(i -> items[i] != null).mapToObj(i -> i).findFirst()
               .map(i -> i+1)
               .orElse(0).byteValue();
            if (code == 0) {
               return tuple(tuple(code, 0), new byte[] {});
            } else {
               byte[] data = ((Serdes<Object>) rawSerdeses.get(code-1)).serializeToBytes(items[code-1]);
               return tuple(tuple(code, data.length), data);
            }
         }, t -> {
            if (t.a.a == 0 || t.a.a-1 >= rawSerdeses.size()) {
               return empty;
            } else {
               Object o = rawSerdeses.get(t.a.a-1).deserializeFromBytes(t.b);
               return lifts.get(t.a.a-1).apply(o);
            }
         });
   }

   static Serdes<Tuple2<Byte, Integer>> codeLengthSerdes() {
      return seq(
            byteSerdes(), // Code high 3 bits (0,1,2,3,4=0,1,2,4,8 bytes, 5,6,7=length encoded in following 1,2,4 bytes) 
            code -> {
               int lengthCode = (code & 0xFF) >>> 5;
               switch (lengthCode) {
                  case 5:
                     return byteSerdes().map(o -> o.byteValue(), t -> (t & 0xFF));
                  case 6:
                     return shortSerdes().map(o -> o.shortValue(), t -> (t & 0xFFFF));
                  case 7:
                     return intSerdes();
                  default:
                     if (lengthCode == 0) {
                        return empty().map(o -> 0, t -> 0);
                     } else {
                        return empty().map(o -> 0, t-> (1<<(lengthCode-1)));
                     }
               }
            }
      ).map(o -> {
         if (o.a > 31) {
            throw new IllegalArgumentException("code can not be more than 31");
         }
         if (o.b == 0) {
            return (Tuple2<Byte, Integer>) tuple(o.a, 0);
         } else if (o.b == 1) {
            return tuple((byte) (o.a | (1 << 5)), 1);
         } else if (o.b == 2) {
            return tuple((byte) (o.a | (2 << 5)), 2);
         } else if (o.b == 4) {
            return tuple((byte) (o.a | (3 << 5)), 3);
         } else if (o.b == 8) {
            return tuple((byte) (o.a | (4 << 5)), 4);
         } else if (o.b < 256) {
            return tuple((byte) (o.a | (5 << 5)), o.b);
         } else if (o.b < 65536) {
            return tuple((byte) (o.a | (6 << 5)), o.b);
         } else {
            return tuple((byte) (o.a | (7 << 5)), o.b);
         }
      },    t -> {
         byte code = (byte) (t.a & 31);
         int lengthEncoding = t.a >>> 5;
         if (lengthEncoding == 0) {
            return tuple(code, 0);
         } else if (lengthEncoding < 5) {
            return tuple(code, 1<<(lengthEncoding-1));
         } else {
            return tuple(code, t.b);
         }
      });
   }
}

