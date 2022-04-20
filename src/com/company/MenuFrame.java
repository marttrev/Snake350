package com.company;

import javax.swing.*;

public class MenuFrame extends JFrame {
    private MenuPanel panel = new MenuPanel(this);
    public MenuFrame () {

        setTitle("Menu");
        add(panel);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
