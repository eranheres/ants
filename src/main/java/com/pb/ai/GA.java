package com.pb.ai;

import com.pb.ant.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GA {
    @Autowired SelectionAlgorithm selectionAlgorithm;
    @Autowired OffspringsAlgorithm offspringsAlgorithm;
    @Autowired CrossoverAlgorithm crossoverAlgorithm;
    @Autowired MutationAlgorithm mutationAlgorithm;

    @Value("${com.pb.crossoverRate}")
    double crossoverRate;
    @Value("${com.pb.mutationRate}")
    double mutationRate;
    @Value("${com.pb.chromoMutationRate}")
    double chromoMutationRate;
    @Value("${com.pb.chromoValMutationRate}")
    double chromoValMutationRate;

    private void mergePopulations(Population dest, Population src) {
        for (Spawn spawn : src.getSpawns())
            dest.getSpawns().add(spawn);
    }

    private Double bestFit(Population population) {
        Double max = 0.0;
        for (Spawn s : population.getSpawns()) {
            max = Math.max(s.getFitness(), max);
        }
        return max;
    }
    private Double avgFit(Population population) {
        Double count = 0.0;
        for (Spawn s : population.getSpawns()) {
            count += s.getFitness();
        }
        return count/population.getSize();
    }

    public void doStats(Game game, int generationCount) {
        Double avgFit = avgFit(game.getPopulation());
        Double bestFit = bestFit(game.getPopulation());

        System.out.println("generation "+generationCount+" best:"+bestFit+" avgFit:"+avgFit);
    }

    public void run(Game game) {
        int generationCount = 0;
        while (true) {
            generationCount++;
            game.play();
            doStats(game, generationCount);
            Population newPopulation = selectionAlgorithm.select(game.getPopulation());
            Integer amountToSpawn    = game.getPopulation().getSize() - newPopulation.getSize();
            Population offsprings    = offspringsAlgorithm.spawn(newPopulation, amountToSpawn);
            crossoverAlgorithm.crossover(newPopulation, crossoverRate);
            mutationAlgorithm.mutate(newPopulation, mutationRate, chromoMutationRate, chromoValMutationRate);
            mergePopulations(newPopulation, offsprings);
            game.setPopulation(newPopulation);
        }
    }
}
