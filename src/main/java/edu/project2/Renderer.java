package edu.project2;

public class Renderer {
    private static final String HORIZONTAL_DIVIDER_CLOSED = "---+";
    private static final String HORIZONTAL_DIVIDER_OPEN = "   +";
    private static final String EMPTY_CELL = "   ";

    private Renderer() {
    }

    public static String render(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder string = new StringBuilder();
        for (int y = 0; y < maze.height; y++) {
            string.append("+");
            for (int x = 0; x < maze.width; x++) {
                Cell currentCell = maze.grid[y][x];
                if (currentCell.upperWall) {
                    string.append(HORIZONTAL_DIVIDER_CLOSED);
                } else {
                    string.append(HORIZONTAL_DIVIDER_OPEN);
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
                string.append(EMPTY_CELL);
            }
            string.append("|");
            string.append("\n");
        }
        string.append('+');
        for (int x = 0; x < maze.width; x++) {
            string.append(HORIZONTAL_DIVIDER_CLOSED);
        }
        return string.toString();
    }

    public static String render(Maze maze, Coordinate[] path) {
        if (maze == null || path == null) {
            throw new IllegalArgumentException();
        }
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
                    string.append(HORIZONTAL_DIVIDER_CLOSED);
                } else {
                    string.append(HORIZONTAL_DIVIDER_OPEN);
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
                    string.append(EMPTY_CELL);
                }
            }
            string.append("|");
            string.append("\n");
        }
        string.append('+');
        for (int x = 0; x < maze.width; x++) {
            string.append(HORIZONTAL_DIVIDER_CLOSED);
        }
        return string.toString();
    }

}
