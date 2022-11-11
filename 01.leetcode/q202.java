import java.util.HashSet;
import java.util.Set;

public class q202 {
    // 202. 快乐数
    public static void main(String[] args) {
        q202 q = new q202();
        System.out.println(q.isHappy(18));
    }
    public boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }

    private int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }
}
