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

public final class Tuple4<A, B, C, D> implements Tuple {
   public final A a;
   public final B b;
   public final C c;
   public final D d;

   public Tuple4(A a, B b, C c, D d) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
   }

   @Override
   public Stream<Object> stream() {
      return Stream.of(a, b, c, d);
   }

   public List<Tuple4<A, B, C, D>> explode() {
      return asList(
            this.a==null?null:new Tuple4<A, B, C, D>(a, null, null, null),
            this.b==null?null:new Tuple4<A, B, C, D>(null, b, null, null),
            this.c==null?null:new Tuple4<A, B, C, D>(null, null, c, null),
            this.d==null?null:new Tuple4<A, B, C, D>(null, null, null, d)
      ).stream().filter(Objects::nonNull).collect(Collectors.toList());
   }

   public Tuple4<A, B, C, D> merge(Tuple4<A, B, C, D> other) {
      return new Tuple4<A, B, C, D>(
            other.a==null?this.a:other.a,
            other.b==null?this.b:other.b,
            other.c==null?this.c:other.c,
            other.d==null?this.d:other.d
      );
   }

   public static <A, B, C, D> Tuple4<A, B, C, D> merge(List<Tuple4<A, B, C, D>> list) {
      return list.stream().reduce(new Tuple4<A, B, C, D>(null, null, null, null), Tuple4::merge);
   }

   public static <A, B, C, D> Tuple4<A, B, C, D> liftA(A a) {
      return new Tuple4<A, B, C, D>(a, null, null, null);
   }

   public static <A, B, C, D> Tuple4<A, B, C, D> liftB(B b) {
      return new Tuple4<A, B, C, D>(null, b, null, null);
   }

   public static <A, B, C, D> Tuple4<A, B, C, D> liftC(C c) {
      return new Tuple4<A, B, C, D>(null, null, c, null);
   }

   public static <A, B, C, D> Tuple4<A, B, C, D> liftD(D d) {
      return new Tuple4<A, B, C, D>(null, null, null, d);
   }
}
