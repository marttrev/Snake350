package com.company;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * Controls the front-end of the Snake game, including GUI and
 * user controls.
 *
 * @author Lucas Champoux, Trevor Martin, Raunak Shahi
 * @version 1.0
 */
public final class SnakePanel extends JPanel implements ActionListener {

    /** The GameBoard responsible for the game logic. */
    private GameBoard gameBoard;
    /** The width of the graphical representation of the game. */
    private int gridWidth;
    /** The height of the graphical representation of the game. */
    private int gridHeight;
    /** The size, X and Y, of a single pixel on the graphical
     *  representation of the GameBoard. */
    private int pixelDivision;
    /** The time taken by each turn before the game refreshes its
     *  state. */
    private int tickRate;
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
    /** Recieves input for resetting the game. */
    private Action resetAction;
    /** Recieves input for pausing the game. */
    private Action pauseAction;
    /** Represents the north direction. */
    private static final int NORTH = 0;
    /** Represents the east direction. */
    private static final int EAST = 1;
    /** Represents the south direction. */
    private static final int SOUTH = 2;
    /** Represents the west direction. */
    private static final int WEST = 3;
    /** The speed of easy difficulty. */
    private static final int EASY_SPEED = 200;
    /** The speed of medium difficulty. */
    private static final int MEDIUM_SPEED = 100;
    /** The speed of hard difficulty. */
    private static final int HARD_SPEED = 50;
    /** The speed of expert difficulty. */
    private static final int EXPERT_SPEED = 25;
    /** The score increment of easy difficulty. */
    private static final int EASY_INCREMENT = 50;
    /** The score increment of medium difficulty. */
    private static final int MEDIUM_INCREMENT = 1000;
    /** The score increment of hard difficulty. */
    private static final int HARD_INCREMENT = 5000;
    /** The score increment of expert difficulty. */
    private static final int EXPERT_INCREMENT = 20000;
    /** The dimensions, [x, y], of the tiny level. */
    private static final int[] TINY_DIMENSIONS = new int[]{15, 7};
    /** The dimensions, [x, y], of the small level. */
    private static final int[] SMALL_DIMENSIONS = new int[]{15, 15};
    /** The dimensions, [x, y], of the medium level. */
    private static final int[] MEDIUM_DIMENSIONS = new int[]{23, 23};
    /** The dimensions, [x, y], of the large level. */
    private static final int[] LARGE_DIMENSIONS = new int[]{31, 31};
    /** The dimensions, [x, y], of the giant level. */
    private static final int[] GIANT_DIMENSIONS = new int[]{41, 31};
    /** The pixel division intended for use with smaller levels. */
    private static final int SMALL_LEVEL_PIXEL_SIZE = 32;
    /** The pixel division intended for use with larger levels. */
    private static final int LARGE_LEVEL_PIXEL_SIZE = 24;
    /** The number representation of the easy difficulty. */
    private static final int EASY = 0;
    /** The number representation of the medium difficulty. */
    private static final int MEDIUM = 1;
    /** The number representation of the hard difficulty. */
    private static final int HARD = 2;
    /** The number representation of the expert difficulty. */
    private static final int EXPERT = 3;
    /** The number representation of the tiny level. */
    private static final int TINY = 0;
    /** The number representation of the small level. */
    private static final int SMALL = 1;
    /** The number representation of the medium level. */
    private static final int MEDIUM_LEVEL = 2;
    /** The number representation of the large level. */
    private static final int LARGE = 3;
    /** The number representation of the giant level. */
    private static final int GIANT = 4;
    /** Represents the number of pixels - 1 in the X direction. */
    private int xPixels;
    /** Represents the number of pixels - 1 in the Y direction. */
    private int yPixels;
    /** Keeps track of current score. */
    private int score;
    /** Keeps track of the increment for the score per food eaten. */
    private int scoreIncrement = 0;
    /** The color of the gridlines. */
    private Color glColor;
    /** The color of the snake body. */
    private Color bodyColor;
    /** The color of the snake head. */
    private Color headColor;
    /** The color of the food. */
    private Color foodColor;
    /** The color of the background. */
    private Color backColor;

    /**
     * Constructor. Generates a fixed-sized GameBoard, initializes input
     * interface, and begins the game.
     * @param diff The difficulty level, 0-3, of the game.
     * @param level The number, 0-4, of the level to select.
     * @param lColor The color in which to draw the gridlines.
     * @param sColor The color in which to draw the snake body.
     * @param sHeadColor The color in which to draw the snake
     *                   head.
     * @param fColor The color in which to draw the food.
     * @param bgColor The color in which to draw the backgorund.
     */
    public SnakePanel(final int diff, final int level, final Color lColor,
                      final Color sColor, final Color sHeadColor,
                      final Color fColor, final Color bgColor) {
        // Set colors
        glColor = lColor;
        bodyColor = sColor;
        headColor = sHeadColor;
        foodColor = fColor;
        backColor = bgColor;

        // Set speed and score increments.
        if (diff == EASY) {
            tickRate = EASY_SPEED;
            scoreIncrement = EASY_INCREMENT;
        } else if (diff == MEDIUM) {
            tickRate = MEDIUM_SPEED;
            scoreIncrement = MEDIUM_INCREMENT;
        } else if (diff == HARD) {
            tickRate = HARD_SPEED;
            scoreIncrement = HARD_INCREMENT;
        } else {
            tickRate = EXPERT_SPEED;
            scoreIncrement = EXPERT_INCREMENT;
        }

        // Set board size
        if (level == TINY) {
            pixelDivision = SMALL_LEVEL_PIXEL_SIZE;
            xPixels = TINY_DIMENSIONS[0];
            yPixels = TINY_DIMENSIONS[1];
        } else if (level == SMALL) {
            pixelDivision = SMALL_LEVEL_PIXEL_SIZE;
            xPixels = SMALL_DIMENSIONS[0];
            yPixels = SMALL_DIMENSIONS[1];
        } else if (level == MEDIUM_LEVEL) {
            pixelDivision = LARGE_LEVEL_PIXEL_SIZE;
            xPixels = MEDIUM_DIMENSIONS[0];
            yPixels = MEDIUM_DIMENSIONS[1];
        } else if (level == LARGE) {
            pixelDivision = LARGE_LEVEL_PIXEL_SIZE;
            xPixels = LARGE_DIMENSIONS[0];
            yPixels = LARGE_DIMENSIONS[1];
        } else {
            pixelDivision = LARGE_LEVEL_PIXEL_SIZE;
            xPixels = GIANT_DIMENSIONS[0];
            yPixels = GIANT_DIMENSIONS[1];
        }

        // Clear score
        score = 0;

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
        resetAction = new ResetAction();
        pauseAction = new PauseAction();

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "northAction");
        this.getActionMap().put("northAction", northAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "southAction");
        this.getActionMap().put("southAction", southAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "westAction");
        this.getActionMap().put("westAction", westAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "eastAction");
        this.getActionMap().put("eastAction", eastAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("R"), "resetAction");
        this.getActionMap().put("resetAction", resetAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("P"), "pauseAction");
        this.getActionMap().put("pauseAction", pauseAction);

        // Fit everything within bounds
        setPreferredSize(new Dimension(gridWidth, gridHeight));

        // Explain the rules
        JFrame frame = new JFrame("Rules");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame,
                "Make the snake eat the food to score points.\n"
                + "Avoid running into walls or yourself to stay alive!\n"
                        + "\n"
                        + "To move, use the arrow keys.\n"
                        + "To reset the game, press the R key.\n"
                        + "To pause the game, press the P key.\n"
                        + "\n"
                        + "Good luck!");

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
            graphics.setColor(backColor);
            graphics.fillRect(0, 0, gridWidth, gridHeight);

            // Draw food on screen
            graphics.setColor(foodColor);
            graphics.fillRect(gameBoard.getFoodXCoord() * pixelDivision,
                    gameBoard.getFoodYCoord() * pixelDivision, pixelDivision,
                    pixelDivision);

            // Draw snake on screen
            SnakeNode snake = gameBoard.getHead();
            graphics.setColor(headColor);
            while (snake != null) {
                graphics.fillRect(snake.getXCoord() * pixelDivision,
                        snake.getYCoord() * pixelDivision, pixelDivision,
                        pixelDivision);
                graphics.setColor(bodyColor);
                snake = snake.getNext();
            }

            // Generate gridlines, if desired
            graphics.setColor(glColor);
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
            ((JFrame) SwingUtilities.getWindowAncestor(this)).setVisible(false);
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
        JOptionPane.showMessageDialog(frame,
                "Your game is over!\nYour score is "
                        + NumberFormat.getInstance().format(score) + ".");
        String highScore = JOptionPane.showInputDialog(frame,
                "Enter your name: ");
        highScore = score + "," + highScore;
        SaveHandler.writeHighScore(highScore);

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
        JOptionPane.showMessageDialog(frame,
                "Congratulations! You have won!\nYour score is "
                        + NumberFormat.getInstance().format(score) + ".");
        String highScore = JOptionPane.showInputDialog(frame,
                "Enter your name: ");
        highScore = score + "," + highScore;
        SaveHandler.writeHighScore(highScore);

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
                score += scoreIncrement;
            }
            active = !gameBoard.isDead();
            if (gameBoard.hasWon()) {
                win();
                ((JFrame) SwingUtilities.getWindowAncestor(
                        this)).setVisible(false);
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
    private class NorthAction extends AbstractAction {
        /**
         * Receives input from the player, then moves the facing
         * direction of the snake north.
         * @param event A press of the desired key on the keyboard.
         */
        public void actionPerformed(final ActionEvent event) {
            if (timer.isRunning()) {
                gameBoard.setHeadDirection(NORTH);
            }
        }
    }

    /**
     * An action, prompted by user input, that will move the player
     * character's (snake's) face in the south direction.
     *
     * @author Lucas Champoux, Trevor Martin, Raunak Shahi
     */
    private class SouthAction extends AbstractAction {
        /**
         * Receives input from the player, then moves the facing
         * direction of the snake south.
         * @param event A press of the desired key on the keyboard.
         */
        public void actionPerformed(final ActionEvent event) {
            if (timer.isRunning()) {
                gameBoard.setHeadDirection(SOUTH);
            }
        }
    }

    /**
     * An action, prompted by user input, that will move the player
     * character's (snake's) face in the west direction.
     *
     * @author Lucas Champoux, Trevor Martin, Raunak Shahi
     */
    private class WestAction extends AbstractAction {
        /**
         * Receives input from the player, then moves the facing
         * direction of the snake west.
         * @param event A press of the desired key on the keyboard.
         */
        public void actionPerformed(final ActionEvent event) {
            if (timer.isRunning()) {
                gameBoard.setHeadDirection(WEST);
            }
        }
    }

    /**
     * An action, prompted by user input, that will move the player
     * character's (snake's) face in the east direction.
     *
     * @author Lucas Champoux, Trevor Martin, Raunak Shahi
     */
    private class EastAction extends AbstractAction {
        /**
         * Receives input from the player, then moves the facing
         * direction of the snake east.
         * @param event A press of the desired key on the keyboard.
         */
        public void actionPerformed(final ActionEvent event) {
            if (timer.isRunning()) {
                gameBoard.setHeadDirection(EAST);
            }
        }
    }

    private class ResetAction extends AbstractAction {
        public void actionPerformed(final ActionEvent event) {
            // Create new backend instance
            if (timer.isRunning()) {
                gameBoard = new GameBoard(xPixels, yPixels);
                score = 0;
            }
        }
    }

    private class PauseAction extends AbstractAction {
        public void actionPerformed(final ActionEvent event) {
            if (timer.isRunning()) {
                timer.stop();
            } else {
                timer.start();
            }
        }
    }
}
