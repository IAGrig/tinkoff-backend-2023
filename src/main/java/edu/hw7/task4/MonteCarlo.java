package edu.hw7.task4;

public class MonteCarlo {
    private int circleCount;
    private int totalCount;

    public MonteCarlo() {
        circleCount = 0;
        totalCount = 0;
    }

    public double calculatePI(int iterationsCount) {
        for (int iteration = 0; iteration < iterationsCount; iteration++) {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y < 1.0) {
                circleCount++;
            }
            totalCount++;
        }
        return 4 * (circleCount * 1.0 / totalCount);
    }
}
