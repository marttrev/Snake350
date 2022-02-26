package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakePanel extends JPanel implements ActionListener {

    private GameBoard gameBoard;
    private int gridWidth;
    private int gridHeight;
    private int pixelDivision = 32;
    private int tickRate = 100;
    private Timer timer;
    private boolean active;

    public SnakePanel() {
        gameBoard = new GameBoard();
        gridWidth = (gameBoard.getXPixels() + 1) * pixelDivision;
        gridHeight = (gameBoard.getYPixels() + 1) * pixelDivision;
        setPreferredSize(new Dimension(gridWidth, gridHeight));
        active = true;
        timer = new Timer(tickRate, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphic(graphics);
    }

    public void graphic(Graphics graphics) {
        // Draw food on screen
        graphics.setColor(Color.BLACK);
        graphics.fillRect(gameBoard.getFoodXCoord()* pixelDivision, gameBoard.getFoodYCoord()* pixelDivision, pixelDivision, pixelDivision);

        // Draw snake on screen
        SnakeNode snake = gameBoard.getHead();
        while (snake != null) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(snake.getXcoord()* pixelDivision, snake.getYcoord()* pixelDivision, pixelDivision, pixelDivision);
            snake = snake.getNext();
        }

        // Generate gridlines, if desired
        for (int i = 0; i < gridHeight/pixelDivision; i++) {
            graphics.drawLine(i * pixelDivision, 0, i * pixelDivision, gridHeight);
            graphics.drawLine(0, i * pixelDivision, gridWidth, i * pixelDivision);
        }
    }

    public void actionPerformed (ActionEvent event) {

    }
}
