package edu.hw8.task3;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class MultiThreadPasswordHacker extends AbstractPasswordHacker {
    private final int threadsCount;
    private final ConcurrentHashMap<String, String> results;
    private final CountDownLatch countDownLatch;

    public MultiThreadPasswordHacker(
        Map<String, String> leakedPasswords,
        String alphabet,
        int minPasswordsLength,
        int maxPasswordsLength,
        int threadsCount
    ) {
        super(leakedPasswords, alphabet, minPasswordsLength, maxPasswordsLength);
        this.results = new ConcurrentHashMap<>();
        this.threadsCount = threadsCount;
        this.countDownLatch = new CountDownLatch(threadsCount * (maxPasswordsLength - minPasswordsLength + 1));
    }

    @Override
    public Map<String, String> hack() {
        IntStream.range(minPasswordsLength, maxPasswordsLength + 1).forEach(this::hackPasswordsWithLength);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    private void hackPasswordsWithLength(int passwordsLength) {
        int indexesPerThread = (int) (Math.ceil((double) alphabet.length() / threadsCount));
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        for (int i = 0; i < alphabet.length(); i += indexesPerThread) {
            final int startIndex = i;
            final int finishIndex = Math.min(i + indexesPerThread, alphabet.length());
            executorService.execute(() -> {
                PasswordsGenerator passwordsGenerator =
                    new PasswordsGenerator(alphabet, passwordsLength, startIndex, finishIndex);
                while (results.size() != leakedPasswords.size()) {
                    try {
                        String password = passwordsGenerator.nextPassword();
                        handlePassword(password, results);
                    } catch (NoSuchElementException e) {
                        break;
                    }
                }
                if (results.size() == leakedPasswords.size()) {
                    executorService.shutdownNow();
                }
                countDownLatch.countDown();
            });
        }
        executorService.close();
    }
}
