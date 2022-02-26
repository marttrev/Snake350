package com.company;

public class SnakeNode {

    private int xcoord;
    private int ycoord;
    private int direction; //0 = North, 1 = East, 2 = South, 3 = West
    private SnakeNode next;
    private SnakeNode previous;
    private boolean isHead;
    private boolean isTail;


    public SnakeNode(int xcoord, int ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.direction = 0;
        isHead = false;
        isTail = false;
    }

    public SnakeNode(int xcoord, int ycoord, boolean mode){
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.direction = 0;

        if(mode){
            isHead = true;
            isTail = false;
        }
        else{
            isHead = false;
            isTail = true;
        }
    }

    public SnakeNode(SnakeNode original){
        this.xcoord = original.getXCoord();
        this.ycoord = original.getYCoord();
        this.direction = original.getDirection();
        this.next = original.getNext();
        this.previous = original.getPrevious();
        this.isHead = this.isHead();
        this.isTail = this.isTail();
    }

    public int getXCoord() {
        return xcoord;
    }

    public void setXCoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYCoord() {
        return ycoord;
    }

    public void setYCoord(int ycoord) {
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public boolean isTail() {
        return isTail;
    }

    public void setTail(boolean tail) {
        isTail = tail;
    }

    public void linkNodeBehind(SnakeNode newNode){
        this.next = newNode;
        this.next.previous = this;
    }

    public void linkNodeFront(SnakeNode newNode){
        newNode.next = this;
        this.previous.next = newNode;
    }

    public void addNewSegment(){
        SnakeNode currentNode = this;
        do {
            currentNode = currentNode.next;
        } while (!currentNode.isTail());

        SnakeNode newNode = new SnakeNode(currentNode);

        currentNode.setNext(newNode);
        newNode.setPrevious(currentNode);

        currentNode.setTail(false);
        newNode.setTail(true);


    }
}
