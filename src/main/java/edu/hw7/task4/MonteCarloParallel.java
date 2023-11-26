package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloParallel {
    private final int threadsCount = 8;
    private int circleCount;
    private int totalCount;

    public MonteCarloParallel() {
        circleCount = 0;
        totalCount = 0;
    }

    public double calculatePI(int iterationsCount) {
        int iterationsPart = iterationsCount / threadsCount;
        Thread[] threads = new Thread[threadsCount];
        for (int threadIndex = 0; threadIndex < threadsCount; threadIndex++) {
            Thread thread = new Thread(() -> {
                int localСircleCount = 0;
                int localTotalCount = 0;
                for (int iteration = 0; iteration < iterationsPart; iteration++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();
                    if (x * x + y * y < 1.0) {
                        localСircleCount++;
                    }
                    localTotalCount++;
                }
                synchronized (this) {
                    circleCount += localСircleCount;
                    totalCount += localTotalCount;
                }

            });
            threads[threadIndex] = thread;
            thread.start();
        }
        for (int threadIndex = 0; threadIndex < threadsCount; threadIndex++) {
            try {
                threads[threadIndex].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return 4 * (circleCount * 1.0 / totalCount);
    }
}
