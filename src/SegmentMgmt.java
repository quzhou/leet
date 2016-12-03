/**
 * Created by qzhou on 12/8/16.
 */
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

public class SegmentMgmt {
    private static class Segment {
        public int offset;
        public int length;
        public Segment(int offset, int length) {
            this.offset = offset;
            this.length = length;
        }
    }

    private NavigableMap<Integer, Segment> map;

    public SegmentMgmt() {
        this.map = new TreeMap<Integer, Segment>();
    }

    public void remember(int offset, int length) {
        map.put(offset, new Segment(offset, length));
    }

    public void forget(int offset, int length) {
        if (map.isEmpty()) {
            return;
        }

        Map.Entry<Integer, Segment> floor = map.floorEntry(offset);
        int end = offset + length;
        Map.Entry<Integer, Segment> endFloor = map.floorEntry(end);
        boolean floorNull = false;

        if (endFloor == null) {
            return;
        }
        if (floor == null) {
            floorNull = true;
            floor = map.firstEntry();
        }

        NavigableMap<Integer, Segment> submap = map.subMap(floor.getKey(), true, endFloor.getKey(), true);
        Set<Integer> keys = submap.keySet();

        for (Integer key : keys) {
            System.out.println(key);
        }


    }
}
