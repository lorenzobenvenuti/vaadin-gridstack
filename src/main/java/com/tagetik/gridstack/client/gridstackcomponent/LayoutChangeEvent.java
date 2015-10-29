package com.tagetik.gridstack.client.gridstackcomponent;

import com.google.gwt.event.shared.GwtEvent;

/**
 * The event fired when the item layout changes
 * @author lorenzob
 *
 */
public class LayoutChangeEvent extends GwtEvent<LayoutChangeHandler> {

	public static final Type<LayoutChangeHandler> TYPE = new Type<LayoutChangeHandler>();
	
	@Override
	protected void dispatch(LayoutChangeHandler handler) {
		handler.onLayoutChange(this);
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LayoutChangeHandler> getAssociatedType() {
		return TYPE;
	}
	
}
