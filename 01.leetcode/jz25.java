public class jz25 {
//    剑指 Offer 25. 合并两个排序的链表
    public static void main(String[] args) {
        jz25 jz25 = new jz25();
        ListNode node3 = new ListNode(4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);
        ListNode node6 = new ListNode(4);
        ListNode node5 = new ListNode(3,node6);
        ListNode node4 = new ListNode(1,node5);
        ListNode.print(jz25.mergeTwoLists(node1,node4));
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0), cur = pre;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur=cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return pre.next;
    }

}
