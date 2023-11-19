package edu.project2;

public class Main {
    public static void main(String[] args) {
        final int mazeHeight = 50;
        final int mazeWidth = 50;
        final int pathStartX = 0;
        final int pathStartY = 0;
        final int pathEndX = mazeHeight - 1;
        final int pathEndY = mazeWidth - 1;

        Maze maze;
        maze = Generator.generate(mazeHeight, mazeWidth);
        Coordinate[] path =
            Solver.solve(maze, new Coordinate(pathStartX, pathStartY), new Coordinate(pathEndX, pathEndY));
        System.out.println(Renderer.render(maze, path));
    }
}
