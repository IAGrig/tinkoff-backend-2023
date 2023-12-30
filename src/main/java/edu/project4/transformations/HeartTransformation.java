package edu.project4.transformations;

import edu.project4.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.getHyp() * Math.sin(point.getHyp() * Math.atan(point.y() / point.x())) * 0.8;
        double y = -point.getHyp() * Math.cos(point.getHyp() * Math.atan(point.y() / point.x())) * 0.65 - 0.5;
        return new Point(x, y);
    }
}
