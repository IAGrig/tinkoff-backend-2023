package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Solver {
    public static Coordinate[] solve(Maze maze, Coordinate start, Coordinate end) {
        if (maze == null || start == null || end == null) {
            throw new IllegalArgumentException();
        }
        Coordinate[] path = new Coordinate[0];
        HashMap<Coordinate, Coordinate> parents = new HashMap<>();
        boolean[][] used = new boolean[maze.height][maze.width];
        ArrayDeque<Coordinate> queue = new ArrayDeque<>();
        Coordinate currentCoordinate;
        queue.add(start);
        while (!queue.isEmpty()) {
            currentCoordinate = queue.poll();
            used[currentCoordinate.row()][currentCoordinate.col()] = true;

            if (currentCoordinate.equals(end)) {
                path = buildPath(start, end, parents);
                break;
            }

            ArrayList<Coordinate> neighbours = getNeighbours(maze, used, currentCoordinate);
            for (Coordinate coordinate : neighbours) {
                queue.add(coordinate);
                parents.put(coordinate, currentCoordinate);
            }
        }
        return path;
    }

    private static ArrayList<Coordinate> getNeighbours(
        Maze maze,
        boolean[][] used,
        Coordinate coordinate
    ) {
        ArrayList<Coordinate> neighbours = new ArrayList<>();
        int currentX = coordinate.col();
        int currentY = coordinate.row();

        if (currentX != 0 && !maze.grid[currentY][currentX].leftWall) {
            if (!used[currentY][currentX - 1]) {
                neighbours.add(maze.grid[currentY][currentX - 1].coordinate);
            }
        }
        if (currentY != 0 && !maze.grid[currentY][currentX].upperWall) {
            if (!used[currentY - 1][currentX]) {
                neighbours.add(maze.grid[currentY - 1][currentX].coordinate);
            }
        }
        if (currentX != maze.width - 1 && !maze.grid[currentY][currentX].rightWall) {
            if (!used[currentY][currentX + 1]) {
                neighbours.add(maze.grid[currentY][currentX + 1].coordinate);
            }
        }
        if (currentY != maze.height - 1 && !maze.grid[currentY][currentX].bottomWall) {
            if (!used[currentY + 1][currentX]) {
                neighbours.add(maze.grid[currentY + 1][currentX].coordinate);
            }
        }
        return neighbours;
    }

    private static Coordinate[] buildPath(
        Coordinate start,
        Coordinate end,
        HashMap<Coordinate, Coordinate> parents
    ) {
        ArrayList<Coordinate> path = new ArrayList<>();
        Coordinate currentCoordinate = end;
        while (!currentCoordinate.equals(start)) {
            path.add(currentCoordinate);
            currentCoordinate = parents.get(currentCoordinate);
        }
        path.add(start);
        return path.reversed().toArray(new Coordinate[0]);
    }
}
