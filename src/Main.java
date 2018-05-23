import model.Matrix;
import model.NeuralNetwork;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rnd = new Random();

        double[][][] teachingData = new double[][][]
                {
                        {{0, 1}, {1}},
                        {{1, 0}, {1}},
                        {{1, 1}, {0}},
                        {{0, 0}, {0}},
                };

        long f = System.currentTimeMillis();

        NeuralNetwork nn = new NeuralNetwork(2, 2, 1);
        nn.setLearningRate(.1);
        nn.setHiddenBias(-0.5, 0.5);

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 50000; i++) {

                int pick = rnd.nextInt(4);
                nn.train(teachingData[pick][0], teachingData[pick][1]);

            }
            System.out.println("Target - 1, Guess: " + nn.feedForward(new double[]{1, 0})[0]);
            System.out.println("Target - 1, Guess: " + nn.feedForward(new double[]{0, 1})[0]);
            System.out.println("Target - 0, Guess: " + nn.feedForward(new double[]{1, 1})[0]);
            System.out.println("Target - 0, Guess: " + nn.feedForward(new double[]{0, 0})[0]);
            System.out.println();
        }

        System.out.println("\nProgram worked for : " + (System.currentTimeMillis() - f) + " millis.");
    }
}
