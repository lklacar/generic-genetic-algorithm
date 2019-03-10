package com.lukaklacar.geneticalgorithm.population;

import com.lukaklacar.geneticalgorithm.chromosome.Chromosome;

import java.util.ArrayList;
import java.util.Collection;

public class Population<T extends Chromosome> extends ArrayList<T> {
    public Population(int initialCapacity) {
        super(initialCapacity);
    }

    public Population() {
    }

    public Population(Collection<? extends T> c) {
        super(c);
    }
}
