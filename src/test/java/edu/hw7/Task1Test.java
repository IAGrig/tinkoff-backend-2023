package edu.hw7;

import edu.hw7.task1.Counter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @DisplayName("Задание 1")
    @Test
    public void task1Test(){
        Counter counter = new Counter();
        assertThat(counter.getValue()).isEqualTo(0);
        new Thread(counter::increment).start();
        new Thread(counter::increment).start();
        new Thread(counter::increment).start();
        new Thread(counter::increment).start();
        new Thread(counter::increment).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e){
        }
        assertThat(counter.getValue()).isEqualTo(5);
    }
}
