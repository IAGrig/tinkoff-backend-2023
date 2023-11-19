package edu.project3;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;

public class LogReport {
    private final List<String> files;
    private final OffsetDateTime fromDate;
    private final OffsetDateTime toDate;
    private int requestsCount;
    private int answersCount;
    private int totalAnswersSize;
    private final HashMap<String, Integer> requestedResources;
    private final HashMap<Integer, Integer> answerCodes;

    public LogReport(List<String> files, OffsetDateTime fromDate, OffsetDateTime toDate) {
        this.files = files;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.requestedResources = new HashMap<>();
        this.answerCodes = new HashMap<>();
    }

    public void addRequest(String requestedResource) {
        requestsCount++;
        requestedResources.put(requestedResource, requestedResources.getOrDefault(requestedResource, 0) + 1);
    }

    public void addAnswer(int answerCode, int answersSize) {
        answersCount++;
        totalAnswersSize += answersSize;
        answerCodes.put(answerCode, answerCodes.getOrDefault(answerCode, 0) + 1);
    }

    public List<String> getFiles() {
        return files;
    }

    public OffsetDateTime getFromDate() {
        return fromDate;
    }

    public OffsetDateTime getToDate() {
        return toDate;
    }

    public int getRequestsCount() {
        return requestsCount;
    }

    public int getAverageAnswerSize() {
        return totalAnswersSize / answersCount;
    }

    public HashMap<String, Integer> getRequestedResources() {
        return requestedResources;
    }

    public HashMap<Integer, Integer> getAnswerCodes() {
        return answerCodes;
    }
}
