package com.pb.ai;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 *
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CrossoverAlgorithm {
    @Autowired Random random;

    public void crossover(Population population, double rate) {
        int popSize = population.getSize();
        int amount = Double.valueOf(popSize * rate).intValue() / 2;
        for (int i=0; i<amount; i++) {
            int firstpos = random.nextInt(popSize);
            int secondpos = random.nextInt(popSize);
            Genome first  = population.getGenome(firstpos);
            Genome second = population.getGenome(secondpos);
            first.crossover(second);
        }
    }
}
