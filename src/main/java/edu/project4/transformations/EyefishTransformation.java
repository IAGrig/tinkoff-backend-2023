package edu.project4.transformations;

import edu.project4.Point;

public class EyefishTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = 2 / (point.getHyp() + 1) * point.x();
        double y = 2 / (point.getHyp() + 1) * point.y();
        return new Point(x, y);
    }
}
