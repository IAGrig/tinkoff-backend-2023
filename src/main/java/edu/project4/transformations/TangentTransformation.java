package edu.project4.transformations;

import edu.project4.Point;

public class TangentTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.sin(point.x()) / Math.cos(point.y());
        double y = Math.tan(point.y());
        return new Point(x, y);
    }
}
