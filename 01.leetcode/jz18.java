public class jz18 {
    //剑指 Offer 18. 删除链表的节点
//    给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
//
//    返回删除后的链表的头节点。
//    示例 1:
//
//    输入: head = [4,5,1,9], val = 5
//    输出: [4,1,9]
//    解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
//    示例 2:
//
//    输入: head = [4,5,1,9], val = 1
//    输出: [4,5,9]
//    解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.

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
