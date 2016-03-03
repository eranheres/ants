package com.pb.ai;

import lombok.Getter;

public class Layer {
    @Getter int inputsPerNeuron;
    @Getter int amountOfNeurons;

    public static Layer generate(int numberOfNeurons, int inputsPerNeon) {
        Layer layer = new Layer();
        layer.inputsPerNeuron = inputsPerNeon;
        layer.amountOfNeurons = numberOfNeurons;
        return layer;
    }

    public double[] apply(double input[], double weights[], int from) {
        double[] output = new double[amountOfNeurons];
        int i = from;
        for (int j=0; j<amountOfNeurons; j++) {
            output[j] = (applyNeuron(input, weights, i));
            i += inputsPerNeuron+1;
        }
        return output;
    }

    private static final double BIAS_FACTOR = (-1.0);

    private double applyNeuron(double[] input, double weights[], int from) {
        double sum = 0.0;
        for (int i=0; i<inputsPerNeuron; i++) {
            sum += input[i] * weights[i + from];
        }
        sum += weights[from + inputsPerNeuron] * BIAS_FACTOR;
        return sigmoid(sum);
    }

    public static double sigmoid(double x) {
        return (x / (1 + ((x <= 0.0) ? 0.0 - x : x)));
        //return (1/( 1 + Math.exp(-x)));
    }

    public Integer getWeightsAmount() {
        return (inputsPerNeuron+1)*amountOfNeurons;
    }
}
