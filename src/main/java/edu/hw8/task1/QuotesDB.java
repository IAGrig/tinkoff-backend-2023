package edu.hw8.task1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class QuotesDB {
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<String>> quotes;
    private final String defaultAnswer = "Сам такой!";

    public QuotesDB() {
        this.quotes = new ConcurrentHashMap<>();
    }

    public void loadBase() {
        // in production it can read from a real database
        addQuote("Не переходи на личности там, где их нет");
        addQuote("Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
        addQuote("А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
        addQuote("Чем ниже интеллект, тем громче оскорбления");
    }

    public void addQuote(String quote) {
        String[] words = removeSymbols(quote).toLowerCase().split(" ");
        for (String word : words) {
            CopyOnWriteArrayList<String> arrayList = quotes.get(word);
            if (arrayList == null) {
                arrayList = new CopyOnWriteArrayList<>();
                quotes.put(word, arrayList);
            }
            arrayList.add(quote);
        }
    }

    public String getQuote(String keyword) {
        String cleanKeyword = removeSymbols(keyword).toLowerCase();
        CopyOnWriteArrayList<String> arrayList = quotes.get(cleanKeyword);
        if (arrayList == null) {
            return defaultAnswer;
        }
        int randomIndex = (int) (Math.random() * arrayList.size());
        return arrayList.get(randomIndex);
    }

    private String removeSymbols(String word) {
        return word.replaceAll("[^А-яA-zёЁ ]", "");
    }
}
