/**
 * Created by qzhou on 11/18/16.
 */
import java.util.ArrayList;
import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        this.stack = new Stack<Integer>();
        this.minStack = new Stack<Integer>();
    }

    public void push(int number) {
        if (minStack.empty()) {
            minStack.push(number);
        } else if (number <= minStack.peek()) {
            minStack.push(number);
        }

        stack.push(number);
    }

    public int pop() {
        if (stack.empty()) {
            return 0;
        }

        int ret = stack.pop();
        if (minStack.peek() == ret) {
            minStack.pop();
        }
        return ret;
    }

    public int min() {
        if (minStack.empty()) {
            return 0;
        }
        return minStack.peek();
    }
}
