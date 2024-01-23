package edu.project4.transformations;

import edu.project4.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.atan(point.y() / point.x()) / Math.PI * 2;
        double y = point.getHyp() - 1;
        return new Point(x, y);
    }
}
