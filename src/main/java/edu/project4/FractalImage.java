package edu.project4;

public record FractalImage(Pixel[][] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[][] data = new Pixel[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
//                data[y][x] = new Pixel(255, 255,255, 0);
                data[y][x] = new Pixel(0, 0, 0, 0);
            }
        }

        return new FractalImage(data, width, height);
    }

    public boolean contains(int x, int y) {
        return (0 <= x && x < width) && (0 <= y && y < height);
    }

    public Pixel getPixel(int x, int y) {
        // mb check if contains
        return data[y][x];
    }

    public void setPixel(int x, int y, Pixel pixel) {
        data[y][x] = pixel;
    }
}
