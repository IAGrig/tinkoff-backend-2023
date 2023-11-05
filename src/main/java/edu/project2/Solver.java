package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class Solver {
    public static ArrayList<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        ArrayList<Coordinate> path = new ArrayList<>();
        HashMap<Coordinate, Coordinate> parents = new HashMap<>();
        HashMap<Coordinate, Boolean> used = new HashMap<>();
        ArrayDeque<Coordinate> queue = new ArrayDeque<>();
        Coordinate currentCoordinate;
        queue.add(start);
        while (!queue.isEmpty()) {
            currentCoordinate = queue.poll();
            used.put(currentCoordinate, true);

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
        HashMap<Coordinate, Boolean> used,
        Coordinate coordinate
    ) {
        ArrayList<Coordinate> neighbours = new ArrayList<>();
        int currentX = coordinate.col();
        int currentY = coordinate.row();

        if (currentX != 0 && maze.grid[currentX][currentY].leftWall) {
            if (!used.getOrDefault(maze.grid[currentX - 1][currentY], false)) {
                neighbours.add(new Coordinate(currentX - 1, currentY));
            }
        }
        if (currentY != 0 && maze.grid[currentX][currentY].upperWall) {
            if (!used.getOrDefault(maze.grid[currentX][currentY - 1], false)) {
                neighbours.add(new Coordinate(currentX, currentY - 1));
            }
        }
        if (currentX != maze.width - 1 && maze.grid[currentX][currentY].rightWall) {
            if (!used.getOrDefault(maze.grid[currentX + 1][currentY], false)) {
                neighbours.add(new Coordinate(currentX + 1, currentY));
            }
        }
        if (currentX != maze.height - 1 && maze.grid[currentX][currentY].bottomWall) {
            if (!used.getOrDefault(maze.grid[currentX][currentY + 1], false)) {
                neighbours.add(new Coordinate(currentX, currentY + 1));
            }
        }
        return neighbours;
    }

    private static ArrayList<Coordinate> buildPath(
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
        return (ArrayList<Coordinate>) path.reversed();
    }
}
