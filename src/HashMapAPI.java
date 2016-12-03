/**
 * Created by qzhou on 12/6/16.
 * Rubrik
 *
 * Implement the following APIs:
 void addRef(int n); --- add a reference for integer “n”.
 void delRef(int n); --- delete a reference for integer “n”;
 int getIntWithMaxRef(); --- get the integer which has max number of references.
 int getIntWithMinRef(); --- get the integer which has min number of references.

 All above APIs needed to be implementated with O(1) complexity.
 *
 */
import java.util.HashMap;
import java.util.Stack;

public class HashMapAPI {
    private static class Element {
        public int number;
        public int count;
        public Element(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }

    private HashMap<Integer, Integer> map; //number : count
    private Stack<Element> minStack;
    private Stack<Element> maxStack;
    public HashMapAPI() {
        this.map = new HashMap<Integer, Integer>();
        this.minStack = new Stack<Element>();
        this.maxStack = new Stack<Element>();
    }

    public void addRef(int n) {
        Integer count = map.get(n);
        int cntValue;
        if (count == null) {
            cntValue = 1;
        } else {
            cntValue = count.intValue() + 1;
        }
        map.put(n, cntValue);

        // maintain stack
    }

    public void delRef(int n) {
        Integer count = map.get(n);
        int cntValue = 0;
        if (count != null) {
            cntValue = count.intValue();
            if (cntValue > 0) {
                cntValue--;
            }
            map.put(n, cntValue);
        }
    }
}
