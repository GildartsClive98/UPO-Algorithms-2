package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import upo.progdin20024119.Knapsack;

public class KnapsackTest {

    @Test
    public void testKnapsack1() {
        int[] values = { 2, 7, 6, 4 };
        int[] weights = { 13, 7, 2, 1 };

        Knapsack knapsack = new Knapsack(4, 10, values, weights);
        var result = knapsack.getKnapsack();
        assertTrue(17 == result);
    }

    @Test
    public void testKnapsack2() {
        int[] values = { 10, 7, 3, 4 };
        int[] weights = { 13, 7, 2, 5 };

        Knapsack knapsack = new Knapsack(4, 10, values, weights);
        var result = knapsack.getKnapsack();
        assertTrue(10 == result);
    }
}
