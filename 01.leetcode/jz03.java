import java.util.HashSet;
import java.util.Set;

public class jz03 {
//    剑指 Offer 03. 数组中重复的数字
    public static void main(String[] args) {
        int nums[] = {2, 3, 1, 0, 2, 5, 3};
        jz03 jz03 = new jz03();
        System.out.println(jz03.findRepeatNumber(nums));
    }
    public int findRepeatNumber(int[] nums) {
        Set<Integer> dic = new HashSet<>();
        for(int num : nums) {
            if(dic.contains(num)) return num;
            dic.add(num);
        }
        return -1;
    }
}
