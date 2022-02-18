package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakePanel extends JPanel {

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 1024;
    private static final int DIVISION = 32;
    private static final int AREA = ((WIDTH * HEIGHT) / DIVISION);

    public SnakePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }





    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

        }

    }
}
