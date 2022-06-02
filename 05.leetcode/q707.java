import java.util.List;

public class q707 {


    public static void main(String[] args) {

        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.size = 4;
        myLinkedList.head = node1;
        System.out.println(myLinkedList.get(1));
    }

    //单链表
    static class MyLinkedList {
        //size存储链表元素的个数
        int size;
        //虚拟头结点
        ListNode head;

        //初始化链表
        public MyLinkedList() {
            size = 0;
            head = new ListNode(0);
        }

        //获取第index个节点的数值
        public int get(int index) {
            //如果index非法，返回-1
            if (index < 0 || index >= size) {
                return -1;
            }
            ListNode currentNode = head;
            //包含一个虚拟头节点，所以查找第 index+1 个节点
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            return currentNode.val;
        }

        //在链表最前面插入一个节点
        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        //在链表的最后插入一个节点
        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
        // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
        // 如果 index 大于链表的长度，则返回空
        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0) {
                index = 0;
            }
            size++;
            //找到要插入节点的前驱
            ListNode pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            ListNode toAdd = new ListNode(val);
            toAdd.next = pred.next;
            pred.next = toAdd;
        }

        //删除第index个节点
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            size--;
            ListNode pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            pred.next = pred.next.next;
        }
    }

    //extends => MyLinkedList1（双链表继承了单链表）
    //双链表
    class MyLinkedList1 extends MyLinkedList {
        class ListNode {
            int val;
            ListNode next,prev;
            ListNode(int x) {val = x;}
        }

        int size;
        ListNode head,tail;//Sentinel node

        /** Initialize your data structure here. */
        public MyLinkedList1() {
            size = 0;
            head = new ListNode(0);
            tail = new ListNode(0);
            head.next = tail;
            tail.prev = head;
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            if(index < 0 || index >= size){return -1;}
            ListNode cur = head;

            // 通过判断 index < (size - 1) / 2 来决定是从头结点还是尾节点遍历，提高效率
            if(index < (size - 1) / 2){
                for(int i = 0; i <= index; i++){
                    cur = cur.next;
                }
            }else{
                cur = tail;
                for(int i = 0; i <= size - index - 1; i++){
                    cur = cur.prev;
                }
            }
            return cur.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            ListNode cur = head;
            ListNode newNode = new ListNode(val);
            newNode.next = cur.next;
            cur.next.prev = newNode;
            cur.next = newNode;
            newNode.prev = cur;
            size++;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            ListNode cur = tail;
            ListNode newNode = new ListNode(val);
            newNode.next = tail;
            newNode.prev = cur.prev;
            cur.prev.next = newNode;
            cur.prev = newNode;
            size++;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if(index > size){return;}
            if(index < 0){index = 0;}
            ListNode cur = head;
            for(int i = 0; i < index; i++){
                cur = cur.next;
            }
            ListNode newNode = new ListNode(val);
            newNode.next = cur.next;
            cur.next.prev = newNode;
            newNode.prev = cur;
            cur.next = newNode;
            size++;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if(index >= size || index < 0){return;}
            ListNode cur = head;
            for(int i = 0; i < index; i++){
                cur = cur.next;
            }
            cur.next.next.prev = cur;
            cur.next = cur.next.next;
            size--;
        }
    }
}
