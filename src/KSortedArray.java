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
    class Element {
        public int row;
        public ListNode node;
        public Element(int row, ListNode node) {
            this.row = row;
            this.node = node;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        Comparator<Element> nodeComparator = new Comparator<Element>() {
            public int compare(Element left, Element right) {
                return (left.node.val - right.node.val);
            }
        };
        PriorityQueue<Element> heap = new PriorityQueue<Element>(lists.length, nodeComparator);

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                Element elem = new Element(i, lists[i]);
                heap.add(elem);
                lists[i] = lists[i].next;
            }
        }

        while (!heap.isEmpty()) {
            Element elem = heap.remove();
            cur.next = elem.node;
            cur = cur.next;

            if (lists[elem.row] != null) {
                Element nextElem = new Element(elem.row, lists[elem.row]);
                heap.add(nextElem);
                lists[elem.row] = lists[elem.row].next;
            }
        }
        return dummy.next;
    }
}
