package com.tagetik.gridstack;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.vaadin.util.ReflectTools;

/**
 * Listener of a {@link GridstackLayoutChangeEvent}
 * @author lorenzob
 *
 */
public interface GridstackLayoutChangeListener extends Serializable {

    public static final Method ON_LAYOUT_CHANGE_METHOD = ReflectTools
            .findMethod(GridstackLayoutChangeListener.class, "onLayoutChange",
                    GridstackLayoutChangeEvent.class);

    /**
     * Invoked when the user changes the grid layout
     * @param event
     */
    void onLayoutChange(GridstackLayoutChangeEvent event);

}
