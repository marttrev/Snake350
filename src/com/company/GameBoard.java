package com.company;

/**
 * Holds the backend game logic for the Snake game, including
 * movement and collision checking.
 *
 * @author Lucas Champoux, Trevor Martin, Raunak Shahi
 * @version 1.0
 */
public class GameBoard {
    /** The length of the snake to begin the game. */
    private static final int START_LENGTH = 3;
    /** The current X-Coordinate of the food. */
    private int foodXCoord;
    /** THe current Y-Coordinate of the food. */
    private int foodYCoord;
    /** The SnakeNode that contains the head of the snake. */
    private SnakeNode head;
    /** The SnakeNode that contains the tail of the snake. */
    private SnakeNode tail;
    /** The current number of SnakeNodes in the snake. */
    private int snakeLength;
    /** The number of pixels - 1 in the X direction on the
     *  playing field. */
    private int xpixels;
    /** The number of pixels - 1 in the Y direction on the
     *  playing field. */
    private int ypixels;

    /**
     * Constructor. Creates a new GameBoard of the specified dimensions.
     * @param pXpixels One less than the number of pixels in the x direction
     *                desired for the GameBoard (ex. 0 is one pixel)
     * @param pYpixels One less than the number of pixels in the y direction
     *                desired for the GameBoard (ex. 0 is one pixel)
     */
    public GameBoard(final int pXpixels, final int pYpixels) {
        xpixels = pXpixels;
        ypixels = pYpixels;
        head = new SnakeNode(2, 0, true);
        head.setNext(new SnakeNode(1, 0));
        tail = new SnakeNode(0, 0, false);
        head.getNext().setNext(tail);
        head.getNext().setPrevious(head);
        tail.setPrevious(head.getNext());
        head.setDirection(1);
        snakeLength = START_LENGTH;

        generateFood();
    }

    /**
     * Moves each body segment of the snake by one pixel.
     */
    public void moveSnake() {
        // Used for appending to snake if necessary
        int tailX = tail.getXCoord();
        int tailY = tail.getYCoord();

        tail.setXCoord(tail.getPrevious().getXCoord());
        tail.setYCoord(tail.getPrevious().getYCoord());

        SnakeNode snake = tail.getPrevious();

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

    /**
     * Checks if the snake moving into its current position has resulted
     * in its death.
     * @return true if the snake is dead, false otherwise.
     */
    public boolean isDead() {
        // Check horizontal
        if (head.getXCoord() < 0 || head.getXCoord() > xpixels) {
            return true;
        }

        // Check vertical
        if (head.getYCoord() < 0 || head.getYCoord() > ypixels) {
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

    /**
     * Checks if the snake covers the area of the entire board, thus
     * winning the game.
     * @return true if the snake is the covering the entire board, false
     *         otherwise.
     */
    public boolean hasWon() {
        if (snakeLength == (xpixels + 1) * (ypixels + 1)) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the snake, in its current position, can eat the food in
     * its current position.
     * @param tailX The X-Coordinate of the tail of the snake in its current
     *              position, used to extend the snake should the need arise.
     * @param tailY The Y-Coordinate of the tail of the snake in its current
     *              position, used to extend the snake should the need arise.
     */
    public void checkEaten(final int tailX, final int tailY) {
        if (head.getXCoord() == foodXCoord && head.getYCoord() == foodYCoord) {
            tail.setTail(false);
            tail.setNext(new SnakeNode(tailX, tailY, false));
            tail.getNext().setPrevious(tail);
            tail = tail.getNext();
            snakeLength++;

            generateFood();
        }
    }

    /**
     * Accesses the X-Coordinate of the food in its current position.
     * @return The X-Coordinate of the food in its current position.
     */
    public int getFoodXCoord() {
        return foodXCoord;
    }

    /**
     * Accesses the Y-Coordinate of the food in its current position.
     * @return The Y-Coordinate of the food in its current position.
     */
    public int getFoodYCoord() {
        return foodYCoord;
    }

    /**
     * Modifies the X-Coordinate of the food to that of the parameter
     * pFoodXCoord.
     * @param pFoodXCoord The desired new X-Coordinate for the food.
     */
    public void setFoodXCoord(final int pFoodXCoord) {
        this.foodXCoord = pFoodXCoord;
    }

    /**
     * Modifies the Y-Coordinate of the food to that of the parameter
     * pFoodYCoord.
     * @param pFoodYCoord The desired new Y-Coordinate for the food.
     */
    public void setFoodYCoord(final int pFoodYCoord) {
        this.foodYCoord = pFoodYCoord;
    }

    /**
     * Accesses the SnakeNode containing the head of the snake.
     * @return The SnakeNode containing the head of the snake.
     */
    public SnakeNode getHead() {
        SnakeNode copy = new SnakeNode(head);
        return copy;
    }

    /**
     * Modifies the direction in which the snake is travelling.
     */
    public void setHeadDirection(int direction) {
        head.setDirection(direction);
    }

    /**
     * Accesses the SnakeNode containing the tail of the snake.
     * @return The SnakeNode containing the tail of the snake.
     */
    public SnakeNode getTail() {
        SnakeNode copy = new SnakeNode(tail);
        return copy;
    }

    /**
     * Accesses the number of pixels in the X direction on the GameBoard.
     * @return The number of pixels in the X direction on the GameBoard - 1.
     */
    public int getXpixels() {
        return xpixels;
    }

    /**
     * Accesses the number of pixels in the Y direction on the GameBoard.
     * @return The number of pixels in the Y direction on the GameBoard - 1.
     */
    public int getYpixels() {
        return ypixels;
    }

    /**
     * Generates a new set of food coordinates to replace the previous food
     * coordinates.
     */
    private void generateFood() {
        // Generate coordinates
        foodXCoord = (int) (Math.random() * xpixels);
        foodYCoord = (int) (Math.random() * ypixels);

        // Ensure no collision with snake head
        if (head.getXCoord() == foodXCoord && head.getYCoord() == foodYCoord) {
            foodXCoord = (foodXCoord + 1) % xpixels;
        }
    }

}
