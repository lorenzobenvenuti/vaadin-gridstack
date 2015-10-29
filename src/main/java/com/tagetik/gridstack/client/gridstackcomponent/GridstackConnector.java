package com.tagetik.gridstack.client.gridstackcomponent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.tagetik.gridstack.GridstackComponent;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.Connect;

/**
 * The connector glueing together a {@link GridstackWidget} and a {@link GridstackComponent}
 * @author lorenzob
 *
 */
@SuppressWarnings("serial")
@Connect(GridstackComponent.class)
public class GridstackConnector extends AbstractComponentContainerConnector {

    GridstackServerRpc rpc = RpcProxy.create(GridstackServerRpc.class, this);

	public GridstackConnector() {
	    // when the grid layout changes the positions and sizes of the items
	    // are sent to the server
	    getWidget().addLayoutChangeHandler(new LayoutChangeHandler() {
            
            @Override
            public void onLayoutChange(LayoutChangeEvent event) {
                rpc.onLayoutChange(getData());
            }
            
        });
	}
	
	/**
	 * Retrieves the state for the items
	 * @return
	 */
	private Map<String, GridstackItemData> getData() {
	    Map<String, GridstackItemData> values = new HashMap<>();
	    for (ComponentConnector connector : getChildComponents()) {
	        values.put(connector.getConnectorId(), getWidget().getData((GridstackItemWidget) connector.getWidget()));
	    }
	    return values;
    }

    @Override
	protected Widget createWidget() {
		return GWT.create(GridstackWidget.class);
	}

	@Override
	public GridstackWidget getWidget() {
		return (GridstackWidget) super.getWidget();
	}

	@Override
	public GridstackState getState() {
		return (GridstackState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
	    getWidget().startBatch();
	    try {
    	    updateItemsSizeAndPosition();
	    } finally {
	        getWidget().endBatch();
	    }
	}

	/**
	 * Updates size and position of different items
	 */
    private void updateItemsSizeAndPosition() {
        Map<Connector, GridstackItemData> data = getState().itemData;
        for (ComponentConnector child : getChildComponents()) {
            GridstackItemData itemData = data.get(child);
            getWidget().resize(
                (GridstackItemWidget) child.getWidget(), 
                itemData.getItemWidth(), 
                itemData.getItemHeight()
            );
            if (!itemData.isAutoPosition()) {
                getWidget().move(
                    (GridstackItemWidget) child.getWidget(), 
                    itemData.getX(), 
                    itemData.getY()
                );
            }
        }
    }
	
	@Override
	public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
	    getWidget().startBatch();
	    try {
	        createOrDestroyChildren();
	    } finally {
	        getWidget().endBatch();
	    }
	}

	/**
	 * Adds/removes Widgets depending on the added/removed Components 
	 */
    private void createOrDestroyChildren() {
        List<Widget> removedWidgets = new LinkedList<Widget>();
		List<Widget> newWidgets = new LinkedList<Widget>();
		Map<Widget, GridstackItemData> states = new HashMap<Widget, GridstackItemData>();
		for (ComponentConnector connector : getChildComponents()) {
			Widget widget = connector.getWidget();
			states.put(widget, getState().itemData.get(connector));
			newWidgets.add(widget);
		}
		List<Widget> currWidgets = new  LinkedList<Widget>();
		Iterator<Widget> wI = getWidget().iterator();
		while (wI.hasNext()) {
		    Widget widget = wI.next();
			currWidgets.add(widget);
			if (!newWidgets.contains(widget)) {
				removedWidgets.add(widget);
			}
		}
		for (Widget widget : removedWidgets) {
			getWidget().removeItem((GridstackItemWidget) widget);
		}
		for (Widget widget : newWidgets) {
			if (!currWidgets.contains(widget)) {
			    GridstackItemData data = states.get(widget);
                getWidget().addItem(
				    (GridstackItemWidget) widget,
				    data.getX(),
				    data.getY(),
				    data.getItemWidth(),
				    data.getItemHeight(),
				    data.isAutoPosition()
				);
			}
		}
    }

	@Override
	public void updateCaption(ComponentConnector connector) {
		// nop
	}

}

