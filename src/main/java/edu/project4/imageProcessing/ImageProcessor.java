package edu.project4.imageProcessing;

import edu.project4.FractalImage;

@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);
}
