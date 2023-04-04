import java.util.Scanner;

public class jz25 {
//    剑指 Offer 25. 合并两个排序的链表


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入链表1: ");
        String input1 = scanner.nextLine();

        System.out.print("请输入链表2: ");
        String input2 = scanner.nextLine();

        scanner.close();

        ListNode merged = null;
        ListNode l1 = createList(input1);
        ListNode l2 = createList(input2);
        merged = mergeTwoLists(l1, l2);

        System.out.println("Input: " + input1 + ", " + input2);
        System.out.print("Output: ");
        while (merged != null) {
            System.out.print(merged.val);
            if (merged.next != null) {
                System.out.print("->");
            }
            merged = merged.next;
        }
        System.out.println();
    }

    private static ListNode createList(String input) {
        String[] nodes = input.split("->");
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        for (String node : nodes) {
            cur.next = new ListNode(Integer.parseInt(node));
            cur = cur.next;
        }
        return dummyHead.next;
    }



}
