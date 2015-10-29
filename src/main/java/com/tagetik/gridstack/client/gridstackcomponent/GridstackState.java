package com.tagetik.gridstack.client.gridstackcomponent;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.AbstractLayoutState;

/**
 * The state of the Gridstack component
 * @author lorenzob
 *
 */
@SuppressWarnings("serial")
public class GridstackState extends AbstractLayoutState {
    
    /**
     * The data associated to the items of the grid
     */
    public Map<Connector, GridstackItemData> itemData = new HashMap<>();
    
}