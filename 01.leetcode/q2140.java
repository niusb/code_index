import java.util.ArrayList;
import java.util.List;

public class q2140 {

    public static void main(String[] args) {
        int[][] questions = {{3,2},{4,3},{4,4},{2,5}};
        q2140 q2140 = new q2140();
        System.out.println(q2140.mostPoints(questions));
    }
    public long mostPoints(int[][] questions) {
        int len = questions.length;
        long[] dp = new long[len+1];
        for(int i=len-1; i>=0; i--){
            int[] q = questions[i];
            int j = q[1] + i + 1;
            dp[i] = Math.max(dp[i+1], q[0] + (j < len ? dp[j] : 0));
        }
        return dp[0];
    }


}
