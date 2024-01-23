package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @DisplayName("Задание 7.1")
    @Test
    public void task7_1Test() {
        Task7 task7 = new Task7();
        assertThat(task7.task7_1("")).isFalse();
        assertThat(task7.task7_1("0")).isFalse();
        assertThat(task7.task7_1("01")).isFalse();
        assertThat(task7.task7_1("123")).isFalse();
        assertThat(task7.task7_1("000")).isTrue();
        assertThat(task7.task7_1("1111")).isFalse();
        assertThat(task7.task7_1("11011")).isTrue();
    }

    @DisplayName("Задание 7.2")
    @Test
    public void task7_2Test() {
        Task7 task7 = new Task7();
        assertThat(task7.task7_2("")).isFalse();
        assertThat(task7.task7_2("0")).isFalse();
        assertThat(task7.task7_2("01")).isFalse();
        assertThat(task7.task7_2("123")).isFalse();
        assertThat(task7.task7_2("000")).isTrue();
        assertThat(task7.task7_2("1111")).isTrue();
        assertThat(task7.task7_2("11011")).isTrue();
    }

    @DisplayName("Задание 7.3")
    @Test
    public void task7_3Test() {
        Task7 task7 = new Task7();
        assertThat(task7.task7_3("")).isFalse();
        assertThat(task7.task7_3("0")).isTrue();
        assertThat(task7.task7_3("01")).isTrue();
        assertThat(task7.task7_3("123")).isFalse();
        assertThat(task7.task7_3("000")).isTrue();
        assertThat(task7.task7_3("1111")).isFalse();
        assertThat(task7.task7_3("11011")).isFalse();
    }
}
