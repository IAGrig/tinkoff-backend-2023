package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TasksTest {
    @Test
    @DisplayName("Длина видео")
    void minutesToSecondsTest() {
        Tasks task = new Tasks();
        assertThat(task.minutesToSeconds("10:30")).isEqualTo(630);
        assertThat(task.minutesToSeconds("00:30")).isEqualTo(30);
        assertThat(task.minutesToSeconds("0:00")).isEqualTo(0);
        assertThat(task.minutesToSeconds("10:60")).isEqualTo(-1);
        assertThat(task.minutesToSeconds("100:00")).isEqualTo(6000);
        assertThat(task.minutesToSeconds("-10:03")).isEqualTo(-1);
    }

    @Test
    @DisplayName("Количество цифр")
    void countDigitsTest() {
        Tasks task = new Tasks();
        assertThat(task.countDigits(3)).isEqualTo(1);
        assertThat(task.countDigits(30)).isEqualTo(2);
        assertThat(task.countDigits(10000)).isEqualTo(5);
        assertThat(task.countDigits(0)).isEqualTo(1);
        assertThat(task.countDigits(-10)).isEqualTo(2);
    }

    @Test
    @DisplayName("Вложенный массив")
    void isNestableTest() {
        Tasks task = new Tasks();
        int[][] test1 = {{1, 2, 3, 4}, {0, 6}};
        int[][] test2 = {{3, 1}, {4, 0}};
        int[][] test3 = {{9, 9, 8}, {8, 9}};
        int[][] test4 = {{1, 2, 3, 4}, {2, 3}};
        int[][] test5 = {{}, {1, 2, 3}};
        assertThat(task.isNestable(test1[0], test1[1])).isTrue();
        assertThat(task.isNestable(test2[0], test2[1])).isTrue();
        assertThat(task.isNestable(test3[0], test3[1])).isFalse();
        assertThat(task.isNestable(test4[0], test4[1])).isFalse();
        assertThat(task.isNestable(test5[0], test5[1])).isTrue();
    }

    @Test
    @DisplayName("Сломанная строка")
    void fixStringTest() {
        Tasks task = new Tasks();
        assertThat(task.fixString("")).isEqualTo("");
        assertThat(task.fixString("t")).isEqualTo("t");
        assertThat(task.fixString("te")).isEqualTo("et");
        assertThat(task.fixString("tes")).isEqualTo("ets");
        assertThat(task.fixString("test")).isEqualTo("etts");
        assertThat(task.fixString("hTsii  s aimex dpus rtni.g")).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Особый палиндром")
    void isPalindromeDescendantTest() {
        Tasks task = new Tasks();
        assertThat(task.isPalindromeDescendant(123312)).isTrue();
        assertThat(task.isPalindromeDescendant(11)).isTrue();
        assertThat(task.isPalindromeDescendant(11211230)).isTrue();
        assertThat(task.isPalindromeDescendant(13001120)).isTrue();
        assertThat(task.isPalindromeDescendant(23336014)).isTrue();
        assertThat(task.isPalindromeDescendant(1317)).isFalse();
    }

    @Test
    void isPalindromeTest() {
        Tasks task = new Tasks();
        assertThat(task.isPalindrome(123321)).isTrue();
        assertThat(task.isPalindrome(11)).isTrue();
        assertThat(task.isPalindrome(123123)).isFalse();
        assertThat(task.isPalindrome(1234321)).isTrue();
        assertThat(task.isPalindrome(1234322)).isFalse();
    }

    @Test
    @DisplayName("Постоянная Капрекара")
    void countKTest() {
        Tasks task = new Tasks();
        assertThat(task.countK(3)).isEqualTo(-1);
        assertThat(task.countK(3333)).isEqualTo(-1);
        assertThat(task.countK(6174)).isEqualTo(0);
        assertThat(task.countK(6621)).isEqualTo(5);
        assertThat(task.countK(6554)).isEqualTo(4);
        assertThat(task.countK(1234)).isEqualTo(3);
    }

    @Test
    @DisplayName("Циклический битовый сдвиг влево")
    void rotateLeftTest() {
        Tasks task = new Tasks();
        assertThat(task.rotateLeft(8, 2)).isEqualTo(2);
        assertThat(task.rotateLeft(15, 1)).isEqualTo(15);
        assertThat(task.rotateLeft(13, 0)).isEqualTo(13);
        assertThat(task.rotateLeft(13, 1)).isEqualTo(11);
        assertThat(task.rotateLeft(13, 2)).isEqualTo(7);
        assertThat(task.rotateLeft(13, 3)).isEqualTo(14);
    }

    @Test
    @DisplayName("Циклический битовый сдвиг вправо")
    void rotateRightTest() {
        Tasks task = new Tasks();
        assertThat(task.rotateRight(8, 2)).isEqualTo(2);
        assertThat(task.rotateRight(15, 1)).isEqualTo(15);
        assertThat(task.rotateRight(13, 0)).isEqualTo(13);
        assertThat(task.rotateRight(13, 1)).isEqualTo(14);
        assertThat(task.rotateRight(13, 2)).isEqualTo(7);
        assertThat(task.rotateRight(13, 3)).isEqualTo(11);
    }

    @Test
    void rotateLeft1PositionTest() {
        Tasks task = new Tasks();
        assertThat(task.rotateLeft1Position(8, 4)).isEqualTo(1);
        assertThat(task.rotateLeft1Position(17, 5)).isEqualTo(3);
        assertThat(task.rotateLeft1Position(7, 3)).isEqualTo(7);
        assertThat(task.rotateLeft1Position(1, 1)).isEqualTo(1);
    }

    @Test
    @DisplayName("Кони на доске")
    void knightBoardCaptureTest() {
        Tasks task = new Tasks();
        int[][] desk1 = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}};
        assertThat(task.knightBoardCapture(desk1)).isTrue();

        int[][] desk2 = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}};
        assertThat(task.knightBoardCapture(desk2)).isFalse();

        int[][] desk3 = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}};
        assertThat(task.knightBoardCapture(desk3)).isFalse();
    }
}
