/**
 * Copyright (C) 2019 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.union;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static com.vanillasource.serdes.basic.Serdeses.*;
import java.io.UncheckedIOException;
import com.vanillasource.serdes.Serdes;
import com.vanillasource.serdes.basic.Serdeses.*;
import static com.vanillasource.serdes.union.Unions.*;
import com.vanillasource.serdes.tuple.Tuple4;
import static com.vanillasource.serdes.tuple.Tuples.*;

@Test
public final class UnionsTests {
   private static final Serdes<Tuple4<String, Integer, Boolean, Long>> SERDES = union(
         stringSerdes(), intSerdes(), booleanSerdes(), longSerdes());

   public void testDeserializingUnknownCodeReturnsEmpty() {
      Tuple4<String, Integer, Boolean, Long> result = SERDES.deserializeFromBytes(new byte[] { 9 });

      assertNull(result.a);
      assertNull(result.b);
      assertNull(result.c);
      assertNull(result.d);
   }

   public void testSerdesOfAWorks() {
      Tuple4<String, Integer, Boolean, Long> result = SERDES.deserializeFromBytes(
            SERDES.serializeToBytes(tuple("abc", null, null, null))
      );

      assertEquals(result.a, "abc");
   }

   public void testSerdesOfBWorks() {
      Tuple4<String, Integer, Boolean, Long> result = SERDES.deserializeFromBytes(
            SERDES.serializeToBytes(tuple(null, 42, null, null))
      );

      assertEquals((int) result.b, 42);
   }

   public void testSerdesOfCWorks() {
      Tuple4<String, Integer, Boolean, Long> result = SERDES.deserializeFromBytes(
            SERDES.serializeToBytes(tuple(null, null, true, null))
      );

      assertTrue(result.c);
   }

   public void testSerdesOfDWorks() {
      Tuple4<String, Integer, Boolean, Long> result = SERDES.deserializeFromBytes(
            SERDES.serializeToBytes(tuple(null, null, null, 9876543210L))
      );

      assertEquals((long) result.d, 9876543210L);
   }

   public void testUnionPicksFirstNonNullValue() {
      Tuple4<String, Integer, Boolean, Long> result = SERDES.deserializeFromBytes(
            SERDES.serializeToBytes(tuple("abc", 42, null, null))
      );

      assertEquals(result.a, "abc");
      assertNull(result.b);
   }

   public void testEmptyTupleIsSerializedToCodeZero() {
      byte[] result = SERDES.serializeToBytes(tuple(null, null, null, null));

      assertEquals(result, new byte[] { 0 });
   }
}

