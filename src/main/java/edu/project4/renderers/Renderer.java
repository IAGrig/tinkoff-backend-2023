package edu.project4.renderers;

import edu.project4.AffineCoefficients;
import edu.project4.FractalImage;
import edu.project4.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        List<AffineCoefficients> affines,
        int samples,
        short iterPerSample,
        int symmetry
    );
}
