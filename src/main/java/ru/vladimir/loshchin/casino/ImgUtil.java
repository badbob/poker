package ru.vladimir.loshchin.casino;

import java.awt.image.BufferedImage;

public class ImgUtil {

    public static Matrix imgToMatrix(BufferedImage img) {
        var result = new Matrix(img.getWidth(), img.getHeight());
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                if (img.getRGB(x, y) != -1) {
                    result.set(x, y);
                }
            }
        }
        return result;
    }
}
