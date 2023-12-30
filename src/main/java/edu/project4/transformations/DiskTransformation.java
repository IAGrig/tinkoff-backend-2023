package edu.project4.transformations;

import edu.project4.Point;

public class DiskTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = 1 / Math.PI * Math.atan(point.y() / point.x()) * Math.sin(Math.PI * point.getHyp());
        double y = 1 / Math.PI * Math.atan(point.y() / point.x()) * Math.cos(Math.PI * point.getHyp());
        return new Point(x, y);
    }
}
