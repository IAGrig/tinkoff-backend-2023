package edu.hw8.task3;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SingleThreadPasswordHackerTest {
    private final static Map<String, String> leakedPasswords = PasswordHackersTestUtils.getLeakedPasswords();
    private final static String alphabet = PasswordHackersTestUtils.getAlphabet();
    private final static int minPasswordsLength = PasswordHackersTestUtils.getMinPasswordsLength();
    private final static int maxPasswordsLength = PasswordHackersTestUtils.getMaxPasswordsLength();

    @Test
    public void hackTest() {
        SingleThreadPasswordHacker passwordHacker =
            new SingleThreadPasswordHacker(leakedPasswords, alphabet, minPasswordsLength, maxPasswordsLength);
        assertThat(passwordHacker.hack().isEmpty()).isFalse();
        assertThatThrownBy(() -> new SingleThreadPasswordHacker(
            leakedPasswords,
            null,
            maxPasswordsLength,
            minPasswordsLength
        )).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SingleThreadPasswordHacker(
            leakedPasswords,
            alphabet,
            maxPasswordsLength,
            minPasswordsLength
        )).isExactlyInstanceOf(
            IllegalArgumentException.class);
        assertThatThrownBy(() -> new SingleThreadPasswordHacker(
            null,
            alphabet,
            minPasswordsLength,
            maxPasswordsLength
        )).isExactlyInstanceOf(
            IllegalArgumentException.class);
    }
}
