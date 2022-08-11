import java.util.Arrays;

public class jz21 {
//    剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        jz21 jz21 = new jz21();
        System.out.println(Arrays.toString(jz21.exchange(nums)));
    }

    public int[] exchange(int[] nums) {
        int i = 0, j = nums.length - 1, tmp;
        while(i < j) {
            while(i < j && (nums[i] & 1) == 1) i++;
            while(i < j && (nums[j] & 1) == 0) j--;
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }

}
