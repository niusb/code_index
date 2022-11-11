import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class jz32_3 {
//    剑指 Offer 32 - III. 从上到下打印二叉树 III
public List<List<Integer>> levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    if(root != null) queue.add(root);
    while(!queue.isEmpty()) {
        LinkedList<Integer> tmp = new LinkedList<>();
        for(int i = queue.size(); i > 0; i--) {
            TreeNode node = queue.poll();
            if(res.size() % 2 == 0) tmp.addLast(node.val);
            else tmp.addFirst(node.val);
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
        res.add(tmp);
    }
    return res;
}
}
