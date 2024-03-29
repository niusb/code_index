public class q226 {
    //226.翻转二叉树
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(7);
        TreeNode root = new TreeNode(4,left,right);
        q226 q226 = new q226();
        q226.invertTree(root);
        PrintBTree printBTree = new PrintBTree();
        System.out.println(printBTree.printTree(root));
    }
    public TreeNode invertTree(TreeNode root) {
        //递归函数的终止条件，节点为空时返回
        if(root==null) {
            return null;
        }
        //下面三句是将当前节点的左右子树交换
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        //递归交换当前节点的 左子树
        invertTree(root.left);
        //递归交换当前节点的 右子树
        invertTree(root.right);
        //函数返回时就表示当前这个节点，以及它的左右子树
        //都已经交换完了
        return root;
    }

}
