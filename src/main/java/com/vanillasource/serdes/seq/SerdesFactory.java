/**
 * Copyright (C) 2021 Robert Braeutigam.
 *
 * All rights reserved.
 */

package com.vanillasource.serdes.seq;

import com.vanillasource.serdes.Serdes;
import java.util.function.Function;

public interface SerdesFactory<P, T> extends Function<P, Serdes<T>> {
}
