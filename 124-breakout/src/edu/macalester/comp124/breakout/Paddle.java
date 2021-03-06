package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

import java.awt.*;
import java.awt.event.MouseMotionListener;

public class Paddle extends Rectangle {

    private double x;
    private double y;
    public MouseMotionListener ml;

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
    public Paddle(double x, double y, double width, double height) {
        super(350,800,120,10);
        this.setFillColor(new Color(0,0,0));
        this.setFilled(true);
        this.setStroked(false);

    }

}