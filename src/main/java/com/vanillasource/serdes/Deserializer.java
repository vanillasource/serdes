/**
 * Copyright (C) 2019 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes;

import java.io.InputStream;
import java.io.ByteArrayInputStream;

public interface Deserializer<A> {
   A deserialize(InputStream input);

   default A deserializeFromBytes(byte[] bytes) {
      return deserialize(new ByteArrayInputStream(bytes));
   }
}

