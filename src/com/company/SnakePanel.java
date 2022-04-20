package com.company;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     *  representation of the GameBoard. */
    private static int pixelDivision;
    /** The time taken by each turn before the game refreshes its
     *  state. */
    private static int tickRate;
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
    /** Represents the north direction. */
    private static final int NORTH = 0;
    /** Represents the east direction. */
    private static final int EAST = 1;
    /** Represents the south direction. */
    private static final int SOUTH = 2;
    /** Represents the west direction. */
    private static final int WEST = 3;
    /** Represents the number of pixels - 1 in the X direction. */
    private static int xPixels = 23;
    /** Represents the number of pixels - 1 in the Y direction. */
    private static int yPixels = 23;
    /** Keeps track of current score. */
    private int score = 0;
    /** The color of the background. */
    private Color lColor;
    /** The color of the snake body. */
    private Color sColor;
    /** The color of the snake head. */
    private Color sHeadColor;
    /** The color of the food. */
    private Color fColor;
    /** The color of the background. */
    private Color bgColor;

    /**
     * Constructor. Generates a fixed-sized GameBoard, initializes input
     * interface, and begins the game.
     */
    public SnakePanel(int diff, int level, Color lColor, Color sColor, Color sHeadColor, Color fColor, Color bgColor) {
        // Set colors
        this.lColor = lColor;
        this.sColor = sColor;
        this.sHeadColor = sHeadColor;
        this.fColor = fColor;
        this.bgColor = bgColor;

        // Set speed
        if (diff == 0) {
            tickRate = 200;
        } else if (diff == 1) {
            tickRate = 100;
        } else if (diff == 2) {
            tickRate = 50;
        } else {
            tickRate = 25;
        }

        // Set board size
        if (level == 0) {
            pixelDivision = 32;
            xPixels = 15;
            yPixels = 7;
        } else if (level == 1) {
            pixelDivision = 32;
            xPixels = 15;
            yPixels = 15;
        } else if (level == 2) {
            pixelDivision = 24;
            xPixels = 23;
            yPixels = 23;
        } else if (level == 3) {
            pixelDivision = 24;
            xPixels = 31;
            yPixels = 31;
        } else {
            pixelDivision = 24;
            xPixels = 41;
            yPixels = 31;
        }

        // Create backend instance
        gameBoard = new GameBoard(xPixels, yPixels);

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
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphic(graphics);
    }

    /**
     * Modifies the graphics on the screen to give a representation
     * of the current state of the game. Terminates the program
     * upon a loss.
     * @param graphics The graphics desired to be modified.
     */
    public void graphic(final Graphics graphics) {
        if (active) {
            // Draw background color
            graphics.setColor(bgColor);
            graphics.fillRect(0, 0, gridWidth, gridHeight);

            // Draw food on screen
            graphics.setColor(fColor);
            graphics.fillRect(gameBoard.getFoodXCoord() * pixelDivision,
                    gameBoard.getFoodYCoord() * pixelDivision, pixelDivision,
                    pixelDivision);

            // Draw snake on screen
            SnakeNode snake = gameBoard.getHead();
            graphics.setColor(sHeadColor);
            while (snake != null) {
                graphics.fillRect(snake.getXCoord() * pixelDivision,
                        snake.getYCoord() * pixelDivision, pixelDivision,
                        pixelDivision);
                graphics.setColor(sColor);
                snake = snake.getNext();
            }

            // Generate gridlines, if desired
            graphics.setColor(lColor);
            for (int i = 0; i < gridWidth / pixelDivision; i++) {
                graphics.drawLine(i * pixelDivision, 0,
                        i * pixelDivision, gridHeight);
            }

            for (int i = 0; i < gridHeight / pixelDivision; i++) {
                graphics.drawLine(0, i * pixelDivision,
                        gridWidth, i * pixelDivision);
            }
        } else {
            lose();
            System.exit(0);
        }
    }

    /**
     * When the game is lost, produces a message alerting the player.
     * @return true upon successful execution.
     */
    public boolean lose() {
        timer.stop();
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Your game is over!\nYour score is " + score + ".");
        return true;
    }

    /**
     * When the game is won, produces a message alerting the player.
     * @return true upon successful execution.
     */
    public boolean win() {
        timer.stop();
        JFrame frame = new JFrame("Congratulations!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Congratulations! You have won!\nYour score is " + score + ".");
        return true;
    }

    /**
     * Ensures that each tick of the clock is synced with both an update
     * of the game logic and an update of the GUI. Terminates the program
     * upon a win.
     * @param event A change in the clock state.
     */
    public void actionPerformed(final ActionEvent event) {
        if (active) {
            if (gameBoard.moveSnake()) {
                score++;
            }
            active = !gameBoard.isDead();
            if (gameBoard.hasWon()) {
                win();
                System.exit(0);
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
        public void actionPerformed(final ActionEvent event) {
            gameBoard.setHeadDirection(NORTH);
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
        public void actionPerformed(final ActionEvent event) {
            gameBoard.setHeadDirection(SOUTH);
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
        public void actionPerformed(final ActionEvent event) {
            gameBoard.setHeadDirection(WEST);
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
        public void actionPerformed(final ActionEvent event) {
            gameBoard.setHeadDirection(EAST);
        }
    }
}
