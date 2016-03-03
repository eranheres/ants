package com.pb.ant;

import com.pb.ai.Genome;
import com.pb.ai.Network;
import com.pb.ai.Spawn;
import com.sun.tools.javac.util.ArrayUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Ant implements Spawn {
    private World.LocXY loc;
    private int energy;
    private Genome genome;
    @Getter private List<World.LocXY> history;
    @Getter private List<Integer> historyDir;

    public Ant(Genome genome) {
        this.genome = genome;
        history = new ArrayList<>();
        historyDir = new ArrayList<>();
    }

    @Override
    public double getFitness() {
        return energy;
    }
    @Override
    public void setFitness(double fitness) {
        this.energy = Double.valueOf(fitness).intValue();
    }

    @Override
    public int compareTo(Object o) {
        Double t = this.getFitness();
        return t.compareTo(((Ant) o).getFitness());
    }

    public void clearHistory() {
        history = new ArrayList<>();
        historyDir = new ArrayList<>();
    }

    @Override
    public Ant clone() {
        Ant ant = new Ant(genome.clone());
        ant.loc = new World.LocXY(loc);
        ant.energy = energy;
        ant.history = new ArrayList<>(history);
        return ant;
    }

    public void doStep(double world[], int width, int height, Network network) {
        network.setWeights(genome.getChromo());
        double input[] = world;
        input[input.length-2] = loc.getX();
        input[input.length-1] = loc.getY();
        input[0] = loc.getX();
        input[1] = loc.getY();
        double instructions[] = network.apply(input);
        double max = Arrays.stream(instructions).max().getAsDouble();
        int pos = 0;
        for (; pos < 4; pos++) {
            if (instructions[pos] == max)
                break;
        }

        if (pos == 0) {
            loc.setX((loc.getX() + 1)%width);
        } else if (pos == 1) {
            loc.setX(loc.getX()==0?width-1:loc.getX()-1);
        } else if (pos == 2) {
            loc.setY((loc.getY() + 1)%height);
        } else if (pos == 3) {
            loc.setY(loc.getY()==0?height-1:loc.getY()-1);
        } else
            throw new IllegalStateException("wrong direction value");
        if ((loc.getX()<0) || (loc.getX()<0))
            throw new IllegalStateException("b");
        if (world[loc.getX() + (loc.getY() * width)] != 0) {
            energy+=5;
            world[loc.getX() + (loc.getY() * width)] = 0.0;
            //world.addFood();
        }

        if ((!historyDir.isEmpty()) && (pos != historyDir.get(historyDir.size()-1))) {
            energy+=1;
        }
        historyDir.add(pos);
        //energy--;
    }
}
