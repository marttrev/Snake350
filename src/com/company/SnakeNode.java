package com.company;

import java.util.Objects;

public class SnakeNode {

    private int xcoord;
    private int ycoord;
    private SnakeNode next;
    private SnakeNode previous;

    public SnakeNode(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public SnakeNode getNext() {
        return next;
    }

    public void setNext(SnakeNode next) {
        this.next = next;
    }

    public SnakeNode getPrevious() {
        return previous;
    }

    public void setPrevious(SnakeNode previous) {
        this.previous = previous;
    }
}
