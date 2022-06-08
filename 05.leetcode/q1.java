import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class q1 {
    // 1. 两数之和 (哈希表实现）
    public static void main(String[] args) {
        q1 q = new q1();
        int[] nums = {2,7,11,15};
        System.out.println(Arrays.toString(q.twoSum(nums,9)));
    }
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if(nums == null || nums.length == 0){
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            // 计算出目标值与当前值的 差值 ，匹配之后的数值中是否有相等的
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                res[1] = i;
                res[0] = map.get(temp);
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
