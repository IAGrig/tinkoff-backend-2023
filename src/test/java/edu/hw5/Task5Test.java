package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @DisplayName("Задание 5")
    @Test
    public void checkCarNumberTest() {
        Task5 task5 = new Task5();
        assertThat(task5.checkCarNumber("А123ВЕ777")).isTrue();
        assertThat(task5.checkCarNumber("О777ОО177")).isTrue();
        assertThat(task5.checkCarNumber("123АВЕ777")).isFalse();
        assertThat(task5.checkCarNumber("А123ВГ77")).isFalse();
        assertThat(task5.checkCarNumber("А123ВЕ7777")).isFalse();
        assertThat(task5.checkCarNumber("А123ВЕ7")).isFalse();
        assertThat(task5.checkCarNumber("А123ВЯ77")).isFalse();
        assertThat(task5.checkCarNumber("А123ВZ77")).isFalse();
        assertThat(task5.checkCarNumber("")).isFalse();
    }
}
