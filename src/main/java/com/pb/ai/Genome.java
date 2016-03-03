package com.pb.ai;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Genome implements Comparable {
    @Getter private double[] chromo;
    @Getter @Setter Double fitness;
    @Setter private Random random;

    public Genome() {
    }

    public Genome(double[] chromo) {
        this.chromo = chromo;
    }

    public void mutate(double maxChromeMutation, double maxValueMutation) {
        double valsToMutate = random.nextDouble() * chromo.length * maxChromeMutation;
        for (int i=0; i<Double.valueOf(valsToMutate).intValue(); i++) {
            int pos = Double.valueOf((random.nextDouble()) * chromo.length).intValue();
            double delta = (random.nextDouble() * maxValueMutation) - (maxValueMutation/2);
            chromo[pos] = chromo[pos] + delta;
            if (chromo[pos] < 0)
                chromo[pos] = 0;
            if (chromo[pos]<0)
                continue;
        }
    }

    public void crossover(Genome partner) {
        int pos1 = Double.valueOf((random.nextDouble()) * chromo.length).intValue();
        int pos2 = Double.valueOf((random.nextDouble()) * chromo.length).intValue();
        int min = Math.min(pos1, pos2);
        int max = Math.max(pos1, pos2);
        for (int i=min; i<=max; i++) {
            Double temp = this.chromo[i];
            this.chromo[i] = partner.chromo[i];
            partner.chromo[i] = temp;
        }
    }

    @Override
    public Genome clone() {
        double dchromo[] = new double[chromo.length];
        System.arraycopy(chromo, 0, dchromo, 0, chromo.length);

        Genome genome = new Genome(dchromo);
        genome.chromo = dchromo;
        genome.setRandom(random);
        return genome;
    }


    @Override
    public int compareTo(Object o) {
        Double t = this.getFitness();
        return t.compareTo(((Genome) o).getFitness());
    }
}
