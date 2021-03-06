/*
 * Copyright 2010 Traction Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tractionsoftware.gwt.user.client.ui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.tractionsoftware.gwt.user.client.ui.impl.UTCTimeBoxImpl;
import com.tractionsoftware.gwt.user.client.util.DomUtils;

/**
 * UTC time entry widget.
 *
 * <h3>Code Source</h3>
 *
 * <p>
 * This is external code that was copied into the CedarCommon codebase under
 * the terms of its license.
 * </p>
 *
 * <blockquote>
 *    <table border="1" cellpadding="5" cellspacing="0">
 *       <tbody>
 *          <tr>
 *             <td><i>Source:</i></td>
 *             <td><a href="http://tractionsoftware.github.io/gwt-traction/">Traction Software</a></td>
 *          </tr>
 *          <tr>
 *             <td><i>Date:</i></td>
 *             <td>July, 2014</td>
 *          </tr>
 *          <tr>
 *             <td><i>Reason:</i></td>
 *             <td>
 *                  The official Traction-supplied jar in Maven only supports Java 1.7+, but CedarCommon supports Java 1.6+.
 *                  I also made some minor bug fixes based on my own experience with the code.
 *             </td>
 *          </tr>
 *       </tbody>
 *    </table>
 * </blockquote>
 *
 * @author Andy @ Traction Software
 */
public class UTCTimeBox extends Composite implements HasValue<Long>, HasValueChangeHandlers<Long>, HasText, HasEnabled {

    public UTCTimeBoxImpl impl;
    private boolean enabled = true;

    /**
     * By default the predefined SHORT time format will be used.
     */
    public UTCTimeBox() {
        this(DateTimeFormat.getFormat(PredefinedFormat.TIME_SHORT));
    }

    /**
     * Allows a UTCTimeBox to be created with a specified format.
     */
    public UTCTimeBox(DateTimeFormat timeFormat) {
        // used deferred binding for the implementation
        impl = GWT.create(UTCTimeBoxImpl.class);
        impl.setTimeFormat(timeFormat);
        initWidget(impl.asWidget());
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Long> handler) {
        return impl.addValueChangeHandler(handler);
    }

    @Override
    public Long getValue() {
        return impl.getValue();
    }

    @Override
    public void setValue(Long value) {
        impl.setValue(value);
    }

    @Override
    public void setValue(Long value, boolean fireEvents) {
        impl.setValue(value, fireEvents);
    }

    @Override
    public String getText() {
        return impl.getText();
    }

    @Override
    public void setText(String text) {
        impl.setText(text);
    }

    @Override
    public boolean isEnabled() {
        // return DomUtils.isEnabled(getElement());
        return this.enabled;     // changed for CedarCommon, original did not work (not sure why)
    }

    @Override
    public void setEnabled(boolean enabled) {
        DomUtils.setEnabled(getElement(), enabled);
        this.enabled = enabled;  // added for CedarCommon, original did not work (not sure why)
    }

    /**
     * The HTML5 implementation will ignore this.
     */
    public void setVisibleLength(int length) {
        impl.setVisibleLength(length);
    }

    public void setTabIndex(int tabIndex) {
        impl.setTabIndex(tabIndex);
    }

    /**
     * If this is a text based control, it will validate the value
     * that has been typed.
     */
    public void validate() {
        impl.validate();
    }

    // ----------------------------------------------------------------------
    // utils

    public static final Long getValueForNextHour() {
        Date date = new Date();
        long value = UTCDateBox.date2utc(date);

        // remove anything after an hour and add an hour
        long hour = 60 * 60 * 1000;
        value = value % UTCDateBox.DAY_IN_MS;
        return value - (value % hour) + hour;
    }

}
