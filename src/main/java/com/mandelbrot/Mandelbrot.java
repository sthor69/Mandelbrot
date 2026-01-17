package com.mandelbrot;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Mandelbrot {

    private static final int MAX_ITER = 1000;

    public static BufferedImage generate(int width, int height, double centerX, double centerY, double scale) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double zx = 0;
                double zy = 0;
                double cX = centerX + (x - width / 2.0) * scale / width;
                double cY = centerY + (y - height / 2.0) * scale / width;
                int iter = 0;
                while (zx * zx + zy * zy < 4 && iter < MAX_ITER) {
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter++;
                }
                if (iter == MAX_ITER) {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    float hue = (float) iter / MAX_ITER;
                    int color = Color.HSBtoRGB(hue, 1, 1);
                    image.setRGB(x, y, color);
                }
            }
        }
        return image;
    }
}
