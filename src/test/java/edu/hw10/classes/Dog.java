package edu.hw10.classes;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public class Dog {
    private final int age;

    private final String name;

    private final double weight;

    public Dog(@Min(1) @Max(50) int age, @NotNull String name, @Min(5) double weight) {
        this.age = age;
        this.name = name;
        this.weight = weight;
    }

    public Dog create(int age, String name, double weight) {
        return new Dog(age, name, weight);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
