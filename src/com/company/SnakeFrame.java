package com.company;

import javax.swing.*;

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
    public SnakeFrame() {
        setTitle("Snake 1.0");
        add(new SnakePanel());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
