package com.tagetik.gridstack.client.gridstackcomponent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.tagetik.gridstack.GridstackItemComponent;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.ui.AbstractSingleComponentContainerConnector;
import com.vaadin.shared.ui.Connect;

/**
 * The connector associated to a grid item
 * @author lorenzob
 *
 */
@SuppressWarnings("serial")
@Connect(GridstackItemComponent.class)
public class GridstackItemConnector extends AbstractSingleComponentContainerConnector {

    @Override
    protected Widget createWidget() {
        return GWT.create(GridstackItemWidget.class);
    }
    
    @Override
    public GridstackItemWidget getWidget() {
        return (GridstackItemWidget) super.getWidget();
    }
    
    @Override
    public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
        getWidget().setContent(getContentWidget());
    }

    @Override
    public GridstackItemState getState() {
        return (GridstackItemState) super.getState();
    }
    
    @Override
    public void updateCaption(ComponentConnector connector) {
        // nop
    }
    
}
