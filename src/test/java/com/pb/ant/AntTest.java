package com.pb.ant;

import com.pb.ai.Genome;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AntTest {

    @Test
    public void testClone() throws Exception {
        Ant ant = new Ant(new Genome(new double[]{}));
        ant.setLoc(new World.LocXY(1,2));
        Ant cloned = ant.clone();

        assertTrue(cloned.getLoc().equals(ant.getLoc()));
        cloned.getLoc().setX(2);
        assertFalse(cloned.getLoc().equals(ant.getLoc()));

    }
}