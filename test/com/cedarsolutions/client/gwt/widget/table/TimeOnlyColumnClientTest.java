/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 *              C E D A R
 *          S O L U T I O N S       "Software done right."
 *           S O F T W A R E
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * Copyright (c) 2014 Kenneth J. Pronovici.
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
package com.cedarsolutions.client.gwt.widget.table;

import java.util.Date;

import com.cedarsolutions.client.gwt.junit.ClientTestCase;
import com.cedarsolutions.client.gwt.widget.table.ColumnWithName.Sortable;
import com.cedarsolutions.util.gwt.GwtDateUtils;

/**
 * Client-side unit tests for TimeOnlyColumn.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public class TimeOnlyColumnClientTest extends ClientTestCase {

    /** Test constructor, getters and setters. */
    public void testConstructor() {
        TimeOnlyColumn<Row> column  = new TestColumn();
        assertNotNull(column);
        assertEquals(null, column.getName());
        assertFalse(column.isSortable());

        column  = new TestColumn("test");
        assertNotNull(column);
        assertEquals("test", column.getName());
        assertFalse(column.isSortable());

        column = new TestColumn(TestEnum.ONE);
        assertNotNull(column);
        assertEquals("ONE", column.getName());
        assertFalse(column.isSortable());

        column = new TestColumn("test", Sortable.NOT_SORTABLE);
        assertNotNull(column);
        assertEquals("test", column.getName());
        assertFalse(column.isSortable());

        column = new TestColumn(TestEnum.ONE, Sortable.NOT_SORTABLE);
        assertNotNull(column);
        assertEquals("ONE", column.getName());
        assertFalse(column.isSortable());

        column = new TestColumn("test", Sortable.SORTABLE);
        assertNotNull(column);
        assertEquals("test", column.getName());
        assertTrue(column.isSortable());

        column = new TestColumn(TestEnum.ONE, Sortable.SORTABLE);
        assertNotNull(column);
        assertEquals("ONE", column.getName());
        assertTrue(column.isSortable());
    }

    /** Test formatField(). */
    public void testFormatField() {
        Date field;
        Row row = new Row();
        TimeOnlyColumn<Row> column  = new TestColumn();

        // note: no need to test null because it never gets called with null

        column  = new TestColumn();
        row.setValue(GwtDateUtils.createDate(2013, 11, 15, 18, 32, 13, 26));
        field = column.getField(row);
        assertEquals("18:32:13", column.formatField(field));
    }

    /** Test column to work with. */
    private static class TestColumn extends TimeOnlyColumn<Row> {
        TestColumn() {
            super();
        }

        TestColumn(String name) {
            super(name);
        }

        TestColumn(TestEnum name) {
            super(name);
        }

        TestColumn(String name, Sortable sortable) {
            super(name, sortable);
        }

        TestColumn(TestEnum name, Sortable sortable) {
            super(name, sortable);
        }

        @Override
        protected Date getField(Row item) {
            return item.getValue();
        }
    }

    /** A sample row to work with. */
    private static class Row {
        private Date value;

        public Date getValue() {
            return this.value;
        }

        public void setValue(Date value) {
            this.value = value;
        }
    }

    /** Enumeration to test with. */
    private enum TestEnum {
        ONE,
        TWO
    }

}
