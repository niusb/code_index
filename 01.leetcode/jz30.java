import java.util.Stack;

public class jz30 {
//    剑指 Offer 30. 包含 min 函数的栈
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
//        minStack.push(1);
        minStack.push(2);
        System.out.println(minStack.min());
    }
    static class MinStack {
        Stack<Integer> A, B;
        public MinStack() {
            A = new Stack<>();
            B = new Stack<>();
        }
        public void push(int x) {
            A.add(x);
            if(B.empty() || B.peek() >= x)
                B.add(x);
        }
        public void pop() {
            if(A.pop().equals(B.peek()))
                B.pop();
        }
        public int top() {
            return A.peek();
        }
        public int min() {
            return B.peek();
        }
    }
}
