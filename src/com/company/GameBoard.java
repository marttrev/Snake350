package com.company;

public class GameBoard {
    private int foodXCoord;
    private int foodYCoord;
    private SnakeNode head;
    /* In a later release, these will vary based on user input. */
    private int XPixels = 23;
    private int YPixels = 23;

    public GameBoard() {
        head = new SnakeNode(2, 0);
        head.setNext(new SnakeNode(1, 0));
        head.getNext().setNext(new SnakeNode(0, 0));
        head.getNext().setPrevious(head);
        head.getNext().getNext().setPrevious(head.getNext());

        foodXCoord = 4;
        foodYCoord = 4;
    }

    public void generateFood() {
            // Generate coordinates
            foodXCoord = (int) (Math.random() * XPixels);
            foodYCoord = (int) (Math.random() * YPixels);

            // Ensure no collision with snake head
            if (head.getXcoord() == foodXCoord && head.getYcoord() == foodYCoord) {
                foodXCoord = (foodXCoord + 1) % XPixels;
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

    public int getXPixels() {
        return XPixels;
    }

    public int getYPixels() {
        return YPixels;
    }

}
