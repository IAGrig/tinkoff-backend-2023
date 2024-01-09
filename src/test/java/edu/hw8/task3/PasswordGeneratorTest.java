package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PasswordGeneratorTest {
    @Test
    public void nextPasswordTest() {
        PasswordsGenerator passwordsGenerator = new PasswordsGenerator("abc", 2);
        PasswordsGenerator startFinishPasswordsGenerator = new PasswordsGenerator("abcde", 2, 1, 3);
        PasswordsGenerator zeroLenghtPasswordsGenerator = new PasswordsGenerator("abcdefg", 0);

        assertThat(getAllPaswords(passwordsGenerator).size()).isEqualTo(9);
        assertThatThrownBy(() -> new PasswordsGenerator(null, 2)).isExactlyInstanceOf(NullPointerException.class);
        assertThat(getAllPaswords(startFinishPasswordsGenerator).size()).isEqualTo(10);
        assertThat(getAllPaswords(zeroLenghtPasswordsGenerator).size()).isEqualTo(1);
    }

    private List<String> getAllPaswords(PasswordsGenerator passwordsGenerator) {
        ArrayList<String> list = new ArrayList<>();
        for (; ; ) {
            try {
                list.add(passwordsGenerator.nextPassword());
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return list;
    }
}
