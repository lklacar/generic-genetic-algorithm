package com.lukaklacar.geneticalgorithm.algorithm;

import com.lukaklacar.geneticalgorithm.chromosome.Chromosome;
import com.lukaklacar.geneticalgorithm.population.Population;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GeneticAlgorithmResult<T extends Chromosome> {
    private Population<T> population;
    private int generation;
}
