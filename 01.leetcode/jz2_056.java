import java.util.HashSet;
import java.util.Set;

public class jz2_056 {
//    剑指 Offer II 056. 二叉搜索树中两个节点之和
    public static void main(String[] args) {
        jz2_056 jz2_056 = new jz2_056();

    }
    Set<Integer> set = new HashSet<Integer>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

}
