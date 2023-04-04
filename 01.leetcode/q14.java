public class q14 {

    public static void main(String[] args) {
        q14 q14 = new q14();

//        String[] strs = { "customer", "car", "cat" };
//         String[] strs = { "customer", "car", null };//空串
//         String[] strs = {};//空串
         String[] strs = null;//空串
        System.out.println(q14.longestCommonPrefix(strs));// c
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        String ans = strs[0];
        for(int i =1;i<strs.length;i++) {
            int j=0;
            for(;j<ans.length() && j < strs[i].length();j++) {
                if(ans.charAt(j) != strs[i].charAt(j))
                    break;
            }
            ans = ans.substring(0, j);
            if(ans.equals(""))
                return ans;
        }
        return ans;
    }
}

