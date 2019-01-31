package edu.macalester.comp124.breakout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PaddleListener implements MouseMotionListener {

    private Paddle paddle;
    private int x;

    /**
     * Class implements a MouseMotionListener for the Paddle class. The Paddle object follows the mouse on the screen.
     */
    public PaddleListener(Paddle paddle) {
        this.paddle = paddle;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        paddle.setPosition(x-paddle.getWidth()/2, paddle.getY());
    }
}
