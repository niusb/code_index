public class jz27 {
//    剑指 Offer 27. 二叉树的镜像
    public static void main(String[] args) {

    }
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null) return null;
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }
}
