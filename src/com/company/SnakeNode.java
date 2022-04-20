package com.company;

/**
 * Provides a data structure for holding the location of the
 * player character (the snake) at any given time in the Snake
 * game.
 *
 * @author Lucas Champoux, Trevor Martin, Raunak Shahi
 * @version 1.0
 */
public final class SnakeNode {

    /** The X-Coordinate of the body segment of the snake contained
     * within the current SnakeNode. */
    private int xcoord;
    /** The Y-Coordinate of the body segment of the snake contained
     * within the current SnakeNode. */
    private int ycoord;
    /** The direction in which the head of the snake is travelling.
     *  0 is north, 1 is east, 2 is south, 3 is west. */
    private int direction;
    /** The next SnakeNode in the snake's body, heading toward the
     *  tail. */
    private SnakeNode next;
    /** The previous SnakeNode in the snake's body, heading toward
     *  the head. */
    private SnakeNode previous;
    /** States whether the current SnakeNode contains the head. */
    private boolean isHead;
    /** States whether the current SnakeNode contains the tail. */
    private boolean isTail;

    /**
     * A constructor that creates a body segment of the snake, and
     * places its position on the playing field.
     * @param pXcoord The X-Coordinate of the body segment on the
     *               playing field.
     * @param pYcoord The Y-Coordinate of the body segment on the
     *               playing field.
     */
    public SnakeNode(final int pXcoord, final int pYcoord) {
        this.xcoord = pXcoord;
        this.ycoord = pYcoord;
        this.direction = 0;
        isHead = false;
        isTail = false;
    }

    /**
     * A constructor that creates the head or tail segment of the
     * snake, and places its position on the playing field.
     * @param pXcoord The X-Coordinate of the body segment on the
     *               playing field.
     * @param pYcoord The Y-Coordinate of the body segment on the
     *               playing field.
     * @param pIsHead A boolean value determining whether the
     *               segment is the head of the snake (true) or the
     *               tail of the snake (false).
     */
    public SnakeNode(final int pXcoord, final int pYcoord,
                     final boolean pIsHead) {
        this.xcoord = pXcoord;
        this.ycoord = pYcoord;
        this.direction = 0;

        if (pIsHead) {
            this.isHead = true;
            isTail = false;
        } else {
            this.isHead = false;
            isTail = true;
        }
    }

    /**
     * A copy constructor for SnakeNode, cloning the SnakeNode
     * passed in.
     * @param original The SnakeNode designed to be cloned.
     */
    public SnakeNode(final SnakeNode original) {
        this.xcoord = original.xcoord;
        this.ycoord = original.ycoord;
        this.direction = original.direction;
        this.next = original.next;
        this.previous = original.previous;
        this.isHead = original.isHead;
        this.isTail = original.isTail;
    }

    /**
     * Accesses the X-Coordinate of the body segment contained within
     * the SnakeNode.
     * @return The X-Coordinate of the body segment contained within
     * the SnakeNode.
     */
    public int getXCoord() {
        return xcoord;
    }

    /**
     * Accesses the Y-Coordinate of the body segment contained within
     * the SnakeNode.
     * @return The Y-Coordinate of the body segment contained within
     * the SnakeNode.
     */
    public int getYCoord() {
        return ycoord;
    }

    /**
     * Modifies the Y-Coordinate of the body segment contained within
     * the SnakeNode.
     * @param pYcoord The desired new Y-Coordinate.
     */
    public void setYCoord(final int pYcoord) {
        this.ycoord = pYcoord;
    }

    /**
     * Accesses the next body segment in the snake, heading toward the
     * tail.
     * @return The next SnakeNode in the snake, heading toward the tail.
     */
    public SnakeNode getNext() {
        if (next == null) {
            return null;
        }
        SnakeNode copy = new SnakeNode(next);
        return copy;
    }

    /**
     * Accesses the cardinal direction in which the body segment of the snake
     * contained within the SnakeNode is travelling.
     * @return The cardinal direction of travel held within the SnakeNode.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Modifies the cardinal direction in which the body segment of the snake
     * contained within the SnakeNode is travelling.
     * @param pDirection The new direction of travel held within the SnakeNode.
     */
    public void setDirection(final int pDirection) {
        this.direction = pDirection;
    }

    /**
     * Checks if the current SnakeNode contains the head of the snake.
     * @return true if the current SnakeNode contains the head, false otherwise.
     */
    public boolean isHead() {
        return isHead;
    }

    /**
     * Checks if the current SnakeNode contains the tail of the snake.
     * @return true if the current SnakeNode contains the tail, false otherwise.
     */
    public boolean isTail() {
        return isTail;
    }

    /**
     * Changes whether the body segment contained within the current SnakeNode
     * is the tail or not.
     * @param tail true if desired to set as tail, false if desired to set not
     *             as tail.
     */
    public void setTail(final boolean tail) {
        isTail = tail;
    }

    /**
     * Generates a starter snake of three pixels size, built off the top-left
     * corner coordinate, facing rightward in horizontal orientation.
     * @return An array containing the head node at index 0, and the tail node
     *         at index 1, of the newly created snake.
     */
    public static SnakeNode[] generateStartSnake() {
        SnakeNode head = new SnakeNode(2, 0, true);
        head.next = new SnakeNode(head.xcoord - 1, head.ycoord, false);
        head.next.isTail = false;
        SnakeNode tail = new SnakeNode(head.xcoord - 2, head.ycoord, false);
        head.next.next = tail;
        head.next.previous = head;
        tail.previous = head.next;
        head.direction = 1;
        return new SnakeNode[]{head, tail};
    }

    /**
     * Adds a node to the tail of the current snake, then sets that node as
     * the tail.
     */
    public void addTailNode() {
        if (isTail) {
            isTail = false;
            next = new SnakeNode(xcoord, ycoord, false);
            next.isTail = true;
            next.previous = this;
        } else {
            next.addTailNode();
        }
    }

    public void moveSnake() {
        // Move the body of the snake
        SnakeNode tail = next;
        while (!tail.isTail) {
            tail = tail.next;
        }
        moveHelper(tail);

        // Move the head of the snake
        if (direction == 0) {
            ycoord = ycoord - 1;
        } else if (direction == 1) {
            xcoord = xcoord + 1;
        } else if (direction == 2) {
            ycoord = ycoord + 1;
        } else {
            xcoord = xcoord - 1;
        }

    }

    /** Moves the body of the snake with the given tail.
     * @param tail The tail of the snake to be moved.
     */
    private void moveHelper(final SnakeNode tail) {
        SnakeNode node = tail;
        while (!node.isHead) {
            node.xcoord = node.previous.xcoord;
            node.ycoord = node.previous.ycoord;
            node = node.previous;
        }
    }
}
