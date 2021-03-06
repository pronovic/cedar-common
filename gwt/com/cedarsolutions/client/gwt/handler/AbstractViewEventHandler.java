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
package com.cedarsolutions.client.gwt.handler;

import com.cedarsolutions.client.gwt.event.UnifiedEvent;
import com.cedarsolutions.client.gwt.event.ViewEventHandler;

/**
 * Abstract view event handler with an associated parent.
 * @param <P> Parent presenter or view associated with handler
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public abstract class AbstractViewEventHandler<P> extends AbstractEventHandler<P> implements ViewEventHandler {

    /** Constructor. */
    protected AbstractViewEventHandler(P parent) {
        super(parent);
    }

    /** Handle a unified view event. */
    @Override
    public abstract void handleEvent(UnifiedEvent event);

}
