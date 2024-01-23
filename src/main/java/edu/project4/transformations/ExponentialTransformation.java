package edu.project4.transformations;

import edu.project4.Point;

public class ExponentialTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.exp(point.x() - 1) * Math.cos(Math.PI * point.y());
        double y = Math.exp(point.x() - 1) * Math.sin(Math.PI * point.y());
        return new Point(x, y);
    }
}
