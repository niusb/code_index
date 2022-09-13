import java.util.LinkedList;
import java.util.List;

public class jz09 {
//    剑指 Offer 09. 用两个栈实现队列
    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);
        System.out.println(cQueue.A);
    }

    static class CQueue {
        LinkedList<Integer> A, B;
        public CQueue() {
            A = new LinkedList<Integer>();
            B = new LinkedList<Integer>();
        }
        public void appendTail(int value) {
            A.addLast(value);
        }
        public int deleteHead() {
            if(!B.isEmpty()) return B.removeLast();
            if(A.isEmpty()) return -1;
            while(!A.isEmpty())
                B.addLast(A.removeLast());
            return B.removeLast();
        }
    }
}

