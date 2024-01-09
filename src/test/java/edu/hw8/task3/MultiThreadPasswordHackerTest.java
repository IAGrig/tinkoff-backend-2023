package edu.hw8.task3;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MultiThreadPasswordHackerTest {
    private static final int threadsCount = 6;
    private final static Map<String, String> leakedPasswords = PasswordHackersTestUtils.getLeakedPasswords();
    private final static String alphabet = PasswordHackersTestUtils.getAlphabet();
    private final static int minPasswordsLength = PasswordHackersTestUtils.getMinPasswordsLength();
    private final static int maxPasswordsLength = PasswordHackersTestUtils.getMaxPasswordsLength();

    @Test
    public void hackTest() {
        MultiThreadPasswordHacker passwordHacker = new MultiThreadPasswordHacker(leakedPasswords,
            alphabet,
            minPasswordsLength,
            maxPasswordsLength,
            threadsCount
        );
        assertThat(passwordHacker.hack().isEmpty()).isFalse();
        assertThatThrownBy(() -> new MultiThreadPasswordHacker(
            leakedPasswords,
            null,
            maxPasswordsLength,
            minPasswordsLength,
            threadsCount
        )).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new MultiThreadPasswordHacker(
            leakedPasswords,
            alphabet,
            maxPasswordsLength,
            minPasswordsLength,
            threadsCount
        )).isExactlyInstanceOf(
            IllegalArgumentException.class);
        assertThatThrownBy(() -> new MultiThreadPasswordHacker(
            null,
            alphabet,
            minPasswordsLength,
            maxPasswordsLength,
            threadsCount
        )).isExactlyInstanceOf(
            IllegalArgumentException.class);
    }
}
