package edu.project2;

public class Renderer {
    public static String render(Maze maze) {
        StringBuilder string = new StringBuilder();
        for (int y = 0; y < maze.height; y++) {
            string.append("+");
            for (int x = 0; x < maze.width; x++) {
                Cell currentCell = maze.grid[x][y];
                if (currentCell.upperWall) { // mb check upper cell
                    string.append("---+");
                } else {
                    string.append("   +");
                }
            }
            string.append("\n");
            for (int x = 0; x < maze.width; x++) {
                Cell currentCell = maze.grid[x][y];
                if (currentCell.leftWall) {
                    string.append("|");
                } else {
                    string.append(" ");
                }
                string.append("   ");
//                if (currentCell.rightWall){
//                    string.append("|");
//                }else{
//                    string.append(" ");
//                }
            }
            string.append("|");
//            string.append("\n");
//            for(int x = 0; x < maze.width; x++) {
//                Cell currentCell = maze.grid[x][y];
//                if (currentCell.bottomWall){
//                    string.append("---+");
//                }else{
//                    string.append("   +");
//                }
//            }
            string.append("\n");
        }
        string.append('+');
        for (int x = 0; x < maze.width; x++) {
            string.append("---+");
        }
        return string.toString();
    }

}
