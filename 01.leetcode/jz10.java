public class jz10 {
//    剑指 Offer 10- I. 斐波那契数列
    public static void main(String[] args) {
        jz10 jz10 = new jz10();
        System.out.println(jz10.fib(3));

    }
    public int fib(int n) {
        int a = 0, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
