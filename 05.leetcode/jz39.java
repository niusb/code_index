public class jz39 {
    public static void main(String[] args) {
        jz39 jz39 = new jz39();
        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(jz39.majorityElement(nums));
    }
    public int majorityElement(int[] nums) {
        int x = 0, votes = 0;
        for(int num : nums){
            if(votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }
}
