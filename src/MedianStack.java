/**
 * Created by qzhou on 9/22/17.
 * extension to min stack
 */
import java.util.Stack;

/* Use stack and balanced BST.
 * Because you need to remove specific number for pop()
 * O(lgN) for add/remove, O(1) for find Median
 */
public class MedianStack {
/*
    public MedianStack() {
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

    public int median() {
        if (minStack.empty()) {
            return 0;
        }
        return minStack.peek();
    }
*/
}
