package edu.project2;

public class Cell {
    public Coordinate coordinate;
    public boolean leftWall = true;
    public boolean rightWall = true;
    public boolean upperWall = true;
    public boolean bottomWall = true;

    public Cell(int x, int y) {
        this.coordinate = new Coordinate(x, y);
    }
}
