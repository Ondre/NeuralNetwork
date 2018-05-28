import model.Matrix;
import model.NeuralNetwork;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rnd = new Random();

/*
        double[][][] teachingData = new double[][][]
                {
                        {{0, 1}, {1}},
                        {{1, 0}, {1}},
                        {{1, 1}, {0}},
                        {{0, 0}, {0}},
                };
*/

        double[][][] teachingData = new double[][][]
                {
                        {{1, 1, 0}, {1 ,0 ,0}},
                        {{1, 0, 1}, {1 ,0 ,0}},
                        {{0, 1, 1}, {0 ,1 ,0}},
                        {{0, 1, 0}, {0 ,0 ,1}},
                        {{0, 1, 1}, {0 ,1, 0}},
                        {{0, 0, 1}, {0 ,0 ,1}},
                        {{0, 1, 0}, {0, 0, 1}},
                        {{1, 1, 1}, {1, 0, 0}},
                        {{0, 0, 0}, {0, 1, 0}},
                };

        long f = System.currentTimeMillis();

        NeuralNetwork nn = new NeuralNetwork(3, 2, 3);
        nn.setLearningRate(.01);
        nn.setHiddenBias(-1, 1);

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 50000; i++) {

                int pick = rnd.nextInt(teachingData.length);
                nn.train(teachingData[pick][0], teachingData[pick][1]);

            }
            System.out.println("Target - {1 ,0 ,0}, Guess: " + Arrays.toString(nn.feedForward(new double[]{1, 1, 0})));
            System.out.println("Target - {1 ,0 ,0}, Guess: " + Arrays.toString(nn.feedForward(new double[]{1, 0, 0})));
            System.out.println("Target - {0 ,1 ,0}, Guess: " + Arrays.toString(nn.feedForward(new double[]{0, 1, 1})));
            System.out.println("Target - {0 ,1 ,0}, Guess: " + Arrays.toString(nn.feedForward(new double[]{0, 0, 0})));
            System.out.println("Target - {0 ,0 ,1}, Guess: " + Arrays.toString(nn.feedForward(new double[]{0, 0, 1})));
            System.out.println("Target - {0 ,0 ,1}, Guess: " + Arrays.toString(nn.feedForward(new double[]{0, 1, 0})));
            System.out.println();
        }

        System.out.println("\nProgram worked for : " + (System.currentTimeMillis() - f) + " millis.");

    }
}
