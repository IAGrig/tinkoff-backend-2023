package edu.project4;

import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double a, double b, double c, double d, double e, double f, int red, int green,
                                 int blue) {
    public static AffineCoefficients create() {
        double a = ThreadLocalRandom.current().nextDouble(-1, 1);
        double b = ThreadLocalRandom.current().nextDouble(-1, 1);
        double c = ThreadLocalRandom.current().nextDouble(-1, 1);
        double d = ThreadLocalRandom.current().nextDouble(-1, 1);
        double e = ThreadLocalRandom.current().nextDouble(-1, 1);
        double f = ThreadLocalRandom.current().nextDouble(-1, 1);
        int red = getRandomColorValue();
        int green = getRandomColorValue();
        int blue = getRandomColorValue();
        if (checkCoefficients(a, b, c, d, e, f)) {
            return new AffineCoefficients(a, b, c, d, e, f, red, green, blue);
        }
        return AffineCoefficients.create();
    }

    private static boolean checkCoefficients(double a, double b, double c, double d, double e, double f) {
        return (a * a + d * d < 1) && (b * b + e * e < 1) &&
            (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }

    private static int getRandomColorValue() {
        return ThreadLocalRandom.current().nextInt(0, 256);
    }

}
