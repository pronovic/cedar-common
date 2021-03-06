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

/**
 * Exception classes that are shared across Cedar Solutions projects.
 *
 * <p>
 * In general, Cedar Solutions code should throw exceptions in either the
 * CedarException or CedarRuntimeException hierarchy. That way, application
 * code can easily catch any exception generated by the application itself.
 * </p>
 *
 * <p>
 * Both CedarException and CedarRuntimeException are translatable to GWT
 * client-side code, and can also be used on the server side.  Both exceptions
 * also feature built-in support for localization.  Localization behavior is
 * delegated to the client application.  However, exceptions accept a
 * LocalizableMessage in addition to a String message, and this
 * LocalizableMessage contains enough information for clients to implement
 * localization (or at least to recognize a specific error condition and
 * generate their own message).
 * </p>
 *
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
package com.cedarsolutions.exception;
