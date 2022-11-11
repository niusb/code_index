import java.util.HashMap;
import java.util.Map;

public class jz35 {
//    剑指 Offer 35. 复杂链表的复制

    public static void main(String[] args) {
        jz35 jz35 = new jz35();
        Node node = new Node(3);
        System.out.println(jz35.copyRandomList(node).val);
    }
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while(cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        // 4. 构建新链表的 next 和 random 指向
        while(cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 5. 返回新链表的头节点
        return map.get(head);
    }
    //复杂链表节点定义
    static class Node {
        int val;
        Node next, random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}
