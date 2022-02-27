package com.company;

public class GameBoard {
    private int foodXCoord;
    private int foodYCoord;
    private SnakeNode head;
    private SnakeNode tail;
    private int snakeLength;
    /* In a later release, these will vary based on user input. */
    private int XPixels = 23;
    private int YPixels = 23;

    public GameBoard() {
        head = new SnakeNode(2, 0, true);
        head.setNext(new SnakeNode(1, 0));
        tail = new SnakeNode(0, 0, false);
        head.getNext().setNext(tail);
        head.getNext().setPrevious(head);
        tail.setPrevious(head.getNext());
        head.setDirection(1);
        snakeLength = 3;

        generateFood();
    }

    // Constructor exclusively for testing.
    public GameBoard(boolean testMode) {
        XPixels = 3;
        YPixels = 0;

        head = new SnakeNode(3, 0, true);
        head.setNext(new SnakeNode(2, 0));
        tail = new SnakeNode(1, 0, false);
        head.getNext().setNext(tail);
        head.getNext().setPrevious(head);
        tail.setPrevious(head.getNext());
        head.setDirection(1);
        snakeLength = 3;

        if (testMode) {
            foodXCoord = 3;
            foodYCoord = 0;
        } else {
            foodXCoord = 0;
            foodYCoord = 0;
        }
    }

    public void moveSnake() {
        SnakeNode snake = getTail();

        // Used for appending to snake if necessary
        int tailX = snake.getXCoord();
        int tailY = snake.getYCoord();

        // Move the body of the snake
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

        // Append if necessary
        checkEaten(tailX, tailY);
    }

    public boolean isDead() {
        // Check horizontal
        if (head.getXCoord() < 0 || head.getXCoord() > XPixels) {
            return true;
        }

        // Check vertical
        if (head.getYCoord() < 0 || head.getYCoord() > YPixels) {
            return true;
        }

        // Check self
        int headX = head.getXCoord();
        int headY = head.getYCoord();
        SnakeNode snake = getHead().getNext();

        while (snake != null) {
            if (snake.getXCoord() == headX && snake.getYCoord() == headY) {
                return true;
            }
            snake = snake.getNext();
        }
        return false;
    }

    public boolean hasWon() {
        if (snakeLength == (XPixels + 1) * (YPixels + 1)) {
            return true;
        }

        return false;
    }

    public void checkEaten(int tailX, int tailY) {
        if (head.getXCoord() == foodXCoord && head.getYCoord() == foodYCoord) {
            tail.setTail(false);
            tail.setNext(new SnakeNode(tailX, tailY, false));
            tail.getNext().setPrevious(tail);
            tail = tail.getNext();
            snakeLength++;

            generateFood();
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
