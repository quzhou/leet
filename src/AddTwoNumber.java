/**
 * Created by quzhou on 9/9/15.
 * https://leetcode.com/problems/add-two-numbers/
 *
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
public class AddTwoNumber {
    public static class ListNode {
        ListNode next;
        int val;
        ListNode(int d) { val = d; next = null; }
    }

    public static ListNode add(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) { return null; }
        ListNode cur1 = l1, cur2 = l2;
        ListNode head = new ListNode(0), cur = head, pre = null;
        int value, carry = 0;
        while (cur1 != null && cur2 != null) {
            value = cur1.val + cur2.val + carry;
            cur.val = value % 10;
            carry = value / 10;
            cur1 = cur1.next; cur2 = cur2.next;
            cur.next = new ListNode(0);
            pre = cur;
            cur = cur.next;
        }
        while (cur1 != null) {
            value = cur1.val + carry;
            cur.val = value % 10;
            carry = value / 10;
            cur1 = cur1.next;
            cur.next = new ListNode(0);
            pre = cur;
            cur = cur.next;
        }
        while (cur2 != null) {
            value = cur2.val + carry;
            cur.val = value % 10;
            carry = value / 10;
            cur2 = cur2.next;
            cur.next = new ListNode(0);
            pre = cur;
            cur = cur.next;
        }
        if (carry > 0) {
            cur.val = carry;
            pre = cur;
        }
        pre.next = null;
        return head;
    }
}
