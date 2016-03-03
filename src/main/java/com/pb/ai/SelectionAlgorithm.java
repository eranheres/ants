package com.pb.ai;

import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultiset;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

// Selection algorithm - tournament
@Component
@NoArgsConstructor
@AllArgsConstructor
public class SelectionAlgorithm {
    @Autowired Random random;

    double maxTournamentSizeRate = 0.10;

    Population select(Population population) {
        Population newPopulation = new Population();
        TreeMultiset<Spawn> sorted = TreeMultiset.create(Ordering.natural().reversed());
        population.getSpawns().forEach(sorted::add);
        double sum = 0;
        for (Spawn s : sorted) {
            sum += s.getFitness();
        }
        while (newPopulation.getSize() < population.getSize()) {
            double selectedVal = random.nextDouble() * sum;
            Spawn selected = null;
            for (Spawn s : sorted) {
                selectedVal -= s.getFitness();
                if (selectedVal > 0) {
                    continue;
                }
                selected = s;
                break;
            }
            if (selected == null) {
                throw new IllegalStateException();
            }
            newPopulation.getSpawns().add(selected.clone());
        }
        return newPopulation;
    }

    Population select1(Population population, double surviveRate) {
        Integer maxTournamentSize = Double.valueOf(maxTournamentSizeRate * population.getSize()).intValue();
        List<Spawn> genome = population.getSpawns();
        int newPopulationSize = Double.valueOf(genome.size() * surviveRate).intValue();
        List<Spawn> newPopulation = new LinkedList<>();
        while (true) {
            TreeMultiset<Spawn> sorted = TreeMultiset.create(Ordering.natural().reversed());
            int tournamentSize = random.nextInt(maxTournamentSize) + 1;
            for (int i=0; i<tournamentSize; i++) {
                int pos = random.nextInt(genome.size());
                Spawn val = genome.get(pos);
                genome.remove(pos);
                sorted.add(val);
            }
            int selection = Double.valueOf(sorted.size() * surviveRate).intValue();
            for (Spawn selected : sorted) {
                newPopulation.add(selected);
                if (newPopulation.size() == newPopulationSize)
                    return new Population(newPopulation);
                if (selection-- <= 0)
                    break;
            }
        }
    }
}
