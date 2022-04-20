package com.company;

import javax.swing.JFrame;

/**
 * Provides a container for the MenuPanel, allowing for a
 * GUI configurator for the Snake game.
 *
 * @author Lucas Champoux, Trevor Martin, Raunak Shahi
 * @version 1.0
 */
public final class MenuFrame extends JFrame {
    /**
     * Constructor. Creates a frame to hold a MenuPanel,
     * adds a MenuPanel, then displays to the user.
     */
    public MenuFrame() {
        setTitle("Menu");
        add(new MenuPanel());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
