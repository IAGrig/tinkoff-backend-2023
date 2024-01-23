package edu.project4;

import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double a, double b, double c, double d, double e, double f, int red, int green,
                                 int blue) {
    private static final int RGB_MAX_BOUND = 256;

    public static AffineCoefficients create() {
        double a;
        double b;
        double c;
        double d;
        double e;
        double f;
        int red;
        int green;
        int blue;
        do {
            a = ThreadLocalRandom.current().nextDouble(-1, 1);
            b = ThreadLocalRandom.current().nextDouble(-1, 1);
            c = ThreadLocalRandom.current().nextDouble(-1, 1);
            d = ThreadLocalRandom.current().nextDouble(-1, 1);
            e = ThreadLocalRandom.current().nextDouble(-1, 1);
            f = ThreadLocalRandom.current().nextDouble(-1, 1);
            red = getRandomColorValue();
            green = getRandomColorValue();
            blue = getRandomColorValue();
        } while (!checkCoefficients(a, b, c, d, e, f));
        return new AffineCoefficients(a, b, c, d, e, f, red, green, blue);
    }

    private static boolean checkCoefficients(double a, double b, double c, double d, double e, double f) {
        return (a * a + d * d < 1) && (b * b + e * e < 1)
            && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }

    private static int getRandomColorValue() {
        return ThreadLocalRandom.current().nextInt(0, RGB_MAX_BOUND);
    }

}
