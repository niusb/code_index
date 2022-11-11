package 周赛.第296次周赛;

import java.util.Arrays;

public class two {
    // 2294.划分数组使最大差为 K
    public static void main(String[] args) {
        two two = new two();
        int[] nums = {3,6,1,2,5};
        System.out.println(two.partitionArray(nums, 2));
    }

    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, min = nums[0], cnt = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] - min > k) {
                cnt++;
                min = nums[i];
            }
        }
        return cnt;
    }

}
