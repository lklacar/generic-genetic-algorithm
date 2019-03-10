package com.lukaklacar.geneticalgorithm.example;

import com.lukaklacar.geneticalgorithm.algorithm.GeneticAlgorithm;
import com.lukaklacar.geneticalgorithm.algorithm.GeneticAlgorithmConfiguration;
import com.lukaklacar.geneticalgorithm.algorithm.IterationCountThreshold;
import com.lukaklacar.geneticalgorithm.algorithm.Threshold;
import com.lukaklacar.geneticalgorithm.chromosome.Chromosome;
import com.lukaklacar.geneticalgorithm.chromosome.ChromosomePair;
import com.lukaklacar.geneticalgorithm.population.Population;
import com.lukaklacar.geneticalgorithm.selection.RouletteSelectionAlgorithm;
import com.lukaklacar.geneticalgorithm.selection.SelectionAlgorithm;
import com.lukaklacar.geneticalgorithm.util.ImageUtil;
import com.lukaklacar.geneticalgorithm.util.MathUtil;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ImageGeneticAlgorithm extends GeneticAlgorithm<ImageChromosome> {
    private BufferedImage referenceImage;

    public ImageGeneticAlgorithm(GeneticAlgorithmConfiguration geneticAlgorithmConfiguration, BufferedImage referenceImage) {
        super(geneticAlgorithmConfiguration);
        this.referenceImage = referenceImage;
    }

    @Override
    protected Population<ImageChromosome> getInitialPopulation() {
        int size = ImageUtil.getImageBytes(referenceImage).length;
        return IntStream.range(0, geneticAlgorithmConfiguration.getPopulationSize())
                .boxed()
                .map(i -> {
                    byte[] randomBytes = MathUtil.randomByteArray(size);
                    BufferedImage image = ImageUtil.createImageFromBytes(randomBytes);
                    return new ImageChromosome(referenceImage, image);
                }).collect(Collectors.toCollection(Population::new));
    }

    @Override
    protected ChromosomePair<ImageChromosome> crossover(ImageChromosome chromosome1, ImageChromosome chromosome2) {
        byte[] bytes1 = ImageUtil.getImageBytes(chromosome1.getCurrentImageValue());
        byte[] bytes2 = ImageUtil.getImageBytes(chromosome2.getCurrentImageValue());

        int pivot = MathUtil.randomInteger(0, bytes1.length);

        for (int i = 0; i < pivot; i++) {
            byte temp = bytes1[i];
            bytes1[i] = bytes2[i];
            bytes2[i] = temp;
        }

        ImageChromosome child1 = new ImageChromosome(chromosome1.getImageReference(), ImageUtil.createImageFromBytes(bytes1));
        ImageChromosome child2 = new ImageChromosome(chromosome1.getImageReference(), ImageUtil.createImageFromBytes(bytes2));


        return ChromosomePair
                .<ImageChromosome>builder()
                .chromosome1(child1)
                .chromosome2(child2)
                .build();
    }

    @Override
    protected SelectionAlgorithm<ImageChromosome> getSelectionAlgorithm() {
        return new RouletteSelectionAlgorithm<>();
    }

    @Override
    protected Threshold getThreshold() {
        return new IterationCountThreshold(500);
    }

    @Override
    protected ImageChromosome mutate(ImageChromosome chromosome) {
        byte[] imageBytes = ImageUtil.getImageBytes(chromosome.getCurrentImageValue());
        for (int i = 0; i < 100; i++) {
            int randomPosition = MathUtil.randomInteger(0, imageBytes.length);
            imageBytes[randomPosition] = (byte) MathUtil.randomInteger(0, 255);
        }
        BufferedImage mutatedImage = ImageUtil.createImageFromBytes(imageBytes);
        return new ImageChromosome(chromosome.getImageReference(), mutatedImage);
    }
}
