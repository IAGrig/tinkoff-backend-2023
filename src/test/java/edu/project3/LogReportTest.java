package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LogReportTest {
    private static final LogReport logReport = new LogReport(List.of(), OffsetDateTime.MIN, OffsetDateTime.MAX);

    @Test
    public void addRequestTest() {
        Map<String, Integer> map = logReport.getRequestedResources();

        assertThat(map.isEmpty()).isTrue();
        logReport.addRequest("test");
        assertThat(map.get("test")).isEqualTo(1);

        assertThatThrownBy(() -> logReport.addRequest(null)).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void addAnswerTest() {
        Map<Integer, Integer> map = logReport.getAnswerCodes();

        assertThat(map.isEmpty()).isTrue();
        logReport.addAnswer(0, 0);
        assertThat(map.get(0)).isEqualTo(1);
    }

    @Test
    public void addHttpMethodTest() {
        Map<String, Integer> map = logReport.getHttpMethods();

        assertThat(map.isEmpty()).isTrue();
        logReport.addHttpMethod("test");
        assertThat(map.get("test")).isEqualTo(1);

        assertThatThrownBy(() -> logReport.addHttpMethod(null)).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void addUserAgentTest() {
        Map<String, Integer> map = logReport.getUserAgents();

        assertThat(map.isEmpty()).isTrue();
        logReport.addUserAgent("test");
        assertThat(map.get("test")).isEqualTo(1);

        assertThatThrownBy(() -> logReport.addUserAgent(null)).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
