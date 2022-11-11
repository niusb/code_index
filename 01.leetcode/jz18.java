public class jz18 {
    //剑指 Offer 18. 删除链表的节点
    public static void main(String[] args) {
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        jz18 jz18 = new jz18();
//        System.out.println(jz18.deleteNode(node1,1));
        ListNode listNode = new ListNode();
        listNode.print(jz18.deleteNode(node1,2));
    }
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) return head.next;
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val){
            pre = cur;
            cur = cur.next;
        }
        //cur = val 时，只要cur不为空，对当前cur进行剔除
        if (cur != null) pre.next = cur.next;
        return head;
    }
}
