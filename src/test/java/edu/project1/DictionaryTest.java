package edu.project1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DictionaryTest {
    @Test
    void randomWordTest(){
        Dictionary dictionary = new Dictionary();
        assertThat(dictionary.randomWord()).isNotEmpty();
    }
}
