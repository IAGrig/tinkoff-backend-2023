package edu.project3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class FilesReaderTest {
    private final String testFilePath = "src/main/resources/project3/logs.txt";
    private final String testURLPath =
        "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
    private final FilesReader fileReader = new FilesReader(testFilePath);
    private final FilesReader URLReader = new FilesReader(testURLPath);
    private final FilesReader emptyPathReader = new FilesReader("");
    private final FilesReader nullReader = new FilesReader(null);

    @Test
    public void linesTest() {
        assertThat(fileReader.lines().toList().isEmpty()).isFalse();
        assertThat(URLReader.lines().toList().isEmpty()).isFalse();
        assertThat(emptyPathReader.lines()).isNotNull();
        assertThatThrownBy(nullReader::lines).isExactlyInstanceOf(NullPointerException.class);
    }

    @Test
    public void getPathsTest() {
        assertThat(fileReader.getPaths().isEmpty()).isFalse();
        assertThat(URLReader.getPaths().isEmpty()).isFalse();
        assertThat(emptyPathReader.getPaths()).isNotNull();
        assertThatThrownBy(nullReader::getPaths).isExactlyInstanceOf(NullPointerException.class);
    }
}
