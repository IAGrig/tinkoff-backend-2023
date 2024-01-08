package edu.hw7;

import edu.hw7.task1.Counter;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @DisplayName("Задание 1")
    @Test
    public void task1Test() {
        final int threadsCount = 5;
        final int iterationsPerThread = 100;
        Counter counter = new Counter();
        assertThat(counter.getValue()).isEqualTo(0);
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++) {
            threads.add(new Thread(() -> {
                for (int iteration = 0; iteration < iterationsPerThread; iteration++) {
                    counter.increment();
                }
            }));
        }

        threads.stream().forEach(Thread::start);

        try {
            for (int i = 0; i < threadsCount; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
        }
        assertThat(counter.getValue()).isEqualTo(threadsCount * iterationsPerThread);
    }
}
