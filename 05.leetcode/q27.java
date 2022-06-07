public class q27 {
    // 27. 移除元素
    public static void main(String[] args) {
        q27 q = new q27();
        int[] nums = {3,2,2,3};
        int n = q.removeElement(nums, 3);
        System.out.println(n);
    }
    public int removeElement(int[] nums, int val) {
        // 快慢指针
        int fastIndex = 0;
        int slowIndex;
        for (slowIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;

    }
}
