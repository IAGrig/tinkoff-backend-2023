package edu.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FilesReader {
    String path;

    public FilesReader(String path) {
        this.path = path;
    }

    public Stream<String> lines() {
        if (Pattern.matches("^https?://.*\\..*", path)) {
            return getLinesFromURL(path);
        } else {
            return getLinesFromFilePattern(path);
        }
    }

    public List<String> getPaths() {
        if (Pattern.matches("^https?://.*\\..*", path)) {
            return List.of(path);
        } else {
            try {
                PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + path);
                Stream<Path> pathStream = Files.find(Path.of(""), Integer.MAX_VALUE, (p, f) -> pathMatcher.matches(p));
                return pathStream.map(Path::toString).toList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private Stream<String> getLinesFromURL(String path) {
        try {
            URL url = new URI(path).toURL();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            return reader.lines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<String> getLinesFromFilePattern(String path) {
        Stream<String> result = Stream.empty();
        try {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + path);
            Stream<Path> pathStream = Files.find(Path.of(""), Integer.MAX_VALUE, (p, f) -> pathMatcher.matches(p));
            result = pathStream.flatMap(p -> {
                try {
                    return Files.lines(p);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
