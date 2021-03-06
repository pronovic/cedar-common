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
package com.cedarsolutions.client.gwt.widget.table;

import java.io.Serializable;

import com.cedarsolutions.client.gwt.custom.table.CheckboxCell;
import com.cedarsolutions.client.gwt.custom.table.SwitchableSelectionModel;
import com.cedarsolutions.client.gwt.custom.table.SwitchableSelectionModel.SelectionType;
import com.cedarsolutions.client.gwt.junit.ClientTestCase;
import com.cedarsolutions.client.gwt.widget.table.DataTable.DisabledResources;
import com.cedarsolutions.client.gwt.widget.table.DataTable.SelectionColumn;
import com.cedarsolutions.client.gwt.widget.table.DataTable.SelectionHeader;
import com.cedarsolutions.client.gwt.widget.table.DataTable.StandardResources;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Client-side unit tests for DataTable.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public class DataTableClientTest extends ClientTestCase {

    /** Test constructor. */
    public void testConstructor() {
        KeyProvider provider = new KeyProvider();

        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        assertNotNull(table);
        assertEquals(100, table.getPageSize());
        // can't verify the width, apparently
        assertNull(table.getKeyProvider());
        assertNotNull(table.getLoadingIndicator());

        table = new DataTable<Whatever>(100, "200px", provider);
        assertNotNull(table);
        assertEquals(100, table.getPageSize());
        // can't verify the width, apparently
        assertSame(provider, table.getKeyProvider());
        assertNotNull(table.getLoadingIndicator());
    }

    /** Test getPager(). */
    public void testGetPager() {
        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        DataTablePager pager = table.getPager();
        assertNotNull(pager);
        assertEquals(table.getPageSize(), pager.getPageSize());
        assertSame(table, pager.getDisplay());
    }

    /** Test getStandardResources(). */
    public void testGetStandardResources() {
        // Just confirm that it doesn't blow up; we can't really inspect the result
        CellTable.Resources style = DataTable.getStandardResources();
        assertNotNull(style);
        assertTrue(style instanceof StandardResources);
    }

    /** Test getDisabledResources(). */
    public void testGetDisabledResources() {
        // Just confirm that it doesn't blow up; we can't really inspect the result
        CellTable.Resources style = DataTable.getDisabledResources();
        assertNotNull(style);
        assertTrue(style instanceof DisabledResources);
    }

    /** Spot-check addSelectionColumn() for the default selection type. */
    @SuppressWarnings("rawtypes")
    public void testAddSelectionColumnDefault() {
        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        assertFalse(table.hasSelectionColumn());

        table.addSelectionColumn(10, Unit.PCT, new KeyProvider());
        assertTrue(table.hasSelectionColumn());
        assertEquals(1, table.getColumnCount());
        assertTrue(table.getColumn(0) instanceof SelectionColumn);
        assertTrue(table.getColumnHeader(0) instanceof SelectionHeader);
        assertEquals(SelectionType.MULTI, table.getSelectionType());
        assertEquals(SelectionType.MULTI, ((SelectionColumn) table.getColumn(0)).getSelectionType());
        assertEquals(SelectionType.MULTI, ((SelectionHeader) table.getColumnHeader(0)).getSelectionType());
    }

    /** Spot-check addSelectionColumn() for a MULTI selection type. */
    @SuppressWarnings("rawtypes")
    public void testAddSelectionColumnMulti() {
        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        assertFalse(table.hasSelectionColumn());

        table.addSelectionColumn(10, Unit.PCT, new KeyProvider(), SelectionType.MULTI);
        assertTrue(table.hasSelectionColumn());
        assertEquals(1, table.getColumnCount());
        assertTrue(table.getColumn(0) instanceof SelectionColumn);
        assertTrue(table.getColumnHeader(0) instanceof SelectionHeader);
        assertEquals(SelectionType.MULTI, table.getSelectionType());
        assertEquals(SelectionType.MULTI, ((SelectionColumn) table.getColumn(0)).getSelectionType());
        assertEquals(SelectionType.MULTI, ((SelectionHeader) table.getColumnHeader(0)).getSelectionType());
    }

    /** Spot-check addSelectionColumn() for a SINGLE selection type. */
    @SuppressWarnings("rawtypes")
    public void testAddSelectionColumnSingle() {
        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        assertFalse(table.hasSelectionColumn());

        table.addSelectionColumn(10, Unit.PCT, new KeyProvider(), SelectionType.SINGLE);
        assertTrue(table.hasSelectionColumn());
        assertEquals(1, table.getColumnCount());
        assertTrue(table.getColumn(0) instanceof SelectionColumn);
        assertTrue(table.getColumnHeader(0) instanceof SelectionHeader);
        assertEquals(SelectionType.SINGLE, table.getSelectionType());
        assertEquals(SelectionType.SINGLE, ((SelectionColumn) table.getColumn(0)).getSelectionType());
        assertEquals(SelectionType.SINGLE, ((SelectionHeader) table.getColumnHeader(0)).getSelectionType());
    }

    /** Spot-check addColumn() and addSelectionColumn() together. */
    public void testAddColumn() {
        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        assertFalse(table.hasSelectionColumn());

        SomethingColumn column = new SomethingColumn();

        table.addSelectionColumn(10, Unit.PCT, new KeyProvider());
        table.addColumn(column, "header", "footer");
        assertEquals(2, table.getColumnCount());
        assertTrue(table.getColumn(0) instanceof SelectionColumn);
        assertTrue(table.getColumnHeader(0) instanceof SelectionHeader);
        assertSame(column, table.getColumn(1));
        assertEquals("header", table.getColumnHeader(1));
        assertEquals("footer", table.getColumnFooter(1));
    }

    /** Spot-check setSelectionType(). */
    @SuppressWarnings("rawtypes")
    public void testSetSelectionType() {
        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        assertFalse(table.hasSelectionColumn());

        table.addSelectionColumn(10, Unit.PCT, new KeyProvider(), SelectionType.SINGLE);
        assertEquals(SelectionType.SINGLE, table.getSelectionType());
        assertEquals(SelectionType.SINGLE, ((SelectionColumn) table.getColumn(0)).getSelectionType());
        assertEquals(SelectionType.SINGLE, ((SelectionHeader) table.getColumnHeader(0)).getSelectionType());

        table.setSelectionType(SelectionType.MULTI);
        assertEquals(SelectionType.MULTI, table.getSelectionType());
        assertEquals(SelectionType.MULTI, ((SelectionColumn) table.getColumn(0)).getSelectionType());
        assertEquals(SelectionType.MULTI, ((SelectionHeader) table.getColumnHeader(0)).getSelectionType());

        table.setSelectionType(SelectionType.SINGLE);
        assertEquals(SelectionType.SINGLE, table.getSelectionType());
        assertEquals(SelectionType.SINGLE, ((SelectionColumn) table.getColumn(0)).getSelectionType());
        assertEquals(SelectionType.SINGLE, ((SelectionHeader) table.getColumnHeader(0)).getSelectionType());
    }

    /** Spot-check setNoRowsMessage(). */
    public void testSetNoRowsMessage() {
        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        assertNull(table.getNoRowsMessage());
        table.setNoRowsMessage("hello");
        assertEquals("hello", table.getNoRowsMessage());
    }

    /** Spot-check selection functionality. */
    public void testGetSelections() {
        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        table.addColumn(new SomethingColumn());
        assertTrue(table.getSelectedRecords().isEmpty());
        table.selectNone();
        table.selectAll();
        table.selectItems(true);

        table = new DataTable<Whatever>(100, "200px");
        table.addSelectionColumn(10, Unit.PCT, new KeyProvider());  // adds a selection model, too
        table.addColumn(new SomethingColumn());
        assertTrue(table.getSelectedRecords().isEmpty());
        table.selectNone();
        table.selectAll();
        table.selectItems(true);
    }

    /** Test SelectionColumn. */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void testSelectionColumn() {
        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        KeyProvider keyProvider = new KeyProvider();
        SwitchableSelectionModel<Whatever> selectionModel = new SwitchableSelectionModel<Whatever>(keyProvider, SelectionType.MULTI);
        Whatever value = new Whatever();

        SelectionColumn column = table.new SelectionColumn(selectionModel);
        assertTrue(column.getCell() instanceof CheckboxCell);
        assertFalse(column.isSortable());
        assertSame(selectionModel, column.selectionModel);
        assertEquals(SelectionType.MULTI, column.selectionModel.getSelectionType());
        assertTrue(((CheckboxCell) column.getCell()).isEnabled());

        column.setSelectionType(SelectionType.SINGLE);
        assertEquals(SelectionType.SINGLE, column.selectionModel.getSelectionType());
        assertTrue(((CheckboxCell) column.getCell()).isEnabled());

        column.setSelectionType(SelectionType.MULTI);
        assertEquals(SelectionType.MULTI, column.selectionModel.getSelectionType());
        assertTrue(((CheckboxCell) column.getCell()).isEnabled());

        column.selectionModel.setSelected(value, true);
        assertTrue(column.getValue(value));

        column.selectionModel.setSelected(value, false);
        assertFalse(column.getValue(value));
    }

    /** Test SelectionHeader. */
    @SuppressWarnings("rawtypes")
    public void testSelectionHeader() {
        DataTable<Whatever> table = new DataTable<Whatever>(100, "200px");
        KeyProvider keyProvider = new KeyProvider();
        SwitchableSelectionModel<Whatever> selectionModel = new SwitchableSelectionModel<Whatever>(keyProvider, SelectionType.MULTI);

        SelectionHeader header = table.new SelectionHeader(table, selectionModel);
        assertTrue(header.getCell() instanceof CheckboxCell);
        assertSame(table, header.table);
        assertSame(selectionModel, header.selectionModel);
        assertEquals(SelectionType.MULTI, header.selectionModel.getSelectionType());
        assertFalse(header.getValue());
        assertTrue(((CheckboxCell) header.getCell()).isEnabled());

        header.setSelectionType(SelectionType.SINGLE);
        assertEquals(SelectionType.SINGLE, header.selectionModel.getSelectionType());
        assertFalse(((CheckboxCell) header.getCell()).isEnabled());

        header.setSelectionType(SelectionType.MULTI);
        assertEquals(SelectionType.MULTI, header.selectionModel.getSelectionType());
        assertTrue(((CheckboxCell) header.getCell()).isEnabled());
    }

    /** Class for our data table to hold. */
    @SuppressWarnings("serial")
    private static class Whatever implements Serializable {
    }

    /** Key provider. */
    private static class KeyProvider implements ProvidesKey<Whatever> {
        @Override
        public Object getKey(Whatever item) {
            return "1";
        }
    }

    /** Column. */
    private static class SomethingColumn extends Column<Whatever, String> {
        SomethingColumn() {
            super(new TextCell());
        }

        @Override
        public String getValue(Whatever object) {
            return "whatever";
        }
    }

}
