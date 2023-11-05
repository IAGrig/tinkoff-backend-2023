package edu.project2;

public class Cell {
    Coordinate coordinate;
    boolean leftWall = true;
    boolean rightWall = true;
    boolean upperWall = true;
    boolean bottomWall = true;

    public Cell(int x, int y) {
        this.coordinate = new Coordinate(x, y);
    }
}
