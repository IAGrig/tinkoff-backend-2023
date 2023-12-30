package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @DisplayName("Задание 6")
    @Test
    public void isSubstringOf() {
        Task6 task6 = new Task6();
        assertThat(task6.isSubstringOf("", "achfdbaabgabcaabg")).isTrue();
        assertThat(task6.isSubstringOf("abc", "achfdbaabgabcaabg")).isTrue();
        assertThat(task6.isSubstringOf("abc", "abc")).isTrue();
        assertThat(task6.isSubstringOf("abc", "abaaaac")).isTrue();
        assertThat(task6.isSubstringOf("abcc", "ababbbbbaacc")).isTrue();
        assertThat(task6.isSubstringOf(".", "achfdbaabgabcaabg")).isFalse();
        assertThat(task6.isSubstringOf("abc", "aabbbaababbda")).isFalse();
        assertThat(task6.isSubstringOf("ab..c", "abaacabbbc")).isFalse();
        assertThat(task6.isSubstringOf("db\\d", "aaaadb1jknbjnj")).isFalse();
    }
}
