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
package com.cedarsolutions.util.gwt;

import java.util.Date;

import com.cedarsolutions.client.gwt.junit.ClientTestCase;
import com.google.gwt.i18n.shared.DateTimeFormat;

/**
 * Client-side unit tests for GwtDateUtils.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public class GwtDateUtilsClientTest extends ClientTestCase {

    /** Test getCurrentDate(). */
    public void testGetCurrentDate() {
        assertNotNull(GwtDateUtils.getCurrentDate());
    }

    /** Test getCurrentDateAtMidnight(). */
    public void testGetCurrentDateAtMidnight() {
        Date current = GwtDateUtils.getCurrentDate();
        Date midnight = GwtDateUtils.getCurrentDateAtMidnight();
        assertEquals(GwtDateUtils.formatDate(current), GwtDateUtils.formatDate(midnight));
        assertEquals("00:00:00", GwtDateUtils.formatDate(midnight, "HH:mm:ss"));
    }

    /** Test getCurrentDateAtTime(). */
    public void testGetCurrentDateAtTime() {
        Date current = GwtDateUtils.getCurrentDate();
        Date time = GwtDateUtils.getCurrentDateAtTime("14:32");
        assertEquals(GwtDateUtils.formatDate(current), GwtDateUtils.formatDate(time));
        assertEquals("14:32:00", GwtDateUtils.formatDate(time, "HH:mm:ss"));
    }

    /** Test resetTime(). */
    public void testResetTime() {
        Date input = GwtDateUtils.createDate(2011, 12, 14, 13, 42, 17, 996);
        Date expected = GwtDateUtils.createDate(2011, 12, 14, 0, 0, 0, 0);
        Date result = GwtDateUtils.resetTime(input, "00:00");
        assertEquals(expected, result);

        input = GwtDateUtils.createDate(2011, 12, 14, 13, 42, 17, 996);
        expected = GwtDateUtils.createDate(2011, 12, 14, 19, 32, 0, 0);
        result = GwtDateUtils.resetTime(input, "19:32");
        assertEquals(expected, result);
    }

    /** Test getFormat(). */
    public void testGetFormat() {
        DateTimeFormat result = GwtDateUtils.getFormat("yyyy-MM-dd");
        assertNotNull(result);
        assertEquals("yyyy-MM-dd", result.getPattern());
    }

    /** Test getDateFormat(). */
    public void testGetDateFormat() {
        DateTimeFormat result = GwtDateUtils.getDateFormat();
        assertNotNull(result);
        assertEquals(GwtDateUtils.DATE_FORMAT, result.getPattern());
    }

    /** Test getTimeFormat(). */
    public void testGetTimeFormat() {
        DateTimeFormat result = GwtDateUtils.getTimeFormat();
        assertNotNull(result);
        assertEquals(GwtDateUtils.TIME_FORMAT, result.getPattern());
    }

    /** Test getTimestampFormat(). */
    public void testGetTimestampFormat() {
        DateTimeFormat result = GwtDateUtils.getTimestampFormat();
        assertNotNull(result);
        assertEquals(GwtDateUtils.TIMESTAMP_FORMAT, result.getPattern());
    }

    /** Test formatDate(). */
    public void testFormatDate() {
        assertEquals("", GwtDateUtils.formatDate(null));
        assertEquals("2011-01-02", GwtDateUtils.formatDate(GwtDateUtils.createDate(2011, 1, 2, 3, 4, 5, 6)));
        assertEquals("2011-11-12", GwtDateUtils.formatDate(GwtDateUtils.createDate(2011, 11, 12, 13, 14, 15, 16)));
        assertEquals("2011-11-12", GwtDateUtils.formatDate(GwtDateUtils.createDate(2011, 11, 12, 13, 14, 15, 167)));
    }

    /** Test formatDateTime(). */
    public void testFormatTime() {
        assertEquals("", GwtDateUtils.formatDate(null));
        assertEquals("2011-01-02T03:04", GwtDateUtils.formatTime(GwtDateUtils.createDate(2011, 1, 2, 3, 4, 5, 6)));
        assertEquals("2011-11-12T13:14", GwtDateUtils.formatTime(GwtDateUtils.createDate(2011, 11, 12, 13, 14, 15, 16)));
        assertEquals("2011-11-12T13:14", GwtDateUtils.formatTime(GwtDateUtils.createDate(2011, 11, 12, 13, 14, 15, 167)));
    }

    /** Test formatTimestamp(). */
    public void testFormatTimestamp() {
        assertEquals("", GwtDateUtils.formatDate(null));
        assertEquals("2011-01-02T03:04:05,006", GwtDateUtils.formatTimestamp(GwtDateUtils.createDate(2011, 1, 2, 3, 4, 5, 6)));
        assertEquals("2011-11-12T13:14:15,016", GwtDateUtils.formatTimestamp(GwtDateUtils.createDate(2011, 11, 12, 13, 14, 15, 16)));
        assertEquals("2011-11-12T13:14:15,167", GwtDateUtils.formatTimestamp(GwtDateUtils.createDate(2011, 11, 12, 13, 14, 15, 167)));
    }

    /** Test formatDate() for an arbitrary format. */
    public void testFormatDateArbitraryFormat() {
        assertEquals("", GwtDateUtils.formatDate(null, (String) null));
        assertEquals("", GwtDateUtils.formatDate(null, "yyyy-MM-dd"));
        assertEquals("", GwtDateUtils.formatDate(null, "yyyy-MMM-dd"));
        assertEquals("", GwtDateUtils.formatDate(GwtDateUtils.createDate(2011, 12, 14), (String) null));
        assertEquals("", GwtDateUtils.formatDate(GwtDateUtils.createDate(2011, 12, 14), ""));
        assertEquals("********", GwtDateUtils.formatDate(GwtDateUtils.createDate(2011, 12, 14), "********"));   // bogus pattern
        assertEquals("2011-12-14", GwtDateUtils.formatDate(GwtDateUtils.createDate(2011, 12, 14), "yyyy-MM-dd"));
        assertEquals("2011-Dec-14", GwtDateUtils.formatDate(GwtDateUtils.createDate(2011, 12, 14), "yyyy-MMM-dd"));
    }

    /** Test parseDate(). */
    public void testParseDate() {
        try {
            GwtDateUtils.parseDate("2011-12-14", "********");  // bogus pattern
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) { }

        assertEquals(null, GwtDateUtils.parseDate(null, null));
        assertEquals(null, GwtDateUtils.parseDate(null, "yyyy-MM-dd"));
        assertEquals(null, GwtDateUtils.parseDate("", "yyyy-MM-dd"));
        assertEquals(null, GwtDateUtils.parseDate("2011-12-14", null));
        assertEquals(null, GwtDateUtils.parseDate("2011-12-14", ""));
        assertEquals(GwtDateUtils.createDate(2011, 12, 14), GwtDateUtils.parseDate("2011-12-14", "yyyy-MM-dd"));
        assertEquals(GwtDateUtils.createDate(2011, 12, 14), GwtDateUtils.parseDate("2011-Dec-14", "yyyy-MMM-dd"));
    }

    /** Test createDate() using copy from another date. */
    public void testCopyDate() {
        Date date = GwtDateUtils.createDate(2011, 11, 12, 13, 14, 15, 167);
        assertEquals(date, GwtDateUtils.createDate(date));
        assertEquals(null, GwtDateUtils.createDate((Date) null));
    }

    /** Test createDate() using individual fields. */
    public void testCreateDateFields() {
        // Expected values are taken from the results in DateUtilsTest.testCreateDateFields()

        try {
            GwtDateUtils.createDate(-1, 1, 21, 14, 27, 54, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(10000, 1, 21, 14, 27, 54, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 0, 21, 14, 27, 54, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 13, 21, 14, 27, 54, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 1, 0, 14, 27, 54, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 1, 32, 14, 27, 54, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 1, 21, -1, 27, 54, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 1, 21, 25, 27, 54, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 1, 21, 14, -1, 54, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 1, 21, 14, 60, 54, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 1, 21, 14, 27, -1, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 1, 21, 14, 27, 60, 334);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 1, 21, 14, 27, 54, -1);
        } catch (IllegalArgumentException e) { }

        try {
            GwtDateUtils.createDate(2011, 1, 21, 14, 27, 54, 1000);
        } catch (IllegalArgumentException e) { }


        Date date = GwtDateUtils.createDate(2011, 1, 21);
        assertEquals(1295589600000L, date.getTime());

        date = GwtDateUtils.createDate(2011, 1, 21, 14, 27);
        assertEquals(1295641620000L, date.getTime());

        date = GwtDateUtils.createDate(2011, 1, 21, 14, 27, 54);
        assertEquals(1295641674000L, date.getTime());

        date = GwtDateUtils.createDate(2011, 1, 21, 14, 27, 54, 334);
        assertEquals(1295641674334L, date.getTime());
    }

    /** Test the formatElapsedTime() method. */
    public void testFormatElapsedTime() {
        long oneSecond = GwtDateUtils.MILLISECONDS_PER_SECOND;
        long twoSeconds = GwtDateUtils.MILLISECONDS_PER_SECOND * 2;
        long oneMinute = GwtDateUtils.MILLISECONDS_PER_MINUTE;
        long tenMinutes = GwtDateUtils.MILLISECONDS_PER_MINUTE * 10;
        long elevenMinutes = GwtDateUtils.MILLISECONDS_PER_MINUTE * 11;
        long oneHour = GwtDateUtils.MILLISECONDS_PER_HOUR;
        long tenHours = GwtDateUtils.MILLISECONDS_PER_HOUR * 10;
        long oneDay = GwtDateUtils.MILLISECONDS_PER_DAY;

        assertEquals("00:00:00,001", GwtDateUtils.formatElapsedTime(1));
        assertEquals("00:00:00,002", GwtDateUtils.formatElapsedTime(2));
        assertEquals("00:00:00,999", GwtDateUtils.formatElapsedTime(oneSecond - 1));
        assertEquals("00:00:01,000", GwtDateUtils.formatElapsedTime(oneSecond));
        assertEquals("00:00:01,001", GwtDateUtils.formatElapsedTime(oneSecond + 1));
        assertEquals("00:00:01,002", GwtDateUtils.formatElapsedTime(oneSecond + 2));
        assertEquals("00:00:02,000", GwtDateUtils.formatElapsedTime(twoSeconds));
        assertEquals("00:00:59,999", GwtDateUtils.formatElapsedTime(oneMinute - 1));
        assertEquals("00:01:00,000", GwtDateUtils.formatElapsedTime(oneMinute));
        assertEquals("00:01:00,001", GwtDateUtils.formatElapsedTime(oneMinute + 1));
        assertEquals("00:01:01,001", GwtDateUtils.formatElapsedTime(oneMinute + oneSecond + 1));
        assertEquals("00:09:59,999", GwtDateUtils.formatElapsedTime(tenMinutes - 1));
        assertEquals("00:10:00,000", GwtDateUtils.formatElapsedTime(tenMinutes));
        assertEquals("00:10:59,999", GwtDateUtils.formatElapsedTime(elevenMinutes - 1));
        assertEquals("00:59:59,999", GwtDateUtils.formatElapsedTime(oneHour - 1));
        assertEquals("01:00:00,000", GwtDateUtils.formatElapsedTime(oneHour));
        assertEquals("01:00:00,001", GwtDateUtils.formatElapsedTime(oneHour + 1));
        assertEquals("09:59:59,999", GwtDateUtils.formatElapsedTime(tenHours - 1));
        assertEquals("10:00:00,000", GwtDateUtils.formatElapsedTime(tenHours));
        assertEquals("10:00:00,001", GwtDateUtils.formatElapsedTime(tenHours + 1));
        assertEquals("23:59:59,999", GwtDateUtils.formatElapsedTime(oneDay - 1));
        assertEquals("1 day 00:00:00,000", GwtDateUtils.formatElapsedTime(oneDay));
        assertEquals("1 day 00:00:00,001", GwtDateUtils.formatElapsedTime(oneDay + 1));
        assertEquals("2 days 00:00:12,222", GwtDateUtils.formatElapsedTime((oneDay * 2) + 12222));
    }
}

