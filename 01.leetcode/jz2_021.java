public class jz2_021 {
//    剑指 Offer II 021. 删除链表的倒数第 n 个结点
    public static void main(String[] args) {
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4,node4);
        ListNode node2 = new ListNode(3,node3);
        ListNode node1 = new ListNode(2,node2);
        ListNode head = new ListNode(1,node1);
        jz2_021 jz2_021 = new jz2_021();
        ListNode.print(jz2_021.removeNthFromEnd(head, 2));
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //设置虚拟头结点
        ListNode dummy = new ListNode(0,head);
        int length = getLength(head);
        //寻找目标节点删除
        ListNode cur = dummy;
        //注意调试起始点
        for (int i = 0; i < length - n; ++i) {
            cur = cur.next;
        }
        //Exception in thread "main" java.lang.NullPointerException
        //	at jz2_021.removeNthFromEnd(jz2_021.java:22)
        //	at jz2_021.main(jz2_021.java:10)
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null){
            ++length;
            head = head.next;
        }
        return length;
    }

//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode dummy = new ListNode(0, head);
//        int length = getLength(head);
//        ListNode cur = dummy;
//        for (int i = 1; i < length - n + 1; ++i) {
//            cur = cur.next;
//        }
//        cur.next = cur.next.next;
//        ListNode ans = dummy.next;
//        return ans;
//    }

}
