package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

import java.awt.*;

public class Brick extends Rectangle {
    private double ROW_HEIGHT = 200;
    /**
     * Constructor to create the rectangle object and initialize its instance variables.
     * The default creates a rectangle at position x,y with the specified width and height.
     * The rectangle is drawn with a 1 pixel black stroke outline by default.
     *
     * @param x      position
     * @param y      position
     * @param width
     * @param height
     */
    public Brick(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setFillColor(new Color((int)(Math.random() * 0x1000000)));
        this.setFilled(true);
        this.setStroked(false);
        this.setStrokeColor(new Color(0,0,0));
        this.setStroked(true);
    }

}
