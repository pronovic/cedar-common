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
package com.cedarsolutions.client.gwt.module.view;

import com.cedarsolutions.client.gwt.custom.tab.TabLayoutPanel;
import com.cedarsolutions.client.gwt.event.UnifiedEvent;
import com.cedarsolutions.client.gwt.event.UnifiedEventType;
import com.cedarsolutions.client.gwt.event.ViewEventHandler;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.History;

/**
 * Specialized page that is intended to be a tab.
 * @author Kenneth J. Pronovici <pronovic@ieee.org>
 */
public abstract class ModuleTabView extends ModulePageView implements IModuleTabView {

    /** Parent tab layout panel. */
    private TabLayoutPanel parentPanel;

    /** Index of this tab on the layout panel. */
    private int tabIndex;

    /** Whether the tab has been initialized. */
    private boolean initialized;

    /** Event handler for the initialization event (if any). */
    private ViewEventHandler initializationEventHandler;

    /** Event handler for the selected event (if any). */
    private ViewEventHandler selectedEventHandler;

    /**
     * Set the context that this tab exists in.
     * @param parentPanel Parent tab layout panel
     * @param tabIndex    Index of this tab on the layout panel
     */
    @Override
    public void setContext(TabLayoutPanel parentPanel, int tabIndex) {
        this.parentPanel = parentPanel;
        this.tabIndex = tabIndex;
        this.parentPanel.addBeforeSelectionHandler(new SelectionHandler(this));
    }

    /** Get the parent TabLayoutPanel. */
    @Override
    public TabLayoutPanel getParentPanel() {
        return this.parentPanel;
    }

    /** Get the history token for this tab, or null for no history. */
    @Override
    public String getHistoryToken() {
        return null;
    }

    /** Get the tab index. */
    @Override
    public int getTabIndex() {
        return this.tabIndex;
    }

    /** Whether the tab has been initialized yet. */
    @Override
    public boolean isInitialized() {
        return this.initialized;
    }

    /** Mark that the tab has been initialized. */
    @Override
    public void markInitialized() {
        this.initialized = true;
    }

    /** Get the initialization event handler. */
    @Override
    public ViewEventHandler getInitializationEventHandler() {
        return this.initializationEventHandler;
    }

    /** Set the initialization event handler. */
    @Override
    public void setInitializationEventHandler(ViewEventHandler initializationEventHandler) {
        this.initializationEventHandler = initializationEventHandler;
    }

    /** Get the selected event handler. */
    @Override
    public ViewEventHandler getSelectedEventHandler() {
        return this.selectedEventHandler;
    }

    /** Set the selected event handler. */
    @Override
    public void setSelectedEventHandler(ViewEventHandler selectedEventHandler) {
        this.selectedEventHandler = selectedEventHandler;
    }

    /** Select the tab, as if the normal BeforeSelectionHandler was invoked. */
    @Override
    public void selectTab() {
        if (!this.isInitialized()) {
            if (this.getInitializationEventHandler() != null) {
                UnifiedEvent init = new UnifiedEvent(UnifiedEventType.INIT_EVENT);
                this.getInitializationEventHandler().handleEvent(init);
            }

            this.markInitialized();
        }

        // The selected event handler is always called, even when initialize is also called.
        // That way, the client doesn't need any special logic for the "selected first time" case.
        if (this.getSelectedEventHandler() != null) {
            UnifiedEvent selected = new UnifiedEvent(UnifiedEventType.SELECTED_EVENT);
            this.getSelectedEventHandler().handleEvent(selected);
        }

        if (this.getHistoryToken() != null) {
            History.newItem(this.getHistoryToken(), false);
        }
    }

    /** Initialization handler. */
    protected static class SelectionHandler implements BeforeSelectionHandler<Integer> {
        private IModuleTabView view;

        public SelectionHandler(IModuleTabView view) {
            this.view = view;
        }

        @Override
        public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
            onBeforeSelection(event.getItem());
        }

        protected void onBeforeSelection(int tabIndex) {
            if (tabIndex == this.view.getTabIndex()) {
                this.view.selectTab();
            }
        }

    }
}
