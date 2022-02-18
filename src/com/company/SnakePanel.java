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
    // Temporary, will likely be accessed by method later:
    private int foodXCoord;
    private int foodYCoord;

    public SnakePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphic(graphics);
    }

    public void graphic(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(getFoodXCoord(), getFoodYCoord(), DIVISION, DIVISION);
    }

    // Temporary, will be found in other class later
    private int getFoodXCoord() {
        foodXCoord = 768;
        return foodXCoord;
    }

    private int getFoodYCoord() {
        foodYCoord = 768;
        return foodYCoord;
    }





    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

        }

    }
}
