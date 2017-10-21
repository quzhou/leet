import java.util.*;

/**
 * Created by quzhou on 10/21/17.
 */
public class DictMatch {
    public static class TrieNode {
        public HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        public boolean hasWord;
        public String s;
    }

    public static class Trie {
        public TrieNode root;
        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String str) {
            TrieNode cur = this.root;
            TrieNode node;

            for (int i = 0; i < str.length(); i++) {
                if (cur.children.containsKey(str.charAt(i))) {
                    node = cur.children.get(str.charAt(i));
                } else {
                    node = new TrieNode();
                    cur.children.put(str.charAt(i), node);
                }

                cur = node;
            }

            cur.hasWord = true;
            cur.s = str;
        }
    }

    // "*o*d"
    public static List<String> findMatch(Trie tree, String str) {
        List<String> res = new ArrayList<String>();
        Queue<TrieNode> q = new LinkedList<TrieNode>();

        int idx = 0;
        int sz = 1;
        q.add(tree.root);

        // d, g, m, c
        // sz == 4
        // idx = 1

        while (!q.isEmpty()) {
            TrieNode cur = q.remove();
            sz--;

            // check for this trie node
            if (idx == str.length()) {
                if (cur.hasWord) {
                    res.add(cur.s);
                }

                continue;
            }

            // add part of the trie to the queue
            char ch = str.charAt(idx);
            if (ch == '*') {
                for (TrieNode node : cur.children.values()) {
                    q.add(node);
                }
            } else {
                if (cur.children.containsKey(ch)) {
                    q.add(cur.children.get(ch));
                }
            }

            if (sz == 0) {
                sz = q.size();
                idx++;
            }
        }

        return res;
    }
}
