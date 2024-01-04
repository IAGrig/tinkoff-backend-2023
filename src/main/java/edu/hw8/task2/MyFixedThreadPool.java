package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyFixedThreadPool implements ThreadPool {
    private final static Logger LOGGER = LogManager.getLogger();
    private final int threadsCount;
    private final List<Worker> workers;
    private final BlockingQueue<Runnable> tasks;
    private boolean isWorking;

    private MyFixedThreadPool(int threadsCount) {
        this.threadsCount = threadsCount;
        this.workers = new ArrayList<>();
        this.tasks = new LinkedTransferQueue<>();
        this.isWorking = false;
    }

    public static MyFixedThreadPool create(int threadsCount) {
        return new MyFixedThreadPool(threadsCount);
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsCount; i++) {
            workers.add(new Worker());
            workers.get(i).start();
        }
        isWorking = true;
    }

    @Override
    public void execute(Runnable task) {
        try {
            tasks.put(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void join(long millis) throws InterruptedException {
        for (Thread thread : workers) {
            thread.join(millis);
        }
    }

    public void join() throws InterruptedException {
        for (Thread thread : workers) {
            thread.join();
        }
    }

    @Override
    public void close() {
        isWorking = false;
        workers.forEach(Thread::interrupt);
    }


    public class Worker extends Thread {
        @Override
        public void run() {
            while (isWorking && !Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = tasks.take();
                    task.run();
                } catch (InterruptedException e) {
                    LOGGER.warn(String.format("%s: %s", e, e.getMessage()));
                }
            }
        }
    }
}
