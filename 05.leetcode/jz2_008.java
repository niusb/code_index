public class jz2_008 {
//    剑指 Offer II 008. 和大于等于 target 的最短子数组
    public static void main(String[] args) {
        jz2_008 jz2_008 = new jz2_008();
        int s = 7;
        int nums[] = {2,3,1,2,4,3};
        System.out.println(jz2_008.minSubArrayLen(s,nums));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

}
