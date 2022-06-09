public class q541 {
    // 541. 反转字符串 II
    // 例：k为2时，前4个字母反转前2个
    public static void main(String[] args) {
        q541 q = new q541();
        String s = "abcdefg";
        System.out.println(q.reverseStr(s,2));
    }

    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        // 1. 每隔 2k 个字符的前 k 个字符进行反转
        for (int i = 0; i< ch.length; i += 2 * k) {
            // 2. 剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符
            if (i + k <= ch.length) {
                reverse(ch, i, i + k -1);
                continue;
            }
            // 3. 剩余字符少于 k 个，则将剩余字符全部反转
            reverse(ch, i, ch.length - 1);
        }
        return  new String(ch);

    }
    // 定义翻转函数
    public void reverse(char[] ch, int i, int j) {
        for (; i < j; i++, j--) {
            char temp  = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
        }

    }

    // 利用异或求解
//    public String reverseStr(String s, int k) {
//        char[] ch = s.toCharArray();
//        for(int i = 0; i < ch.length; i += 2 * k){
//            int start = i;
//            //这里是判断尾数够不够k个来取决end指针的位置
//            int end = Math.min(ch.length - 1, start + k - 1);
//            //用异或运算反转
//            while(start < end){
//                //可以看作 start 存入了 start && end, 可以通过 ^= start的值 消去 start
//                ch[start] ^= ch[end];
//                ch[end] ^= ch[start];
//                ch[start] ^= ch[end];
//                start++;
//                end--;
//            }
//        }
//        return new String(ch);
//    }
}
