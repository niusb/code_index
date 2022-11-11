public class q222 {
    //222.完全二叉树的节点个数
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1,left,right);
        PrintBTree printBTree = new PrintBTree();
        System.out.println(printBTree.printTree(root));
        q222 q222 = new q222();
        System.out.println(q222.countNodes(root));
    }

    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
