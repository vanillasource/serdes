/**
 * Copyright (C) 2019 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;

public final class LimitedOutputStream extends OutputStream {
   private final OutputStream delegate;
   private final long maxLength;
   private long writeLength = 0;

   public LimitedOutputStream(OutputStream delegate, long maxLength) {
      this.delegate = delegate;
      this.maxLength = maxLength;
   }

   @Override
   public void write(int value) throws IOException {
      if (writeLength >= maxLength) {
         throw new EOFException("Limit ("+maxLength+") reached.");
      }
      writeLength++;
      delegate.write(value);
   }
}
