package edu.project4.transformations;

import edu.project4.Point;

public class DiamondTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.sin(Math.atan(point.y() / point.x())) * Math.cos(point.getHyp());
        double y = Math.cos(Math.atan(point.y() / point.x())) * Math.sin(point.getHyp());
        return new Point(x, y);
    }
}
