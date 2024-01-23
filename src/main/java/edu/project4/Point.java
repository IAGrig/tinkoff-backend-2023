package edu.project4;

public record Point(double x, double y) {
    public double getHyp() {
        return Math.sqrt(x * x + y * y);
    }
}
