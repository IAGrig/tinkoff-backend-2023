package edu.project2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Maze maze;
        maze = Generator.generate(6, 8);
        ArrayList<Coordinate> path = Solver.solve(maze, new Coordinate(0, 0), new Coordinate(3, 3));
        System.out.println(Renderer.render(maze));
    }
}
