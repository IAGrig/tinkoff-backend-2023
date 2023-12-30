package edu.hw10;

import edu.hw10.classes.Dog;
import edu.hw10.task1.RandomObjectGenerator;
import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomObjectGeneratorTest {
    @RepeatedTest(100)
    public void randomObjectGeneratorTest() throws NoSuchMethodException {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        Dog dog = (Dog) generator.nextObject(Dog.class);
        assertThat(dog.getAge() <= 50).isTrue();
        assertThat(dog.getName()).isNotNull();
        assertThat(dog.getWeight() >= 5).isTrue();
    }

}
