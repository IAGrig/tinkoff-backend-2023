package edu.project4.transformations;

import edu.project4.Point;

public class HorseshoeTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = 1 / point.getHyp() * (point.x() - point.y()) * (point.x() + point.y());
        double y = 1 / point.getHyp() * 2 * point.x() * point.y();
        return new Point(x, y);
    }
}
