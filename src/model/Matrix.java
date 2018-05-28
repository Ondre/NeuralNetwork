package model;

import java.util.Random;

public class Matrix {

    private Random rnd = new Random();

    private double data[][];
    private int rows;
    private int cols;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Matrix(int rows, int cols){
     this.rows = rows;
     this.cols = cols;
     data = new double[rows][cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                data[i][j] = 0;
            }
        }
    }

    public Matrix multiply(double learningRate) {

        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                    result.data[i][j] = learningRate * this.data[i][j];
            }
        }
        return result;

    }

    public void randomize(){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                data[i][j] = rnd.nextDouble() * 2 - 1;
            }
        }
    }
    public void randomize(double from, double to) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                data[i][j] = from + (from - to) * rnd.nextDouble();
            }
        }
    }

    public void fillWith(double number){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = number;
            }
        }
    }

    public void print() {
        System.out.println(' ');
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + ", ");
            }
            System.out.println(' ');
        }
    }

    public static Matrix fromArray(double[] data){
        Matrix result = new Matrix(data.length, 1);

        for (int i = 0; i < data.length; i++) {
                result.data[i][0] = data[i];
        }
        return result;
    }

    public double[] toArray(){

        double[] result = new double[this.rows * this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[this.cols * i + j] = this.data[i][j];
            }
        }

        return result;
    }

    public static Matrix multiply(Matrix a, Matrix b){
        if (a.cols != b.rows) {
            System.out.println("Multiplication impossible for: " + a.cols + "x" + a.rows + " to " + b.cols + "x" + b.rows );
            return null;}

        Matrix result = new Matrix(a.rows, b.cols);

        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < b.cols; j++) {
                for (int k = 0; k < b.rows; k++) {
                    result.data[i][j] += a.data[i][k] * b.data[k][j];
                }
            }
        }
        return result;
    }

    public static Matrix multiplyHadamard(Matrix a, Matrix b){
        if (a.cols != b.cols || a.rows != b.rows) return null;

        Matrix result = new Matrix(a.rows, b.cols);

        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                    result.data[i][j] += a.data[i][j] * b.data[i][j];
            }
        }
        return result;
    }

    public void add(Matrix m) {
        if (null != m && this.rows == m.rows && this.cols == m.cols)
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.data[i][j] += m.data[i][j];
            }
        }
    }

    public static Matrix subtract(Matrix m, Matrix b) {

        Matrix result = new Matrix(m.rows, m.cols);

        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                result.data[i][j] = m.data[i][j] - b.data[i][j];
            }
        }

        return result;
    }

    public static Matrix sigmoid(Matrix m) {
        Matrix result = new Matrix(m.rows,m.cols);

        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                result.data[i][j] = NeuralNetwork.Util.sigmoid(m.data[i][j]);
            }
        }
        return result;
    }

    public static Matrix dsigmoid(Matrix m) {
        Matrix result = new Matrix(m.rows,m.cols);

        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                result.data[i][j] = NeuralNetwork.Util.dsigmoid(m.data[i][j]);
            }
        }
        return result;
    }

    public static Matrix transpose(Matrix m){
        Matrix result = new Matrix(m.cols,m.rows);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                result.data[i][j] = m.data[j][i];
            }
        }
        return result;

    }

}
