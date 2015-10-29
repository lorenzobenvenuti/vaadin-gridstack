package com.tagetik.gridstack;

import com.vaadin.ui.Component;

/**
 * Event fired when the user changes the layout of the grid
 * @author lorenzob
 *
 */
@SuppressWarnings("serial")
public class GridstackLayoutChangeEvent extends Component.Event {

    public GridstackLayoutChangeEvent(Component source) {
        super(source);
    }

}
