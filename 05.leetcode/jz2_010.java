public class jz2_010 {
    public static void main(String[] args) {
        jz2_010 jz2_010 = new jz2_010();
        System.out.println(jz2_010.numWays(10));
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
