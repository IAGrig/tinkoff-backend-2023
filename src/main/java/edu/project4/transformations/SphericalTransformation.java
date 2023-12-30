package edu.project4.transformations;

import edu.project4.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x() / (point.getHyp() * point.getHyp());
        double y = point.y() / (point.getHyp() * point.getHyp());
        return new Point(x, y);
    }
}
