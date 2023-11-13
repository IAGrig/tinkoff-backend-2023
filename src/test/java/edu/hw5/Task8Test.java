package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @DisplayName("Задание 8 (бонусное, +1 балл)")
    @Test
    public void oddLengthTest() {
        Task8 task8 = new Task8();
        assertThat(task8.oddLength("")).isFalse();
        assertThat(task8.oddLength("1")).isTrue();
        assertThat(task8.oddLength("10")).isFalse();
        assertThat(task8.oddLength("123")).isFalse();
        assertThat(task8.oddLength("101")).isTrue();
        assertThat(task8.oddLength("1010111")).isTrue();
        assertThat(task8.oddLength("11111")).isTrue();
    }

    @DisplayName("Задание 8 (бонусное, +1 балл)")
    @Test
    public void not11Nor111() {
        Task8 task8 = new Task8();
        assertThat(task8.not11Nor111("")).isTrue();
        assertThat(task8.not11Nor111("1")).isTrue();
        assertThat(task8.not11Nor111("10")).isTrue();
        assertThat(task8.not11Nor111("123")).isFalse();
        assertThat(task8.not11Nor111("101")).isTrue();
        assertThat(task8.not11Nor111("1010111")).isTrue();
        assertThat(task8.not11Nor111("11111")).isTrue();
        assertThat(task8.not11Nor111("11")).isFalse();
        assertThat(task8.not11Nor111("111")).isFalse();
    }

    @DisplayName("Задание 8 (бонусное, +1 балл)")
    @Test
    public void oneOnOddPositions() {
        Task8 task8 = new Task8();
        assertThat(task8.oneOnOddPositions("")).isFalse();
        assertThat(task8.oneOnOddPositions("1")).isTrue();
        assertThat(task8.oneOnOddPositions("10")).isTrue();
        assertThat(task8.oneOnOddPositions("123")).isFalse();
        assertThat(task8.oneOnOddPositions("101")).isTrue();
        assertThat(task8.oneOnOddPositions("1010111")).isTrue();
        assertThat(task8.oneOnOddPositions("11111")).isTrue();
        assertThat(task8.oneOnOddPositions("0101010")).isFalse();
        assertThat(task8.oneOnOddPositions("1111110110")).isFalse();
    }

    @DisplayName("Задание 8 (бонусное, +1 балл)")
    @Test
    public void noConsecutiveOne() {
        Task8 task8 = new Task8();
        assertThat(task8.noConsecutiveOne("")).isTrue();
        assertThat(task8.noConsecutiveOne("1")).isTrue();
        assertThat(task8.noConsecutiveOne("10")).isTrue();
        assertThat(task8.noConsecutiveOne("123")).isFalse();
        assertThat(task8.noConsecutiveOne("101")).isTrue();
        assertThat(task8.noConsecutiveOne("1010111")).isFalse();
        assertThat(task8.noConsecutiveOne("11111")).isFalse();
    }
}
