package edu.project2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Generator {
    public static Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        Stack<Cell> stack = new Stack();
        HashMap<Coordinate, Boolean> used = new HashMap<>();
        stack.add(maze.grid[0][0]);
        Cell currentCell = maze.grid[0][0];
        while (!stack.empty()) {
            int currentX = currentCell.coordinate.col();
            int currentY = currentCell.coordinate.row();
            ArrayList<Cell> available = new ArrayList<>();
            if (currentX != 0) {
                if (!used.getOrDefault(new Coordinate(currentX - 1, currentY), false)) {
                    available.add(maze.grid[currentX - 1][currentY]);
                }
            }
            if (currentY != 0) {
                if (!used.getOrDefault(new Coordinate(currentX, currentY - 1), false)) {
                    available.add(maze.grid[currentX][currentY - 1]);
                }
            }
            if (currentX != maze.width - 1) {
                if (!used.getOrDefault(new Coordinate(currentX + 1, currentY), false)) {
                    available.add(maze.grid[currentX + 1][currentY]);
                }
            }
            if (currentY != maze.height - 1) {
                if (!used.getOrDefault(new Coordinate(currentX, currentY + 1), false)) {
                    available.add(maze.grid[currentX][currentY + 1]);
                }
            }
            if (available.size() == 0) {
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
            maze.grid[x1][y1].rightWall = false;
            maze.grid[x2][y2].leftWall = false;
        }
        if (x1 - x2 == 1 & y1 == y2) {
            maze.grid[x1][y1].leftWall = false;
            maze.grid[x2][y1].rightWall = false;
        }
        if (x1 == x2 && y2 - y1 == 1) {
            maze.grid[x1][y1].bottomWall = false;
            maze.grid[x2][y2].upperWall = false;
        }
        if (x1 == x2 && y1 - y2 == 1) {
            maze.grid[x1][y1].upperWall = false;
            maze.grid[x2][y2].bottomWall = false;
        }
    }
}
