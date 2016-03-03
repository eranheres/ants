package com.pb.ai;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Network {
    @Getter List<Layer> layers;
    @Setter double weights[];

    public static Network generate(List<Integer> layersSizes) {
        List<Layer> layers = new ArrayList<>();
        boolean firstLayer = true;
        int inputs = 0;
        for (Integer layerSize : layersSizes) {
            if (!firstLayer) {
                layers.add(Layer.generate(layerSize, inputs));
            }
            firstLayer = false;
            inputs = layerSize;
        }
        Network network = new Network();
        network.layers = layers;
        return network;
    }

    public double[] apply(double[] input) {
        int from = 0;
        for (Layer layer : layers) {
            input = layer.apply(input, weights, from);
            from += layer.getWeightsAmount();
        }
        return input;
    }
/*
    public List<Double> apply(List<Double> netInput) {
        double input[] = new double[netInput.size()];
        int i=0;
        for (Double val : netInput)
            input[i++] = val;
        double output[] = apply(input);
        List<Double> ret = new ArrayList<>();
        for (double val : output) {
            ret.add(val);
        }
        return ret;
    }
*/
    public int getWeightsAmount() {
        int sum = 0;
        for (Layer layer : layers) {
            sum += layer.getWeightsAmount();
        }
        return sum;
    }
}
