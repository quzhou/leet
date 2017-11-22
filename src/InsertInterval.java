/**
 * Created by qzhou on 12/5/16.
 * http://www.lintcode.com/en/problem/insert-interval/
 */
import java.util.List;
import java.util.ArrayList;

public class InsertInterval {
    /**
     * Insert newInterval into intervals.
     * @param intervals: Sorted interval list.
     * @param newInterval: A new interval.
     * @return: A new sorted interval list.
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        int size = intervals.size();
        if (size == 0) {
            result.add(new Interval(newInterval.start, newInterval.end));
            return result;
        }

        int i;
        for (i = 0; i < size; i++) {
            Interval cur = intervals.get(i);
            if (newInterval.start < cur.start) {
                break;
            }
        }
        if (i == size) {
            intervals.add(newInterval);
        } else {
            intervals.add(i, newInterval);
        }
        size++;

        result.add(new Interval(intervals.get(0).start, intervals.get(0).end));
        int curIdx = 0;

        for (i = 1; i < size; i++) {
            Interval cur = result.get(curIdx);
            Interval next = intervals.get(i);

            if (next.start <= cur.end) {
                cur.end = Math.max(cur.end, next.end);
            } else {
                result.add(new Interval(next.start, next.end));
                curIdx++;
            }
        }

        return result;
    }

    // method 2. Method 1 is to insert then merge (2 steps)
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<Interval>();
        int i = 0;

        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i++));
        }
        int start = (i == intervals.size()) ? newInterval.start : Math.min(newInterval.start, intervals.get(i).start);

        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            i++;
        }
        int end = (i - 1 < 0) ? newInterval.end : Math.max(newInterval.end, intervals.get(i-1).end);

        res.add(new Interval(start, end));

        while (i < intervals.size()) {
            res.add(intervals.get(i++));
        }
        return res;
    }
}
