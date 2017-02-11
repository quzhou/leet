/**
 * Created by qzhou on 2/10/17.
 * http://www.lintcode.com/en/problem/word-search/
 */
import java.util.HashMap;
import java.util.ArrayList;

public class WordSearch {
    class TrieNode {
        public String s;
        public HashMap<Character, TrieNode> children;
        public boolean hasWord;

        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            s = "";
            hasWord = false;
        }
    }

    class Trie {
        public TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.children.containsKey(c)) {
                    cur = cur.children.get(c);
                } else {
                    TrieNode node = new TrieNode();
                    cur.children.put(c, node);
                    cur = node;
                }
            }
            cur.hasWord = true;
            cur.s = word;
        }

        public boolean find(String s) {
            TrieNode cur = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (cur.children.containsKey(c)) {
                    cur = cur.children.get(c);
                } else {
                    return false;
                }
            }

            return cur.hasWord;
        }
    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean found = findFrom(board, i, j, word, 0);
                if (found) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findFrom(char[][] board, int x, int y, String word,
                             int idx) {
        if (idx == word.length()) {
            return true;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length ||
                board[x][y] == 0 || board[x][y] != word.charAt(idx)) {
            return false;
        }

        boolean found = false;
        char now = board[x][y];
        board[x][y] = 0;

        int[] dx = new int[] {1, 0, -1, 0};
        int[] dy = new int[] {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            found = findFrom(board, x+dx[i], y+dy[i], word, idx+1);
            if (found) {
                break;
            }
        }

        board[x][y] = now;
        return found;
    }

    // http://www.lintcode.com/en/problem/word-search-ii
    // 一个字母一个字母的比较，用trie instead of hash
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        Trie trie = new Trie();
        for (String str : words) {
            trie.insert(str);
        }

        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                find(board, i, j, trie.root, result);
            }
        }

        return result;
    }

    private void find(char[][] board, int x, int y,
                      TrieNode node, ArrayList<String> result) {
        if (node.hasWord) {
            if (!result.contains(node.s)) {
                result.add(node.s);
            }
        }

        // 字母已用，或不可能的一步
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length ||
            board[x][y] == 0 || !node.children.containsKey(board[x][y])) {
            return;
        }

        TrieNode cur = node.children.get(board[x][y]);

        char now = board[x][y];
        board[x][y] = 0;

        int[] dx = new int[] {1, 0, -1, 0};
        int[] dy = new int[] {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            find(board, x+dx[i], y+dy[i], cur, result);
        }

        board[x][y] = now;
    }
}
