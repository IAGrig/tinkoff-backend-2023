package edu.project4.imageProcessing;

import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Point;
import java.util.HashMap;
import java.util.Map;

public class GammaCorrection implements ImageProcessor {
    public static final double GAMMA = 1.9;

    @Override
    public void process(FractalImage image) {
        double max = 0.0;

        Map<Point, Double> correction = new HashMap<>();

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                if (image.contains(x, y)) {
                    Pixel pixel = image.getPixel(x, y);
                    double normal = Math.log10(pixel.getHitCount());
                    max = Math.max(max, normal);
                    correction.put(new Point(x, y), normal);
                }
            }
        }

        for (var entry : correction.entrySet()) {
            Point point = entry.getKey();
            double normal = entry.getValue();
            normal /= max;

            Pixel pixel = image.getPixel((int) point.x(), (int) point.y());

            int newR = (int) (pixel.getRed() * Math.pow(normal, 1.0 / GAMMA));
            int newG = (int) (pixel.getGreen() * Math.pow(normal, 1.0 / GAMMA));
            int newB = (int) (pixel.getBlue() * Math.pow(normal, 1.0 / GAMMA));

            image.setPixel((int) point.x(), (int) point.y(), new Pixel(newR, newG, newB, pixel.getHitCount()));
        }
    }
}
