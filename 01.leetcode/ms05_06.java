public class ms05_06 {
//    https://leetcode.cn/problems/convert-integer-lcci/
    public static void main(String[] args) {
        int A = 29, B = 15;
        ms05_06 ms05_06 = new ms05_06();
        System.out.println(ms05_06.convertInteger(A, B));
    }

    public int convertInteger(int A, int B) {
//        将a和b换算为二进制形式后按位进行异或运算，即遇相同位取0不同位取1
        int temp = A ^ B;
        int count = 0;
        while (temp != 0) {
            temp &= (temp - 1);  // 去掉二进制表示的最右边的1
            count++;
        }
        return count;
    }
}
