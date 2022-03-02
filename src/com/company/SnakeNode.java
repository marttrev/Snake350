package com.company;

/**
 * Provides a data structure for holding the location of the
 * player character (the snake) at any given time in the Snake
 * game.
 *
 * @author Lucas Champoux, Trevor Martin, Raunak Shahi
 * @version 1.0
 */
public class SnakeNode {

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
     * @param xcoord The X-Coordinate of the body segment on the
     *               playing field.
     * @param ycoord The Y-Coordinate of the body segment on the
     *               playing field.
     */
    public SnakeNode(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.direction = 0;
        isHead = false;
        isTail = false;
    }

    /**
     * A constructor that creates the head or tail segment of the
     * snake, and places its position on the playing field.
     * @param xcoord The X-Coordinate of the body segment on the
     *               playing field.
     * @param ycoord The Y-Coordinate of the body segment on the
     *               playing field.
     * @param isHead A boolean value determining whether the
     *               segment is the head of the snake (true) or the
     *               tail of the snake (false).
     */
    public SnakeNode(int xcoord, int ycoord, boolean isHead){
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.direction = 0;

        if(isHead){
            this.isHead = true;
            isTail = false;
        }
        else{
            this.isHead = false;
            isTail = true;
        }
    }

    /**
     * A copy constructor for SnakeNode, cloning the SnakeNode
     * passed in.
     * @param original The SnakeNode designed to be cloned.
     */
    public SnakeNode(SnakeNode original){
        this.xcoord = original.getXCoord();
        this.ycoord = original.getYCoord();
        this.direction = original.getDirection();
        this.next = original.getNext();
        this.previous = original.getPrevious();
        this.isHead = original.isHead();
        this.isTail = original.isTail();
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
     * Modifies the X-Coordinate of the body segment contained within
     * the SnakeNode.
     * @param xcoord The desired new X-Coordinate.
     */
    public void setXCoord(int xcoord) {
        this.xcoord = xcoord;
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
     * @param ycoord The desired new Y-Coordinate.
     */
    public void setYCoord(int ycoord) {
        this.ycoord = ycoord;
    }

    /**
     * Accesses the next body segment in the snake, heading toward the
     * tail.
     * @return The next SnakeNode in the snake, heading toward the tail.
     */
    public SnakeNode getNext() {
        return next;
    }

    /**
     * Replaces the next body segment in the snake, heading toward the
     * tail, with the parameter next.
     * @param next The desired next SnakeNode in the snake, heading
     *             toward the tail.
     */
    public void setNext(SnakeNode next) {
        this.next = next;
    }

    /**
     * Accesses the previous body segment in the snake, heading toward
     * the head.
     * @return The previous SnakeNode in the snake, heading toward the head.
     */
    public SnakeNode getPrevious() {
        return previous;
    }

    /**
     * Replaces the previous body segment in the snake, heading toward
     * the head, with the parameter previous.
     * @param previous The desired previous SnakeNode in the snake, heading
     *                 toward the head.
     */
    public void setPrevious(SnakeNode previous) {
        this.previous = previous;
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
     * @param direction The new direction of travel held within the SnakeNode.
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Checks if the current SnakeNode contains the head of the snake.
     * @return true if the current SnakeNode contains the head, false otherwise.
     */
    public boolean isHead() {
        return isHead;
    }

    /**
     * Changes whether the body segment contained within the current SnakeNode
     * is the head or not.
     * @param head true if desired to set as head, false if desired to set not
     *             as head.
     */
    public void setHead(boolean head) {
        isHead = head;
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
    public void setTail(boolean tail) {
        isTail = tail;
    }

}
