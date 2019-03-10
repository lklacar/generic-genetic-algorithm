package com.lukaklacar.geneticalgorithm.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;

public class ImageUtil {

    public static byte[] getImageBytes(BufferedImage image) {
        return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    }

    public static BufferedImage loadImageFromFile(String path) {
        // TODO: Handle exception
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static BufferedImage createImageFromBytes(byte[] imageData) {

        // convert byte array back to BufferedImage
        InputStream in = new ByteArrayInputStream(imageData);
        try {
            BufferedImage bImageFromConvert = ImageIO.read(in);
            return bImageFromConvert;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
