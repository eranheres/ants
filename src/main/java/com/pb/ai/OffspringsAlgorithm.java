package com.pb.ai;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Simple reborn algorithm that uses partical crossover on the new generation
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
public class OffspringsAlgorithm {
    @Autowired Random random;

    public Population spawn(Population population, Integer amount) {
        Population offsprings = new Population();
        while (offsprings.getSize() < amount) {
            Integer pos = random.nextInt(population.getSize());
            offsprings.getSpawns().add(population.getSpawns().get(pos).clone());
        }
        return offsprings;
    }
}
