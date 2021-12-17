/**
 * Copyright (C) 2019 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.io;

import java.io.IOException;
import java.io.InputStream;

public final class LimitedInputStream extends InputStream {
   private final InputStream input;
   private final long maxLength;
   private long readLength = 0;

   public LimitedInputStream(InputStream input, long maxLength) {
      this.input = input;
      this.maxLength = maxLength;
   }

   @Override
   public int read() throws IOException {
      if (readLength < maxLength) {
         readLength++;
         return input.read() & 0xFF;
      }
      return -1;
   }
}
