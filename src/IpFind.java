/**
 * Created by qzhou on 12/6/16.
 * Rubrik
 *
 *  Given a list of IPv4 prefix, in the form of e.g. 192.168.1.0/24, etc, and given an IP address,
 *  using what data structure to store the IPv4 prefix to support quick query to find all the
 *  prefixes that the IPv4 address falls in. Hint: using trie.
 */
import java.util.ArrayList;

// store prefixes to the trie, in binary form
public class IpFind {
    public class TrieNode {
        public TrieNode[] children;
        public String prefix;

        public TrieNode() {
            this.prefix = "";
            this.children = new TrieNode[2];
        }

        public void insert(String word, int index, String data) {
            if (index == word.length()) {
                this.prefix = data;
                return;
            }

            int pos = word.charAt(index) - '0';
            if (children[pos] == null) {
                children[pos] = new TrieNode();
            }
            children[pos].insert(word, index + 1, data);
        }

        // return the last node
        public TrieNode find(String word, int index) {
            if (index == word.length()) {
                return this;
            }

            int pos = word.charAt(index) - '0';
            if (children[pos] == null) {
                return null;
            }
            return children[pos].find(word, index + 1);
        }
    }

    private TrieNode root;
    public IpFind() {
        this.root = new TrieNode();
    }

    public String intToBinary(long num) {
        String ans = "";
        long cur = num;
        while (cur > 1) {
            ans = cur % 2 + ans;
            cur = cur >> 1;
        }
        ans = cur + ans;
        return ans;
    }

    // 16.23.4.7/21
    public String convertBinary(String str) {
        String[] arr = str.split("/");
        int mask = 32;
        long network = 0;

        if (arr.length == 2) {
            mask = Integer.parseInt(arr[1]);
        }

        String[] nw = arr[0].split("\\.");
        int num = 24;
        for (int i = 0; i < 4; i++) {
            long temp = Integer.parseInt(nw[i]);
            network += temp << num;
            num -= 8;
        }

        String ans = intToBinary(network);
        int sz = 32 - ans.length();
        for (int i = 1; i <= sz; i++) {
            ans = "0" + ans;
        }

        ans = ans.substring(0, mask);
        return ans;
    }

    // ip is 10101100...

    public void insert(String network) {
        String binary = convertBinary(network);
        root.insert(binary, 0, network);
    }

    public ArrayList<String> getNetworks(String decimalIp) {
        String ip = convertBinary(decimalIp);
        ArrayList<String> result = new ArrayList<String>();

        TrieNode cur = root;
        for (int i = 0; i < 32; i++) {
            int pos = ip.charAt(i) - '0';
            if (cur.children[pos] == null) {
                break;
            }

            if (!cur.children[pos].prefix.isEmpty()) {
                result.add(cur.children[pos].prefix);
            }

            cur = cur.children[pos];
        }

        return result;
    }
}
