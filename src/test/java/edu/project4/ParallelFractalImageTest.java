package edu.project4;

import edu.project4.imageProcessing.GammaCorrection;
import edu.project4.imageProcessing.ImageProcessor;
import edu.project4.renderers.Renderer;
import edu.project4.renderers.ParallelRenderer;
import edu.project4.transformations.*;
import edu.project4.utils.ImageUtils;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ParallelFractalImageTest {
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


        Renderer renderer = new ParallelRenderer(12);

        FractalImage fractalImage = FractalImage.create(1980, 1080);
        Rect world = new Rect(-10, -10, 20,20);
        renderer.render(fractalImage, world, transformations, affines,50000, (short) 550, 5, 123);
        ImageProcessor gamma = new GammaCorrection(0.9);
        gamma.process(fractalImage);

        ImageUtils.save(fractalImage, Path.of("src/main/java/edu/project4/parallelfractal."), ImageFormat.JPEG);
    }
}
