package edu.project3.renderers;

import edu.project3.LogReport;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractRenderer implements Renderer {

    @Override
    public void render(LogReport logReport, String filepath, boolean addFormatExtensionToFilename) {
        Path path = Path.of(filepath);
        try (OutputStream out = Files.newOutputStream(path)) {
            writeMainTable(logReport, out);
            writeRequestedResources(logReport, out);
            writeAnswersCodes(logReport, out);
            writeHttpMethods(logReport, out);
            writeUserAgents(logReport, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void writeMainTable(LogReport logReport, OutputStream out) throws IOException;

    protected abstract void writeRequestedResources(LogReport logReport, OutputStream out) throws IOException;

    protected abstract void writeAnswersCodes(LogReport logReport, OutputStream out) throws IOException;

    protected abstract void writeHttpMethods(LogReport logReport, OutputStream out) throws IOException;

    protected abstract void writeUserAgents(LogReport logReport, OutputStream out) throws IOException;
}
