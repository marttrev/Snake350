package com.company;

import javax.swing.*;

public class SnakeFrame extends JFrame {

    public SnakeFrame() {
        setTitle("Snake (Beta)");
        add(new SnakePanel());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
