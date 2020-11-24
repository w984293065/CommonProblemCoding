package MajorWrapperClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 内置两个构造方法：若只传递价值矩阵和开始点，则开始点到所有点的路径和成本都可以计算出；若还传递了结束点，则只计算开始点到结束点的路径和成本
 * */
public class Dijkstra {
    /**
     * 成本系数列表（不传end参数）：按照从小到大的顺序排列，对应的结束节点在indexList中
     * */
    ArrayList<Double> costList = new ArrayList<>();
    /**
     * end节点列表（不传end参数）：表示路径的最后一个节点，和costList, routeList相同位置对应
     * */
    ArrayList<Integer> indexList = new ArrayList<>();
    /**
     * 最短路径列表（不传end参数）：与indexList和costList对应
     * */
    ArrayList<ArrayList<Integer>> routeList = new ArrayList<>();
    /**
     * （传end参数）：从source到end的最短路径的成本
     * */
    double cost;
    /**
     * （传end参数）：从source到end的最短路径
     * */
    ArrayList<Integer> route = new ArrayList<>();


    /**
     *
     * @param matrix:价值系数矩阵
     * @param source：开始节点
     */
    Dijkstra(double[][] matrix, int source){
        double[] sLabel = new double[matrix.length];
        Arrays.fill(sLabel,Double.MAX_VALUE);
        sLabel[source] = 0;
        double[] tLabel = sLabel.clone();
        int[] prede = new int[matrix.length];
        Arrays.fill(prede, -1);
        HashSet<Integer> wait = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            if (i != source){
                wait.add(i);
            }
        }
        int now = source;

        while (!wait.isEmpty()){
            double minT = Double.MAX_VALUE;
            int indexMin = -1;
            for (int i: wait){
                double temp;
                if (sLabel[now] == Double.MAX_VALUE || matrix[now][i] == Double.MAX_VALUE){
                    temp = Double.MAX_VALUE;
                }else {
                    temp = sLabel[now]+matrix[now][i];
                }
                if (tLabel[i] >temp){
                    tLabel[i] = temp;
                    prede[i] = now;
                }
                if (tLabel[i] < minT){
                    minT = tLabel[i];
                    indexMin = i;
                }
            }
            now = indexMin;
            sLabel[indexMin] = minT;
            wait.remove(indexMin);
            indexList.add(indexMin);
            ArrayList<Integer> revRoute = new ArrayList<>();
            revRoute.add(indexMin);
            while (prede[indexMin] != -1){
                revRoute.add(prede[indexMin]);
                indexMin = prede[indexMin];
            }
            ArrayList<Integer> route = new ArrayList<>();
            for (int i = revRoute.size()-1; i >= 0 ; i--) {
                route.add(revRoute.get(i));
            }
            routeList.add(route);
            double c = 0;
            for (int i = 0; i < route.size()-1; i++) {
                c += matrix[route.get(i)][route.get(i+1)];
            }
            costList.add(c);
        }

    }


    /**
     *
     * @param matrix：价值系数矩阵
     * @param source：开始节点
     * @param end：结束节点
     */
    Dijkstra(double[][] matrix, int source, int end){
        double[] sLabel = new double[matrix.length];
        Arrays.fill(sLabel,Double.MAX_VALUE);
        sLabel[source] = 0;
        double[] tLabel = sLabel.clone();
        int[] prede = new int[matrix.length];
        Arrays.fill(prede, -1);
        HashSet<Integer> wait = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            if (i != source){
                wait.add(i);
            }
        }
        int now = source;

        while (now != end){
            double minT = Double.MAX_VALUE;
            int indexMin = -1;
            for (int i: wait){
                double temp;
                if (sLabel[now] == Double.MAX_VALUE || matrix[now][i] == Double.MAX_VALUE){
                    temp = Double.MAX_VALUE;
                }else {
                    temp = sLabel[now]+matrix[now][i];
                }
                if (tLabel[i] >temp){
                    tLabel[i] = temp;
                    prede[i] = now;
                }
                if (tLabel[i] < minT){
                    minT = tLabel[i];
                    indexMin = i;
                }
            }
            now = indexMin;
            sLabel[indexMin] = minT;
            wait.remove(indexMin);
        }
        now = end;
        ArrayList<Integer> tempRoute = new ArrayList<>();
        tempRoute.add(now);
        while (prede[now] != -1){
            tempRoute.add(prede[now]);
            now = prede[now];
        }
        for (int i = tempRoute.size()-1; i >= 0 ; i--) {
            route.add(tempRoute.get(i));
        }
        for (int i = 0; i < route.size()-1; i++) {
            cost += matrix[route.get(i)][route.get(i+1)];
        }
    }
}
