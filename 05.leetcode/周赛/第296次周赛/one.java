package 周赛.第296次周赛;

public class one {
    // 2293. 极大极小游戏
    public static void main(String[] args) {
        one one = new one();
        int[] nums = {3};
        System.out.println(one.minMaxGame(nums));
    }
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        // 重复整个过程，直到长度n==1为止
        while (n != 1) {
            int cnt = 0;
            for (int i = 0; i < n; ) {
                // 原地变换
                if (cnt % 2 == 0) {
                    nums[cnt] = Math.min(nums[i], nums[i + 1]);
                } else {
                    nums[cnt] = Math.max(nums[i], nums[i + 1]);
                }
                i += 2;
                cnt++;
            }
            // 更新长度
            n = cnt;
        }
        // 由于原地变换，最后返回数组首元素
        return nums[0];
    }
}
