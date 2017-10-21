/**
 * 实现分页显示。给了以下一些输入数据，要求将以下行分页显示，每页12行，其中每行已经按score排好序，
 * 分页显示的时候如果有相同host id的行，则将后面同host id的行移到下一页。
 *
 "host_id, listing_id, score, city",
 "1,28,300.1,SanFrancisco",
 "4,5,209.1,SanFrancisco",
 "20,7,208.1,SanFrancisco",
 "23,8,207.1,SanFrancisco",
 "16,10,206.1,Oakland",
 "1,16,205.1,SanFrancisco",
 "6,29,204.1,SanFrancisco"
 */

import java.util.*;

// limit based on host_id
public class PageList {
    public List<List<String>> paging(String[] items, int size) {
        // Don't know how many pages are needed
        List<List<String>> res = new ArrayList<List<String>>();
        List<Set<String>> hostsList = new ArrayList<Set<String>>(); //to avoid same host_id

        for (String str : items) {
            String host = str.split(",")[0];

            // try to insert to existing page
            boolean done = false;
            for (int i = 0; i < res.size(); i++) {
                Set<String> hosts = hostsList.get(i);
                if (hosts.size() < size && !hosts.contains(host)) {
                    hosts.add(host);
                    res.get(i).add(str);

                    done = true;
                    break;
                }
            }

            if (!done) { //need new page
                List<String> page = new ArrayList<String>();
                Set<String> hosts = new HashSet<String>();

                page.add(str);
                hosts.add(host);
                res.add(page);
                hostsList.add(hosts);
            }
        }

        return res;
    }

    public void test() {
        String[] lines = {
                "1,28,300.1,SanFrancisco",
                "4,5,209.1,SanFrancisco",
                "1,5,209.8,SanJose",
                "20,7,208.1,SanFrancisco",
                "23,8,207.1,SanFrancisco",
                "16,10,206.1,Oakland",
                "1,16,205.1,SanFrancisco",
                "6,29,204.1,SanFrancisco"
        };

        List<List<String>> pages = paging(lines, 3);
        for (List<String> page : pages) {
            System.out.println(page);
        }
    }
}
