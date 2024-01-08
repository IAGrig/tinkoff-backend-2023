package edu.project3;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CmdArgumentsHelperTest {
    @Test
    public void getPathTest() {
        String[] args = new String[] {"--path", "test"};
        String[] argsWithoutPath = new String[] {"--test", "test"};
        String[] emptyArgs = new String[0];

        assertThat(CmdArgumentsHelper.getPath(args)).isEqualTo("test");
        assertThat(CmdArgumentsHelper.getPath(argsWithoutPath)).isBlank();
        assertThat(CmdArgumentsHelper.getPath(emptyArgs)).isBlank();
        assertThatThrownBy(() -> CmdArgumentsHelper.getPath(null)).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void getFromDateTest() {
        String[] args = new String[] {"--from", "2023-08-31"};
        String[] argsWithoutDate = new String[] {"--test", "test"};
        String[] emptyArgs = new String[0];

        assertThat(CmdArgumentsHelper.getFromDate(args)).isEqualTo(OffsetDateTime.of(
            2023,
            8,
            31,
            0,
            0,
            0,
            0,
            ZoneOffset.UTC
        ));
        assertThat(CmdArgumentsHelper.getFromDate(argsWithoutDate)).isEqualTo(OffsetDateTime.MIN);
        assertThat(CmdArgumentsHelper.getFromDate(emptyArgs)).isEqualTo(OffsetDateTime.MIN);
        assertThatThrownBy(() -> CmdArgumentsHelper.getFromDate(null)).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void getToDateTest() {
        String[] args = new String[] {"--to", "2023-08-31"};
        String[] argsWithoutDate = new String[] {"--test", "test"};
        String[] emptyArgs = new String[0];

        assertThat(CmdArgumentsHelper.getToDate(args)).isEqualTo(OffsetDateTime.of(
            2023,
            8,
            31,
            0,
            0,
            0,
            0,
            ZoneOffset.UTC
        ));
        assertThat(CmdArgumentsHelper.getToDate(argsWithoutDate)).isBeforeOrEqualTo(OffsetDateTime.now());
        assertThat(CmdArgumentsHelper.getToDate(emptyArgs)).isBeforeOrEqualTo(OffsetDateTime.now());
        assertThatThrownBy(() -> CmdArgumentsHelper.getToDate(null)).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void getOutputFormatTest() {
        String[] args = new String[] {"--format", "adoc"};
        String[] argsWithoutFormat = new String[] {"--test", "test"};
        String[] emptyArgs = new String[0];

        assertThat(CmdArgumentsHelper.getOutputFormat(args)).isEqualTo(OutputFormat.ADOC);
        assertThat(CmdArgumentsHelper.getOutputFormat(argsWithoutFormat)).isNull();
        assertThat(CmdArgumentsHelper.getOutputFormat(emptyArgs)).isNull();
        assertThatThrownBy(() -> CmdArgumentsHelper.getOutputFormat(null)).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void getResultPathTest() {
        String[] args = new String[] {"--result", "test"};
        String[] argsWithoutResultPath = new String[] {"--test", "test"};
        String[] emptyArgs = new String[0];

        assertThat(CmdArgumentsHelper.getResultPath(args)).isEqualTo("test");
        assertThat(CmdArgumentsHelper.getResultPath(argsWithoutResultPath)).isEqualTo("result");
        assertThat(CmdArgumentsHelper.getResultPath(emptyArgs)).isEqualTo("result");
        assertThatThrownBy(() -> CmdArgumentsHelper.getResultPath(null)).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
