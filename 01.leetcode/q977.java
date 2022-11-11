import java.util.Arrays;

public class q977 {
    // 977. 有序数组的平方
    public static void main(String[] args) {
        q977 q = new q977();
        int[] nums = {-4,-1,0,3,10};
        int[] n = q.sortedSquares(nums);
//        foreach循环
//        for (int a : n
//             ) {
//            System.out.println(a);
//        }

//        Arrays.toString =>数组转字符串
        System.out.println(Arrays.toString(n));
    }
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[nums.length];
        int idnex = nums.length - 1;
//        只能先放右边，因为始末两个树的平方，必定有一个是最大的
        while (left <= right) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                result[idnex] = nums[right] * nums[right];
                idnex--;
                right--;
            }else{
                result[idnex] = nums[left] * nums[left];
                idnex--;
                left++;
            }
        }
        return result;
    }

//    public int[] sortedSquares(int[] nums) {
//        int right = nums.length - 1;
//        int left = 0;
//        int[] result = new int[nums.length];
//        int index = result.length - 1;
//        while (left <= right) {
//            if (nums[left] * nums[left] > nums[right] * nums[right]) {
//                result[index--] = nums[left] * nums[left];
//                ++left;
//            } else {
//                result[index--] = nums[right] * nums[right];
//                --right;
//            }
//        }
//        return result;
//    }
}
