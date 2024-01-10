package edu.project2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Generator {
    private Generator() {
    }

    public static Maze generate(int height, int width) {
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException();
        }
        Maze maze = new Maze(height, width);
        Stack<Cell> stack = new Stack<>();
        HashMap<Coordinate, Boolean> used = new HashMap<>();
        stack.add(maze.grid[0][0]);
        Cell currentCell = maze.grid[0][0];
        while (!stack.empty()) {
            int currentX = currentCell.coordinate.col();
            int currentY = currentCell.coordinate.row();
            ArrayList<Cell> available = new ArrayList<>();
            if (currentX != 0) {
                if (!used.getOrDefault(new Coordinate(currentX - 1, currentY), false)) {
                    available.add(maze.grid[currentY][currentX - 1]);
                }
            }
            if (currentY != 0) {
                if (!used.getOrDefault(new Coordinate(currentX, currentY - 1), false)) {
                    available.add(maze.grid[currentY - 1][currentX]);
                }
            }
            if (currentX != maze.width - 1) {
                if (!used.getOrDefault(new Coordinate(currentX + 1, currentY), false)) {
                    available.add(maze.grid[currentY][currentX + 1]);
                }
            }
            if (currentY != maze.height - 1) {
                if (!used.getOrDefault(new Coordinate(currentX, currentY + 1), false)) {
                    available.add(maze.grid[currentY + 1][currentX]);
                }
            }
            if (available.isEmpty()) {
                currentCell = stack.pop();
                continue;
            }
            used.put(currentCell.coordinate, true);
            int nextCellIndex = (int) (Math.random() * available.size());
            Cell nextCell = available.get(nextCellIndex);
            breakWalls(maze, currentCell.coordinate, nextCell.coordinate);
            stack.push(nextCell);
            currentCell = nextCell;
        }

        return maze;
    }

    private static void breakWalls(Maze maze, Coordinate coordinate1, Coordinate coordinate2) {
        int x1 = coordinate1.col();
        int y1 = coordinate1.row();
        int x2 = coordinate2.col();
        int y2 = coordinate2.row();
        if (x2 - x1 == 1 && y1 == y2) {
            maze.grid[y1][x1].rightWall = false;
            maze.grid[y2][x2].leftWall = false;
        }
        if (x1 - x2 == 1 & y1 == y2) {
            maze.grid[y1][x1].leftWall = false;
            maze.grid[y2][x2].rightWall = false;
        }
        if (x1 == x2 && y2 - y1 == 1) {
            maze.grid[y1][x1].bottomWall = false;
            maze.grid[y2][x2].upperWall = false;
        }
        if (x1 == x2 && y1 - y2 == 1) {
            maze.grid[y1][x1].upperWall = false;
            maze.grid[y2][x2].bottomWall = false;
        }
    }
}
