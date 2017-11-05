import java.util.*;

/**
 * Created by quzhou on 10/21/17.
 */
public class RomanEncode {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        boolean hasWord;
        Set<String> str = new HashSet<String>();
    }

    public TrieNode root = new TrieNode();

    public void insert(String input) {
        String str = encode(input); //now roman

        TrieNode cur = this.root;
        TrieNode node;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (cur.children.containsKey(ch)) {
                node = cur.children.get(ch);
            } else {
                node = new TrieNode();
                cur.children.put(ch, node);
            }

            cur = node;
        }

        cur.hasWord = true;
        cur.str.add(input);
    }

    public String encode(String input) {
        String[] code = {
                "I", "II", "III", "IV", "V",
                "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV",
                "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI"
        };

        String res = "";
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            res += code[ch - 'a'];
        }
        return res;
    }

    public List<String> decode(String input) {
        List<String> res = new ArrayList<String>();
        TrieNode cur = this.root;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (!cur.children.containsKey(ch)) {
                return res; //not found
            }

            cur = cur.children.get(ch);
        }

        for (String word : cur.str) {
            res.add(word);
        }
        return res;
    }

    public boolean startWith(String input) {
        TrieNode cur = this.root;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (!cur.children.containsKey(ch)) {
                return false;
            }

            cur = cur.children.get(ch);
        }

        return true;
    }
}
