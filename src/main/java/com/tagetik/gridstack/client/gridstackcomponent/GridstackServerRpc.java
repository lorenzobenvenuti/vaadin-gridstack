package com.tagetik.gridstack.client.gridstackcomponent;

import java.util.Map;

import com.tagetik.gridstack.GridstackComponent;
import com.vaadin.shared.communication.ServerRpc;

/**
 * Server RPC for the {@link GridstackComponent}
 * @author lorenzob
 *
 */
public interface GridstackServerRpc extends ServerRpc {

    /**
     * Invoked when the layout changes
     * @param values
     */
	void onLayoutChange(Map<String, GridstackItemData> data);

}
