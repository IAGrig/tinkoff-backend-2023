package edu.project4.transformations;

import edu.project4.Point;

public class SinusoidalTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = Math.sin(point.x());
        double y = Math.sin(point.y());
        return new Point(x, y);
    }
}
