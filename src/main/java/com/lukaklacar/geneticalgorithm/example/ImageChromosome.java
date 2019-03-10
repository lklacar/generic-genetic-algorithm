package com.lukaklacar.geneticalgorithm.example;

import com.lukaklacar.geneticalgorithm.chromosome.Chromosome;
import com.lukaklacar.geneticalgorithm.util.ImageUtil;

import java.awt.image.BufferedImage;

public class ImageChromosome extends Chromosome {

    private BufferedImage imageReference;
    private BufferedImage currentImageValue;

    public ImageChromosome(BufferedImage imageReference, BufferedImage currentImageValue) {
        this.imageReference = imageReference;
        this.currentImageValue = currentImageValue;
    }

    @Override
    public double getFitness() {
        byte[] referenceImageBytes = ImageUtil.getImageBytes(imageReference);
        byte[] currentImageBytes = ImageUtil.getImageBytes(currentImageValue);

        if (referenceImageBytes.length != currentImageBytes.length) {
            // TODO: Custom exception
            throw new RuntimeException("Image sizes don't match");
        }

        int differenceValue = 0;

        for (int i = 0; i < referenceImageBytes.length; i++) {
            differenceValue = differenceValue + Math.abs(referenceImageBytes[i] - currentImageBytes[i]);
        }

        return Integer.MAX_VALUE - differenceValue;
    }

    public BufferedImage getImageReference() {
        return imageReference;
    }

    public void setImageReference(BufferedImage imageReference) {
        this.imageReference = imageReference;
    }

    public BufferedImage getCurrentImageValue() {
        return currentImageValue;
    }

    public void setCurrentImageValue(BufferedImage currentImageValue) {
        this.currentImageValue = currentImageValue;
    }
}
