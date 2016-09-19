/**
 * Created by quzhou on 9/14/15.
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Given a linked list, remove the nth node from the end of list and return its head.
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Given n will always be valid.
 */
public class RemoveNthNode {
    public static class ListNode {
        ListNode next;
        int val;
        public ListNode(int data) { val = data; next = null; }
    }

    public static ListNode generateList (int[] data) {
        if (data.length == 0) return null;
        ListNode head = new ListNode(data[0]);
        ListNode pre = head, cur = null;
        for (int i = 1; i < data.length; i++) {
            cur = new ListNode(data[i]);
            pre.next = cur;
            pre = cur;
        }
        pre.next = null;
        return head;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node1 = head, node2 = head, preNode = null;
        for (int i = 1; i < n; i++){
            node2 = node2.next;
        }
        if (node2.next == null) {
            return node1.next;
        }
        while (node2.next != null) {
            preNode = node1;
            node1 = node1.next;
            node2 = node2.next;
        }
        preNode.next = node1.next;
        return head;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5};
        ListNode head = generateList(data);
        removeNthFromEnd(head, 2);

        //print list
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
