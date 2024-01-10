package edu.project2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RendererTest {
    @Test
    public void renderTest() {
        int mazeHeight = 8;
        int mazeWidth = 10;
        Maze maze = Generator.generate(mazeHeight, mazeWidth);

        int startX = 0;
        int startY = 0;
        int finishX = mazeWidth - 1;
        int finishY = mazeHeight - 1;
        Coordinate[] path = Solver.solve(maze, new Coordinate(startX, startY), new Coordinate(finishX, finishY));

        String renderedMaze = Renderer.render(maze);
        String renderedMazeWithPath = Renderer.render(maze, path);

        assertThat(renderedMaze).isNotNull();
        assertThat(renderedMaze).isNotBlank();
        assertThat(renderedMazeWithPath).isNotNull();
        assertThat(renderedMazeWithPath).isNotBlank();

        assertThatThrownBy(() -> Renderer.render(null)).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Renderer.render(maze, null)).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Renderer.render(null, path)).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
