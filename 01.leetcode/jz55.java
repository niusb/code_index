public class jz55 {
//    剑指 Offer 55 - I. 二叉树的深度
//    输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
//
//    例如：
//
//    给定二叉树 [3,9,20,null,null,15,7]，
//
//            3
//            / \
//            9  20
//            /  \
//            15   7
//    返回它的最大深度 3 。
    public static void main(String[] args) {
        TreeNode treeNode4 = new TreeNode(7);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode2 = new TreeNode(20,null,treeNode4);
        TreeNode treeNode1 = new TreeNode(9,treeNode3,null);
        TreeNode treeNode0 = new TreeNode(3,treeNode1,treeNode2);
        jz55 jz55 = new jz55();
        System.out.println(jz55.maxDepth(treeNode0));
    }

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


}
