package com.company;

import javax.swing.JFrame;
import java.awt.Color;

/**
 * Provides a container for the SnakePanel, allowing for a
 * GUI for the Snake game.
 *
 * @author Lucas Champoux, Trevor Martin, Raunak Shahi
 * @version 2.0
 */
public final class SnakeFrame extends JFrame {

    /**
     * Constructor. Creates a frame to hold a SnakePanel,
     * adds a SnakePanel, then displays to the user. All
     * parameters are passed into the SnakePanel constructor.
     * @param diff The difficulty level, 0-3, of the game.
     * @param level The number, 0-4, of the level to select.
     * @param lColor The color in which to draw the gridlines.
     * @param sColor The color in which to draw the snake body.
     * @param sHeadColor The color in which to draw the snake
     *                   head.
     * @param fColor The color in which to draw the food.
     * @param bgColor The color in which to draw the backgorund.
     */
    public SnakeFrame(final int diff, final int level, final Color lColor,
                      final Color sColor, final Color sHeadColor,
                      final Color fColor, final Color bgColor) {
        setTitle("Snake 2.0");
        add(new SnakePanel(diff, level, lColor,
                sColor, sHeadColor, fColor, bgColor));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private SnakeFrame() {
    }
}
