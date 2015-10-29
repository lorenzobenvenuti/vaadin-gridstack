package com.tagetik.gridstack.client.gridstackcomponent;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.ComplexPanel;

/**
 * GWT port of the Gridstack dynamic grid
 * @author lorenzob
 *
 */
public class GridstackWidget extends ComplexPanel {

    /**
     * Inner class defining the state of an item
     * @author lorenzob
     *
     */
    static class DataJavascriptObject extends JavaScriptObject {
        
        protected  DataJavascriptObject() {}
        
        public native final int getX() /*-{
            return this.x;
        }-*/;
        
        public native final int getY() /*-{
            return this.y;
        }-*/;
        
        public native final int getWidth() /*-{
            return this.width;
        }-*/;
        
        public native final int getHeight() /*-{
            return this.height;
        }-*/;
        
    }

    private final HandlerManager handlerManager = new HandlerManager(this);
    
    public static final String CLASSNAME = "grid-stack";
    
    private boolean initialized;

    private boolean batchUpdating = true;
    
    public GridstackWidget() {
        setElement(Document.get().createDivElement());
        setStyleName(CLASSNAME);
    }
    
    @Override
    protected void onAttach() {
        super.onAttach();
        if (!initialized) {
            initialized = true;
            nativeInitGridstack(getElement());
        }
    }
    
    /**
     * Initializes the Gridstack component
     * @param container
     */
    private native void nativeInitGridstack(Element container) /*-{
        var options = {
            cell_height: 80,
            vertical_margin: 10,
            animate: true,
            always_show_resize_handle: /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent),
            draggable: {
                handle: '.grid-stack-item-header'
            }
        };
        var gs = $wnd.$(container);
        gs.gridstack(options);
        var self = this;
        gs.on('change', function(e, items) {
            self.@com.tagetik.gridstack.client.gridstackcomponent.GridstackWidget::fireLayoutChangeEvent()();
        });
    }-*/;
    
    /**
     * Fires a {@link LayoutChangeEvent}
     */
    private void fireLayoutChangeEvent() {
        if (!batchUpdating) {
            // the LayoutChangeEvent is fired only if the batch update mode
            // is deactivated
            handlerManager.fireEvent(new LayoutChangeEvent());
        }
    }

    /**
     * Adds an handler on the {@link LayoutChangeEvent} 
     * @param handler
     */
    public void addLayoutChangeHandler(LayoutChangeHandler handler) {
        handlerManager.addHandler(LayoutChangeEvent.TYPE, handler);
    }

    /**
     * Starts a batch update of the Gridstack object
     */
    public void startBatch() {
        batchUpdating = true;
        // TODO: the nativeBatchUpdate is commented because it doesn't work
        // when invoking the resize method on multiple items!
        // nativeBatchUpdate(getElement());
    }

    /**
     * Wrapper of the native "batch_update" function
     * @param container
     */
    private native void nativeBatchUpdate(Element container) /*-{
        var grid = $wnd.$(container).data('gridstack');
        grid.batch_update();
    }-*/;
    
    /**
     * Ends a batch update of the Gridstack object
     * @param syncState dice se va lanciato un evento di sincronizzazione
     */
    public void endBatch() {
        // TODO: the commit is commented because it doesn't work
        // when invoking the resize method on multiple items!
        //nativeCommit(getElement());
        batchUpdating = false;
    }

    /**
     * Wrapper of the native "commit" function
     * @param container
     */
    private native void nativeCommit(Element container) /*-{
        var grid = $wnd.$(container).data('gridstack');
        grid.commit();
    }-*/;

    /**
     * Wrapper of the native "add_widget" method
     * @param container
     * @param item
     * @param x
     * @param y
     * @param width
     * @param height
     * @param auto
     */
    private native void nativeAddWidget(
        Element container,
        Element item,
        int x,
        int y,
        int width,
        int height,
        boolean auto
    ) /*-{
        var grid = $wnd.$(container).data('gridstack');
        grid.add_widget($wnd.$(item), x, y, width, height, auto);
    }-*/;

    /**
     * Adds an item to the grid
     * @param widget
     * @param width
     * @param height
     */
    public void addItem(GridstackItemWidget widget, int width, int height) {
        addItem(widget, 0, 0, width, height, true);
    }
    
    /**
     * Adds an item to the grid
     * @param widget
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void addItem(GridstackItemWidget widget, int x, int y, int width, int height) {
        addItem(widget, x, y, width, height, false);
    }
    
    /**
     * Adds an item to the  grid; x and y are ignored if auto is <code>true</code>
     * @param widget
     * @param x
     * @param y
     * @param width
     * @param height
     * @param auto
     */
    public void addItem(GridstackItemWidget widget, int x, int y, int width, int height, boolean auto) {
        nativeAddWidget(
            getElement(),
            widget.getElement(),
            x,
            y,
            width,
            height,
            auto
        );
        add(widget, getElement());
        widget.getElement().getStyle().clearPosition();
    }

    /**
     * Removes an item from the grid
     * @param widget
     */
    public void removeItem(GridstackItemWidget widget) {
        nativeRemoveWidget(getElement(), widget.getElement());
        remove(widget);
    }

    /**
     * Wrapper for the native "remove_widget" method
     * @param container
     * @param item
     */
    private native void nativeRemoveWidget(Element container, Element item) /*-{
        var grid = $wnd.$(container).data('gridstack');
        grid.remove_widget($wnd.$(item), false);
    }-*/;
    
    /**
     * Resizes an item
     * @param widget
     * @param width
     * @param height
     */
    public void resize(GridstackItemWidget widget, int width, int height) {
        nativeResizeWidget(getElement(), widget.getElement(), width, height);
    }

    /**
     * Wrapper of the native "resize" method
     * @param container
     * @param item
     * @param width
     * @param height
     */
    private native void nativeResizeWidget(Element container, Element item, int width, int height) /*-{
        var grid = $wnd.$(container).data('gridstack');
        grid.resize($wnd.$(item), width, height);
    }-*/;
    
    /**
     * Moves an item
     * @param widget
     * @param width
     * @param height
     */
    public void move(GridstackItemWidget widget, int x, int y) {
        nativeMoveWidget(getElement(), widget.getElement(), x, y);
    }
    
    /**
     * Wrapper of the native "move" method
     * @param container
     * @param item
     * @param x
     * @param y
     */
    private native void nativeMoveWidget(Element container, Element item, int x, int y) /*-{
        var grid = $wnd.$(container).data('gridstack');
        grid.move($wnd.$(item), x, y);
    }-*/;

    /**
     * Returns the data associated with a node
     * @param item
     * @return
     */
    public GridstackItemData getData(GridstackItemWidget item) {
        GridstackItemData data = new GridstackItemData();
        DataJavascriptObject nativeData = nativeGetData(item.getElement());
        data.setAutoPosition(false); // Makes sense only when adding items
        data.setItemHeight(nativeData.getHeight());
        data.setItemWidth(nativeData.getWidth());
        data.setX(nativeData.getX());
        data.setY(nativeData.getY());
        return data;
    }
    
    /**
     * Returns a JavaScriptObject that represents the data associated to an item
     * @param element
     * @return
     */
    private native DataJavascriptObject nativeGetData(Element item) /*-{
        var el = $wnd.$(item).first();
        var node = el.data('_gridstack_node');
        return { 
            x: node.x,
            y: node.y,
            width: node.width,
            height: node.height 
        };
    }-*/;
    
}
