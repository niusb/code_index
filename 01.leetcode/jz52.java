public class jz52 {
//    剑指 Offer 52. 两个链表的第一个公共节点
//    tags:链表
    public static void main(String[] args) {
        ListNode node4 = new ListNode(5);
        ListNode node3 = new ListNode(4,node4);
        ListNode node2 = new ListNode(8,node3);
        ListNode node7 = new ListNode(1,node2);
        ListNode node6 = new ListNode(0,node7);
        ListNode node5 = new ListNode(5,node6);
        ListNode node1 = new ListNode(1,node2);
        ListNode node0 = new ListNode(4,node1);
        jz52 jz52 = new jz52();
        ListNode.print(jz52.getIntersectionNode(node0,node5));
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

}
