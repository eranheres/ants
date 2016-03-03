package com.pb.ant;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class World {
    @Getter
    @Setter
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class LocXY {
        public LocXY(LocXY loc) {
            X = loc.getX();
            Y = loc.getY();
        }
        private int X;
        private int Y;
    }

    double[] field;
    @Getter private int worldHeight;
    @Getter private int worldWidth;
    Random random;

    public World(int worldWidth, int worldHeight, int foodAmount) {
        this.random = new Random();
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        field = new double[worldHeight * worldWidth];
        for (int i=0; i<worldWidth; i++) {
            for (int j=0; j<worldHeight; j++) {
                field[j*worldWidth+i] = 0.0;
            }
        }
        for (int i=0; i<foodAmount; i++) {
            addFood();
        }
    }

    public void addFood() {
        while (true) {
            int posX = random.nextInt(worldWidth);
            int posY = random.nextInt(worldHeight);
            int pos = posX + (posY * worldWidth);
            if (field[pos] != 0)
                continue;
            field[posX + (posY * worldWidth)] = 1.0;
            break;
        }
    }

    public void removeFood(LocXY loc) {
        field[loc.getX() + (loc.getY() * worldWidth)] = 0.0;
    }

    List<LocXY> getFoodsLoc() {
        List<LocXY> ret = new ArrayList<>();
        for (int y=0; y<worldHeight; y++) {
            for (int x = 0; x < worldWidth; x++) {
                if (field[x + y * worldWidth] != 0) {
                   ret.add(new LocXY(x,y));
                }
            }
        }
        return ret;
    }

    long countFood() {
        return Arrays.stream(field).filter(p->p==(1.0)).count();
    }
}
