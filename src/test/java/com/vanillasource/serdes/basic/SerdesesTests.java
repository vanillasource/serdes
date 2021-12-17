/**
 * Copyright (C) 2019 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.basic;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static com.vanillasource.serdes.basic.Serdeses.*;
import java.io.UncheckedIOException;

@Test
public final class SerdesesTests {
   public void testStringCanSerializeAndDeserialize() {
      byte[] data = stringSerdes(10).serializeToBytes("abcd");

      String value = stringSerdes(10).deserializeFromBytes(data);

      assertEquals(value, "abcd");
   }

   public void testStringCanContainMaxLengthLatinCharacters() {
      byte[] data = stringSerdes(5).serializeToBytes("abcde");

      String value = stringSerdes(5).deserializeFromBytes(data);

      assertEquals(value, "abcde");
   }

   @Test(expectedExceptions = UncheckedIOException.class)
   public void testStringCanNotContainMaxLengthUTFCharacters() {
      byte[] data = stringSerdes(5).serializeToBytes("αbcde");
   }
}

