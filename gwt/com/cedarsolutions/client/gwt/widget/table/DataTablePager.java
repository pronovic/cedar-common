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
package com.cedarsolutions.client.gwt.widget.table;

import com.cedarsolutions.client.gwt.custom.pager.SimplePager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.Range;

/**
 * Standard pager for use with DataTable.
 *
 * <p>
 * This pager maintains a set page size and displays page numbers and
 * total pages more elegantly than the original Google pager.  It also
 * fixes rendering problems with the last page button, which should
 * only be shown if the last page is known to exist.
 * </p>
 *
 * <p>
 * This pager does not support the fast forward button that is provided
 * by the original SimplePager widget.
 * </p>
 *
 * <p>
 * The initial implementation was taken from a Google Groups posting,
 * referenced below.  I have modified that implementation over time.
 * So much of this class has been overridden that it almost should be
 * rewritten from scratch, but I've been trying to avoid that.
 * </p>
 *
 * @see <a href="http://www.mail-archive.com/google-web-toolkit@googlegroups.com/msg62640.html">Google Groups</a>
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public class DataTablePager extends SimplePager {

    /** Configured page size. */
    private int pageSize;

    /** Create a pager with the standard options. */
    public DataTablePager(DataTable<?> parent) {
        super(TextLocation.CENTER, getResources(), false, 0, true);
        this.pageSize = parent.getPageSize();
        this.setPageSize(this.pageSize);
        this.setDisplay(parent);
    }

    /** Get the customized style for this widget. */
    protected static SimplePager.Resources getResources() {
        return GWT.create(SimplePager.Resources.class);
    }

    /** {@inheritDoc} */
    @Override
    protected void onRangeOrRowCountChanged() {
      super.onRangeOrRowCountChanged();
      this.setLastPageButtonDisabled(!this.hasLastPage());
    }

    /** {@inheritDoc} */
    @Override
    public int getPageSize() {
        // Normally, SimplePager derives this from the visible range
        return this.pageSize;
    }

    /** {@inheritDoc} */
    @Override
    public void nextPage() {
        // Page forward by an exact size rather than the number of visible
        // rows as is in the norm in the underlying implementation
        if (getDisplay() != null) {
            Range range = getDisplay().getVisibleRange();
            setPageStart(range.getStart() + getPageSize());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void previousPage() {
        // Page back by an exact size rather than the number of visible rows
        // as is in the norm in the underlying implementation
        if (getDisplay() != null) {
            Range range = getDisplay().getVisibleRange();
            setPageStart(range.getStart() - getPageSize());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setPageStart(int index) {
        // Override so the last page is shown with a number of rows less
        // than the pageSize rather than always showing the pageSize number
        // of rows and possibly repeating rows on the last and penultimate page
        if (getDisplay() != null) {
            Range range = getDisplay().getVisibleRange();
            int displayPageSize = getPageSize();
            if (isRangeLimited() && getDisplay().isRowCountExact()) {
                displayPageSize = Math.min(getPageSize(), getDisplay().getRowCount() - index);
            }
            index = Math.max(0, index);
            if (index != range.getStart()) {
                getDisplay().setVisibleRange(index, displayPageSize);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    protected String createText() {
        // Override to display "0 of 0" when there are no records (otherwise
        // you get "1-1 of 0") and "1 of 1" when there is only one record
        // (otherwise you get "1-1 of 1"). Not internationalised (but
        // neither is SimplePager).  There's also a special case if the
        // final page is empty: we get "> 10 of 10" instead of "11 of 10".
        NumberFormat formatter = NumberFormat.getFormat("#,###");
        HasRows display = getDisplay();
        Range range = display.getVisibleRange();
        int pageStart = range.getStart() + 1;
        int size = range.getLength();
        int dataSize = display.getRowCount();
        int endIndex = Math.min(dataSize, pageStart + size - 1);
        endIndex = Math.max(pageStart, endIndex);
        boolean exact = display.isRowCountExact();
        if (dataSize == 0) {
            return "0 of 0";
        } else if (pageStart == endIndex) {
            if (pageStart > dataSize) {
                // This is better than "11 of 10" if the final page is empty
                return "> " + formatter.format(dataSize) + " of " + formatter.format(dataSize);
            } else {
                return formatter.format(pageStart) + " of " + formatter.format(dataSize);
            }
        } else {
            return formatter.format(pageStart) + "-" + formatter.format(endIndex) + (exact ? " of " : " of over ") + formatter.format(dataSize);
        }
    }

    /**
     * Enable or disable the last page button.
     * @param disabled true to disable, false to enable
     */
    protected void setLastPageButtonDisabled(boolean disabled) {
      if (lastPage != null) {
        lastPage.setDisabled(disabled);
      }
    }

    /** True if we know for certain there is a last page beyond the one currently displayed. */
    protected boolean hasLastPage() {
        HasRows display = getDisplay();
        Range range = display.getVisibleRange();
        int pageStart = range.getStart() + 1;
        int size = range.getLength();
        int dataSize = display.getRowCount();
        int endIndex = Math.min(dataSize, pageStart + size - 1);
        endIndex = Math.max(pageStart, endIndex);
        boolean exact = display.isRowCountExact();
        if (dataSize == 0) {
            return false;
        } else if (pageStart == endIndex) {
            return false;
        } else {
            return !exact ? false : dataSize > endIndex;
        }
    }

}
