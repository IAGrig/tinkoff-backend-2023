package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @DisplayName("Задание 4")
    @Test
    public void checkIfContainsSpecialTest() {
        Task4 task4 = new Task4();

        assertThat(task4.checkIfContainsSpecial("")).isFalse();
        assertThat(task4.checkIfContainsSpecial("1234")).isFalse();
        assertThat(task4.checkIfContainsSpecial("12345#")).isTrue();
        assertThat(task4.checkIfContainsSpecial("###")).isTrue();
    }
}
