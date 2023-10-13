import java.util.Random;

public class MatrixMultiplication {

    static int n = 10;
    static int[][] matrix = new int[n][n];
    static int[][] matrix2 = new int[n][n];
    static int[][] result = new int[n][n];

    public static void main(String[] args) {

        Random rand = new Random();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = rand.nextInt(100);
            }
        }

        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                matrix2[i][j] = rand.nextInt(100);
            }
        }

        System.out.println("This is first matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nThis is second matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix2[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        try {

            Thread[] threads = new Thread[n];
            for (int i = 0; i < n; ++i) {
                threads[i] = new Thread(new MatrixMultiplier(n, n, i));
            }

            for (Thread thread : threads) {
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n\nResult:");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

}

class Multiply extends MatrixMultiplication {

    private int i;
    private int j;
    private int row;

    public Multiply(int i, int j, int row) {
        this.i = i;
        this.j = j;
        this.row = row;
    }

    public void multiplyMatrix() {

        int sum;
        for (int a = 0; a < i; a++) {
            sum = 0;
            for (int b = 0; b < j; b++) {
                sum += matrix[row][b] * matrix2[b][a];

            }

            result[row][a] = sum;
        }
        System.out.println("Thread " + (row + 1) + " is Working!");
    }
}


class MatrixMultiplier implements Runnable {

    private final Multiply mul;

    public MatrixMultiplier(int i, int j, int row) {
        this.mul = new Multiply(i, j, row);
    }

    @Override
    public void run() {
        mul.multiplyMatrix();
    }
}
