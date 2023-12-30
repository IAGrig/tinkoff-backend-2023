package edu.project4;

import java.util.concurrent.ThreadLocalRandom;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        return (x <= p.x() && p.x() < width + x) && (y <= p.y() && p.y() < height + y);
    }

    public Point getRandomPoint() {
        double x = ThreadLocalRandom.current().nextDouble(0, width);
        double y = ThreadLocalRandom.current().nextDouble(0, width);
        return new Point(x, y);
    }
}
