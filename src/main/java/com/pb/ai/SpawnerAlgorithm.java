package com.pb.ai;

import com.pb.ant.World;
import com.pb.ant.Ant;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
@NoArgsConstructor
public class SpawnerAlgorithm {
    @Autowired Random random;

    @Setter World world;
    @Setter Integer genomeSize;

    private final static int antInitialEnergy = 1000;

    public Spawn spawn() {
        double list[] = new double[genomeSize];
        for (int i=0; i<genomeSize; i++)
            list[i] = random.nextDouble();
        Ant ant = new Ant(new Genome(list));
        ant.getGenome().setRandom(random);
        //ant.setLoc(new World.LocXY(random.nextInt(world.getWorldWidth()), random.nextInt(world.getWorldHeight())));
        ant.setLoc(new World.LocXY(0,0));
        ant.setEnergy(antInitialEnergy);
        return ant;
    }
}
