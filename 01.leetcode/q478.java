import java.util.Arrays;
import java.util.Random;

public class q478 {
    // 478.在圆内随机生成点
    public static void main(String[] args) {
        Solution s = new Solution(3.0,0.0,0);
        double[] d = s.randPoint();
        System.out.println(Arrays.toString(d));
    }
    static class Solution {
        Random random;
        double xc, yc, r;

        public Solution(double radius, double x_center, double y_center) {
            random = new Random();
            xc = x_center;
            yc = y_center;
            r = radius;
        }

        public double[] randPoint() {
            while (true) {
                double x = random.nextDouble() * (2 * r) - r;
                double y = random.nextDouble() * (2 * r) - r;
                if (x * x + y * y <= r * r) {
                    return new double[]{xc + x, yc + y};
                }
            }
        }
    }
}
