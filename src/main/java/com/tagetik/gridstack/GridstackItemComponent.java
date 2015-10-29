package com.tagetik.gridstack;

import com.tagetik.gridstack.client.gridstackcomponent.GridstackItemState;
import com.vaadin.ui.AbstractSingleComponentContainer;

/**
 * An item of the grid
 * @author lorenzob
 *
 */
@SuppressWarnings("serial")
public class GridstackItemComponent extends AbstractSingleComponentContainer {
    
    @Override
    protected GridstackItemState getState() {
        return (GridstackItemState) super.getState();
    }

    /**
     * Sets the item background color
     * @param color
     */
    public void setBackgroundColor(String color) {
        getState().backgroundColor = color;
    }
    
    /**
     * Retrieves the item background color
     * @return
     */
    public String getBackgroundColor() {
        return getState().backgroundColor;
    }
    
    /**
     * Sets the header text color
     * @param color
     */
    public void setHeaderTextColor(String color) {
        getState().headerTextColor = color;
    }
    
    /**
     * Retrieves the header text color
     * @return
     */
    public String getHeaderTextColor() {
        return getState().headerTextColor;
    }
    
    /**
     * Sets the header text
     * @param headerText
     */
    public void setHeaderText(String headerText) {
        getState().headerText = headerText;
    }
    
    /**
     * Retrieves the header text
     * @return
     */
    public String getHeaderText() {
        return getState().headerText;
    }

}
