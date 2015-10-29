package com.tagetik.gridstack.client.gridstackcomponent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * An item of the grid
 * @author lorenzo
 *
 */
public class GridstackItemWidget extends Composite {

    interface Style extends CssResource {
        
        /**
         * The style of the item title
         * @return
         */
        String title();
        
        /**
         * The style of the header
         * @return
         */
        String header();
        
    } 
    
    interface GridstackItemUiBinder extends UiBinder<HTMLPanel, GridstackItemWidget> {}
    
    private static final String DEFAULT_BACKGROUND_COLOR = "#ffccff";
    private static final String DEFAULT_HEADER_TEXT_COLOR = "#000000";
    
    private static GridstackItemUiBinder uiBinder = GWT.create(GridstackItemUiBinder.class);

    @UiField DivElement contentDiv;
    
    @UiField DivElement headerDiv;
    
    @UiField DivElement titleDiv;
    
    @UiField SimplePanel contentPanel;
    
    @UiField Style style;

    private String backgroundColor;
    private String headerTextColor;

    private String headerText;

    public GridstackItemWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
        setHeaderTextColor(DEFAULT_HEADER_TEXT_COLOR);
    }
    
    /**
     * Sets the title of the gridstack item
     * @param headerText
     */
    public void setHeaderText(String headerText) {
        this.headerText = headerText;
        titleDiv.setInnerText(headerText == null ? "" : SafeHtmlUtils.htmlEscape(headerText));
    }
    
    /**
     * Returns the title of the Gridstack item
     * @return
     */
    public String getHeaderText() {
        return headerText;
    }

    /**
     * Sets the content of the Gridstack item
     * @param widget
     */
    public void setContent(Widget widget) {
        contentPanel.setWidget(widget);
    }
    
    /**
     * Sets the item background color 
     * @param color
     */
    public void setBackgroundColor(String color) {
        this.backgroundColor = color;
        contentDiv.getStyle().setBackgroundColor(color);
    }
    
    /**
     * Returns the item background color
     * @return
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }
    
    /**
     * Sets the header text color
     * @param color
     */
    public void setHeaderTextColor(String color) {
        headerTextColor = color;
        headerDiv.getStyle().setColor(color);
    }

    /**
     * Returns the header text color
     * @return
     */
    public String getHeaderTextColor() {
        return headerTextColor;
    }

}
