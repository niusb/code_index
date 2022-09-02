import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class jz2_086 {
//    剑指 Offer II 086. 分割回文子字符串
    public static void main(String[] args) {
        String s = "google";
        jz2_086 jz2_086 = new jz2_086();
        String[][] ret = jz2_086.partition(s);
        //二维数组的输出
        for(int i=0;i<ret.length;i++){
            System.out.println(Arrays.toString(ret[i]));
        }
//        System.out.println(Arrays.toString(ret));
    }
    boolean[][] f;
    List<List<String>> tmp = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    public String[][] partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        int rows = tmp.size();
        String[][] ret = new String[rows][];
        for (int i = 0; i < rows; ++i) {
            int cols = tmp.get(i).size();
            ret[i] = new String[cols];
            for (int j = 0; j < cols; ++j) {
                ret[i][j] = tmp.get(i).get(j);
            }
        }
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            tmp.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

}
