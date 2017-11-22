/**
 * Created by quzhou on 11/6/17.
 * https://leetcode.com/problems/range-module/description/
 */
import java.util.*;

public class RangeModule {
    class Interval {
        int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // key is the start point
    // non-overlapping
    private TreeMap<Integer, Interval> intervals;

    public RangeModule() {
        intervals = new TreeMap<Integer, Interval>();
    }

    public void addRange(int left, int right) {
        // Interval 'cur' may overlap with others
        Interval cur = intervals.get(left);
        if (cur == null) {
            cur = new Interval(left, right);
            intervals.put(left, cur);
        } else {
            cur.end = Math.max(cur.end, right);
        }

        // now merge intervals

        // lower one
        Integer lower = intervals.lowerKey(cur.start);
        if (lower != null && intervals.get(lower).end >= cur.start) { //overlap
            int end = Math.max(cur.end, intervals.get(lower).end);
            cur = intervals.get(lower);
            cur.end = end;
            intervals.remove(left);
        }

        // higher ones
        Integer higher = intervals.higherKey(cur.start); //like next
        while (higher != null) {
            if (intervals.get(higher).start > cur.end) {
                break;
            }

            cur.end = Math.max(cur.end, intervals.get(higher).end);
            intervals.remove(higher);

            higher = intervals.higherKey(cur.start);
        }
    }

    public boolean queryRange(int left, int right) {
        Integer low = intervals.floorKey(left);
        if (low == null) {
            return false;
        }

        Interval cur = intervals.get(low);

        return (right <= cur.end);
    }

    public void removeRange(int left, int right) {
        // handle exact match
        Interval cur = intervals.get(left);
        if (cur != null) {
            if (right < cur.end) {
                Interval one = new Interval(right, cur.end);
                intervals.remove(left);
                intervals.put(right, one);
                return;
            } else {
                intervals.remove(left);
            }
        }

        Integer lower = intervals.lowerKey(left);
        if (lower != null && intervals.get(lower).end > left) {
            if (intervals.get(lower).end > right) { // 2 intervals
                Interval one = new Interval(right, intervals.get(lower).end);
                intervals.put(right, one);

                intervals.get(lower).end = left;
            } else {
                intervals.get(lower).end = left;
            }
        }

        Integer higher = intervals.higherKey(left);
        while (higher != null) {
            if (intervals.get(higher).start >= right) {
                break;
            }

            if (intervals.get(higher).end <= right) {
                intervals.remove(higher);
            } else {
                Interval one = new Interval(right, intervals.get(higher).end);
                intervals.remove(higher);
                intervals.put(right, one);
            }

            higher = intervals.higherKey(left);
        }
    }
}
