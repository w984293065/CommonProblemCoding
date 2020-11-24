package MajorWrapperClass;

import java.util.Arrays;

/**
 * Floyd算法：求任意两点的最短路径
 */
public class Floyd {
    /**
     * 任意两点的最小距离:bestDist[i][j]=从i到j的最小距离
     */
    double[][] bestDist;

    /**
     * Floyd算法的构造方法
     * @param dist 任意两点的距离矩阵，必须是不含负数的方阵
     */
    Floyd(double[][] dist){
        if (dist == null || dist.length == 0 || dist.length !=dist[0].length){
            RuntimeException e = new RuntimeException("传入的二维数组不合逻辑！");
            throw  e;
        }
        for (double[] doubles:dist
             ) {
            for (double d:doubles
                 ) {
                if (d < 0) throw new RuntimeException("传入的二维数组中含有负值！");
            }
        }
        bestDist = new double[dist.length][dist[0].length];
        double[][] newDist = new double[dist.length][dist[0].length];
        for (int i = 0; i < newDist.length; i++) {
            System.arraycopy(dist[i], 0, bestDist[i], 0, bestDist[i].length);
        }
        do {
            for (int i = 0; i < newDist.length; i++) {
                System.arraycopy(bestDist[i], 0, newDist[i], 0, newDist[i].length);
            }
            for (int i = 0; i < newDist.length; i++) {
                for (int j = 0; j < newDist[i].length; j++) {
                    double min = Double.MAX_VALUE;
                    for (int k = 0; k < newDist.length; k++) {
                        double now = 0;
                        if (newDist[i][k] == Double.MAX_VALUE || newDist[k][j] == Double.MAX_VALUE){
                            now = Double.MAX_VALUE;
                        }else now = newDist[i][k] + newDist[k][j];
                        if (now < min){
                            min = now;
                        }
                    }
                    bestDist[i][j] = min;
                }
            }
        }while (!Arrays.deepEquals(bestDist, newDist));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(bestDist.length*(bestDist.length+3));
        for (double[] t:bestDist
        ) {
            sb.append(Arrays.toString(t));
            sb.append("\n");
        }
        return sb.toString();
    }
}
