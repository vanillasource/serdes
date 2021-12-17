/**
 * Copyright (C) 2021 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.field;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static com.vanillasource.serdes.basic.Serdeses.*;
import java.io.UncheckedIOException;
import com.vanillasource.serdes.Serdes;
import com.vanillasource.serdes.basic.Serdeses.*;
import static com.vanillasource.serdes.field.Fields.*;
import com.vanillasource.serdes.tuple.Tuple4;
import com.vanillasource.serdes.tuple.Tuple3;
import static com.vanillasource.serdes.tuple.Tuples.*;

@Test
public final class FieldsTests {
   private static final Serdes<Tuple4<String, Integer, Boolean, Long>> SERDES = fields(
         stringSerdes(), intSerdes(), booleanSerdes(), longSerdes());

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

   public void testSerdesOfAllValuesWork() {
      Tuple4<String, Integer, Boolean, Long> result = SERDES.deserializeFromBytes(
            SERDES.serializeToBytes(tuple("abc", 42, true, 9876543210L))
      );

      assertEquals(result.a, "abc");
      assertEquals((int) result.b, 42);
      assertTrue(result.c);
      assertEquals((long) result.d, 9876543210L);
   }

   public void testSerdesIsBackwardsCompatible() {
      Serdes<Tuple3<String, Integer, Boolean>> oldSerdes = fields(
         stringSerdes(), intSerdes(), booleanSerdes());

      Tuple4<String, Integer, Boolean, Long> result = SERDES.deserializeFromBytes(
            oldSerdes.serializeToBytes(tuple("abc", 42, true))
      );

      assertEquals(result.a, "abc");
      assertEquals((int) result.b, 42);
      assertTrue(result.c);
      assertNull(result.d);
   }

   public void testSerdesIsForwardsCompabile() {
      Serdes<Tuple3<String, Integer, Boolean>> oldSerdes = fields(
         stringSerdes(), intSerdes(), booleanSerdes());

      Tuple3<String, Integer, Boolean> result = oldSerdes.deserializeFromBytes(
            SERDES.serializeToBytes(tuple("abc", 42, true, 9876543210L))
      );

      assertEquals(result.a, "abc");
      assertEquals((int) result.b, 42);
      assertTrue(result.c);
   }

   public void testEmptyTupleIsSerializedToListOfLengthZero() {
      byte[] result = SERDES.serializeToBytes(tuple(null, null, null, null));

      assertEquals(result, new byte[] { 0, 0, 0, 0 });
   }
}

