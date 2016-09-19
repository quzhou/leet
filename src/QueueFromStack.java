/**
 * Created by qzhou on 9/21/15.
 * https://leetcode.com/problems/implement-queue-using-stacks/
 *
 * Implement the following operations of a queue using stacks.
 *  push(x) -- Push element x to the back of queue.
 *  pop() -- Removes the element from in front of queue.
 *  peek() -- Get the front element.
 *  empty() -- Return whether the queue is empty.
 */
import java.util.Stack;

public class QueueFromStack {
    // s1 oldest on top, s2 newest on top
    Stack<Integer> s1, s2;
    public QueueFromStack() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }

    public void push(int x) {
        s2.push(x);
    }

    public void pop() {
        if (s1.empty() && s2.empty()) {
            return;
        }
        if (!s1.empty()) {
            s1.pop();
        } else {
            while (!s2.empty()) {
                s1.push(s2.pop());
            }
            s1.pop();
        }
    }

    public int peek() {
        if (!s1.empty()) {
            return s1.peek();
        } else {
            while (!s2.empty()) {
                s1.push(s2.pop());
            }
            return s1.peek();
        }
    }

    public boolean empty() {
        return (s1.empty() && s2.empty());
    }
}
