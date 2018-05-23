package model;

import static model.Matrix.multiply;

public class NeuralNetwork {

    //weights
    private Matrix inputHidden;
    private Matrix hiddenOutput;

    // bias
    private Matrix hiddenBias;

    public Matrix getHiddenBias() {
        return hiddenBias;
    }

    public void setHiddenBias(double from, double to) {
        hiddenBias.randomize(from, to);
        outputBias.randomize(from, to);
    }

    public Matrix getOutputBias() {
        return outputBias;
    }

    public void setOutputBias(Matrix outputBias) {
        this.outputBias = outputBias;
    }

    private Matrix outputBias;

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    //learning rate
    private double learningRate = 0.1;

    // create random weights by initial amount of neurons
    public NeuralNetwork(int inputLayers, int hiddenLayers, int outputLayers) {
        // amount of neurons on each layer

        this.inputHidden = new Matrix(hiddenLayers, inputLayers);
        this.hiddenOutput = new Matrix(outputLayers, hiddenLayers);

        inputHidden.randomize();
        hiddenOutput.randomize();

        hiddenBias = new Matrix(hiddenLayers,1);
        outputBias = new Matrix(outputLayers,1);

        hiddenBias.randomize();
        outputBias.randomize();

    }

    public double[] feedForward(double[] input) {

        Matrix inputs = Matrix.fromArray(input);

        //generating the hidden layer
        Matrix hidden = multiply(inputHidden, inputs);
        hidden.add(hiddenBias);
        //activation function
        hidden = Matrix.sigmoid(hidden);

        //generating the output layer
        Matrix output = multiply(hiddenOutput, hidden);
        output.add(outputBias);
        output = Matrix.sigmoid(output);

        return output.toArray();
    }

    public void train(double[] inputsData, double[] targetsData){
        Matrix inputs = Matrix.fromArray(inputsData);

        //generating the hidden layer
        Matrix hidden = multiply(inputHidden, inputs);
        hidden.add(hiddenBias);
        //activation function
        hidden = Matrix.sigmoid(hidden);

        //generating the output layer
        Matrix outputs = Matrix.multiply(hiddenOutput, hidden);
        outputs.add(outputBias);
        outputs = Matrix.sigmoid(outputs);

        Matrix targets = Matrix.fromArray(targetsData);

        //computing output error
        Matrix outputErrors = Matrix.subtract(targets,outputs);

        // calculate gradient for hidden->output weights
        Matrix gradientsOutput = Matrix.dsigmoid(outputs);
        gradientsOutput = Matrix.multiplyHadamard(gradientsOutput, outputErrors);
        gradientsOutput.multiply(this.learningRate);

        //calculate hidden->output deltas
        Matrix hiddenOutputT = Matrix.transpose(hidden);
        Matrix weightsDeltasHO = Matrix.multiply(gradientsOutput, hiddenOutputT);

        //adjust weights of hidden->output
        this.hiddenOutput.add(weightsDeltasHO);
        //adjust the bias by delta which is gradient
        this.outputBias.add(gradientsOutput);

        //computing hidden error
        Matrix hiddenErrors = multiply(Matrix.transpose(hiddenOutput), outputErrors);

        //calculate gradients for input->hidden weigths
        Matrix gradientsHidden = Matrix.dsigmoid(hidden);
        gradientsHidden = Matrix.multiplyHadamard(gradientsHidden, hiddenErrors);
        gradientsHidden.multiply(this.learningRate);

        //calculate input->hidden deltas
        Matrix inputHiddenT = Matrix.transpose(inputs);
        Matrix weightsDeltasIH = Matrix.multiply(gradientsHidden, inputHiddenT);

        //adjust weights of input->hidden
        this.inputHidden.add(weightsDeltasIH);
        //adjust the bias by delta which is gradient
        this.hiddenBias.add(gradientsHidden);


    }


    public static class Util {
        public static double sigmoid(double x) {
            return ( 1 / ( 1 + Math.exp(-1 * x)));
        }

        //derivative of a number y already passed trough sigmoid function
        public static double dsigmoid(double y){
            return y * (1 - y);
        }
    }
}
