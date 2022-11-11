public class jz42 {
//    剑指 Offer 42. 连续子数组的最大和
//    输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
//
//    要求时间复杂度为O(n)。
//
//             
//
//    示例1:
//
//    输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//    输出: 6
//    解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
    public static void main(String[] args) {
        int num[] = {-2,1,-3,4,-1,2,1,-5,4};
        jz42 jz42 = new jz42();
        System.out.println(jz42.maxSubArray(num));
    }

    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

}
