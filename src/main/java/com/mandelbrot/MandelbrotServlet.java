package com.mandelbrot;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mandelbrot")
public class MandelbrotServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double centerX = getDoubleParam(req, "centerX", -0.7);
        double centerY = getDoubleParam(req, "centerY", 0);
        double scale = getDoubleParam(req, "scale", 4.0);
        int width = getIntParam(req, "width", 800);
        int height = getIntParam(req, "height", 600);

        BufferedImage image = Mandelbrot.generate(width, height, centerX, centerY, scale);

        resp.setContentType("image/png");
        ImageIO.write(image, "png", resp.getOutputStream());
    }

    private double getDoubleParam(HttpServletRequest req, String name, double defaultValue) {
        String value = req.getParameter(name);
        if (value != null) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                // Ignore and return default
            }
        }
        return defaultValue;
    }

    private int getIntParam(HttpServletRequest req, String name, int defaultValue) {
        String value = req.getParameter(name);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                // Ignore and return default
            }
        }
        return defaultValue;
    }
}
