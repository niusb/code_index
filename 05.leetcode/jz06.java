import java.util.ArrayList;
import java.util.Arrays;

public class jz06 {
//    剑指 Offer 06. 从尾到头打印链表
    public static void main(String[] args) {
        jz06 jz06 = new jz06();
        ListNode listNode2 = new ListNode(2);
        ListNode listNode1 = new ListNode(1,listNode2);
        ListNode listNode0 = new ListNode(0,listNode1);
        System.out.println(Arrays.toString(jz06.reversePrint(listNode0)));
    }

    ArrayList<Integer> tmp = new ArrayList<Integer>();
    public int[] reversePrint(ListNode head) {
        recur(head);
        int[] res = new int[tmp.size()];
        for(int i = 0; i < res.length; i++)
            res[i] = tmp.get(i);
        return res;
    }
    void recur(ListNode head) {
        if(head == null) return;
        recur(head.next);
        tmp.add(head.val);
    }
}
