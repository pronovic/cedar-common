/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 *              C E D A R
 *          S O L U T I O N S       "Software done right."
 *           S O F T W A R E
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * Copyright (c) 2013 Kenneth J. Pronovici.
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the Apache License, Version 2.0.
 * See LICENSE for more information about the licensing terms.
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * Author   : Kenneth J. Pronovici <pronovic@ieee.org>
 * Language : Java 6
 * Project  : Common Java Functionality
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.cedarsolutions.dao.gae.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests for NoOpFilterPredicate.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public class NoOpFilterPredicateTest {

    /** Test evaluate. */
    @Test public void testEvaluate() {
        NoOpFilterPredicate<String> predicate = new NoOpFilterPredicate<String>();
        assertTrue(predicate.evaluate(null));
        assertTrue(predicate.evaluate(""));
        assertTrue(predicate.evaluate("WHATEVER"));
    }

}
