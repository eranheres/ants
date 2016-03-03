package com.pb.ai;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Population {
    @Getter List<Spawn> spawns;

    public Population() {
        spawns = new LinkedList<>();
    }
    public int getSize() {
        return spawns.size();
    }
    public Genome getGenome(int pos) {
        return spawns.get(pos).getGenome();
    }
}
