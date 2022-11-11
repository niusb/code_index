public class q104 {
    //104.二叉树的最大深度
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(3);
        TreeNode left = new TreeNode(2,left1,null);
        TreeNode right = new TreeNode(7);
        TreeNode root = new TreeNode(4,left,right);
        q104 q104 = new q104();
        System.out.println(q104.maxdepth(root));
        PrintBTree printBTree = new PrintBTree();
        System.out.println(printBTree.printTree(root));
    }

    /**
     * 递归法
     */
    public int maxdepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftdepth = maxdepth(root.left);
        int rightdepth = maxdepth(root.right);
        return Math.max(leftdepth, rightdepth) + 1;

    }
}
