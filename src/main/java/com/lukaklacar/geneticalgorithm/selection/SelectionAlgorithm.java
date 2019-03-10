package com.lukaklacar.geneticalgorithm.selection;

import com.lukaklacar.geneticalgorithm.chromosome.Chromosome;
import com.lukaklacar.geneticalgorithm.chromosome.ChromosomePair;
import com.lukaklacar.geneticalgorithm.population.Population;

public interface SelectionAlgorithm<T extends Chromosome> {
    ChromosomePair<T> choosePair(Population<T> population);
}
