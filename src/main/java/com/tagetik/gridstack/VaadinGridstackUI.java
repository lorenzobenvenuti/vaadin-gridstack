package com.tagetik.gridstack;

import java.util.Date;
import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import com.tagetik.gridstack.client.gridstackcomponent.GridstackItemData;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 */
@Theme("vaadin-gridstack")
@Widgetset("com.tagetik.gridstack.VaadinGridstackWidgetset")
public class VaadinGridstackUI extends UI {

    private Label logsLabel;
    
    @WebServlet(urlPatterns = "/*", name = "VaadinGridstackUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = VaadinGridstackUI.class, productionMode = false)
    public static class VaadinGridstackUIServlet extends VaadinServlet {
    }
    
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSizeFull();
        setContent(layout);
        
        final GridstackComponent gridstackComponent = new GridstackComponent();
        
        // populate the grid
        
        GridstackItemComponent item1 = new GridstackItemComponent();
        item1.setBackgroundColor("#ffccff");
        item1.setContent(new Label("Hi! I'm a Vaadin Label"));
        gridstackComponent.addItem(item1);
        
        final GridstackItemComponent item2 = new GridstackItemComponent();
        CssLayout group = new CssLayout();
        group.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        Button button = new Button("+");
        button.setDescription("Increase width");
        button.addClickListener(new ClickListener() {
            
            @Override
            public void buttonClick(ClickEvent event) {
                gridstackComponent.setItemWidth(item2, gridstackComponent.getItemWidth(item2) + 1);
            }

        });
        group.addComponent(button);
        button = new Button("-");
        button.setDescription("Decrease width");
        button.addClickListener(new ClickListener() {
            
            @Override
            public void buttonClick(ClickEvent event) {
                int itemWidth = gridstackComponent.getItemWidth(item2);
                gridstackComponent.setItemWidth(item2, Math.max(1, itemWidth - 1));
            }

        });
        group.addComponent(button);
        item2.setContent(group);
        item2.setHeaderText("Change size programmatically");
        item2.setBackgroundColor("#ccffff");
        gridstackComponent.addItem(item2);
        gridstackComponent.setItemWidth(item2, 2);
        gridstackComponent.setItemHeight(item2, 1);

        GridstackItemComponent item3 = new GridstackItemComponent();
        Image image = new Image();
        image.setSource(new ThemeResource("img/gwtcon.png"));
        item3.setContent(image);
        item3.setBackgroundColor("#ffffcc");
        gridstackComponent.addItem(item3);
        gridstackComponent.setItemWidth(item3, 3);
        gridstackComponent.setItemHeight(item3, 2);
        
        GridstackItemComponent item4 = new GridstackItemComponent();
        item4.setHeaderText("ACF Fiorentina players in a Vaadin Grid");
        Grid grid = new Grid();
        grid.setSizeFull();
        grid.addColumn("Name");
        grid.addColumn("Surname");
        grid.addColumn("Number",  Integer.class);
        grid.addRow("Borja", "Valero", 20);
        grid.addRow("Nikola", "Kalinic", 9);
        grid.addRow("Giuseppe", "Rossi", 22);
        item4.setContent(grid);
        gridstackComponent.addItem(item4);
        gridstackComponent.setItemWidth(item4, 3);
        gridstackComponent.setItemHeight(item4, 4);

        // The gridstack component is added to a Panel to handle overflow and scrolling
        Panel panel = new Panel();
        panel.setSizeFull();
        gridstackComponent.setWidth("100%");
        panel.setContent(gridstackComponent);
        layout.addComponent(panel);
        layout.setExpandRatio(panel, 2);
        
        // Log text
        logsLabel = new Label();
        logsLabel.setValue("");
        logsLabel.setContentMode(ContentMode.HTML);
        logsLabel.setWidth("100%");
        panel = new Panel();
        panel.setSizeFull();
        panel.setContent(logsLabel);
        layout.addComponent(panel);
        layout.setExpandRatio(panel, 1);
        
        // Every change to the client side is sent to the server and logged 
        gridstackComponent.addLayoutChangeListener(new GridstackLayoutChangeListener() {
            
            @Override
            public void onLayoutChange(GridstackLayoutChangeEvent event) {
                logTime();
                Iterator<Component> iterator = gridstackComponent.iterator();
                while (iterator.hasNext()) {
                    Component component = iterator.next();
                    GridstackItemData data = gridstackComponent.getState().itemData.get(component);
                    logData(data);
                }
            }
            
        });
        
    }
    
    /**
     * Adds a timestamp to the logs
     */
    private void logTime() {
        StringBuilder sb = new StringBuilder(logsLabel.getValue());
        sb.append("<b>").append(new Date().toString()).append("</b><br/>");
        logsLabel.setValue(sb.toString());
    }
    
    /**
     * Prints a {@link GridstackItemData} representation to the logs
     * @param message
     */
    private void logData(GridstackItemData data) {
        StringBuilder sb = new StringBuilder(logsLabel.getValue());
        sb.append(data.toString()).append("<br/>");
        logsLabel.setValue(sb.toString());
        
    }

}
