public class jz10_2 {
//    剑指 Offer 10- II. 青蛙跳台阶问题
    public static void main(String[] args) {
        jz10_2 jz10_2 = new jz10_2();
        System.out.println(jz10_2.numWays(2));
    }
    public int numWays(int n) {
        int a = 1, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
