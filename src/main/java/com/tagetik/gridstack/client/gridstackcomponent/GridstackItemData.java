package com.tagetik.gridstack.client.gridstackcomponent;

import java.io.Serializable;

/**
 * The state of a grid item
 * @author lorenzob
 *
 */
@SuppressWarnings("serial")
public class GridstackItemData implements Serializable {

    private static final int DEFAULT_TILE_WIDTH = 2;
    private static final int DEFAULT_TILE_HEIGHT = 2;

    private int itemWidth = DEFAULT_TILE_WIDTH;
    private int itemHeight = DEFAULT_TILE_HEIGHT;
    private int x;
    private int y;
    private boolean autoPosition = true;
    
    /**
     * Retrieves the item width (in grid units)
     * @return
     */
    public int getItemWidth() {
        return itemWidth;
    }
    /**
     * Sets the item width (in grid units)
     * @param width
     */
    public void setItemWidth(int width) {
        this.itemWidth = width;
    }
    
    /**
     * Retrieves the item height (in grid units)
     * @return
     */
    public int getItemHeight() {
        return itemHeight;
    }
    /**
     * Sets the item height (in grid units)
     * @param height
     */
    public void setItemHeight(int height) {
        this.itemHeight = height;
    }
    
    /**
     * Retrieves the x coordinate (in grid units)
     * @return
     */
    public int getX() {
        return x;
    }
    /**
     * Sets the x coordinate (in grid units)
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieves the y coordinate (in grid units)
     * @return
     */
    public int getY() {
        return y;
    }
    /**
     * Sets the y coordinate (in grid units)
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Tells if the item must be positioned automatically
     * @return
     */
    public boolean isAutoPosition() {
        return autoPosition;
    }
    
    /**
     * Sets the boolean that determines if the tile must be positiones automatically
     * @param autoPosition
     */
    public void setAutoPosition(boolean autoPosition) {
        this.autoPosition = autoPosition;
    }
    
    @Override
    public String toString() {
        return super.toString() + "{" + 
              "width=" + itemWidth + 
            ", height=" + itemHeight + 
            ", x=" + x + 
            ", y=" + y + 
            ", auto=" + autoPosition +
        "}";
    }

}
