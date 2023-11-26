package edu.hw7;

    import edu.hw7.task3.Cache;
    import edu.hw7.task3.Person;
    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.api.Test;
    import javax.swing.plaf.TableHeaderUI;
    import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @DisplayName("Задание 3")
    @Test
    public void task3Test() throws InterruptedException {
        Cache cache = new Cache();

        assertThat(cache.findByName("Paul")).isNull();
        Thread additionThread = new Thread(() -> cache.add(new Person(1, "Paul", "St. Pete, Kronverskiy 49", "+79999999999")));
        additionThread.start();
        additionThread.join();
//        new Thread(() -> assertThat(cache.findByPhone("+79999999999")).isNotNull()).start();
        assertThat(cache.findByAddress("St. Pete, Kronverskiy 49")).isNotNull();
        assertThat(cache.findByName("Paul")).isNotNull();
        cache.delete(1);
//        new Thread(() -> cache.delete(1)).start();
        assertThat(cache.findByPhone("+79999999999")).isNull();
        assertThat(cache.findByAddress("St. Pete, Kronverskiy 49")).isNull();
        assertThat(cache.findByName("Paul")).isNull();
    }
}
