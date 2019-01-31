package edu.macalester.comp124.breakout;

import comp124graphics.CanvasWindow;
import comp124graphics.Ellipse;

import java.awt.*;

public class Ball extends Ellipse {

    public static final int BALL_RADIUS = 20;
    private int dx = 4; //increment on ball's x-axis coordinate
    private int dy = 4; //increment on ball's y-axis coordinate

    /**
     * Constructor to create the ellipse object and initialize its instance variables.
     * The default creates an ellipse at position x,y with a bounding rectangle of width and height.
     * The ellipse is drawn with a 1 pixel black stroke outline by default.
     *
     * @param x      position
     * @param y      position
     */
    public Ball(double x, double y) {
        super(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2); //constructor sets a default value for position, width, and length
        this.setWidthAndHeight(BALL_RADIUS * 2, BALL_RADIUS * 2);
        this.setFillColor(new Color(0,0,0));
        this.setFilled(true);
    }

    /**
     * Method animate takes in a canvas and increments x and y with respect to the constraints of canvas.
     * If x and y reach the canvas constraints, the increment becomes negative, otherwise ball goes forward.
     * @param canvas
     */
    public void animate(CanvasWindow canvas) {
        double x = getX();
        double y = getY();

        if (x < 0 || x > canvas.getWidth() - 2 * BALL_RADIUS) {
            dx = -dx;
        }
        if (y < 0) {
            dy = -dy;
        }
        //if y out of bounds, restart game
        x = x + dx;
        y = y + dy;

        setPosition(x,y);
    }

    /**
     * Checks whether ball falls below the bottom of the canvas.
     * @param canvas
     * @return
     */
    public boolean ballBelow(CanvasWindow canvas) {
        if (getY() > canvas.getHeight() - 2 * BALL_RADIUS) {
            return true;
        } return false;
    }

    /**
     * Checks intersection at all four corners of the ball, determining if object intersected is of Brick or Paddle type.
     * If object intersected is identified as Brick, it is removed.
     * @param canvas
     * @param paddle
     */
    public void checkIntersect(CanvasWindow canvas, Paddle paddle) {
        double x = getX();
        double y = getY();

        if (canvas.getElementAt(x,y) != null) { // processes could be a little less repetitive

            if (canvas.getElementAt(x,y) instanceof Brick){
                bounceOff(canvas, x, y);
                canvas.remove(canvas.getElementAt(x,y));
            } else {
                bouncePaddle(paddle);
            }
        }

        if (canvas.getElementAt(x+getWidth(),y) != null) {

            if (canvas.getElementAt(x+getWidth(),y) instanceof Brick){
                bounceOff(canvas, x+getWidth(), y);
                canvas.remove(canvas.getElementAt(x+getWidth(),y));
            } else {
                bouncePaddle(paddle);
            }
        }

        if (canvas.getElementAt(x, y+getHeight()) != null) {

            if (canvas.getElementAt(x,y+getHeight()) instanceof Brick){
                bounceOff(canvas, x, y+getHeight());
                canvas.remove(canvas.getElementAt(x,y+getHeight()));
            } else {
                bouncePaddle(paddle);
            }
        }


        if (canvas.getElementAt(x+getWidth(), y+getHeight()) != null) {

            if (canvas.getElementAt(x+getWidth(),y+getHeight()) instanceof Brick){
                bounceOff(canvas, x+getWidth(), y+getHeight());
                canvas.remove(canvas.getElementAt(x+getWidth(),y+getHeight()));
            } else {
                bouncePaddle(paddle);
            }
        }

    }

    /**
     * Method sets trajectory for ball depending on the position it hits object at.
     * Specifically interacts with Brick objects.
     * @param canvas
     * @param x
     * @param y
     */
    public void bounceOff(CanvasWindow canvas, double x, double y){

        double ballX = getX();
        double ballY = getY();

        double objectX = canvas.getElementAt(x,y).getPosition().getX();

        if (x >= objectX + canvas.getElementAt(x,y).getBounds().getWidth()/2){
            dy = -dy;
        } else {
            dx = -dx;
            dy = -dy;
        }

        ballX = ballX + dx;
        ballY = ballY + dy;

        setPosition(ballX,ballY);
    }

    /**
     * Method sets trajectory for ball depending on the position it hits paddle at.
     * @param paddle
     */
    public void bouncePaddle(Paddle paddle){
        double x = getX();
        double y = getY();

        double ballCenter = (getX() + getWidth())/2;

        double paddleCenter = (paddle.getX() + paddle.getWidth())/2;

        if (ballCenter == paddleCenter){
            dx = 0;
            dy = -Math.abs(dy);
        } if (ballCenter < paddleCenter) {
            if (dx == 0) {
                dx = 4;
                dy = -Math.abs(dy);
            } else {
                dx = -dx;
                dy = -Math.abs(dy);}
        } if (ballCenter > paddleCenter){
            dy = -Math.abs(dy);
        }

        x = x + dx;
        y = y + dy;

        setPosition(x,y);
    }

}




