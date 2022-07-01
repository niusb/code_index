import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrintBTree {

    //leetcode官方
    //计算高度
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        String[][] res = new String[height][(1 << height) - 1];
        for(String[] arr:res)
            Arrays.fill(arr,"");
        List<List<String>> ans = new ArrayList<>();
        fill(res, root, 0, 0, res[0].length);
        for(String[] arr:res)
            ans.add(Arrays.asList(arr));
        return ans;
    }
    public void fill(String[][] res, TreeNode root, int i, int l, int r) {
        if (root == null)
            return;
        res[i][(l + r) / 2] = "" + root.val;
        fill(res, root.left, i + 1, l, (l + r) / 2);
        fill(res, root.right, i + 1, (l + r + 1) / 2, r);
    }
    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    //个人解法1
//    public List<List<String>> printTree(TreeNode root) {
//        int m = getDepth(root);
//        int n = (int) (Math.pow(2, m) - 1);
//        String[][] res = new String[m][n];
//        for (String[] re : res) {
//            Arrays.fill(re,"");
//        }
//        dfs(root,0,0,n,res);
//        return Arrays.stream(res).map(Arrays::asList).collect(Collectors.toList());
//    }
//
//    private int getDepth(TreeNode root) {
//        if (root == null) return 0;
//        int left = getDepth(root.left);
//        int right = getDepth(root.right);
//        return Math.max(left,right) + 1;
//    }
//
//    private void dfs(TreeNode root, int depth, int l, int r, String[][] res) {
//        if (root == null) return;
//        int mid = (r - l)/2 + l;
//        res[depth][mid] = "" + root.val;
//        dfs(root.left,depth + 1,l,mid, res);
//        dfs(root.right,depth + 1,mid + 1,r, res);
//    }

}
