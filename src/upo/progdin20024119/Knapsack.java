package upo.progdin20024119;

import java.util.Arrays;

public class Knapsack {
    private int[][] matrix;
    private int items;
    private int maxWeight;
    private int[] values;
    private int[] weights;

    public Knapsack(int items, int maxWeight, int[] values, int[] weights) {
        this.items = items;
        this.maxWeight = maxWeight;
        this.values = values;
        this.weights = weights;
        matrix = new int[items + 1][maxWeight + 1];
        Arrays.fill(matrix[0], 0);
        for (int i = 0; i <= items; i++)
            matrix[i][0] = 0;
    }

    public double getKnapsack() {
        for (int i = 1; i < items + 1; i++)
            for (int j = 1; j < maxWeight + 1; j++)
                if (j < weights[i - 1])
                    matrix[i][j] = matrix[i - 1][j];
                else
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i - 1][j - weights[i - 1]] + values[i - 1]);

        return matrix[items][maxWeight];
    }
}
