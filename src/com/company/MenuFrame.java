package com.company;

import javax.swing.JFrame;

public final class MenuFrame extends JFrame {

    public MenuFrame() {
        setTitle("Menu");
        add(new MenuPanel());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
