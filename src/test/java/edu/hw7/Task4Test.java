package edu.hw7;

import edu.hw7.task4.MonteCarlo;
import edu.hw7.task4.MonteCarloParallel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    private final int iterationsCount = 1000000000;

    @DisplayName("Задание 4")
    @Test
    public void calculateTest() {
        MonteCarlo monteCarlo = new MonteCarlo();
        double myPI = monteCarlo.calculatePI(iterationsCount);
        System.out.println("Sequential: " + myPI);
        System.out.println("Sequential diff: " + Math.abs(Math.PI - myPI));
    }

    @DisplayName("Задание 4.5")
    @Test
    public void calculateParallelTest() {
        MonteCarloParallel monteCarlo = new MonteCarloParallel();
        double myPI = monteCarlo.calculatePI(iterationsCount);
        System.out.println("Parallel: " + myPI);
        System.out.println("Parallel diff: " + Math.abs(Math.PI - myPI));
    }
}
