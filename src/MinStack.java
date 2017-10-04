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

    // http://www.lintcode.com/en/problem/stack-sorting/
    // Given a stack of integers, sort it in ascending order using another temporary stack.
    // Pay attention to special case like duplicate numbers in stack!
    // http://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
    public void stackSorting(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        Stack<Integer> s2 = new Stack<Integer>();

        while (!stack.isEmpty()) {
            helper(stack, s2);
        }

        while (!s2.isEmpty()) {
            stack.push(s2.pop());
        }
    }
    private void helper(Stack<Integer> stack, Stack<Integer> s2) {
        int max = stack.pop();

        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            if (cur > max) {
                s2.push(max);
                max = cur;
            } else {
                s2.push(cur);
            }
        }

        // had error here for duplicate int values
        int num = 1;
        while (!s2.isEmpty() && s2.peek() <= max) {
            if (s2.peek() == max) {
                num++;
                s2.pop();
            } else {
                stack.push(s2.pop());
            }
        }

        for (int i = 0; i < num; i++) {
            s2.push(max);
        }
    }
}
