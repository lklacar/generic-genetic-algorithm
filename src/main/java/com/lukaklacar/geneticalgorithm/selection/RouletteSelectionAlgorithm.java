package com.lukaklacar.geneticalgorithm.selection;

import com.lukaklacar.geneticalgorithm.chromosome.Chromosome;
import com.lukaklacar.geneticalgorithm.chromosome.ChromosomePair;
import com.lukaklacar.geneticalgorithm.population.Population;

import java.util.Random;

public class RouletteSelectionAlgorithm<T extends Chromosome> implements SelectionAlgorithm<T> {
    @Override
    public ChromosomePair<T> choosePair(Population<T> population) {
        return ChromosomePair
                .<T>builder()
                .chromosome1(chooseSingle(population))
                .chromosome2(chooseSingle(population))
                .build();
    }

    private T chooseSingle(Population<T> population) {
        double weight_sum = 0;
        for (T t : population) {
            weight_sum += t.getFitness();
        }
        double value = new Random().nextDouble() * weight_sum;
        for (T t : population) {
            value -= t.getFitness();
            if (value < 0) {
                return t;
            }
        }
        return population.get(population.size() - 1);
    }
}
