package edu.project2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class GeneratorTest {
    @Test
    public void generateTest() {
        int mazeHeight = 8;
        int mazeWidth = 10;
        Maze maze = Generator.generate(mazeHeight, mazeWidth);
        assertThat(maze.height).isEqualTo(mazeHeight);
        assertThat(maze.width).isEqualTo(mazeWidth);

        assertThatThrownBy(() -> Generator.generate(0, 0)).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Generator.generate(
            mazeHeight,
            -1
        )).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
