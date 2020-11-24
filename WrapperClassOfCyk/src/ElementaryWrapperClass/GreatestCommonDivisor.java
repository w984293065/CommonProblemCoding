package ElementaryWrapperClass;

public class GreatestCommonDivisor {
    public static int GCD(int a, int b){
        if (a <=0 || b <= 0)throw new RuntimeException("输入参数必须大于0！");
        int min = Math.min(a,b);
        int max = Math.max(a,b);
        int comDiv = 1;
        if (min == 1) return comDiv;
        if (min % 2 == 0 && max % 2 == 0) comDiv = 2;
        int nowDiv = 3;
        while (nowDiv < min){
            if (min % nowDiv == 0 && max % nowDiv == 0) comDiv = nowDiv;
            nowDiv += 2;
        }
        return comDiv;
    }
}
