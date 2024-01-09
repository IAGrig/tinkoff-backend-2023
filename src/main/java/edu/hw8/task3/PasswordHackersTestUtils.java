package edu.hw8.task3;

import java.util.Map;

public class PasswordHackersTestUtils {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MIN_PASSWORDS_LENTGTH = 1;
    private static final int MAX_PASSWORDS_LENTGTH = 4;
    private static final Map<String, String> LEAKED_PASSWORDS = Map.of(
        "aa@mail", "4124bc0a9335c27f086f24ba207a4912",
        "bbb@mail", "08f8e0260c64418510cefb2b06eee5cd",
        "cccc@mail", "41fcba09f2bdcdf315ba4119dc7978dd",
        "a.v.petrov", "827ccb0eea8a706c4c34a16891f84e7b",
        "admin@mail", "21232f297a57a5a743894a0e4a801fc3",
        "pswd@mail", "a3f05c8283e5350106829f855c93c07d"
    );

    private PasswordHackersTestUtils() {
    }

    public static String getAlphabet() {
        return ALPHABET;
    }

    public static int getMinPasswordsLength() {
        return MIN_PASSWORDS_LENTGTH;
    }

    public static int getMaxPasswordsLength() {
        return MAX_PASSWORDS_LENTGTH;
    }

    public static Map<String, String> getLeakedPasswords() {
        return LEAKED_PASSWORDS;
    }
}
