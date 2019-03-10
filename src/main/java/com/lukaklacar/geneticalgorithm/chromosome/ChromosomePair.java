package com.lukaklacar.geneticalgorithm.chromosome;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChromosomePair<T extends Chromosome> {
    private T chromosome1;
    private T chromosome2;
}
