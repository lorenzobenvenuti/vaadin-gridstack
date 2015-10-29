package com.tagetik.gridstack.client.gridstackcomponent;

import com.tagetik.gridstack.GridstackItemComponent;
import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.annotations.DelegateToWidget;

/**
 * The state associated to a {@link GridstackItemComponent}
 * @author lorenzob
 *
 */
@SuppressWarnings("serial")
public class GridstackItemState extends AbstractComponentState {

    /**
     * The header text
     */
    @DelegateToWidget
    public String headerText = "";

    /**
     * The item background color
     */
    @DelegateToWidget
    public String backgroundColor = "#ccffcc";
    
    /**
     * The header text color
     */
    @DelegateToWidget
    public String headerTextColor = "#111111";
    
}
