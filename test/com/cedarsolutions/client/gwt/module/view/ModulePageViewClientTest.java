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
package com.cedarsolutions.client.gwt.module.view;

import com.cedarsolutions.client.gwt.junit.ClientTestCase;

/**
 * Client-side unit tests for ModulePageView.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public class ModulePageViewClientTest extends ClientTestCase {

    /** Test the getViewWidget() method. */
    public void testGetViewWidget() {
        ConcreteModulePageView view = new ConcreteModulePageView();
        assertEquals(view, view.getViewWidget());
    }

    /** Concrete class that we can test with. */
    private static class ConcreteModulePageView extends ModulePageView {
    }

}
