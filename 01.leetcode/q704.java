public class q704 {
    // 704. 二分查找
    public static void main(String[] args) {
        q704 q = new q704();
        int[] nums = {-1,0,3,5,9,12};
        int n = q.search(nums,9);
        System.out.println(n);
    }
    public int search(int[] nums, int target) {
        // 避免当 target 小于nums[0] nums[nums.length - 1]时多次循环运算
        //去除特殊情况
        int length = nums.length;
        if (target < nums[0] || target > nums[length - 1]){
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right){
            //(right - left) >> 1 -- right、left平均值取整
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid -1;
        }
        return -1;
    }

}
