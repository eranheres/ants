package com.pb.ai;

import org.testng.annotations.Test;
import sun.nio.ch.Net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 *
 */
@SuppressWarnings("Duplicates")
public class NetworkTest {

    @Test
    public void testApply() throws Exception {
        List<Integer> layers = Arrays.asList(2,3,2);
        Network network = Network.generate(layers);
        double weights[] = new double[] {
                // Input layer             N1          N2
                /* Weights        */      1,2, 3,     4,5, 6,   7,8, 9,
                /* Layer 1                 N1          N2       N3     */
                /* Weights        */         10,11,12, 13,  14,15,16, 17
                /* Output layer                  N1           N2         */
        };
        network.setWeights(weights);
        double input[] = { 1, 2};
        double expected[] = { Layer.sigmoid(1*1+1*2-1*3), Layer.sigmoid(2*1+2*2-2*3) };
        double actual[] = network.apply(input);
    }

    @Test
    public void testApply2() throws Exception {
        List<Integer> layers = Arrays.asList(2,3,2);
        Network network = Network.generate(layers);
        double weights[] = new double[] {
                // Input layer                 N1=1          N2=1
                /* Weights        */      1,0, 0,     0,1, 0,   1,1, 0,
                /* Layer 1                 N1          N2       N3     */
                /* Weights        */          1,0,0, 0,     1,0,0, 0
                /* Output layer                  N1           N2         */
        };
        network.setWeights(weights);
        double input[] = { 1, 1};
        double actual[] = network.apply(input);
        double v = Layer.sigmoid(1);
        double v2 = Layer.sigmoid(v);
        double expected[] = {Layer.sigmoid(Layer.sigmoid(1)) , Layer.sigmoid(Layer.sigmoid(1))};
        assertEquals(actual, expected);
    }

    @Test
    public void testApply3() throws Exception {
        List<Integer> layers = Arrays.asList(2,3,2);
        Network network = Network.generate(layers);
        double weights[] = new double[] {
                // Input layer                 N1=1          N2=1
                /* Weights        */      1,0, 0,     0,0, 0,   0,0, 0,
                /* Layer 1                 N1          N2       N3     */
                /* Weights        */          1,0,0, 0,     0,1,0, 0
                /* Output layer                  N1           N2         */
        };
        network.setWeights(weights);
        double input[] = { 1, 1};
        double actual[] = network.apply(input);
        double v = Layer.sigmoid(1);
        double v2 = Layer.sigmoid(v);
        double expected[] = {Layer.sigmoid(Layer.sigmoid(1)) , Layer.sigmoid(Layer.sigmoid(0))};
        assertEquals(actual, expected);
    }

    @Test
    public void testApply4() throws Exception {
        List<Integer> layers = Arrays.asList(2,3,2);
        Network network = Network.generate(layers);
        double weights[] = new double[] {
                // Input layer                 N1=1          N2=1
                /* Weights        */      0,0, 0,     0,1, 0,   0,0, 0,
                /* Layer 1                 N1          N2       N3     */
                /* Weights        */          1,0,0, 0,     0,1,0, 0
                /* Output layer                  N1           N2         */
        };
        network.setWeights(weights);
        double input[] = { 1, 1};
        double actual[] = network.apply(input);
        double v = Layer.sigmoid(1);
        double v2 = Layer.sigmoid(v);
        double expected[] = {Layer.sigmoid(Layer.sigmoid(0)) , Layer.sigmoid(Layer.sigmoid(1))};
        assertEquals(actual, expected);
    }
}