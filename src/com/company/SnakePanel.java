package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakePanel extends JPanel {

    private int gridWidth = getGridWidth();
    private int gridHeight = getGridHeight();
    private int pixelDivision = 32;
    private int delay = 500;
    private boolean active = false;
    // The following variables are temporary, will be found in other classes later
    private SnakeNode head;
    private SnakeNode second;
    private SnakeNode tail;
    private int foodXCoord;
    private int foodYCoord;

    public SnakePanel() {
        setPreferredSize(new Dimension(gridWidth, gridHeight));

    }

    public void startGame() {
        newFood();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphic(graphics);
    }

    public void graphic(Graphics graphics) {
        // Draw food on screen
        graphics.setColor(Color.BLACK);
        graphics.fillRect(getFoodXCoord()* pixelDivision, getFoodYCoord()* pixelDivision, pixelDivision, pixelDivision);

        // Draw snake on screen
        /* This call is temporary */ generateSnake();
        SnakeNode snake = getSnakeHead();
        while (snake != null) {
            if (snake.isHead()) {
                graphics.setColor(Color.BLUE);
            } else {
                graphics.setColor(Color.BLACK);
            }
            graphics.fillRect(snake.getXcoord()* pixelDivision, snake.getYcoord()* pixelDivision, pixelDivision, pixelDivision);
            snake = snake.getNext();
        }
    }

    // These private, one-line methods are temporary, will be found in other classes later
    private int getFoodXCoord() {
        return foodXCoord;
    }

    private int getFoodYCoord() {
        return foodYCoord;
    }

    private int getGridWidth() {
        return 768;
    }

    private int getGridHeight() {
        return 768;
    }

    private int newFood() {
        return 0;
    }

    private void generateSnake() {
        head = new SnakeNode(4, 4);
        second = new SnakeNode(4, 5);
        tail = new SnakeNode(5, 5);

        head.setNext(second);
        second.setPrevious(head);
        second.setNext(tail);
        tail.setPrevious(second);
    }

    private SnakeNode getSnakeHead() {
        return head;
    }

    private SnakeNode getSnakeTail() {
        return tail;
    }





    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            repaint();
        }

    }
}
