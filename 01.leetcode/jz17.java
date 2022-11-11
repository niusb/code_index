import java.util.Arrays;

public class jz17 {
    public static void main(String[] args) {
        jz17 jz17 = new jz17();
        System.out.println(Arrays.toString(jz17.printNumbers(1)));
    }

    public int[] printNumbers(int n) {
        int end = (int)Math.pow(10, n) - 1;
        int[] res = new int[end];
        for(int i = 0; i < end; i++)
            res[i] = i + 1;
        return res;
    }

}
