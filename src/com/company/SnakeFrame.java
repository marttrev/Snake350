package com.company;

import javax.swing.JFrame;
import java.awt.*;

/**
 * Provides a container for the SnakePanel, allowing for a
 * GUI for the Snake game.
 *
 * @author Lucas Champoux, Trevor Martin, Raunak Shahi
 * @version 1.0
 */
public class SnakeFrame extends JFrame {

    /**
     * Constructor. Creates a frame to hold a SnakePanel,
     * adds a SnakePanel, then displays to the user.
     */
    public SnakeFrame(int diff, int level, Color lColor, Color sColor, Color sHeadColor, Color fColor, Color bgColor) {
        setTitle("Snake 2.0");
        add(new SnakePanel(diff, level, lColor, sColor, sHeadColor, fColor, bgColor));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private SnakeFrame() {}
}
