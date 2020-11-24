package MajorWrapperClass;

import java.util.*;

import static ElementaryWrapperClass.GreatestCommonDivisor.GCD;

/**
 * 背包问题
 */
public class Knapsack {
    private int[] weights;
    private double[] values;
    private int capacity;
    /**
     * 背包应携带的物品编号
     */
    public HashSet<Integer> bag;
    /**
     * 背包中的物品的价值
     */
    public double value;
    /**
     * 背包中物品的重量
     */
    public double weight;
    private int comDiv = 1;
    private int len;
    //  要充填的表
    private double[][] valueMat;


    /**
     * 构造方法
     * @param weights 重量数组
     * @param values 价值数组
     * @param capacity 装载能力
     */
    Knapsack(int[] weights,double[] values, double capacity){
        if (weights == null || weights.length == 0) throw new RuntimeException("输入数据为空或长度为0！");
        if (weights.length != values.length) throw  new RuntimeException("权重与价值数组长度不等");
        this.values = values;
        this.weights = weights;
        this.capacity = (int) capacity;
        bag = new HashSet<>();
        len = weights.length;
    }

    //  每次求解背包问题，都会先调用clear，以保证该对象可以多次求解
    private void clear(){
        value = 0;
        weight = 0;
        bag.clear();
    }

    /**
     * 预处理器，能减小动态规划中背包问题所占内存
     */
    public void preSolve(){
        comDiv = weights[0];
        int i = 0;
        while (i < weights.length-1){
            i += 1;
            comDiv = GCD(comDiv,weights[i]);
            if (comDiv == 1) break;
        }
        capacity = capacity/comDiv;
        for (int j = 0; j < weights.length; j++) {
            weights[j] = weights[j]/comDiv;
        }
    }

    /**
     * 动态规划求解背包问题
     */
    public void DynamicProgramming(){
        clear();
        valueMat = new double[len][capacity+1];
        value = fitMat(len-1,capacity);
        int cap = capacity;
        for (int i = len-1; i > 0; i--) {
            if (valueMat[i][cap] != valueMat[i-1][cap]){
                bag.add(i);
                cap -= weights[i];
            }
        }
        if (cap >= weights[0]) {
            bag.add(0);
            weight = capacity - cap - weights[0];
        }else {
            weight = capacity-cap;
        }

        weight *= comDiv;
    }

    //  动态规划的递归过程
    //  i是阶段，j是当前cap
    private double fitMat(int i,int j){
        if (i > 0){
            if (j-weights[i] >= 0){
                valueMat[i][j] = Math.max(fitMat(i-1,j),fitMat(i-1,j-weights[i])+values[i]);
            }else {
                valueMat[i][j] = fitMat(i-1,j);
            }
            return valueMat[i][j];
        }else {
            if (j >= weights[i]){
                valueMat[i][j] = values[i];
                return values[i];
            }
            valueMat[i][j] = 0;
            return 0;
        }
    }

    /**
     * 贪心算法
     */
    public void greedy(){
        clear();
        ArrayList<Integer> item = new ArrayList<>();
        ArrayList<Integer> weightsCopy = new ArrayList<>();
        ArrayList<Double> margin = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            item.add(i);
            weightsCopy.add(weights[i]);
            margin.add(values[i]/weights[i]);
        }
        item.sort(Comparator.comparingDouble(i -> margin.get(i)));
        Collections.sort(margin);
        for (int i = len-1; i >= 0; i--) {
            if (weight + weights[item.get(i)] <= capacity){
                bag.add(item.get(i));
                weight += weights[item.get(i)];
                value += values[item.get(i)];
            }else if (weight == 0){
                break;
            }
        }

    }




}
