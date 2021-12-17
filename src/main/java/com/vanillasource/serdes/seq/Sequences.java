/**
 * Copyright (C) 2021 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.seq;

import static com.vanillasource.serdes.tuple.Tuples.*;
import com.vanillasource.serdes.tuple.*;
import com.vanillasource.serdes.Serdes;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.function.Function;
import java.util.Optional;

public final class Sequences {
   private Sequences() {
   }

   public static <A, B> Serdes<Tuple2<A, B>> seq(Serdes<A> aSerdes, SerdesFactory<A, B> bSerdesFactory) {
      return new Serdes<Tuple2<A, B>>() {
         @Override
         public Optional<Integer> maxLength() {
            return aSerdes.maxLength().flatMap(aMaxLength -> 
               bSerdesFactory.maxLength().map(bMaxLength ->
                  aMaxLength + bMaxLength))
            ;
         }

         @Override
         public void serialize(Tuple2<A, B> object, OutputStream output) {
            aSerdes.serialize(object.a, output);
            bSerdesFactory.createFor(object.a).serialize(object.b, output);
         }

         @Override
         public Tuple2<A, B> deserialize(InputStream input) {
            A a = aSerdes.deserialize(input);
            B b = bSerdesFactory.createFor(a).deserialize(input);
            return tuple(a, b);
         }
      };
   }

   public static <A, B, C> Serdes<Tuple3<A, B, C>> seq(
           Serdes<A> aSerdes,
           SerdesFactory<A, B> bSerdesFactory,
           SerdesFactory<Tuple2<A, B>, C> cSerdesFactory
   ) {
      return new Serdes<Tuple3<A, B, C>>() {
         @Override
         public Optional<Integer> maxLength() {
            return aSerdes.maxLength().flatMap(aMaxLength -> 
                bSerdesFactory.maxLength().flatMap(bMaxLength ->
                cSerdesFactory.maxLength().map(cMaxLength ->
                  aMaxLength + bMaxLength + cMaxLength)))
            ;
         }

         @Override
         public void serialize(Tuple3<A, B, C> object, OutputStream output) {
            aSerdes.serialize(object.a, output);
            bSerdesFactory.createFor(object.a).serialize(object.b, output);
            cSerdesFactory.createFor(tuple(object.a, object.b)).serialize(object.c, output);
         }

         @Override
         public Tuple3<A, B, C> deserialize(InputStream input) {
            A a = aSerdes.deserialize(input);
            B b = bSerdesFactory.createFor(a).deserialize(input);
            C c = cSerdesFactory.createFor(tuple(a, b)).deserialize(input);
            return tuple(a, b, c);
         }
      };
   }

   public static <A, B, C, D> Serdes<Tuple4<A, B, C, D>> seq(
           Serdes<A> aSerdes,
           SerdesFactory<A, B> bSerdesFactory,
           SerdesFactory<Tuple2<A, B>, C> cSerdesFactory,
           SerdesFactory<Tuple3<A, B, C>, D> dSerdesFactory
   ) {
      return new Serdes<Tuple4<A, B, C, D>>() {
         @Override
         public Optional<Integer> maxLength() {
            return aSerdes.maxLength().flatMap(aMaxLength -> 
                bSerdesFactory.maxLength().flatMap(bMaxLength ->
                cSerdesFactory.maxLength().flatMap(cMaxLength ->
                dSerdesFactory.maxLength().map(dMaxLength ->
                  aMaxLength + bMaxLength + cMaxLength + dMaxLength))))
            ;
         }

         @Override
         public void serialize(Tuple4<A, B, C, D> object, OutputStream output) {
            aSerdes.serialize(object.a, output);
            bSerdesFactory.createFor(object.a).serialize(object.b, output);
            cSerdesFactory.createFor(tuple(object.a, object.b)).serialize(object.c, output);
            dSerdesFactory.createFor(tuple(object.a, object.b, object.c)).serialize(object.d, output);
         }

         @Override
         public Tuple4<A, B, C, D> deserialize(InputStream input) {
            A a = aSerdes.deserialize(input);
            B b = bSerdesFactory.createFor(a).deserialize(input);
            C c = cSerdesFactory.createFor(tuple(a, b)).deserialize(input);
            D d = dSerdesFactory.createFor(tuple(a, b, c)).deserialize(input);
            return tuple(a, b, c, d);
         }
      };
   }

   public static <A, B, C, D, E> Serdes<Tuple5<A, B, C, D, E>> seq(
           Serdes<A> aSerdes,
           SerdesFactory<A, B> bSerdesFactory,
           SerdesFactory<Tuple2<A, B>, C> cSerdesFactory,
           SerdesFactory<Tuple3<A, B, C>, D> dSerdesFactory,
           SerdesFactory<Tuple4<A, B, C, D>, E> eSerdesFactory
   ) {
      return new Serdes<Tuple5<A, B, C, D, E>>() {
         @Override
         public Optional<Integer> maxLength() {
            return aSerdes.maxLength().flatMap(aMaxLength -> 
                bSerdesFactory.maxLength().flatMap(bMaxLength ->
                cSerdesFactory.maxLength().flatMap(cMaxLength ->
                dSerdesFactory.maxLength().flatMap(dMaxLength ->
                eSerdesFactory.maxLength().map(eMaxLength ->
                  aMaxLength + bMaxLength + cMaxLength + dMaxLength + eMaxLength)))))
            ;
         }

         @Override
         public void serialize(Tuple5<A, B, C, D, E> object, OutputStream output) {
            aSerdes.serialize(object.a, output);
            bSerdesFactory.createFor(object.a).serialize(object.b, output);
            cSerdesFactory.createFor(tuple(object.a, object.b)).serialize(object.c, output);
            dSerdesFactory.createFor(tuple(object.a, object.b, object.c)).serialize(object.d, output);
            eSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d)).serialize(object.e, output);
         }

         @Override
         public Tuple5<A, B, C, D, E> deserialize(InputStream input) {
            A a = aSerdes.deserialize(input);
            B b = bSerdesFactory.createFor(a).deserialize(input);
            C c = cSerdesFactory.createFor(tuple(a, b)).deserialize(input);
            D d = dSerdesFactory.createFor(tuple(a, b, c)).deserialize(input);
            E e = eSerdesFactory.createFor(tuple(a, b, c, d)).deserialize(input);
            return tuple(a, b, c, d, e);
         }
      };
   }

   public static <A, B, C, D, E, F> Serdes<Tuple6<A, B, C, D, E, F>> seq(
           Serdes<A> aSerdes,
           SerdesFactory<A, B> bSerdesFactory,
           SerdesFactory<Tuple2<A, B>, C> cSerdesFactory,
           SerdesFactory<Tuple3<A, B, C>, D> dSerdesFactory,
           SerdesFactory<Tuple4<A, B, C, D>, E> eSerdesFactory,
           SerdesFactory<Tuple5<A, B, C, D, E>, F> fSerdesFactory
   ) {
      return new Serdes<Tuple6<A, B, C, D, E, F>>() {
         @Override
         public Optional<Integer> maxLength() {
            return aSerdes.maxLength().flatMap(aMaxLength -> 
                bSerdesFactory.maxLength().flatMap(bMaxLength ->
                cSerdesFactory.maxLength().flatMap(cMaxLength ->
                dSerdesFactory.maxLength().flatMap(dMaxLength ->
                eSerdesFactory.maxLength().flatMap(eMaxLength ->
                fSerdesFactory.maxLength().map(fMaxLength ->
                  aMaxLength + bMaxLength + cMaxLength + dMaxLength + eMaxLength + fMaxLength))))))
            ;
         }

         @Override
         public void serialize(Tuple6<A, B, C, D, E, F> object, OutputStream output) {
            aSerdes.serialize(object.a, output);
            bSerdesFactory.createFor(object.a).serialize(object.b, output);
            cSerdesFactory.createFor(tuple(object.a, object.b)).serialize(object.c, output);
            dSerdesFactory.createFor(tuple(object.a, object.b, object.c)).serialize(object.d, output);
            eSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d)).serialize(object.e, output);
            fSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d, object.e)).serialize(object.f, output);
         }

         @Override
         public Tuple6<A, B, C, D, E, F> deserialize(InputStream input) {
            A a = aSerdes.deserialize(input);
            B b = bSerdesFactory.createFor(a).deserialize(input);
            C c = cSerdesFactory.createFor(tuple(a, b)).deserialize(input);
            D d = dSerdesFactory.createFor(tuple(a, b, c)).deserialize(input);
            E e = eSerdesFactory.createFor(tuple(a, b, c, d)).deserialize(input);
            F f = fSerdesFactory.createFor(tuple(a, b, c, d, e)).deserialize(input);
            return tuple(a, b, c, d, e, f);
         }
      };
   }

   public static <A, B, C, D, E, F, G> Serdes<Tuple7<A, B, C, D, E, F, G>> seq(
           Serdes<A> aSerdes,
           SerdesFactory<A, B> bSerdesFactory,
           SerdesFactory<Tuple2<A, B>, C> cSerdesFactory,
           SerdesFactory<Tuple3<A, B, C>, D> dSerdesFactory,
           SerdesFactory<Tuple4<A, B, C, D>, E> eSerdesFactory,
           SerdesFactory<Tuple5<A, B, C, D, E>, F> fSerdesFactory,
           SerdesFactory<Tuple6<A, B, C, D, E, F>, G> gSerdesFactory
   ) {
      return new Serdes<Tuple7<A, B, C, D, E, F, G>>() {
         @Override
         public Optional<Integer> maxLength() {
            return aSerdes.maxLength().flatMap(aMaxLength -> 
                bSerdesFactory.maxLength().flatMap(bMaxLength ->
                cSerdesFactory.maxLength().flatMap(cMaxLength ->
                dSerdesFactory.maxLength().flatMap(dMaxLength ->
                eSerdesFactory.maxLength().flatMap(eMaxLength ->
                fSerdesFactory.maxLength().flatMap(fMaxLength ->
                gSerdesFactory.maxLength().map(gMaxLength ->
                  aMaxLength + bMaxLength + cMaxLength + dMaxLength + eMaxLength + fMaxLength + gMaxLength)))))))
            ;
         }

         @Override
         public void serialize(Tuple7<A, B, C, D, E, F, G> object, OutputStream output) {
            aSerdes.serialize(object.a, output);
            bSerdesFactory.createFor(object.a).serialize(object.b, output);
            cSerdesFactory.createFor(tuple(object.a, object.b)).serialize(object.c, output);
            dSerdesFactory.createFor(tuple(object.a, object.b, object.c)).serialize(object.d, output);
            eSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d)).serialize(object.e, output);
            fSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d, object.e)).serialize(object.f, output);
            gSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d, object.e, object.f)).serialize(object.g, output);
         }

         @Override
         public Tuple7<A, B, C, D, E, F, G> deserialize(InputStream input) {
            A a = aSerdes.deserialize(input);
            B b = bSerdesFactory.createFor(a).deserialize(input);
            C c = cSerdesFactory.createFor(tuple(a, b)).deserialize(input);
            D d = dSerdesFactory.createFor(tuple(a, b, c)).deserialize(input);
            E e = eSerdesFactory.createFor(tuple(a, b, c, d)).deserialize(input);
            F f = fSerdesFactory.createFor(tuple(a, b, c, d, e)).deserialize(input);
            G g = gSerdesFactory.createFor(tuple(a, b, c, d, e, f)).deserialize(input);
            return tuple(a, b, c, d, e, f, g);
         }
      };
   }

   public static <A, B, C, D, E, F, G, H> Serdes<Tuple8<A, B, C, D, E, F, G, H>> seq(
           Serdes<A> aSerdes,
           SerdesFactory<A, B> bSerdesFactory,
           SerdesFactory<Tuple2<A, B>, C> cSerdesFactory,
           SerdesFactory<Tuple3<A, B, C>, D> dSerdesFactory,
           SerdesFactory<Tuple4<A, B, C, D>, E> eSerdesFactory,
           SerdesFactory<Tuple5<A, B, C, D, E>, F> fSerdesFactory,
           SerdesFactory<Tuple6<A, B, C, D, E, F>, G> gSerdesFactory,
           SerdesFactory<Tuple7<A, B, C, D, E, F, G>, H> hSerdesFactory
   ) {
      return new Serdes<Tuple8<A, B, C, D, E, F, G, H>>() {
         @Override
         public Optional<Integer> maxLength() {
            return aSerdes.maxLength().flatMap(aMaxLength -> 
                bSerdesFactory.maxLength().flatMap(bMaxLength ->
                cSerdesFactory.maxLength().flatMap(cMaxLength ->
                dSerdesFactory.maxLength().flatMap(dMaxLength ->
                eSerdesFactory.maxLength().flatMap(eMaxLength ->
                fSerdesFactory.maxLength().flatMap(fMaxLength ->
                gSerdesFactory.maxLength().flatMap(gMaxLength ->
                hSerdesFactory.maxLength().map(hMaxLength ->
                  aMaxLength + bMaxLength + cMaxLength + dMaxLength + eMaxLength + fMaxLength + gMaxLength + hMaxLength))))))))
            ;
         }

         @Override
         public void serialize(Tuple8<A, B, C, D, E, F, G, H> object, OutputStream output) {
            aSerdes.serialize(object.a, output);
            bSerdesFactory.createFor(object.a).serialize(object.b, output);
            cSerdesFactory.createFor(tuple(object.a, object.b)).serialize(object.c, output);
            dSerdesFactory.createFor(tuple(object.a, object.b, object.c)).serialize(object.d, output);
            eSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d)).serialize(object.e, output);
            fSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d, object.e)).serialize(object.f, output);
            gSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d, object.e, object.f)).serialize(object.g, output);
            hSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d, object.e, object.f, object.g)).serialize(object.h, output);
         }

         @Override
         public Tuple8<A, B, C, D, E, F, G, H> deserialize(InputStream input) {
            A a = aSerdes.deserialize(input);
            B b = bSerdesFactory.createFor(a).deserialize(input);
            C c = cSerdesFactory.createFor(tuple(a, b)).deserialize(input);
            D d = dSerdesFactory.createFor(tuple(a, b, c)).deserialize(input);
            E e = eSerdesFactory.createFor(tuple(a, b, c, d)).deserialize(input);
            F f = fSerdesFactory.createFor(tuple(a, b, c, d, e)).deserialize(input);
            G g = gSerdesFactory.createFor(tuple(a, b, c, d, e, f)).deserialize(input);
            H h = hSerdesFactory.createFor(tuple(a, b, c, d, e, f, g)).deserialize(input);
            return tuple(a, b, c, d, e, f, g, h);
         }
      };
   }

   public static <A, B, C, D, E, F, G, H, I> Serdes<Tuple9<A, B, C, D, E, F, G, H, I>> seq(
           Serdes<A> aSerdes,
           SerdesFactory<A, B> bSerdesFactory,
           SerdesFactory<Tuple2<A, B>, C> cSerdesFactory,
           SerdesFactory<Tuple3<A, B, C>, D> dSerdesFactory,
           SerdesFactory<Tuple4<A, B, C, D>, E> eSerdesFactory,
           SerdesFactory<Tuple5<A, B, C, D, E>, F> fSerdesFactory,
           SerdesFactory<Tuple6<A, B, C, D, E, F>, G> gSerdesFactory,
           SerdesFactory<Tuple7<A, B, C, D, E, F, G>, H> hSerdesFactory,
           SerdesFactory<Tuple8<A, B, C, D, E, F, G, H>, I> iSerdesFactory
   ) {
      return new Serdes<Tuple9<A, B, C, D, E, F, G, H, I>>() {
         @Override
         public Optional<Integer> maxLength() {
            return aSerdes.maxLength().flatMap(aMaxLength -> 
                bSerdesFactory.maxLength().flatMap(bMaxLength ->
                cSerdesFactory.maxLength().flatMap(cMaxLength ->
                dSerdesFactory.maxLength().flatMap(dMaxLength ->
                eSerdesFactory.maxLength().flatMap(eMaxLength ->
                fSerdesFactory.maxLength().flatMap(fMaxLength ->
                gSerdesFactory.maxLength().flatMap(gMaxLength ->
                hSerdesFactory.maxLength().flatMap(hMaxLength ->
                iSerdesFactory.maxLength().map(iMaxLength ->
                  aMaxLength + bMaxLength + cMaxLength + dMaxLength + eMaxLength + fMaxLength + gMaxLength + hMaxLength + iMaxLength)))))))))
            ;
         }

         @Override
         public void serialize(Tuple9<A, B, C, D, E, F, G, H, I> object, OutputStream output) {
            aSerdes.serialize(object.a, output);
            bSerdesFactory.createFor(object.a).serialize(object.b, output);
            cSerdesFactory.createFor(tuple(object.a, object.b)).serialize(object.c, output);
            dSerdesFactory.createFor(tuple(object.a, object.b, object.c)).serialize(object.d, output);
            eSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d)).serialize(object.e, output);
            fSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d, object.e)).serialize(object.f, output);
            gSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d, object.e, object.f)).serialize(object.g, output);
            hSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d, object.e, object.f, object.g)).serialize(object.h, output);
            iSerdesFactory.createFor(tuple(object.a, object.b, object.c, object.d, object.e, object.f, object.g, object.h)).serialize(object.i, output);
         }

         @Override
         public Tuple9<A, B, C, D, E, F, G, H, I> deserialize(InputStream input) {
            A a = aSerdes.deserialize(input);
            B b = bSerdesFactory.createFor(a).deserialize(input);
            C c = cSerdesFactory.createFor(tuple(a, b)).deserialize(input);
            D d = dSerdesFactory.createFor(tuple(a, b, c)).deserialize(input);
            E e = eSerdesFactory.createFor(tuple(a, b, c, d)).deserialize(input);
            F f = fSerdesFactory.createFor(tuple(a, b, c, d, e)).deserialize(input);
            G g = gSerdesFactory.createFor(tuple(a, b, c, d, e, f)).deserialize(input);
            H h = hSerdesFactory.createFor(tuple(a, b, c, d, e, f, g)).deserialize(input);
            I i = iSerdesFactory.createFor(tuple(a, b, c, d, e, f, g, h)).deserialize(input);
            return tuple(a, b, c, d, e, f, g, h, i);
         }
      };
   }
}

