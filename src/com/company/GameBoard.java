package com.company;

public class GameBoard {
    private int foodXCoord;
    private int foodYCoord;
    private SnakeNode head;
    private SnakeNode tail;
    /* In a later release, these will vary based on user input. */
    private int XPixels = 23;
    private int YPixels = 23;

    public GameBoard() {
        head = new SnakeNode(2, 0);
        head.setHead(true);
        head.setNext(new SnakeNode(1, 0));
        tail = new SnakeNode(0, 0);
        tail.setTail(true);
        head.getNext().setNext(tail);
        head.getNext().setPrevious(head);
        tail.setPrevious(head.getNext());
        head.setDirection(1);

        foodXCoord = 4;
        foodYCoord = 4;
    }

    public void moveSnake() {
        SnakeNode snake = getTail();

        while (!snake.isHead()) {
            snake.setXCoord(snake.getPrevious().getXCoord());
            snake.setYCoord(snake.getPrevious().getYCoord());
            snake = snake.getPrevious();
        }

        // Move the head of the snake
        int headDirection = snake.getDirection();
        if (headDirection == 0) {
            snake.setYCoord(snake.getYCoord() - 1);
        } else if (headDirection == 1) {
            snake.setXCoord(snake.getXCoord() + 1);
        } else if (headDirection == 2) {
            snake.setYCoord(snake.getYCoord() + 1);
        } else {
            snake.setXCoord(snake.getXCoord() - 1);
        }
    }

    public int getFoodXCoord() {
        return foodXCoord;
    }

    public int getFoodYCoord() {
        return foodYCoord;
    }

    public SnakeNode getHead() {
        return head;
    }

    public SnakeNode getTail() {
        return tail;
    }

    public int getXPixels() {
        return XPixels;
    }

    public int getYPixels() {
        return YPixels;
    }

    private void generateFood() {
        // Generate coordinates
        foodXCoord = (int) (Math.random() * XPixels);
        foodYCoord = (int) (Math.random() * YPixels);

        // Ensure no collision with snake head
        if (head.getXCoord() == foodXCoord && head.getYCoord() == foodYCoord) {
            foodXCoord = (foodXCoord + 1) % XPixels;
        }
    }
}
