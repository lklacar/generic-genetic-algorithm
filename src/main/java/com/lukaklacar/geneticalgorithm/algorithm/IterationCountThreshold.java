package com.lukaklacar.geneticalgorithm.algorithm;

public class IterationCountThreshold implements Threshold {

    private int maximumNumberOfIterations;

    public IterationCountThreshold(int maximumNumberOfIterations) {
        this.maximumNumberOfIterations = maximumNumberOfIterations;
    }

    @Override
    public boolean isOver(GeneticAlgorithmState geneticAlgorithmState) {
        return geneticAlgorithmState.getCurrentGeneration() >= maximumNumberOfIterations;
    }
}
