public class jz53_2 {
//    剑指 Offer 53 - II. 0～n-1中缺失的数字
    public static void main(String[] args) {


    }

    public int missingNumber(int[] nums) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] == m) i = m + 1;
            else j = m - 1;
        }
        return i;
    }

}
