public class q28 {
//    28. 找出字符串中第一个匹配项的下标
//    https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
    public static void main(String[] args) {
        q28 q28 = new q28();
        System.out.println(q28.strStr("sadbutsad", "sad"));
    }
        // KMP 算法
        // ss: 原串(string)  pp: 匹配串(pattern)
        public int strStr(String ss, String pp) {
            if (pp.isEmpty()) return 0;

            int n = ss.length(), m = pp.length();

            ss = " " + ss;
            pp = " " + pp;

            char[] s = ss.toCharArray();
            char[] p = pp.toCharArray();

            int[] next = new int[m + 1];


//           为什么i从2开始，是因为ss加了空格
            for (int i = 2, j = 0; i < m; i++){
//                怎么匹配呢
//                匹配不成功到下一个next点
                while (j > 0 && p[i] != p[j + 1]) j = next[j];
//                匹配成功了呢
                if (p[j] == p[j + 1]) j++;
                next[i] = j;
            }

//            对原串进行匹配
            for (int i = 1, j = 0; i < n; i++) {
//                匹配不成功怎么搞
//                跳掉下一个netx点
                while (j > 0 && s[i] != p[j + 1]) j =next[j];
//                匹配成功怎么搞
                if (s[i] == p[j + 1]) j++;

                if (j == m) return i - m;

            }

            

            return -1;
        }


}
