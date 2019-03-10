package com.lukaklacar.geneticalgorithm.algorithm;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GeneticAlgorithmConfiguration {
    private double mutationRate;
    private int populationSize;

}
