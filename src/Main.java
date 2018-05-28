import model.NeuralNetwork;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rnd = new Random();

        //init teachingData
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

        // init nn
        NeuralNetwork nn = new NeuralNetwork(3, 3, 3);
        nn.setLearningRate(.01);
        nn.setHiddenBias(-1, 1);

        // learning nn using teachingData 3D array with inputs and preferred outputs
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 50000; i++) {

                int pick = rnd.nextInt(teachingData.length);
                nn.train(teachingData[pick][0], teachingData[pick][1]);

            }
        }


        //print out result  
        System.out.println("Target - {1 ,0 ,0}, Guess: " + roundAndStringify(nn.guess(new double[]{1, 1, 0})));
        System.out.println("Target - {1 ,0 ,0}, Guess: " + roundAndStringify(nn.guess(new double[]{1, 0, 0})));
        System.out.println("Target - {0 ,1 ,0}, Guess: " + roundAndStringify(nn.guess(new double[]{0, 1, 1})));
        System.out.println("Target - {0 ,1 ,0}, Guess: " + roundAndStringify(nn.guess(new double[]{0, 0, 0})));
        System.out.println("Target - {0 ,0 ,1}, Guess: " + roundAndStringify(nn.guess(new double[]{0, 0, 1})));
        System.out.println("Target - {0 ,0 ,1}, Guess: " + roundAndStringify(nn.guess(new double[]{0, 1, 0})));
        System.out.println();

        System.out.println("\nProgram worked for : " + (System.currentTimeMillis() - f) + " millis.");

    }

    private static String roundAndStringify(double[] inputs){
        int[] result = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            result[i] = (int) Math.round(inputs[i]);
        }
        return Arrays.toString(result);
    }
}
