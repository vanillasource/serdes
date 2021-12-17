/**
 * Copyright (C) 2019 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

public interface Serializer<A> {
   Optional<Integer> maxLength();

   void serialize(A object, OutputStream output);

   default byte[] serializeToBytes(A object) {
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      serialize(object, output);
      return output.toByteArray();
   }
}

