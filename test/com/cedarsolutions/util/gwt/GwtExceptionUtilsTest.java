/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 *              C E D A R
 *          S O L U T I O N S       "Software done right."
 *           S O F T W A R E
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * Copyright (c) 2013-2014 Kenneth J. Pronovici.
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
package com.cedarsolutions.util.gwt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.cedarsolutions.exception.CedarRuntimeException;
import com.cedarsolutions.exception.context.ExceptionContext;

/**
 * Unit tests for GwtExceptionUtils.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public class GwtExceptionUtilsTest {

    /** Test generateStackTrace(). */
    // Just spot-check, since it's hard to confirm the exact output
    @Test public void testGenerateStackTrace() {
        assertEquals(null, GwtExceptionUtils.generateStackTrace(null));

        CedarRuntimeException e = new CedarRuntimeException();
        assertNotNull(GwtExceptionUtils.generateStackTrace(e));

        e.setContext(new ExceptionContext());
        assertNotNull(GwtExceptionUtils.generateStackTrace(e));

        e.getContext().setStackTrace("X");
        assertEquals("X", GwtExceptionUtils.generateStackTrace(e));

        assertNotNull(GwtExceptionUtils.generateStackTrace(new Exception("whatever")));
        assertNotNull(GwtExceptionUtils.generateStackTrace(new Exception("whatever", new Exception("cause"))));
    }

}
