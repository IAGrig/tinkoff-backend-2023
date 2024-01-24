package edu.project4;

import edu.project4.imageProcessing.GammaCorrection;
import edu.project4.imageProcessing.ImageProcessor;
import edu.project4.renderers.ParallelRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.transformations.CylinderTransformation;
import edu.project4.transformations.DiamondTransformation;
import edu.project4.transformations.DiskTransformation;
import edu.project4.transformations.ExponentialTransformation;
import edu.project4.transformations.EyefishTransformation;
import edu.project4.transformations.HeartTransformation;
import edu.project4.transformations.HorseshoeTransformation;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.PolarTransformation;
import edu.project4.transformations.SinusoidalTransformation;
import edu.project4.transformations.SphericalTransformation;
import edu.project4.transformations.TangentTransformation;
import edu.project4.transformations.Transformation;
import edu.project4.utils.ImageUtils;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class ParallelFractalImageTest {
    @Test
    public void fractalImageTest() {

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
        Rect world = new Rect(-10, -10, 20, 20);
        renderer.render(fractalImage, world, transformations, affines, 10000, (short) 300, 5);
        ImageProcessor gamma = new GammaCorrection(0.9);
        gamma.process(fractalImage);

        ImageUtils.save(fractalImage, Path.of("src/main/java/edu/project4/parallelfractal."), ImageFormat.JPEG);
    }
}
