public class q209 {
    // 209. 长度最小的子数组
    public static void main(String[] args) {
        q209 q = new q209();
        int[] nums = {2,3,1,2,4,3};
        int n = q.minSubArrayLen(7,nums);
        System.out.println(n);
    }
    // 滑动窗口
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while(sum >= s){
                result = Math.min(result, right -left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }














//    public int minSubArrayLen(int s, int[] nums) {
//        int left = 0;
//        int sum = 0;
//        int result = Integer.MAX_VALUE;
//        for (int right = 0; right < nums.length; right++) {
//            sum += nums[right];
//            while (sum >= s) {
//                result = Math.min(result, right - left + 1);
//                sum -= nums[left++];
//            }
//        }
//        return result == Integer.MAX_VALUE ? 0 : result;
//    }
}
