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
package com.cedarsolutions.client.gwt.widget;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cedarsolutions.web.metadata.NativeEventType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget-related utilities, which applications should extend and make a singleton.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public abstract class AbstractWidgetUtils {

    /**
     * Get the proper URL for an destination token.
     *
     * <p>
     * This should work even when running with the development mode
     * application server.  To generate the base URL for the site,
     * we take the current URL and chop off everything after the last
     * "#" character.  This is hack-ish, but there doesn't seem to
     * be any other way to do it.
     * </p>
     *
     * @param destinationToken  Token to generate URL for
     * @return Proper URL for the destination token.
     */
    public String getDestinationUrl(String destinationToken) {
        String baseUrl = getWndLocationHref().replaceAll("#.*$", "");
        return baseUrl + "#" + destinationToken;
    }

    /**
     * Get destination token for a URL.
     *
     * <p>
     * The process here is similar to getDestinationUrl(): we strip
     * off everything before the "#" character, and that must be the
     * token.
     * </p>
     *
     * @param destinationUrl  URL to generate token for
     * @return Destination token for the URL
     */
    public String getDestinationToken(String destinationUrl) {
        return destinationUrl.replaceFirst("^.*#", "");
    }

    /** Get the token represented by $wnd.location.href. */
    public String getWndLocationToken() {
        return getDestinationToken(this.getWndLocationHref());
    }

    /** Get $wnd.location.href from the browser's DOM. */
    public String getWndLocationHref() {
        return NativeUtils.getWndLocationHref();
    }

    /** Redirect the current page to an external URL. */
    public void redirect(String url) {
        NativeUtils.redirect(url);
    }

    /**
     * Open the referenced URL, as for download via a button click.
     * @see <a href="http://stackoverflow.com/questions/4703014">Stack Overflow</a>
     */
    public void openUrl(String url) {
        // I originally used NativeUtils.openUrl() to implement this.
        // However, that implementation does not work properly in IE.
        // This implementation works in IE 9, Firefox 40.0.3 and Chrome 45.
        Window.Location.assign(url);
    }

    /**
     * Get the URL prefix of the hosting page, a proxy over GWT.getHostPageBaseURL().
     * @return If non-empty, the base URL is guaranteed to end with a slash
     **/
    public String getHostPageBaseUrl() {
        return GWT.getHostPageBaseURL();
    }

    /** Encode a URL component, a proxy over GWT's URL.encodeQueryString(). */
    public String encodeQueryString(String component) {
        return URL.encodeQueryString(component);
    }

    /** Decode a URL component, a proxy over GWT's URL.decodeQueryString(). */
    public String decodeQueryString(String component) {
        return URL.decodeQueryString(component);
    }

    /**
     * Use a popup's key preview hooks to close the dialog when enter or escape is pressed.
     * @param popup    Popup to operate on
     * @param preview  Preview event passed to popup's onPreviewNativeEvent method
     */
    public void closeOnEnterOrEscape(PopupPanel popup, NativePreviewEvent preview) {
        if (popup != null && preview != null) {
            NativeEvent event = preview.getNativeEvent();
            if (event != null) {
                if (NativeEventType.KEYDOWN.equals(NativeEventType.convert(event.getType()))) {
                    switch (event.getKeyCode()) {
                    case KeyCodes.KEY_ENTER:
                    case KeyCodes.KEY_ESCAPE:
                        popup.hide();
                        break;
                    }
                }
            }
        }
    }

    /**
     * Use a popup's key preview hooks to click a button when escape is pressed.
     * @param preview  Preview event passed to popup's onPreviewNativeEvent method
     * @param button   Button to click.
     */
    public void clickButtonOnEscape(NativePreviewEvent preview, Button button) {
        if (preview != null) {
            NativeEvent event = preview.getNativeEvent();
            if (event != null) {
                if (NativeEventType.KEYDOWN.equals(NativeEventType.convert(event.getType()))) {
                    switch (event.getKeyCode()) {
                    case KeyCodes.KEY_ESCAPE:
                        button.click();
                        break;
                    }
                }
            }
        }
    }

    /**
     * Use a popup's key preview hooks to click a button when enter is pressed.
     * @param preview  Preview event passed to popup's onPreviewNativeEvent method
     * @param button   Button to click.
     */
    public void clickButtonOnEnter(NativePreviewEvent preview, Button button) {
        if (preview != null) {
            NativeEvent event = preview.getNativeEvent();
            if (event != null) {
                if (NativeEventType.KEYDOWN.equals(NativeEventType.convert(event.getType()))) {
                    switch (event.getKeyCode()) {
                    case KeyCodes.KEY_ENTER:
                        button.click();
                        break;
                    }
                }
            }
        }
    }

    /**
     * Globally handle the enter key, using it to click the indicated button.
     * @param button  Button to click when the enter key is pressed
     */
    public void clickOnEnter(Button button) {
        Event.addNativePreviewHandler(new ClickOnEnterHandler(button));
    }

    /** Handler that clicks a button when enter is pressed. */
    protected static class ClickOnEnterHandler implements Event.NativePreviewHandler {
        private Button button;

        public ClickOnEnterHandler(Button button) {
            this.button = button;
        }

        @Override
        public void onPreviewNativeEvent(NativePreviewEvent event) {
            switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                int keyCode = event.getNativeEvent().getKeyCode();
                if (keyCode == KeyCodes.KEY_ENTER) {
                    this.button.click();
                }
            }
        }
    }

    /**
     * Reliably focus on a widget via a scheduled command.'
     * To set focus on a panel, pick a widget (i.e. a text area) within the panel, and focus that.
     * This is also useful for setting focus on a field within a pop-up.
     * @param widget  Widget that supports being focused
     * @see <a href="http://stackoverflow.com/questions/5944612">StackOverflow</a>
     * @see <a href="http://stackoverflow.com/questions/6665927">StackOverflow</a>
     */
    public void setFocusAfterDisplay(final FocusWidget widget) {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                widget.setFocus(true);
            }
        });
    }

    /**
     * Reliably focus on a widget via a scheduled command.
     * To set focus on a panel, pick a widget (i.e. a text area) within the panel, and focus that.
     * @param focusable  Widget that supports being focused
     * @see <a href="http://stackoverflow.com/questions/5944612">StackOverflow</a>
     * @see <a href="http://stackoverflow.com/questions/6665927">StackOverflow</a>
     */
    public void setFocusAfterDisplay(final Focusable focusable) {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                focusable.setFocus(true);
            }
        });
    }

    /**
     * Reliably focus on a widget via a scheduled command, an alias for setFocusAfterDisplay().
     * To set focus on a panel, pick a widget (i.e. a text area) within the panel, and focus that.
     * This is also useful for setting focus on a field within a pop-up.
     * @param widget  Widget that supports being focused
     * @see <a href="http://stackoverflow.com/questions/5944612">StackOverflow</a>
     * @see <a href="http://stackoverflow.com/questions/6665927">StackOverflow</a>
     */
    public void setFocus(final FocusWidget widget) {
        this.setFocusAfterDisplay(widget);
    }

    /**
     * Reliably focus on a widget via a scheduled command, an alias for setFocusAfterDisplay().
     * To set focus on a panel, pick a widget (i.e. a text area) within the panel, and focus that.
     * @param focusable  Widget that supports being focused
     * @see <a href="http://stackoverflow.com/questions/5944612">StackOverflow</a>
     * @see <a href="http://stackoverflow.com/questions/6665927">StackOverflow</a>
     */
    public void setFocus(final Focusable focusable) {
        this.setFocusAfterDisplay(focusable);
    }

    /**
     * Get the child widget at a specified index.
     * @param parent  Parent widget to retrieve child from, like panel
     * @param index   Zero based index of child to retrieve
     * @return Retrieved child, or null if child is not found.
     * @see <a href="http://stackoverflow.com/questions/8448529">Stack Overflow</a>
     */
    public Widget getWidget(Widget parent, int index) {
        if (parent instanceof HasWidgets) {
            Iterator<Widget> iterator = ((HasWidgets) parent).iterator();

            for (int i = 0; i < index; i++) {
                if (iterator.hasNext()) {
                    iterator.next();
                }
            }

            if (iterator.hasNext()) {
                return iterator.next();
            }
        }

        return null;
    }

    /** Get the GWT codeserver query parameter, or null if no codeserver is set. */
    public String getCodeserver() {
        Map<String, List<String>> map = Window.Location.getParameterMap();
        if (map.containsKey("gwt.codesvr") && !map.get("gwt.codesvr").isEmpty()) {
            return map.get("gwt.codesvr").get(0);
        }

        return null;
    }

}
