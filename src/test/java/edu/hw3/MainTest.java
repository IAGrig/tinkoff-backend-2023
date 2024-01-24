package edu.hw3;

import edu.hw3.stocks.Stock;
import edu.hw3.stocks.StockMarket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainTest {
    private static Stream<Arguments> clusterizeTestsProvider() {
        return Stream.of(
            Arguments.of("", new String[] {}),
            Arguments.of("()()()", new String[] {"()", "()", "()"}),
            Arguments.of("((()))", new String[] {"((()))"}),
            Arguments.of("((()))(())()()(()())", new String[] {"((()))", "(())", "()", "()", "(()())"}),
            Arguments.of("((())())(()(()()))", new String[] {"((())())", "(()(()()))"})
        );
    }

    private static Stream<Arguments> freqDictTestsProvider() {
        return Stream.of(
            Arguments.of(new String[] {"a", "bb", "a", "bb"}, new String[] {"bb=2", "a=2"}),
            Arguments.of(new String[] {"this", "and", "that", "and"}, new String[] {"that=1", "and=2", "this=1"}),
            Arguments.of(new String[] {"код", "код", "код", "bug"}, new String[] {"код=3", "bug=1"}),
            Arguments.of(new Integer[] {1, 1, 2, 2}, new String[] {"1=2", "2=2"})
        );
    }

    private static Stream<Arguments> convertToRomanTestsProvider() {
        return Stream.of(
            Arguments.of(1, "I"),
            Arguments.of(2, "II"),
            Arguments.of(4, "IV"),
            Arguments.of(5, "V"),
            Arguments.of(6, "VI"),
            Arguments.of(10, "X"),
            Arguments.of(18, "XVIII"),
            Arguments.of(20, "XX"),
            Arguments.of(356, "CCCLVI"),
            Arguments.of(1234, "MCCXXXIV"),
            Arguments.of(1000, "M"),
            Arguments.of(1001, "MI"),
            Arguments.of(-100, "Unable to convert."),
            Arguments.of(0, "Unable to convert."),
            Arguments.of(10000, "Unable to convert.")
        );
    }

    private static Stream<Arguments> parseContactsTestsProvider() {
        return Stream.of(
            Arguments.of(new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC",
                new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"}
            ),
            Arguments.of(new String[] {}, "DESC", new String[] {}),
            Arguments.of(new String[] {null}, "DESC", new String[] {})
        );
    }

    @DisplayName("Шифр Атбаш")
    @ParameterizedTest
    @CsvSource({
        "Hello world!, Svool dliow!",
        "AbC, ZyX",
        "hello1@ %(name), svool1@ %(mznv)"
    })
    void atbashTest(String argument, String expected) {
        Main main = new Main();
        assertThat(main.atbash(argument)).isEqualTo(expected);
    }

    @DisplayName("Кластеризация скобок")
    @ParameterizedTest
    @MethodSource("clusterizeTestsProvider")
    void clusterizeTest(String argument, String[] expected) {
        Main main = new Main();
        ArrayList<String> actual = main.clusterize(argument);
        String[] actualStrings = actual.toArray(new String[0]);
        assertThat(actualStrings).isEqualTo(expected);
    }

    @DisplayName("Частота слов")
    @ParameterizedTest
    @MethodSource("freqDictTestsProvider")
    public <E> void freqDictTest(E[] arguments, String[] expectedToStringContains) {
        Main main = new Main();
        String actual = main.freqDict(arguments).toString();
        boolean containsAll = true;
        for (String component : expectedToStringContains) {
            if (!actual.contains(component)) {
                containsAll = false;
                break;
            }
        }
        assertThat(containsAll).isTrue();
    }

    @DisplayName("Римские цифры")
    @ParameterizedTest
    @MethodSource("convertToRomanTestsProvider")
    public void convertToRomanTest(int argument, String expected) {
        Main main = new Main();
        assertThat(main.convertToRoman(argument)).isEqualTo(expected);
    }

    @DisplayName("Список контактов")
    @ParameterizedTest
    @MethodSource("parseContactsTestsProvider")
    public void parseContactsTest(String[] namesArray, String sortingMode, String[] expected) {
        Main main = new Main();
        var actual = Main.parseContacts(namesArray, sortingMode);
        assertThat(actual.toString()).isEqualTo(Arrays.toString(expected));
    }

    @DisplayName("Биржа")
    @Test
    void StockMarketTest() {
        StockMarket stockMarket = new StockMarket();
        Stock stock1 = new Stock("AAPL", 1500);
        Stock stock2 = new Stock("TINK", 5000);
        Stock stock3 = new Stock("YNDX", 3000);
        stockMarket.add(stock1);
        assertThat(stockMarket.mostValuableStock()).isEqualTo(stock1);
        stockMarket.add(stock2);
        assertThat(stockMarket.mostValuableStock()).isEqualTo(stock2);
        stockMarket.add(stock3);
        assertThat(stockMarket.mostValuableStock()).isEqualTo(stock2);

    }

    @DisplayName("Дерево и null")
    @Test
    void treeAndNullTest() {
        TreeMapNullComparator comparator = new TreeMapNullComparator();
        TreeMap<String, String> tree = new TreeMap<>(comparator);
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @DisplayName("Обратный итератор")
    @Test
    void BackwardIteratorTest() {
        BackwardIterator iterator = new BackwardIterator(List.of(1, 2, 3));
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(1);
    }
}
