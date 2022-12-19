import java.util.Arrays;
import java.util.LinkedList;

public class jz68 {
//    剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
//    给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
//
//    百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
//
//    例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
//
//
//
//             
//
//    示例 1:
//
//    输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//    输出: 6
//    解释: 节点 2 和节点 8 的最近公共祖先是 6。
//    示例 2:
//
//    输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//    输出: 2
//    解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(new Integer[]{1,2,null,3,5,null}));
        TreeNode root = createBinaryTree(list);
        PrintBTree printBTree = new PrintBTree();
        System.out.println(printBTree.printTree(root));
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val > q.val) { // 保证 p.val < q.val
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        while(root != null) {
            if(root.val < p.val) // p,q 都在 root 的右子树中
                root = root.right; // 遍历至右子节点
            else if(root.val > q.val) // p,q 都在 root 的左子树中
                root = root.left; // 遍历至左子节点
            else break;
        }
        return root;
    }


    public static TreeNode  createBinaryTree(LinkedList<Integer> inputList){
        TreeNode node=null;
        if(inputList == null || inputList.isEmpty()){
            return null;
        }
        //取出队列中的第一个元素
        Integer data=inputList.removeFirst();
        if(data != null){
            //如果不为空，则创建新的对象 构造方法需要添加一个只用data的构造函数
            node = new TreeNode(data);
            //左节点使用同样递归的方法实现
            node.left=createBinaryTree(inputList);
            node.right=createBinaryTree(inputList);

        }
        return node;

    }

}
