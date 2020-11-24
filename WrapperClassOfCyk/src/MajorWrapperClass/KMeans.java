package MajorWrapperClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * k-means聚类算法
 */
public class KMeans {
    private int clusterNum;
    private double[][] valueMat;
    /**
     * 已经分好类的各簇
     */
    public ArrayList<HashSet<Integer>> clusterList;
    /**
     *
     * @param clusterNum：聚类的数量
     * @param valueMat：聚类的样本，一列代表一个个体
     */
    KMeans(int clusterNum, double[][] valueMat){
        this.clusterNum = clusterNum;
        this.valueMat = valueMat;
    }

    /**
     * 对给定数据执行k-means聚类
     * @param maxIterNum:最大迭代次数
     */
    public void startKMeans(int maxIterNum){
        clusterList = new ArrayList<>();
        Random random = new Random();
        //  选择标杆样本
        int[] flagMat = new int[clusterNum];
        ArrayList<Integer> individual = new ArrayList<>();
        for (int i = 0; i < valueMat[0].length; i++) {
            individual.add(i);
        }
        for (int i = 0; i < flagMat.length; i++) {
            int index = random.nextInt(individual.size());
            flagMat[i] = individual.get(index);
            individual.remove(index);
        }
        //  建立均值向量组
        double[][] meanVector = new double[valueMat.length][clusterNum];
        for (int i = 0; i < meanVector.length; i++) {
            for (int j = 0; j < meanVector[i].length; j++) {
                meanVector[i][j] = valueMat[i][flagMat[j]];
            }
        }
         //  初始化各cluster
        for (int value : flagMat) {
            clusterList.add(new HashSet<>());
        }
        boolean update = true;
        int nowIter = 0;
        while (update && nowIter <= maxIterNum){
            nowIter += 1;
            update = false;
            //  计算样本到各均值向量的距离，并选择最小的和其对应的索引
            //  j表示第j个个体
            for (int j = 0; j < valueMat[0].length; j++) {
                double minDist = Double.MAX_VALUE;
                int in = -1;
                //  k表示簇的编号
                for (int k = 0; k < clusterNum; k++) {
                    double dist = 0;
                    //  i表示第i个特征
                    for (int i = 0; i < valueMat.length; i++) {
                        dist += Math.pow(valueMat[i][j]-meanVector[i][k],2);
                    }
                    dist = Math.sqrt(dist);
                    if (dist < minDist){
                        minDist = dist;
                        in = k;
                    }
                }
                clusterList.get(in).add(j);
            }
            //  i表示第i个簇
            for (int i = 0; i < clusterNum; i++) {
                //  j表示第j个特征
                for (int j = 0; j < meanVector.length; j++) {
                    double temp = 0;
                    //  k表示第i个簇中的k个体
                    for (int k:clusterList.get(i)
                         ) {
                        temp += valueMat[j][k];
                    }
                    temp = temp/clusterList.get(i).size();
                    if (temp != meanVector[j][i]){
                        meanVector[j][i] = temp;
                        update = true;
                    }
                }
            }
        }
    }
}
