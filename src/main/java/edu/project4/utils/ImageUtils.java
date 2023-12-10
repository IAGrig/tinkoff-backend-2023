package edu.project4.utils;

import edu.project4.FractalImage;
import edu.project4.ImageFormat;
import edu.project4.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private final static int RED = 16;
    private final static int GREEN = 8;

    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage result = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                if (image.contains(x, y)) {
                    Pixel pixel = image.getPixel(x, y);
                    int rgb = (pixel.getRed() << RED) | (pixel.getGreen() << GREEN) | pixel.getBlue();
                    result.setRGB(x, y, rgb);
                } else {
                    result.setRGB(x, y, 0);
                }
            }
        }

        try {
            Path fullPath = Path.of(filename.toString() + format.name().toLowerCase());
            File outputFile = fullPath.toFile();
//            Files.createDirectories(fullpath.getParent());
//            File outputfile = addExtensionToPath(filename, format.name().toLowerCase()).toFile();
            ImageIO.write(result, format.name().toLowerCase(), outputFile);
//            LOGGER.info("Изображение сохранено в " + fullpath);
        } catch (IOException e) {
//            LOGGER.error(e.getMessage());
        }
    }
}
