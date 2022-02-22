package com.company;


public enum BoardState {
    empty, occupiedBySnake, occupiedByFood, wall
}

class Board {
    private static boolean emptyCell = getemptyCell();
    private static boolean occupiedsnakeCell = getoccupiedsnakCell();
    private static boolean occupiedfoodCell = getoccupiedfoodCell();
    private static boolean wallCell = getwallCell();

    //returns ture if the cell is occipied by the wall
    private static boolean getwallCell() {
        BoardState wallCell = BoardState.wall;
        return false;
    }

    //returns true is the board is occupied by the the food
    private static boolean getoccupiedfoodCell() {
        BoardState foodCell = BoardState.occupiedByFood;
        return false;
    }

    //return true is the cell is occupied by the snake
    private static boolean getoccupiedsnakCell() {
        BoardState snakeCell = BoardState.occupiedBySnake;
        return false;

    }
    //return ture if the cell is empty
    private static boolean getemptyCell() {
        BoardState emptyCell = BoardState.empty;
        return false;
    }

}
