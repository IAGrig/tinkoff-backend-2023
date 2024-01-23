package edu.project3;

import edu.project3.renderers.AdocRenderer;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AdocRendererTest {
    private final static AdocRenderer renderer = new AdocRenderer();

    @Test
    public void renderNullTest() {
        assertThatThrownBy(() -> renderer.render(null, "", false)).isExactlyInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> renderer.render(
            new LogReport(List.of(), OffsetDateTime.MIN, OffsetDateTime.MAX),
            null,
            false
        )).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
