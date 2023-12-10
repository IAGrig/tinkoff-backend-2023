package edu.project4;

public class Pixel {
    private final int r;
    private final int g;
    private final int b;
    private final int hitCount;

    public Pixel(int r, int g, int b, int hitCount) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
    }

    public int getRed() {
        return r;
    }

    public int getGreen() {
        return g;
    }

    public int getBlue() {
        return b;
    }

    public int getHitCount() {
        return hitCount;
    }
}


