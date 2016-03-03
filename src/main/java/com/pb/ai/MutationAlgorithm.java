package com.pb.ai;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
public class MutationAlgorithm {
    @Autowired Random random;

    public void mutate(Population population, double rate, double maxChromeMut, double maxChromeValMute) {
        int popSize = population.getSize();
        int amount = Double.valueOf(popSize * rate).intValue();
        for (int i=0; i<amount; i++) {
            Genome genome = population.getGenome(random.nextInt(popSize));
            genome.mutate(maxChromeMut, maxChromeValMute);
        }
    }
}
