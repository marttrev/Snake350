package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakePanel extends JPanel {

    private static final int WIDTH = getGridWidth();
    private static final int HEIGHT = getGridHeight();
    private static final int DIVISION = 32;
    private static final int AREA = ((WIDTH * HEIGHT) / DIVISION);

    public SnakePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphic(graphics);
    }

    public void graphic(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(getFoodXCoord()*DIVISION, getFoodYCoord()*DIVISION, DIVISION, DIVISION);
    }

    // These private gets are temporary, will be found in other classes later
    private int getFoodXCoord() {
        return 23;
    }

    private int getFoodYCoord() {
        return 23;
    }

    private static int getGridWidth() {
        return 768;
    }

    private static int getGridHeight() {
        return 768;
    }





    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

        }

    }
}
