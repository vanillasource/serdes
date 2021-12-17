/**
 * Copyright (C) 2019 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.union;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.vanillasource.serdes.tuple.Tuple2;
import static com.vanillasource.serdes.tuple.Tuples.*;
import org.testng.annotations.DataProvider;

@Test
public final class CodeLengthTests {
   @Test(dataProvider = "plainEncodedPairs")
   public void testPlainIsEncodedCorrectly(Tuple2<Byte, Integer> plain, byte[] expectedEncoding) {
      byte[] result = Unions.codeLengthSerdes().serializeToBytes(plain);

      assertEquals(result, expectedEncoding);
   }

   @Test(dataProvider = "plainEncodedPairs")
   public void testEncodedIsDecodedCorrectly(Tuple2<Byte, Integer> expectedPlain, byte[] encoding) {
      Tuple2<Byte, Integer> result = Unions.codeLengthSerdes().deserializeFromBytes(encoding);

      assertEquals(result.a, expectedPlain.a, "code");
      assertEquals(result.b , expectedPlain.b, "length");
   }

   @DataProvider(name ="plainEncodedPairs")
   public Object[][] plainEncodedPairs() {
      return new Object[][] {
         { tuple((byte)0, 0), new byte[] { 0 } },
         { tuple((byte)1, 0), new byte[] { 1 } },
         { tuple((byte)31, 0), new byte[] { 31 } },
         { tuple((byte)10, 1), new byte[] { (1 << 5) | 10 } },
         { tuple((byte)31, 1), new byte[] { (1 << 5) | 31 } },
         { tuple((byte)10, 2), new byte[] { (2 << 5) | 10 } },
         { tuple((byte)10, 4), new byte[] { (3 << 5) | 10 } },
         { tuple((byte)10, 8), new byte[] { (byte)((4 << 5)&0xFF) | 10 } },
         { tuple((byte)10, 3), new byte[] { (byte)((5 << 5)&0xFF) | 10, 3 } },
         { tuple((byte)10, 255), new byte[] { (byte)((5 << 5)&0xFF) | 10, (byte)255 } },
         { tuple((byte)10, 256), new byte[] { (byte)((6 << 5)&0xFF) | 10, 1, 0 } },
         { tuple((byte)10, 65535), new byte[] { (byte)((6 << 5)&0xFF) | 10, (byte)255, (byte)255 } },
         { tuple((byte)31, 65535), new byte[] { (byte)((6 << 5)&0xFF) | 31, (byte)255, (byte)255 } },
         { tuple((byte)10, 65536), new byte[] { (byte)((7 << 5)&0xFF) | 10, 0, 1, 0, 0} },
      };
   }
}

