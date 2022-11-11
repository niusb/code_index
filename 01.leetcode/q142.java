public class q142 {
    // 142.环形链表Ⅱ
    public static void main(String[] args) {
        q142 q = new q142();
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        ListNode n = q.detectCycle(node1);
        //todo 为什么 ListNode.print 会一直输出
        System.out.println(n.val);
//        ListNode.print(n);
    }
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// 有环
                ListNode index1 = fast;
                ListNode index2 = head;
                // key step
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                //相遇点==> fast 比 slow 多走n倍环形链表的长度

                //key 通过相遇点状态，构建第二次相遇的分析
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
}
