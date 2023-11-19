package edu.project2;

public class Renderer {
    public static String render(Maze maze) {
        StringBuilder string = new StringBuilder();
        for (int y = 0; y < maze.height; y++) {
            string.append("+");
            for (int x = 0; x < maze.width; x++) {
                Cell currentCell = maze.grid[y][x];
                if (currentCell.upperWall) {
                    string.append("---+");
                } else {
                    string.append("   +");
                }
            }
            string.append("\n");
            for (int x = 0; x < maze.width; x++) {
                Cell currentCell = maze.grid[y][x];
                if (currentCell.leftWall) {
                    string.append("|");
                } else {
                    string.append(" ");
                }
                string.append("   ");
            }
            string.append("|");
            string.append("\n");
        }
        string.append('+');
        for (int x = 0; x < maze.width; x++) {
            string.append("---+");
        }
        return string.toString();
    }

    public static String render(Maze maze, Coordinate[] path) {
        boolean[][] pathMatrix = new boolean[maze.height][maze.width];
        for (Coordinate coordinate : path) {
            pathMatrix[coordinate.row()][coordinate.col()] = true;
        }
        StringBuilder string = new StringBuilder();
        for (int y = 0; y < maze.height; y++) {
            string.append("+");
            for (int x = 0; x < maze.width; x++) {
                Cell currentCell = maze.grid[y][x];
                if (currentCell.upperWall) {
                    string.append("---+");
                } else {
                    string.append("   +");
                }
            }
            string.append("\n");
            for (int x = 0; x < maze.width; x++) {
                Cell currentCell = maze.grid[y][x];
                if (currentCell.leftWall) {
                    string.append("|");
                } else {
                    string.append(" ");
                }
                if (pathMatrix[y][x]) {
                    string.append(" # ");
                } else {
                    string.append("   ");
                }
            }
            string.append("|");
            string.append("\n");
        }
        string.append('+');
        for (int x = 0; x < maze.width; x++) {
            string.append("---+");
        }
        return string.toString();
    }

}
