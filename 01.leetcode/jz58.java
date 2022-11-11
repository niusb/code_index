public class jz58 {
    // 剑指 Offer 58 - II. 左旋转字符串
    public static void main(String[] args) {
        jz58 jz58 = new jz58();
        System.out.println(jz58.reverseLeftWords("abcdefg",2));
    }

    //思路：
    //1、反转前N个字符
    //2、反转n后的字符
    //3、整体反转
//    public String reverseLeftWords(String s, int n) {
//        int len=s.length();
//        StringBuilder sb=new StringBuilder(s);
//        reverseString(sb,0,n-1);
//        reverseString(sb,n,len-1);
//        return sb.reverse().toString();
//    }
    public void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    public String reverseLeftWords(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

}
