package edu.project3.renderers;

import edu.project3.LogReport;

public interface Renderer {
    void render(LogReport logReport, String filepath, boolean addFormatExtensionToFilename);
}
