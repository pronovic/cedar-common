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


/**
 * A ColumnWithName that displays String data in a "typical" way.
 * @param <T> the row type
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public abstract class StringColumn<T> extends TypedColumn<T, String> {

    /** Create a column with no name. */
    public StringColumn() {
        super();
    }

    /** Create a column with a name taken from an enum. */
    @SuppressWarnings("rawtypes")
    public StringColumn(Enum name) {
        super(name);
    }

    /** Create a column with a name taken from an enum. */
    @SuppressWarnings("rawtypes")
    public StringColumn(Enum name, Sortable sortable) {
        super(name, sortable);
    }

    /** Create a column with a name. */
    public StringColumn(String name) {
        super(name);
    }

    /** Create a column with a name. */
    public StringColumn(String name, Sortable sortable) {
        super(name, sortable);
    }

    /** Format a non-null field value properly. */
    @Override
    protected String formatField(String field) {
        return field;
    }

}
