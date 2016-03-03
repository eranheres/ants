package com.pb.ai;

/**
 * Created by eranh on 2/21/16.
 */
public interface Spawn extends Comparable {
    double getFitness();
    void setFitness(double fitness);
    Genome getGenome();
    Spawn clone();
}
