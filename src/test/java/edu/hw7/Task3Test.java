package edu.hw7;

import edu.hw7.task3.Cache;
import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import edu.hw7.task3.ReadWriteCache;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    final static int threadsCount = 8;
    final static String baseName = "Vladimir";
    final static String baseAddress = "Kronverskiy pr. 49, room: ";
    final static String basePhone = "+7999999999";


    @DisplayName("Задание 3")
    @Test
    public void task3Test() {
        Cache cache = new Cache();

        assertThat(cache.findByName("Paul")).isNull();

        Stream.generate(() -> new Thread(() -> {
            addRandomPerson(cache, threadsCount);
        })).limit(threadsCount).forEach(Thread::start);

        Stream.generate(() -> new Thread(() -> {
            deleteRandomPerson(cache, threadsCount);
        })).limit(threadsCount).forEach(Thread::start);


        Stream.generate(() -> new Thread(() -> {
            assertThat(checkRandomPerson(cache, threadsCount)).isTrue();
        })).limit(threadsCount).forEach(Thread::start);
    }

    @DisplayName("Задание 3.5")
    @Test
    public void task3ReadWriteTest() {
        ReadWriteCache cache = new ReadWriteCache();

        assertThat(cache.findByName("Paul")).isNull();

        Stream.generate(() -> new Thread(() -> {
            addRandomPerson(cache, threadsCount);
        })).limit(threadsCount).forEach(Thread::start);

        Stream.generate(() -> new Thread(() -> {
            deleteRandomPerson(cache, threadsCount);
        })).limit(threadsCount).forEach(Thread::start);


        Stream.generate(() -> new Thread(() -> {
            assertThat(checkRandomPerson(cache, threadsCount)).isTrue();
        })).limit(threadsCount).forEach(Thread::start);
    }

    private static void addRandomPerson(PersonDatabase database, int randomBound){
        int id = ThreadLocalRandom.current().nextInt(1, randomBound);
        String name = baseName + id;
        String address = baseAddress + id;
        String phone = basePhone + id;
        database.add(new Person(id, name, address, phone));
    }

    private static void deleteRandomPerson(PersonDatabase database, int randomBound){
        int id = ThreadLocalRandom.current().nextInt(1, randomBound);
        database.delete(id);
    }

    private static boolean checkRandomPerson(PersonDatabase database, int randomBound){
        int id = ThreadLocalRandom.current().nextInt(1, randomBound);
        String name = baseName + id;
        String address = baseAddress + id;
        String phone = basePhone + id;

        return (database.findByName(name) == null) ==
            (database.findByAddress(address) == null) ==
            (database.findByPhone(phone) == null);
    }
}
