import java.util.LinkedList;

public class createBinaryTree extends TreeNode {

    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
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
