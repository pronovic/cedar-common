/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 *              C E D A R
 *          S O L U T I O N S       "Software done right."
 *           S O F T W A R E
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * Copyright (c) 2013-2015 Kenneth J. Pronovici.
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
package com.cedarsolutions.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cedarsolutions.exception.context.ExceptionContext;
import com.cedarsolutions.shared.domain.LocalizableMessage;
import com.cedarsolutions.shared.domain.ValidationErrors;

/**
 * Unit tests for system-wide exceptions.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public class ExceptionTest {

    /** Test CedarException. */
    @Test public void testCedarException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new CedarException();
        } catch (CedarException e) {
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarException((String) null);
        } catch (CedarException e) {
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarException((LocalizableMessage) null);
        } catch (CedarException e) {
            assertNull(message.getText(), e.getMessage());
            assertNull(e.getLocalizableMessage());
            assertNull(e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarException("error");
        } catch (CedarException e) {
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarException(message);
        } catch (CedarException e) {
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarException("error", cause);
        } catch (CedarException e) {
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarException(message, cause);
        } catch (CedarException e) {
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        ExceptionContext context = new ExceptionContext();
        CedarException e = new CedarException(message, cause);
        e.setContext(context);
        assertTrue(e.hasContext());
        assertSame(context, e.getContext());
    }

    /** Test CedarRuntimeException. */
    @Test public void testCedarRuntimeException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new CedarRuntimeException();
        } catch (CedarRuntimeException e) {
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarRuntimeException((String) null);
        } catch (CedarRuntimeException e) {
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarRuntimeException((LocalizableMessage) null);
        } catch (CedarRuntimeException e) {
            assertNull(message.getText(), e.getMessage());
            assertNull(e.getLocalizableMessage());
            assertNull(e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarRuntimeException("error");
        } catch (CedarRuntimeException e) {
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarRuntimeException(message);
        } catch (CedarRuntimeException e) {
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarRuntimeException("error", cause);
        } catch (CedarRuntimeException e) {
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        try {
            throw new CedarRuntimeException(message, cause);
        } catch (CedarRuntimeException e) {
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
            assertFalse(e.hasContext());
            assertNull(e.getContext());
        }

        ExceptionContext context = new ExceptionContext();
        CedarRuntimeException e = new CedarRuntimeException(message, cause);
        e.setContext(context);
        assertTrue(e.hasContext());
        assertSame(context, e.getContext());
    }

    /** Test DaoException. */
    @Test public void testDaoException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new DaoException();
        } catch (CedarRuntimeException e) {
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
        }

        try {
            throw new DaoException("error");
        } catch (CedarRuntimeException e) {
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
        }

        try {
            throw new DaoException(message);
        } catch (CedarRuntimeException e) {
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
        }

        try {
            throw new DaoException("error", cause);
        } catch (CedarRuntimeException e) {
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
        }

        try {
            throw new DaoException(message, cause);
        } catch (CedarRuntimeException e) {
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
        }
    }

    /** Test EnumException. */
    @Test public void testEnumException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new EnumException();
        } catch (EnumException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
        }

        try {
            throw new EnumException("error");
        } catch (EnumException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
        }

        try {
            throw new EnumException(message);
        } catch (EnumException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
        }

        try {
            throw new EnumException("error", cause);
        } catch (EnumException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
        }

        try {
            throw new EnumException(message, cause);
        } catch (EnumException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
        }
    }

    /** Test NotConfiguredException. */
    @Test public void testNotConfiguredException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new NotConfiguredException();
        } catch (NotConfiguredException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
        }

        try {
            throw new NotConfiguredException("error");
        } catch (NotConfiguredException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
        }

        try {
            throw new NotConfiguredException(message);
        } catch (NotConfiguredException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
        }

        try {
            throw new NotConfiguredException("error", cause);
        } catch (NotConfiguredException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
        }

        try {
            throw new NotConfiguredException(message, cause);
        } catch (NotConfiguredException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
        }
    }

    /** Test NotFoundException. */
    @Test public void testNotFoundException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new NotFoundException();
        } catch (NotFoundException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
        }

        try {
            throw new NotFoundException("error");
        } catch (NotFoundException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
        }

        try {
            throw new NotFoundException(message);
        } catch (NotFoundException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
        }

        try {
            throw new NotFoundException("error", cause);
        } catch (NotFoundException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
        }

        try {
            throw new NotFoundException(message, cause);
        } catch (NotFoundException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
        }
    }

    /** Test NotImplementedException. */
    @Test public void testNotImplementedException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new NotImplementedException();
        } catch (NotImplementedException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
        }

        try {
            throw new NotImplementedException("error");
        } catch (NotImplementedException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
        }

        try {
            throw new NotImplementedException(message);
        } catch (NotImplementedException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
        }

        try {
            throw new NotImplementedException("error", cause);
        } catch (NotImplementedException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
        }

        try {
            throw new NotImplementedException(message, cause);
        } catch (NotImplementedException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
        }
    }

    /** Test NotSupportedException. */
    @Test public void testNotSupportedException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new NotSupportedException();
        } catch (NotSupportedException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
        }

        try {
            throw new NotSupportedException("error");
        } catch (NotSupportedException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
        }

        try {
            throw new NotSupportedException(message);
        } catch (NotSupportedException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
        }

        try {
            throw new NotSupportedException("error", cause);
        } catch (NotSupportedException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
        }

        try {
            throw new NotSupportedException(message, cause);
        } catch (NotSupportedException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
        }
    }

    /** Test InvalidDataException. */
    @Test public void testInvalidDataException() {
        Throwable cause = new Exception("cause");

        ValidationErrors details = new ValidationErrors();
        details.setSummary(new LocalizableMessage("key", "context", "text"));
        details.addMessage("key1", "text1");
        details.addMessage("key2", "context", "text2");

        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new InvalidDataException();
        } catch (InvalidDataException e) {
            String expected = "com.cedarsolutions.exception.InvalidDataException";
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
            assertFalse(e.hasDetails());
            assertNull(e.getDetails());
            assertEquals(expected, e.toString());
        }

        try {
            throw new InvalidDataException("error");
        } catch (InvalidDataException e) {
            String expected = "com.cedarsolutions.exception.InvalidDataException: error";
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
            assertFalse(e.hasDetails());
            assertNull(e.getDetails());
            assertEquals(expected, e.toString());
        }

        try {
            throw new InvalidDataException(message);
        } catch (InvalidDataException e) {
            String expected = "com.cedarsolutions.exception.InvalidDataException: whatever";
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
            assertFalse(e.hasDetails());
            assertNull(e.getDetails());
            assertEquals(expected, e.toString());
        }

        try {
            throw new InvalidDataException("error", cause);
        } catch (InvalidDataException e) {
            String expected = "com.cedarsolutions.exception.InvalidDataException: error";
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
            assertFalse(e.hasDetails());
            assertNull(e.getDetails());
            assertEquals(expected, e.toString());
        }

        try {
            throw new InvalidDataException(message, cause);
        } catch (InvalidDataException e) {
            String expected = "com.cedarsolutions.exception.InvalidDataException: whatever";
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
            assertFalse(e.hasDetails());
            assertNull(e.getDetails());
            assertEquals(expected, e.toString());
        }

        try {
            throw new InvalidDataException("error", details);
        } catch (InvalidDataException e) {
            String expected = "com.cedarsolutions.exception.InvalidDataException: error" + "\n" + details.toString();
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
            assertTrue(e.hasDetails());
            assertSame(details, e.getDetails());
            assertEquals(expected, e.toString());
        }

        try {
            throw new InvalidDataException("error", details, cause);
        } catch (InvalidDataException e) {
            String expected = "com.cedarsolutions.exception.InvalidDataException: error" + "\n" + details.toString();
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
            assertTrue(e.hasDetails());
            assertSame(details, e.getDetails());
            assertEquals(expected, e.toString());
        }

        try {
            throw new InvalidDataException(message, details);
        } catch (InvalidDataException e) {
            String expected = "com.cedarsolutions.exception.InvalidDataException: whatever" + "\n" + details.toString();
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
            assertTrue(e.hasDetails());
            assertSame(details, e.getDetails());
            assertEquals(expected, e.toString());
        }

        try {
            throw new InvalidDataException(message, details, cause);
        } catch (InvalidDataException e) {
            String expected = "com.cedarsolutions.exception.InvalidDataException: whatever" + "\n" + details.toString();
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
            assertTrue(e.hasDetails());
            assertSame(details, e.getDetails());
            assertEquals(expected, e.toString());
        }
    }

    /** Test ServiceException. */
    @Test public void testServiceException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new ServiceException();
        } catch (ServiceException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
        }

        try {
            throw new ServiceException("error");
        } catch (ServiceException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
        }

        try {
            throw new ServiceException(message);
        } catch (ServiceException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
        }

        try {
            throw new ServiceException("error", cause);
        } catch (ServiceException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
        }

        try {
            throw new ServiceException(message, cause);
        } catch (ServiceException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
        }
    }

    /** Test RpcSecurityException. */
    @Test public void testRpcSecurityException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new RpcSecurityException();
        } catch (ServiceException e) {
            assertTrue(e instanceof ServiceException);
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
        }

        try {
            throw new RpcSecurityException("error");
        } catch (ServiceException e) {
            assertTrue(e instanceof ServiceException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
        }

        try {
            throw new RpcSecurityException(message);
        } catch (ServiceException e) {
            assertTrue(e instanceof ServiceException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
        }

        try {
            throw new RpcSecurityException("error", cause);
        } catch (ServiceException e) {
            assertTrue(e instanceof ServiceException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
        }

        try {
            throw new RpcSecurityException(message, cause);
        } catch (ServiceException e) {
            assertTrue(e instanceof ServiceException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
        }
    }

    /** Test InvalidArgumentsException. */
    @Test public void testInvalidArgumentsException() {
        Throwable cause = new Exception("cause");
        LocalizableMessage message = new LocalizableMessage("whatever");

        try {
            throw new InvalidArgumentsException();
        } catch (InvalidArgumentsException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(null, e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), (String) null);
            assertNull(e.getCause());
        }

        try {
            throw new InvalidArgumentsException("error");
        } catch (InvalidArgumentsException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertNull(e.getCause());
        }

        try {
            throw new InvalidArgumentsException(message);
        } catch (InvalidArgumentsException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertNull(e.getCause());
        }

        try {
            throw new InvalidArgumentsException("error", cause);
        } catch (InvalidArgumentsException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals("error", e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), "error");
            assertSame(cause, e.getCause());
        }

        try {
            throw new InvalidArgumentsException(message, cause);
        } catch (InvalidArgumentsException e) {
            assertTrue(e instanceof CedarRuntimeException);
            assertEquals(message.getText(), e.getMessage());
            assertMessageValues(e.getLocalizableMessage(), message);
            assertSame(cause, e.getCause());
        }
    }

    /** Assert that message values match expectations. */
    private static void assertMessageValues(LocalizableMessage message, LocalizableMessage expected) {
        assertMessageValues(message, expected.getText(), expected.getKey(), expected.getContext());
    }

    /** Assert that message values match expectations. */
    private static void assertMessageValues(LocalizableMessage message, String text) {
        assertMessageValues(message, text, null, null);
    }

    /** Assert that message values match expectations. */
    private static void assertMessageValues(LocalizableMessage message, String text, String key, String context) {
        assertNotNull(message);
        assertEquals(text, message.getText());
        assertEquals(key, message.getKey());
        assertEquals(context, message.getContext());
    }

}
