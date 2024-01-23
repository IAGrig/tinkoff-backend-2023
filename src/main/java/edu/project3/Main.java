package edu.project3;

import edu.project3.renderers.Renderer;
import edu.project3.renderers.RenderersHelper;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String path = CmdArgumentsHelper.getPath(args);
        OffsetDateTime fromDate = CmdArgumentsHelper.getFromDate(args);
        OffsetDateTime toDate = CmdArgumentsHelper.getToDate(args);
        OutputFormat format = CmdArgumentsHelper.getOutputFormat(args);
        String resultPath = CmdArgumentsHelper.getResultPath(args);

        FilesReader filesReader = new FilesReader(path);
        Stream<String> fileLinesStream = filesReader.lines();
        List<String> files = filesReader.getPaths();
        StatCounter statCounter = new StatCounter(files, fromDate, toDate);
        LogReport logReport = statCounter.getReport(fileLinesStream);
        Renderer renderer = RenderersHelper.getRendererWithFormat(format);
        renderer.render(logReport, resultPath, true);
    }

}
