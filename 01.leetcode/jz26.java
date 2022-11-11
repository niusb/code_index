public class jz26 {
    //剑指 Offer 26. 树的子结构
    public static void main(String[] args) {
        jz26 jz26 = new jz26();
        TreeNode A4 = new TreeNode(2);
        TreeNode A3 = new TreeNode(1,A4,null);
        TreeNode A2 = new TreeNode(5);
        TreeNode A1 = new TreeNode(4,A3,A4);
        TreeNode A0 = new TreeNode(3,A1,A2);
        System.out.println(jz26.isSubStructure(A0,A3));
    }
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }
    boolean recur(TreeNode A, TreeNode B) {
        if(B == null) return true;
        if(A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    public static class jz20 {
    }
}
