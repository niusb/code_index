import java.util.*;

public class q2521 {
    //    2521. 数组乘积中的不同质因数数目
//    给你一个正整数数组 nums ，对 nums 所有元素求积之后，找出并返回乘积中 不同质因数 的数目。
//
//    注意：
//
//    质数 是指大于 1 且仅能被 1 及自身整除的数字。
//    如果 val2 / val1 是一个整数，则整数 val1 是另一个整数 val2 的一个因数。
//
//
//    示例 1：
//
//    输入：nums = [2,4,3,7,10,6]
//    输出：4
//    解释：
//    nums 中所有元素的乘积是：2 * 4 * 3 * 7 * 10 * 6 = 10080 = 25 * 32 * 5 * 7 。
//    共有 4 个不同的质因数，所以返回 4 。
//    示例 2：
//
//    输入：nums = [2,4,8,16]
//    输出：1
//    解释：
//    nums 中所有元素的乘积是：2 * 4 * 8 * 16 = 1024 = 210 。
//    共有 1 个不同的质因数，所以返回 1 。
    public static void main(String[] args) {
        q2521 q2521 = new q2521();
        int[] nums = {2,4,3,7,10,6};
        System.out.println(q2521.distinctPrimeFactors(nums));
    }

    public int distinctPrimeFactors(int[] nums) {
        final int N = (int) (1e3);
        List<Integer> primes = new ArrayList<>();
        boolean[] mark = new boolean[N + 1];
        Arrays.fill(mark, true);
        for (int x = 2; x < N + 1; x++) {
            if (mark[x] == true) {
                primes.add(x);
            }
            for (int j = 0; j < primes.size(); j++) {
                if (x * primes.get(j) > N) {
                    break;
                }
                mark[x * primes.get(j)] = false;
                if (x % primes.get(j) == 0) {
                    break;
                }
            }
        }

        Set<Integer> vis = new HashSet<>();
        for (int num : nums) {
            for (int x : primes) {
                if (x > num) {
                    break;
                }
                if (num % x == 0) {
                    vis.add(x);
                }
            }
        }
        int res = vis.size();
        return res;
    }
}

