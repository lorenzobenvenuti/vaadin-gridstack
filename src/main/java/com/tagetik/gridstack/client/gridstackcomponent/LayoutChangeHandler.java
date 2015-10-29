package com.tagetik.gridstack.client.gridstackcomponent;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link LayoutChangeEvent}
 * @author lorenzo
 *
 */
public interface LayoutChangeHandler extends EventHandler {

    /**
     * Invoked when the item layout changes
     * @param event
     */
	void onLayoutChange(LayoutChangeEvent event);
	
}
