/**
 * Created by qzhou on 10/20/16.
 * https://leetcode.com/problems/Merge-k-Sorted-Lists/
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * 1) Create a Min Heap of size k+1 with first k+1 elements. This will take O(k) time.
 * 2) One by one remove min element from heap, put it in result array, and add a new element to
 * heap from remaining elements.
 *
 * overall complexity will be O(k) + O((n-k)*logK), totally n elements
 * O(NlogK)
 *
 * PriorityQueue is min Heap
 */
import java.util.PriorityQueue;
import java.util.Comparator;

public class KSortedArray {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        Comparator<ListNode> nodeComparator = new Comparator<ListNode>() {
            public int compare(ListNode left, ListNode right) {
                return (left.val - right.val);
            }
        };
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, nodeComparator);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
                lists[i] = lists[i].next;
            }
        }

        int idx = 0;
        while (!heap.isEmpty()) {
            cur.next = heap.remove();
            cur = cur.next;

            while (idx < lists.length && lists[idx] == null) {
                idx++;
            }
            if (idx < lists.length) {
                heap.add(lists[idx]);
                lists[idx] = lists[idx].next;
            }
        }
        return dummy.next;
    }
}
