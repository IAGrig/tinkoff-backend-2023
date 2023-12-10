package edu.project4;

import edu.project4.imageProcessing.GammaCorrection;
import edu.project4.imageProcessing.ImageProcessor;
import edu.project4.renderers.Renderer;
import edu.project4.renderers.SingleThreadRenderer;
import edu.project4.transformations.*;
import edu.project4.utils.ImageUtils;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FractalImageTest {
    @Test
    public void fractalImageTest(){

        List<Transformation> transformations = List.of(
            new CylinderTransformation(),
            new DiamondTransformation(),
            new DiskTransformation(),
            new ExponentialTransformation(),
            new EyefishTransformation(),
            new HeartTransformation(),
            new HorseshoeTransformation(),
            new LinearTransformation(),
            new PolarTransformation(),
            new SinusoidalTransformation(),
            new SphericalTransformation(),
            new TangentTransformation()
        );

        List<AffineCoefficients> affines = Stream.generate(AffineCoefficients::create).limit(6).toList();


        Renderer renderer = new SingleThreadRenderer();

        FractalImage fractalImage = FractalImage.create(1980, 1080);
        Rect world = new Rect(-10, -10, 20,20);
        renderer.render(fractalImage, world, transformations, affines,30000, (short) 150, 16, 123);
        ImageProcessor gamma = new GammaCorrection();
        gamma.process(fractalImage);

        ImageUtils.save(fractalImage, Path.of("src/main/java/edu/project4/fractal."), ImageFormat.JPEG);
    }
}
