package MajorWrapperClass;
import static ElementaryWrapperClass.GreatestCommonDivisor.GCD;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Object[] item = {"one","two","three","four","five","six","seven"};
        int[] weight = {2,6,9,7,4,3,7};
        double[] value = {10,18,13,14,20,17,19};
        double cap = 20;
        Knapsack knapsack = new Knapsack(weight,value,cap);
        knapsack.DynamicProgramming();
        System.out.println(knapsack.value);
        System.out.println(knapsack.weight);
        System.out.println(knapsack.bag);
        knapsack.greedy();
        System.out.println(knapsack.value);
        System.out.println(knapsack.weight);
        System.out.println(knapsack.bag);
    }


}
