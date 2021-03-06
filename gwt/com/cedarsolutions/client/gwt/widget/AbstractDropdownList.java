/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 *              C E D A R
 *          S O L U T I O N S       "Software done right."
 *           S O F T W A R E
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * Copyright (c) 2013-2014,2016 Kenneth J. Pronovici.
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
package com.cedarsolutions.client.gwt.widget;

import java.util.HashMap;
import java.util.Map;

import com.cedarsolutions.util.gwt.GwtStringUtils;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Standard dropdown list, with a hook for concrete classes to specify the "any" constant.
 * @param <T> Type of the list, which MUST have a valid equals()/hashCode() implementation.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public abstract class AbstractDropdownList<T> extends ListBox {

    /** Map from String key to the underlying value. */
    private Map<String, T> keyToObjectValueMap = new HashMap<String, T>();

    /** Map from underlying value back to String key. */
    private Map<T, String> objectValueToKeyMap = new HashMap<T, String>();

    /** Get the display value used for the "any" key (null). */
    protected abstract String getAnyDisplay();

    /**
     * Get the key for a value.
     *
     * <p>
     * The default key is taken from the item's toString() method.  This might
     * or not be appropriate for all drop-down lists.  A drop-down list that
     * needs different behavior should override this method.
     * </p>
     *
     * @param value  Value to get the key for, not null
     * @return Key to associate with the passed-in value.
     */
    protected String getKey(T value) {
        return value.toString();
    }

    /**
     * Get the display string for a value.
     *
     * <p>
     * By default, a value's display string is identical to that value's key.
     * This might or not be appropriate for all drop-down lists.  A drop-down
     * list that needs different behavior should override this method.
     * </p>
     *
     * @param value  Value to get the display string for, not null
     * @return Display string to be presented to user for this value.
     */
    protected String getDisplay(T value) {
        return this.getKey(value);
    }

    /** Add the "any" item to the list, with a key of null. */
    public void addDropdownItemAny() {
        String any = getAnyDisplay();
        this.addDropdownItem(null, "", any);
    }

    /**
     * Add an item to the list, looking up the key and display values.
     * @param item   Item to add to the list
     */
    public void addDropdownItem(T item) {
        this.addDropdownItem(item, getKey(item), getDisplay(item));
    }

    /**
     * Add an item to the list, with explicit key and display values.
     * @param item    Item to add to the list
     * @param key     Key to associate with item
     * @param display Display string to be presented to user
     */
    public void addDropdownItem(T item, String key, String display) {
        this.keyToObjectValueMap.put(key, item);
        this.objectValueToKeyMap.put(item, key);
        this.addItem(display, key);
    }

    /** Whether a specific key is known in the list. */
    public boolean isDropdownKeyKnown(String key) {
        return this.keyToObjectValueMap.containsKey(key);
    }

    /** Whether a specific item is known in the list. */
    public boolean isDropdownItemKnown(T item) {
        return this.objectValueToKeyMap.containsKey(item);
    }

    /**
     * Remove an item from the list, looking up the key.
     * @param item    Item to remove from the list
     */
    public void removeDropdownItem(T item)  {
        String key = this.getKey(item);
        this.removeDropdownItemByKey(key);
    }

    /**
     * Remove an item from the list, looking up the key.
     * @param key    Key of the item to remove from the list
     */
    public void removeDropdownItemByKey(String key)  {
        for (int index = 0; index < this.getItemCount(); index++) {
            String current = this.getValue(index);
            if (GwtStringUtils.equals(current, key)) {
                this.removeItem(index);
                return;
            }
        }
    }

    /**
     * Set the selected object value (not the string value).
     * @param value  Value to select, possibly null to mean "any"
     */
    public void setSelectedObjectValue(T value) {
        String selected = this.objectValueToKeyMap.get(value);
        for (int index = 0; index < this.getItemCount(); index++) {
            String current = this.getValue(index);
            if (GwtStringUtils.equals(current, selected)) {
                this.setItemSelected(index, true);
            } else {
                this.setItemSelected(index, false);
            }
        }
    }

    /**
     * Get the selected object value (not the string value).
     * @return The selected value, possibly null to mean "any". */
    public T getSelectedObjectValue() {
        int index = this.getSelectedIndex();
        if (index < 0) {
            return null;
        } else {
            String selected = this.getValue(this.getSelectedIndex());
            return keyToObjectValueMap.get(selected);
        }
    }

    /**
     * Sets the title associated with this object.
     * The title is the 'tool-tip' displayed to users when they hover over the object.
     * @param title   Title to set
     * @see <a href="http://stackoverflow.com/questions/12494815/gwt-listbox-onmouseover-tooltip">Stack Overflow</a>
     */
    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        SelectElement selectElement = SelectElement.as(this.getElement());
        NodeList<OptionElement> options = selectElement.getOptions();
        for (int i = 0; i < options.getLength(); i++) {
            options.getItem(i).setTitle(title);
        }
    }

}
