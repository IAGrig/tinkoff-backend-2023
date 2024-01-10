package edu.project2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SolverTest {
    @Test
    public void solveTest() {
        int mazeHeight = 8;
        int mazeWidth = 10;
        Maze maze = Generator.generate(mazeHeight, mazeWidth);

        int startX = 0;
        int startY = 0;
        int finishX = mazeWidth - 1;
        int finishY = mazeHeight - 1;
        Coordinate[] path = Solver.solve(maze, new Coordinate(startX, startY), new Coordinate(finishX, finishY));

        assertThat(path).isNotNull();
        assertThat(path).isNotEmpty();
        assertThat(path.length).isGreaterThanOrEqualTo(mazeHeight + mazeWidth - 1);

        assertThatThrownBy(() -> Solver.solve(
            null,
            new Coordinate(startX, startY),
            new Coordinate(finishX, finishY)
        )).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Solver.solve(maze, null, new Coordinate(finishX, finishY))).isExactlyInstanceOf(
            IllegalArgumentException.class);
        assertThatThrownBy(() -> Solver.solve(maze, new Coordinate(startX, startY), null)).isExactlyInstanceOf(
            IllegalArgumentException.class);
    }
}
