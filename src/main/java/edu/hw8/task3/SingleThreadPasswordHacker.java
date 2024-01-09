package edu.hw8.task3;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class SingleThreadPasswordHacker extends AbstractPasswordHacker {
    public SingleThreadPasswordHacker(
        Map<String, String> leakedPasswords,
        String alphabet,
        int minPasswordsLength,
        int maxPasswordsLength
    ) {
        super(leakedPasswords, alphabet, minPasswordsLength, maxPasswordsLength);
    }

    @Override
    public Map<String, String> hack() {
        IntStream.range(minPasswordsLength, maxPasswordsLength + 1).forEach(this::hackPasswordsWithLength);
        return results;
    }

    private void hackPasswordsWithLength(int passwordsLength) {
        PasswordsGenerator passwordsGenerator = new PasswordsGenerator(alphabet, passwordsLength);
        String password;
        while (results.size() != leakedPasswords.size()) {
            try {
                password = passwordsGenerator.nextPassword();
                handlePassword(password, results);
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
}
