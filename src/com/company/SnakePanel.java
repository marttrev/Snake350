package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakePanel extends JPanel implements ActionListener {

    private GameBoard gameBoard;
    private int gridWidth;
    private int gridHeight;
    private int pixelDivision = 32;
    private int tickRate = 100;
    private Timer timer;
    private boolean active;
    private Action northAction;
    private Action southAction;
    private Action eastAction;
    private Action westAction;

    public SnakePanel() {
        // Create backend instance
        gameBoard = new GameBoard();

        // Generate bounds
        gridWidth = (gameBoard.getXPixels() + 1) * pixelDivision;
        gridHeight = (gameBoard.getYPixels() + 1) * pixelDivision;

        // Setup input
        northAction = new NorthAction();
        southAction = new SouthAction();
        eastAction = new EastAction();
        westAction = new WestAction();

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "northAction");
        this.getActionMap().put("northAction", northAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "southAction");
        this.getActionMap().put("southAction", southAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "westAction");
        this.getActionMap().put("westAction", westAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "eastAction");
        this.getActionMap().put("eastAction", eastAction);

        // Fit everything within bounds
        setPreferredSize(new Dimension(gridWidth, gridHeight));

        // Start game
        active = true;
        timer = new Timer(tickRate, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphic(graphics);
    }

    public void graphic(Graphics graphics) {
        if (active) {
            // Draw food on screen
            graphics.setColor(Color.orange);
            graphics.fillRect(gameBoard.getFoodXCoord()* pixelDivision, gameBoard.getFoodYCoord()* pixelDivision, pixelDivision, pixelDivision);

            // Draw snake on screen
            SnakeNode snake = gameBoard.getHead();
            graphics.setColor(Color.BLUE);
            while (snake != null) {
                graphics.fillRect(snake.getXCoord()* pixelDivision, snake.getYCoord()* pixelDivision, pixelDivision, pixelDivision);
                graphics.setColor(Color.BLACK);
                snake = snake.getNext();
            }

            // Generate gridlines, if desired
            for (int i = 0; i < gridHeight/pixelDivision; i++) {
                graphics.drawLine(i * pixelDivision, 0, i * pixelDivision, gridHeight);
                graphics.drawLine(0, i * pixelDivision, gridWidth, i * pixelDivision);
            }
        } else {
            lose();
        }
    }

    private void lose() {
        timer.stop();
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Your game is over!");
        System.exit(0);
    }

    private void win() {
        timer.stop();
        JFrame frame = new JFrame("Congratulations!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Congratulations! You have won!");
        System.exit(0);
    }

    public void actionPerformed (ActionEvent event) {
        if (active) {
            gameBoard.moveSnake();
            active = !gameBoard.isDead();
            if (gameBoard.hasWon()) {
                win();
            }
        }
        repaint();
    }

    public class NorthAction extends AbstractAction {
        public void actionPerformed(ActionEvent event) {
            gameBoard.getHead().setDirection(0);
        }
    }

    public class SouthAction extends AbstractAction {
        public void actionPerformed(ActionEvent event) {
            gameBoard.getHead().setDirection(2);
        }
    }

    public class WestAction extends AbstractAction {
        public void actionPerformed(ActionEvent event) {
            gameBoard.getHead().setDirection(3);
        }
    }

    public class EastAction extends AbstractAction {
        public void actionPerformed(ActionEvent event) {
            gameBoard.getHead().setDirection(1);
        }
    }
}
