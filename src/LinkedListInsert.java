/**
 * Created by quzhou on 10/20/17.
 题目
 插入一个新的节点到一个sorted cycle linkedlist（升序），返回新的节点。给的list节点不一定是最小节点。

 所以需要考虑两种情况：
 1，正常插入在两个节点中间；
 2，插入在最大最小即排序起始处。
 注意：可能list中含有duplicate！！
 */


public class LinkedListInsert {
    public ListNode insert(ListNode head, int val) {
        if (head == null) {
            ListNode node = new ListNode(val);
            node.next = node;
            return node;
        }

        ListNode cur = head;

        do {
            if (val >= cur.val && val <= cur.next.val) {
                break;
            }

            // 如果正好是排序链断开处
            if (cur.val > cur.next.val && (val < cur.next.val || val > cur.val)) {
                break;
            }

            cur = cur.next;
        } while (cur != head);

        // add new node
        ListNode newNode = new ListNode(val);
        newNode.next = cur.next;
        cur.next = newNode;

        return newNode;
    }
}
