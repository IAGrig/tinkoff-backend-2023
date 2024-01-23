package edu.project2;

public final class Maze {
    public final int height;
    public final int width;
    public final Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[y][x] = new Cell(x, y);
            }
        }
    }
}
