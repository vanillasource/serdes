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
      byte[] data = stringSerdes().serializeToBytes("abcd");

      String value = stringSerdes().deserializeFromBytes(data);

      assertEquals(value, "abcd");
   }
}

