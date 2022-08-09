import java.util.Arrays;

//剑指 Offer 60. n个骰子的点数
public class jz60 {
    public static void main(String[] args) {
        jz60 jz60 = new jz60();
        System.out.println(Arrays.toString(jz60.dicesProbability(3)));
    }
    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }

}
