package com.company;

import javax.swing.*;

public class SnakeFrame extends JFrame {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 1024;

    public SnakeFrame() {
        setTitle("Snake (Beta)");
        add(new SnakePanel());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
}
