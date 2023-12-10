package edu.project4.renderers;

import edu.project4.AffineCoefficients;
import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SingleThreadRenderer implements Renderer {
    private static Point applyAffineTransformation(Point point, AffineCoefficients affine) {
        double x = affine.a() * point.x() + affine.b() * point.y() + affine.c();
        double y = affine.d() * point.x() + affine.e() * point.y() + affine.f();
        return new Point(x, y);
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        List<AffineCoefficients> affines,
        int samples,
        short iterPerSample,
        long seed
    ) {
        final double XMIN = -1;
        final double XMAX = 1;
        final double YMIN = -1;
        final double YMAX = 1;

        for (int sample = 0; sample < samples; sample++) {
            Point point = world.getRandomPoint();
            for (int iteration = -20; iteration < iterPerSample; iteration++) {
                AffineCoefficients affine = affines.get(ThreadLocalRandom.current().nextInt(0, affines.size()));
                point = applyAffineTransformation(point, affine);
                Transformation transformation =
                    variations.get(ThreadLocalRandom.current().nextInt(0, variations.size()));
                point = transformation.apply(point); //
                if (iteration >= 0 && world.contains(point)) {
                    int x = canvas.width() - (int) (((XMAX - point.x()) / (XMAX - XMIN)) * canvas.width());
                    int y = canvas.height() - (int) (((YMAX - point.y()) / (YMAX - YMIN)) * canvas.height());

                    if (canvas.contains(x, y)) {
                        int red;
                        int green;
                        int blue;
                        Pixel pixel = canvas.getPixel(x, y);
                        if (pixel.getHitCount() == 0) {
                            red = affine.red();
                            green = affine.green();
                            blue = affine.blue();
                        } else {
                            red = (pixel.getRed() + affine.red()) / 2;
                            green = (pixel.getGreen() + affine.green()) / 2;
                            blue = (pixel.getBlue() + affine.blue()) / 2;
                        }

                        canvas.setPixel(x, y, new Pixel(red, green, blue, pixel.getHitCount() + 1));
                    }
                }
            }
        }
        return canvas;
    }
}
