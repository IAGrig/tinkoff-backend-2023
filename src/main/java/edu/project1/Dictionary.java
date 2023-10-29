package edu.project1;

import org.jetbrains.annotations.NotNull;

public class Dictionary {
    private final String[] words = {"hello", "tinkoff", "backend"};

    @NotNull String randomWord() {
        int index = (int) (Math.random() * words.length);
        return words[index];
    }
}
