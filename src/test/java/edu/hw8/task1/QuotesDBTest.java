package edu.hw8.task1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuotesDBTest {
    @Test
    public void quotesTest() {
        String phrase1 = "Не переходи на личности там, где их нет";
        String phrase2 =
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
        QuotesDB db = new QuotesDB();
        assertThat(db.getQuote("123")).isEqualTo("Сам такой!");
        db.addQuote(phrase1);
        db.addQuote(phrase2);
        assertThat(db.getQuote("123")).isEqualTo("Сам такой!");
        assertThat(db.getQuote("личности")).isEqualTo(phrase1);
        assertThat(db.getQuote("ГЛУпый")).isEqualTo(phrase2);
    }
}
