package edu.project3;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogReport {
    private final List<String> files;
    private final OffsetDateTime fromDate;
    private final OffsetDateTime toDate;
    private final Map<String, Integer> requestedResources;
    private final Map<Integer, Integer> answerCodes;
    private final Map<String, Integer> httpMethods;
    private final Map<String, Integer> userAgents;
    private int requestsCount;
    private int answersCount;
    private int totalAnswersSize;

    public LogReport(List<String> files, OffsetDateTime fromDate, OffsetDateTime toDate) {
        this.files = files;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.requestedResources = new HashMap<>();
        this.answerCodes = new HashMap<>();
        this.httpMethods = new HashMap<>();
        this.userAgents = new HashMap<>();
    }

    public void addRequest(String requestedResource) {
        if (requestedResource == null) {
            throw new IllegalArgumentException();
        }
        requestsCount++;
        requestedResources.put(requestedResource, requestedResources.getOrDefault(requestedResource, 0) + 1);
    }

    public void addAnswer(int answerCode, int answersSize) {
        answersCount++;
        totalAnswersSize += answersSize;
        answerCodes.put(answerCode, answerCodes.getOrDefault(answerCode, 0) + 1);
    }

    public void addHttpMethod(String httpMethod) {
        if (httpMethod == null) {
            throw new IllegalArgumentException();
        }
        httpMethods.put(httpMethod, httpMethods.getOrDefault(httpMethod, 0) + 1);
    }

    public void addUserAgent(String userAgent) {
        if (userAgent == null) {
            throw new IllegalArgumentException();
        }
        userAgents.put(userAgent, userAgents.getOrDefault(userAgent, 0) + 1);
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
        if (answersCount == 0) {
            return 0;
        }
        return totalAnswersSize / answersCount;
    }

    public Map<String, Integer> getRequestedResources() {
        return requestedResources;
    }

    public Map<Integer, Integer> getAnswerCodes() {
        return answerCodes;
    }

    public Map<String, Integer> getHttpMethods() {
        return httpMethods;
    }

    public Map<String, Integer> getUserAgents() {
        return userAgents;
    }
}
