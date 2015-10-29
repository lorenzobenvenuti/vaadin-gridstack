package com.tagetik.gridstack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tagetik.gridstack.client.gridstackcomponent.GridstackItemData;
import com.tagetik.gridstack.client.gridstackcomponent.GridstackServerRpc;
import com.tagetik.gridstack.client.gridstackcomponent.GridstackState;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.shared.Connector;
import com.vaadin.ui.Component;

/**
 * A Vaadin port of the Gridstack.js dynamic grid
 * @author lorenzob
 *
 */
// loads the needed scripts
@JavaScript({
    "jquery.min.js",
    "jquery-ui.min.js",
    "lodash.min.js",
    "gridstack.min.js"
})
// loads the css that styles grid and items
@StyleSheet({ 
    "vaadin://resources/gridstack.css",
    "vaadin://resources/gridstack-vaadin.css"
})
@SuppressWarnings("serial")
public class GridstackComponent extends com.vaadin.ui.AbstractComponentContainer {

    private GridstackServerRpc rpc = new GridstackServerRpc() {

        @Override
        public void onLayoutChange(Map<String, GridstackItemData> values) {
            Map<Connector, GridstackItemData> data = getState().itemData;
            Iterator<Component> cI = iterator();
            while (cI.hasNext()) {
                Component component = cI.next();
                String connectorId = component.getConnectorId();
                if (values.containsKey(connectorId)) {
                    GridstackItemData itemData = values.get(connectorId);
                    data.put(component, itemData);
                }
            }
            fireEvent(new GridstackLayoutChangeEvent(GridstackComponent.this));
        }
    
    };  
    
    private final List<Component> components = new LinkedList<Component>();

    public GridstackComponent() {
        registerRpc(rpc);
    }
    
	@Override
    public GridstackState getState() {
        return (GridstackState) super.getState();
    }
	
	/**
	 * Adds an item to the grid
	 * @param item
	 */
	public void addItem(GridstackItemComponent item) {
        addComponent(item);
    }
    
    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) {
        removeComponent(oldComponent);
        addComponent(newComponent);
    }

    @Override
    public int getComponentCount() {
        return components.size();
    }

    @Override
    public Iterator<Component> iterator() {
        return components.iterator();
    }
    
    @Override
    public void addComponent(Component c) {
        if (!(c instanceof GridstackItemComponent)) {
            throw new IllegalArgumentException("Only GridstackItem components are supported");
        }
        components.add(c);
        getState().itemData.put(c, new GridstackItemData());
        super.addComponent(c);
    }
    
    /**
     * Sets the {@link GridstackItemData} for a specific Component
     * @param c
     * @param data
     */
    public void setItemData(Component c, GridstackItemData data) {
        if (data == null) {
            throw new IllegalArgumentException("Item data cannot be null");
        }
        checkChild(c);
        getState().itemData.put(c, data);
    }

    /**
     * Sets the width of an item
     * @param c
     * @param width
     */
    public void setItemWidth(Component c, int width) {
        checkChild(c);
        getState().itemData.get(c).setItemWidth(width);
    }
    
    /**
     * Retrieves the width of an item
     * @param c
     * @return
     */
    public int getItemWidth(Component c) {
        checkChild(c);
        return getState().itemData.get(c).getItemWidth();
    }

    /**
     * Sets the height of an item
     * @param c
     * @param height
     */
    public void setItemHeight(Component c, int height) {
        checkChild(c);
        getState().itemData.get(c).setItemHeight(height);
    }

    /**
     * Retrieves the height of an item
     * @param c
     * @return
     */
    public int getItemHeight(Component c) {
        checkChild(c);
        return getState().itemData.get(c).getItemHeight();
    }

    
    /**
     * Checks if a component is a children of the {@link GridstackComponent}
     * @param c
     */
    private void checkChild(Component c) {
        if (!getState().itemData.containsKey(c)) {
            throw new IllegalArgumentException("Component is not a child of this grid");
        }
    }
    
    @Override
    public void removeComponent(Component c) {
        super.removeComponent(c);
        components.remove(c);
        getState().itemData.remove(c);
    }

    /**
     * Adds a listener on the "layout change" event
     * @param listener
     */
    public void addLayoutChangeListener(GridstackLayoutChangeListener listener) {
        addListener(
            GridstackLayoutChangeEvent.class, 
            listener, 
            GridstackLayoutChangeListener.ON_LAYOUT_CHANGE_METHOD
        );
    }
    
    /**
     * Removes a listener on the "layout change" event
     * @param listener
     */
    public void removeLayoutChangeListener(GridstackLayoutChangeListener listener) {
        removeListener(
            GridstackLayoutChangeEvent.class, 
            listener, 
            GridstackLayoutChangeListener.ON_LAYOUT_CHANGE_METHOD
        );
    }
	
}
