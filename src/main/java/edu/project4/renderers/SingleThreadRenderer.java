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
        int symmetry
    ) {
        final double XMIN = -1.777;
        final double XMAX = 1.777;
        final double YMIN = -1;
        final double YMAX = 1;
        final int iterationsStart = -20;

        for (int sample = 0; sample < samples; sample++) {
            Point point = world.getRandomPoint();
            for (int iteration = iterationsStart; iteration < iterPerSample; iteration++) {
                AffineCoefficients affine = affines.get(ThreadLocalRandom.current().nextInt(0, affines.size()));
                point = applyAffineTransformation(point, affine);
                Transformation transformation =
                    variations.get(ThreadLocalRandom.current().nextInt(0, variations.size()));
                point = transformation.apply(point); //
                if (iteration >= 0 && world.contains(point)) {
                    double theta2 = 0.0;
                    for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                        Point rotatedPoint = getRotatedPoint(point, theta2);
                        if (!world.contains(rotatedPoint)) {
                            continue;
                        }

                        int x = canvas.width() - (int) (((XMAX - rotatedPoint.x()) / (XMAX - XMIN)) * canvas.width());
                        int y = canvas.height() - (int) (((YMAX - rotatedPoint.y()) / (YMAX - YMIN)) * canvas.height());

                        if (canvas.contains(x, y)) {
                            int red;
                            int green;
                            int blue;
                            Pixel pixel = canvas.getPixel(x, y);
                            synchronized (pixel) {
                                if (pixel.getHitCount() == 0) {
                                    pixel.setR(affine.red());
                                    pixel.setG(affine.green());
                                    pixel.setB(affine.blue());
                                } else {
                                    pixel.setR((pixel.getRed() + affine.red()) / 2);
                                    pixel.setG((pixel.getGreen() + affine.green()) / 2);
                                    pixel.setB((pixel.getBlue() + affine.blue()) / 2);
                                }
                                pixel.incrementHitCount();
                            }
                        }
                    }
                }
            }
        }
        return canvas;
    }

    private Point getRotatedPoint(Point point, double theta2) {
        double x = point.x() * Math.cos(theta2) - point.y() * Math.sin(theta2);
        double y = point.x() * Math.sin(theta2) + point.y() * Math.cos(theta2);
        return new Point(x, y);
    }
}
