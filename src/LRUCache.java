/**
 * Created by qzhou on 10/27/16.
 */
import java.util.HashMap;

public class LRUCache {
    // 1 HashMap, each key points to 1 node
    // and 1 double-linked List
    private static class DoubleLinkNode {
        public int key;
        public int value;
        public DoubleLinkNode next;
        public DoubleLinkNode pre;

        public DoubleLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = this.pre = null;
        }
    }

    private int capacity;
    private int length;
    private HashMap<Integer, DoubleLinkNode> map;
    private DoubleLinkNode head;
    private DoubleLinkNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.length = 0;
        this.map = new HashMap<Integer, DoubleLinkNode>();
        this.head = this.tail = null;
    }

    public int get(int key) {
        DoubleLinkNode node = this.map.get(key);
        if (node == null) {
            return -1;
        }

        // change order in list
        if (node.pre != null) {
            if (node.next == null) { //node is tail
                node.pre.next = null;
                this.tail = node.pre;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }

            //put node to head
            node.next = head;
            node.pre = null;
            node.next.pre = node;
            this.head = node;
        }

        return node.value;
    }

    public void set(int key, int value) {
        if (this.capacity < 1) {
            return;
        }

        DoubleLinkNode node = this.map.get(key);
        if (node == null) { //add new entry
            node = new DoubleLinkNode(key, value);
            this.length++;

            if (this.length > this.capacity) { //remove LRU
                this.map.remove(this.tail.key);

                if (this.capacity == 1) {
                    this.head = this.tail = null;
                } else {
                    this.tail.pre.next = null;
                    this.tail = this.tail.pre;
                }
            }

            node.next = head;
            node.pre = null;
            if (node.next != null) {
                node.next.pre = node;
            }
            this.head = node;
            if (this.length == 1) {
                this.tail = node;
            }

            this.map.put(key, node);
        } else {
            node.value = value;

            if (node.pre != null) {
                if (node.next == null) { //node is tail
                    node.pre.next = null;
                    this.tail = node.pre;
                } else {
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }

                //put node to head
                node.next = head;
                node.pre = null;
                node.next.pre = node;
                this.head = node;
            }
        }
    }
}
