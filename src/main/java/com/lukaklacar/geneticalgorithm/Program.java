package com.lukaklacar.geneticalgorithm;

import com.lukaklacar.geneticalgorithm.algorithm.GeneticAlgorithmConfiguration;
import com.lukaklacar.geneticalgorithm.example.ImageGeneticAlgorithm;
import com.lukaklacar.geneticalgorithm.util.ImageUtil;
import lombok.var;

public class Program {
    public static void main(String[] args) {
        var geneticAlgorithm = new ImageGeneticAlgorithm(
                GeneticAlgorithmConfiguration
                        .builder()
                        .mutationRate(0.1)
                        .populationSize(5)
                        .build(),
                ImageUtil.loadImageFromFile("/home/luka/Pictures/47377778_286339268938671_9096739538927091712_n.jpg")
        );

        geneticAlgorithm.start();
    }
}
