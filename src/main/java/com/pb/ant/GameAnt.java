package com.pb.ant;

import com.pb.ai.Genome;
import com.pb.ai.Network;
import com.pb.ai.Population;
import com.pb.ai.Spawn;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 *
 */
@Service
public class GameAnt {
    @Getter Network network;
    @Getter World world;
    @Getter @Setter Population population;

    @Getter Ant bestAnt;

    @Value("${com.pb.worldSize}")
    private int worldSize;
    @Value("${com.pb.foodAmount}")
    private int foodAmount;
    @Value("${com.pb.stepsEachGen}")
    private int stepsEachGen;
    @Value("#{'${com.pb.hiddenLayers}'.split(',')}")
    private List<Integer> hiddenLayers;
    @Value("${com.pb.populationSize}")
    int populationSize;

    @Autowired Random random;

    @Getter int generation = 0;
    private void initPopulation(int populationSize) {
        population = new Population();
        for (int i=0; i<populationSize; i++) {
            population.getSpawns().add(spawnAnt());
        }
    }
    public void init() {
        world = new World(worldSize, worldSize, foodAmount);
        List<Integer> layers = new ArrayList<>();
        //layers.add(worldSize*worldSize+2);
        layers.add(2);
        layers.addAll(hiddenLayers);
        layers.add(4);
        network = Network.generate(layers);
        initPopulation(network.getWeightsAmount());
    }

    public Spawn spawnAnt() {
        int genomeSize = network.getWeightsAmount();
        double list[] = new double[genomeSize];
        for (int i=0; i<genomeSize; i++)
            list[i] = random.nextDouble();
        Ant ant = new Ant(new Genome(list));
        ant.getGenome().setRandom(random);
        //ant.setLoc(new World.LocXY(random.nextInt(world.getWorldWidth()), random.nextInt(world.getWorldHeight())));
        ant.setLoc(new World.LocXY(0,0));
        ant.setEnergy(0);
        return ant;
    }

    private Ant bestAnt() {
        return (Ant)population.getSpawns().stream().max(Comparator.comparing(Spawn::getFitness)).get();
    }

    private void changeWorld() {
        List<World.LocXY> foods = world.getFoodsLoc();
        world.removeFood(foods.get(Double.valueOf(foods.size()*random.nextDouble()).intValue()));
        world.addFood();
    }

    public void play() {
        // Do step for each population ant
        generation++;
        if (generation % 20 == 0) {
            changeWorld();
        }
        bestAnt = bestAnt().clone();
        for (Spawn spawn : population.getSpawns()) {
            Ant ant = (Ant) spawn;
            ant.setFitness(0);
            ant.clearHistory();
            ant.setLoc(new World.LocXY(0,0));
            double temp[] = new double[world.field.length+2];
            System.arraycopy(world.field, 0, temp, 0, world.field.length);
            for (int i=0; i<stepsEachGen; i++) {
                World.LocXY prevLoc = new World.LocXY(ant.getLoc().getX(), ant.getLoc().getY());
                ant.doStep(temp, world.getWorldWidth(), world.getWorldHeight(), network);
                ant.getHistory().add(new World.LocXY(ant.getLoc().getX(), ant.getLoc().getY()));
                if (prevLoc.equals(ant.getLoc()))
                    continue;
            }

        }
    }
}
