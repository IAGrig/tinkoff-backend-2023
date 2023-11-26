package edu.hw7;


import edu.hw7.task2.ParallelFactorial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @DisplayName("Задание 2")
    @Test
    public void task2Test(){
        assertThat(ParallelFactorial.calculateFactorial(1)).isEqualTo(1);
        assertThat(ParallelFactorial.calculateFactorial(2)).isEqualTo(2);
        assertThat(ParallelFactorial.calculateFactorial(3)).isEqualTo(6);
        assertThat(ParallelFactorial.calculateFactorial(4)).isEqualTo(24);
        assertThat(ParallelFactorial.calculateFactorial(5)).isEqualTo(120);
        assertThat(ParallelFactorial.calculateFactorial(6)).isEqualTo(720);
        assertThat(ParallelFactorial.calculateFactorial(15)).isEqualTo(3628800);
    }
}
