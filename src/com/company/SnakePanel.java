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
    // The following variables are temporary, will be found in other classes later
    private SnakeNode head;
    private SnakeNode second;
    private SnakeNode tail;

    public SnakePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphic(graphics);
    }

    public void graphic(Graphics graphics) {
        // Draw food on screen
        graphics.setColor(Color.BLACK);
        graphics.fillRect(getFoodXCoord()*DIVISION, getFoodYCoord()*DIVISION, DIVISION, DIVISION);

        // Draw snake on screen
        /* This call is temporary */ generateSnake();
        SnakeNode snake = getSnakeHead();
        while (snake != null) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(snake.getXcoord()*DIVISION, snake.getYcoord()*DIVISION, DIVISION, DIVISION);
            snake = snake.getNext();
        }
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

        }

    }
}
