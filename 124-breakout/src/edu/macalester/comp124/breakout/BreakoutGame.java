package edu.macalester.comp124.breakout;


import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main program for the breakout game.
 *
 */
public class BreakoutGame extends CanvasWindow {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 1000;
    private double rowHeight = 200;
    private Ball ball;
    private int round;


    public BreakoutGame() {
        super("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        resetGame();
    }

    /**
     * Method resets game and sets the round count back to 0.
     */
    public void resetGame() {
        round = 0;
        newRound();
    }

    /**
     * Method creates a new Ball object at given coordinates and moves ball until three rounds have passed. When three
     * rounds have passed, the user is asked to choose between option to reset game or not.
     */
    public void newRound() {
        ball = new Ball(350, 600);
        while (round < 3) {
            round++;
            move();
        }
        userInput();
        checkWin();
    }

    /**
     * Asks user to reset game or to close window.
     */
    public void userInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Type 'yes' to reset the game, 'no' to exit game: ");
        String input = scan.next();
        if (input.equals("yes")){
            resetGame();
        } else {
            closeWindow();
        }
    }

    /**
     * Checks whether or not the user has won a game. Method unsuccessful.
     * @return
     */
    public void checkWin() {
        List<GraphicsObject> list = new ArrayList<>();

        for (int x = 0; x < getWidth(); x++){
            for (int y = 0; y < getHeight(); y++){
                list.add(getElementAt(x,y));
            }
        }
        System.out.println(list);

        for (GraphicsObject element: list){
            if (!(element instanceof Brick)){
                System.out.println("CONGRATULATIONS! :)");
                userInput();
            }
        }
    }

    /**
     * Main method:
     */
    public void move() {
        add(ball);
        Paddle paddle = new Paddle(350,500,100,10);
        add(paddle);
        PaddleListener pl = new PaddleListener(paddle);
        addMouseMotionListener(pl);
        createBrickWall();
        while (!ball.ballBelow(this)) { //while round not over: if there is at least one brick on the screen/ball above paddle
            ball.animate(this);
            ball.checkIntersect(this, paddle); //method to intersect paddle and intersect brick
            pause(5);
        }
            System.out.println("Round number: " + round);
            removeAll();
            newRound();
    }

    /**
     * Creates a single row of Brick objects.
     */
    public void createBrickRow() {
        Brick firstBrick = new Brick(0, rowHeight, 100, 40);
        for (int i = 0; i < 8; i++) {
            if (!(i * firstBrick.getWidth() > this.getWidth())) {
                add(new Brick(i * firstBrick.getWidth(), rowHeight, firstBrick.getWidth(), firstBrick.getHeight()));
            }
        }
    }

    /**
     * Iterates the creation of a single row of Brick objects to make a Brick Wall.
     */
    public void createBrickWall(){
        for (int i = 0; i < 4; i++){
            rowHeight = 40 + rowHeight;
            createBrickRow();
        } rowHeight = 200;
    }

    public static void main(String[] args){
        BreakoutGame prog = new BreakoutGame();
    }

}
