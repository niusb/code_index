public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countNum(2,22,2));
//        System.out.println(solution.maxDictionaryOrder("aabcbccacbbcbaaba"));
    }

    public int countNum (int L, int R, int x) {
        long mulk = x;
        int ans = 0;
        for (int k =0; R >= mulk; ++k){
            ans += (R /(mulk * 10)) * mulk + Math.min(Math.max(R % (mulk * 10) - mulk + 1,0), mulk);
            mulk *= 10;
        }
        return ans;
    }


//    public String maxDictionaryOrder (String s) {
//        char max ='a';
//        StringBuffer res = new StringBuffer();
//        for (int i = s.length() - 1; i >= 0; i--) {
//            if (s.charAt(i) >= max){
//                max= s.charAt(i);
//                res.append(max);
//            }
//        }
//        String ans =res.reverse().toString();
//        return ans;
//    }
}
