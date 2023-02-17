import javax.management.ListenerNotFoundException;

public class q203 {
    // 203. 移除链表元素
    public static void main(String[] args) {
        q203 q = new q203();
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode n = q.removeElements(node1, 3);
        n.print(n);
    }

    public ListNode removeElements(ListNode head, int val) {
//        2023/02/17手写
        if (head == null){
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null){
            if (cur.val == val){
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;

//        if (head == null) {
//            return head;
//        }
//        // 因为删除可能涉及到头节点，所以设置dummy节点，统一操作
//        ListNode dummy = new ListNode(-1, head);
//        ListNode pre = dummy;
//        ListNode cur = head;
//        while (cur != null) {
//            if (cur.val == val) {
//                pre.next = cur.next;
//            } else {
//                pre = cur;
//            }
//            cur = cur.next;
//        }
//        return dummy.next;
    }
}
