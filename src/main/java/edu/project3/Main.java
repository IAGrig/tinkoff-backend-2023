package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String path = CmdArgumentsHandler.getPath(args);
        OffsetDateTime fromDate = CmdArgumentsHandler.getFromDate(args);
        OffsetDateTime toDate = CmdArgumentsHandler.getToDate(args);
        OutputFormat format = CmdArgumentsHandler.getOutputFormat(args);

        FilesReader filesReader = new FilesReader(path);
        Stream<String> fileLinesStream = filesReader.lines();
        List<String> files = filesReader.getPaths();
        StatCounter statCounter = new StatCounter(files, fromDate, toDate);
        LogReport logReport = statCounter.getReport(fileLinesStream);
        Renderer renderer = new Renderer();
        renderer.render(logReport, format);
    }

}
