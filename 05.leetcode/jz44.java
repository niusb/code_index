public class jz44 {
//    剑指 Offer 44. 数字序列中某一位的数字
    public static void main(String[] args) {
        jz44 jz44 = new jz44();
        System.out.println(jz44.findNthDigit(99));
    }

    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }
}
