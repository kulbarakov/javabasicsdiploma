package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;


public class Converter implements TextGraphicsConverter {
    private int width = Integer.MAX_VALUE;
    private int height = Integer.MAX_VALUE;
    private double maxRatio = Double.MAX_VALUE;
    private TextColorSchema schema = new Schema();

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));

        BufferedImage bwImg = getScaledBufferedImage(img);
        int newWidth = bwImg.getWidth();
        int newHeight = bwImg.getHeight();

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);

        WritableRaster bwRaster = bwImg.getRaster();
        char[][] imgMatrix = pixelsToChar(bwRaster, newWidth, newHeight);

        return charMatrixToString(imgMatrix);
    }

    private BufferedImage getScaledBufferedImage(BufferedImage img) throws BadImageSizeException {
        double imgRatio = (double) img.getWidth() / img.getHeight();
        if ((imgRatio > maxRatio))
            throw new BadImageSizeException(imgRatio, maxRatio);

        int newWidth = img.getWidth();
        int newHeight = img.getHeight();
        if ((img.getWidth() > width) || (img.getHeight() > height)) {
            if (img.getWidth() > img.getHeight()) {
                newWidth = width;
                newHeight = (int) (newWidth / imgRatio);
            } else {
                newHeight = height;
                newWidth = (int) (newHeight * imgRatio);
            }
        }
        return new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
    }

    private char[][] pixelsToChar(WritableRaster bwRaster, int width, int height) {
        int[] buf = new int[3];
        char[][] imgMatrix = new char[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int color = bwRaster.getPixel(w, h, buf)[0];
                char c = schema.convert(color);
                imgMatrix[h][w] = c;
            }
        }
        return imgMatrix;
    }

    private String charMatrixToString(char[][] imgMatrix) {
        StringBuilder result = new StringBuilder();
        for (char[] matrix : imgMatrix) {
            for (char c : matrix) {
                result.append(c).append(c);
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }

}
