public class jz2_050 {
//    剑指 Offer II 050. 向下的路径节点之和
    public static void main(String[] args) {
        jz2_050 jz2_050 = new jz2_050();
        TreeNode node8 = new TreeNode(1);
        TreeNode node7 = new TreeNode(-2);
        TreeNode node6 = new TreeNode(3);
        TreeNode node5 = new TreeNode(11);
        TreeNode node4 = new TreeNode(2,node8,null);
        TreeNode node3 = new TreeNode(3,node6,node7);
        TreeNode node2 = new TreeNode(-3,null,node5);
        TreeNode node1 = new TreeNode(5,node3,node4);
        TreeNode root = new TreeNode(10,node1,node2);
        System.out.println(jz2_050.pathSum(root,8));
    }
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = rootSum(root, targetSum);
        ret += pathSum(root.left, targetSum);
        ret += pathSum(root.right, targetSum);
        return ret;
    }

    public int rootSum(TreeNode root, int targetSum) {
        int ret = 0;

        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            ret++;
        }

        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;
    }
}
