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
package com.cedarsolutions.tools.label;

import com.google.gwt.i18n.client.ConstantsWithLookup;

/**
 * ConstantsWithLookup interface that we can use for testing.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public interface VariableTestResources extends ConstantsWithLookup {
    @DefaultBooleanValue(true)
    boolean booleanValue();

    @DefaultIntValue(15)
    int intValue();

    @DefaultStringValue("string")
    String stringValue();
}
