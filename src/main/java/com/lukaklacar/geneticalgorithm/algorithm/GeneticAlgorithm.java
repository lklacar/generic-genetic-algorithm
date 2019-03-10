package com.lukaklacar.geneticalgorithm.algorithm;

import com.lukaklacar.geneticalgorithm.chromosome.Chromosome;
import com.lukaklacar.geneticalgorithm.chromosome.ChromosomePair;
import com.lukaklacar.geneticalgorithm.population.Population;
import com.lukaklacar.geneticalgorithm.selection.SelectionAlgorithm;
import lombok.var;

public abstract class GeneticAlgorithm<T extends Chromosome> {

    protected GeneticAlgorithmConfiguration geneticAlgorithmConfiguration;

    protected GeneticAlgorithm(GeneticAlgorithmConfiguration geneticAlgorithmConfiguration) {
        this.geneticAlgorithmConfiguration = geneticAlgorithmConfiguration;
    }

    public GeneticAlgorithmResult start() {
        var currentState = GeneticAlgorithmState.builder().build();
        var threshold = this.getThreshold();
        var population = getInitialPopulation();
        int currentGeneration = 1;

        while (!threshold.isOver(currentState)) {
            currentState = GeneticAlgorithmState
                    .builder()
                    .currentGeneration(currentGeneration)
                    .build();

            population = iterate(population);
            currentGeneration += 1;
        }

        return GeneticAlgorithmResult
                .<T>builder()
                .generation(currentGeneration)
                .population(population)
                .build();
    }

    private Population<T> iterate(Population<T> oldPopulation) {
        var newPopulation = new Population<T>();
        var selectionAlgorithm = getSelectionAlgorithm();

        while (newPopulation.size() < oldPopulation.size()) {
            var pair = selectionAlgorithm.choosePair(oldPopulation);
            newPopulation.add(mutateIfNeeded(pair.getChromosome1()));
            newPopulation.add(mutateIfNeeded(pair.getChromosome2()));
        }

        return newPopulation;
    }

    private T mutateIfNeeded(T chromosome) {
        if (shouldMutate()) {
            return mutate(chromosome);
        }
        return chromosome;
    }

    private boolean shouldMutate() {
        return true;
    }

    protected abstract Population<T> getInitialPopulation();

    protected abstract ChromosomePair<T> crossover(T chromosome1, T chromosome2);

    protected abstract SelectionAlgorithm<T> getSelectionAlgorithm();

    protected abstract Threshold getThreshold();

    protected abstract T mutate(T chromosome);
}
