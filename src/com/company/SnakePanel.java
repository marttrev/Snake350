package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Controls the front-end of the Snake game, including GUI and
 * user controls.
 *
 * @author Lucas Champoux, Trevor Martin, Raunak Shahi
 * @version 1.0
 */
public class SnakePanel extends JPanel implements ActionListener {

    /** The GameBoard responsible for the game logic. */
    private GameBoard gameBoard;
    /** The width of the graphical representation of the game. */
    private int gridWidth;
    /** The height of the graphical representation of the game. */
    private int gridHeight;
    /** The size, X and Y, of a single pixel on the graphical
     * representation of the GameBoard. */
    private int pixelDivision = 32;
    /** The time taken by each turn before the game refreshes its
     * state. */
    private int tickRate = 100;
    /** A timer that refreshes the game state. */
    private Timer timer;
    /** States whether the game is currently running. */
    private boolean active;
    /** Receives input for moving the snake north. */
    private Action northAction;
    /** Receives input for moving the snake south. */
    private Action southAction;
    /** Receives input for moving the snake east. */
    private Action eastAction;
    /** Receives input for moving the snake west. */
    private Action westAction;

    /**
     * Constructor. Generates a fixed-sized GameBoard, initializes input
     * interface, and begins the game.
     */
    public SnakePanel() {
        // Create backend instance
        gameBoard = new GameBoard(23, 23);

        // Generate bounds
        gridWidth = (gameBoard.getXpixels() + 1) * pixelDivision;
        gridHeight = (gameBoard.getYpixels() + 1) * pixelDivision;

        // Setup input
        northAction = new NorthAction();
        southAction = new SouthAction();
        eastAction = new EastAction();
        westAction = new WestAction();

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "northAction");
        this.getActionMap().put("northAction", northAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "southAction");
        this.getActionMap().put("southAction", southAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "westAction");
        this.getActionMap().put("westAction", westAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "eastAction");
        this.getActionMap().put("eastAction", eastAction);

        // Fit everything within bounds
        setPreferredSize(new Dimension(gridWidth, gridHeight));

        // Start game
        active = true;
        timer = new Timer(tickRate, this);
        timer.start();
    }

    /**
     * Draws the graphics on the screen.
     * @param graphics The graphics desired to be drawn on the screen.
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphic(graphics);
    }

    /**
     * Modifies the graphics on the screen to give a representation
     * of the current state of the game.
     * @param graphics The graphics desired to be modified.
     */
    public void graphic(Graphics graphics) {
        if (active) {
            // Draw food on screen
            graphics.setColor(Color.orange);
            graphics.fillRect(gameBoard.getFoodXCoord()* pixelDivision, gameBoard.getFoodYCoord()* pixelDivision, pixelDivision, pixelDivision);

            // Draw snake on screen
            SnakeNode snake = gameBoard.getHead();
            graphics.setColor(Color.BLUE);
            while (snake != null) {
                graphics.fillRect(snake.getXCoord()* pixelDivision, snake.getYCoord()* pixelDivision, pixelDivision, pixelDivision);
                graphics.setColor(Color.BLACK);
                snake = snake.getNext();
            }

            // Generate gridlines, if desired
            for (int i = 0; i < gridHeight/pixelDivision; i++) {
                graphics.drawLine(i * pixelDivision, 0, i * pixelDivision, gridHeight);
                graphics.drawLine(0, i * pixelDivision, gridWidth, i * pixelDivision);
            }
        } else {
            lose();
        }
    }

    /**
     * When the game is lost, produces a message alerting the player
     * and terminates the program.
     */
    private void lose() {
        timer.stop();
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Your game is over!");
        System.exit(0);
    }

    /**
     * When the game is won, produces a message alerting the player
     * and terminates the program.
     */
    private void win() {
        timer.stop();
        JFrame frame = new JFrame("Congratulations!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Congratulations! You have won!");
        System.exit(0);
    }

    /**
     * Ensures that each tick of the clock is synced with both an update
     * of the game logic and an update of the GUI.
     * @param event A change in the clock state.
     */
    public void actionPerformed (ActionEvent event) {
        if (active) {
            gameBoard.moveSnake();
            active = !gameBoard.isDead();
            if (gameBoard.hasWon()) {
                win();
            }
        }
        repaint();
    }

    /**
     * An action, prompted by user input, that will move the player
     * character's (snake's) face in the north direction.
     *
     * @author Lucas Champoux, Trevor Martin, Raunak Shahi
     */
    public class NorthAction extends AbstractAction {
        /**
         * Receives input from the player, then moves the facing
         * direction of the snake north.
         * @param event A press of the desired key on the keyboard.
         */
        public void actionPerformed(ActionEvent event) {
            gameBoard.getHead().setDirection(0);
        }
    }

    /**
     * An action, prompted by user input, that will move the player
     * character's (snake's) face in the south direction.
     *
     * @author Lucas Champoux, Trevor Martin, Raunak Shahi
     */
    public class SouthAction extends AbstractAction {
        /**
         * Receives input from the player, then moves the facing
         * direction of the snake south.
         * @param event A press of the desired key on the keyboard.
         */
        public void actionPerformed(ActionEvent event) {
            gameBoard.getHead().setDirection(2);
        }
    }

    /**
     * An action, prompted by user input, that will move the player
     * character's (snake's) face in the west direction.
     *
     * @author Lucas Champoux, Trevor Martin, Raunak Shahi
     */
    public class WestAction extends AbstractAction {
        /**
         * Receives input from the player, then moves the facing
         * direction of the snake west.
         * @param event A press of the desired key on the keyboard.
         */
        public void actionPerformed(ActionEvent event) {
            gameBoard.getHead().setDirection(3);
        }
    }

    /**
     * An action, prompted by user input, that will move the player
     * character's (snake's) face in the east direction.
     *
     * @author Lucas Champoux, Trevor Martin, Raunak Shahi
     */
    public class EastAction extends AbstractAction {
        /**
         * Receives input from the player, then moves the facing
         * direction of the snake east.
         * @param event A press of the desired key on the keyboard.
         */
        public void actionPerformed(ActionEvent event) {
            gameBoard.getHead().setDirection(1);
        }
    }
}
