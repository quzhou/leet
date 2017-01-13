/**
 * Created by qzhou on 1/6/17.
 * https://leetcode.com/problems/alien-dictionary/
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class AlienDictionary {
    public class Node {
        public int degree;
        public ArrayList<Integer> neighbour = new ArrayList<Integer>();
        void Node() {
            degree = 0;
        }
    }
    public String alienOrder(String[] words) {
        Node[] node = new Node[26];
        boolean[] happen = new boolean[26];
        for (int i = 0; i < 26; i++) {
            node[i] = new Node();
        }
        //Build the Graph
        for (int i = 0; i < words.length; i++) {
            int startPoint = 0, endPoint = 0;
            for (int j = 0; j < words[i].length(); j++) {
                happen[charToInt(words[i].charAt(j))] = true;
            }
            if (i != words.length - 1) {
                for (int j = 0; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
                    if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                        startPoint = charToInt(words[i].charAt(j));
                        endPoint = charToInt(words[i + 1].charAt(j));
                        break;
                    }
                }
            }
            if (startPoint != endPoint) {
                node[startPoint].neighbour.add(endPoint);
                node[endPoint].degree++;
            }
        }
        //Topological Sort
        Queue<Integer> queue = new LinkedList<Integer>();
        String ans = "";
        for (int i = 0; i < 26; i++) {
            if (node[i].degree == 0 && happen[i]) {
                queue.offer(i);
                ans = ans + intToChar(i);
            }
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i : node[now].neighbour) {
                node[i].degree--;
                if (node[i].degree == 0) {
                    queue.offer(i);
                    ans = ans + intToChar(i);
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (node[i].degree != 0) {
                return "";
            }
        }
        return ans;
    }
    public char intToChar(int i) {
        return (char)('a' + i);
    }
    public int charToInt(char ch) {
        return ch - 'a';
    }

    // my old way
    private void addSeq(String[] words, ArrayList<String> cont, int start, int end, int col) {
        if (start == -1 || start > end) {
            return;
        }

        String seq = "";

        for (int i = start; i <= end; i++) {
            if (col >= words[i].length()) {
                continue;
            }

            if (seq.isEmpty() || seq.charAt(seq.length() - 1) != words[i].charAt(col)) {
                seq += words[i].charAt(col);
            }
        }

        if (!seq.isEmpty()) {
            cont.add(seq);
        }
    }

    public String alienOrder2(String[] words) {
        ArrayList<String> seqs = new ArrayList<String>();

        for (int idx = 0; ; idx++) {
            int cnt = 0;

            String last = "";
            int start = -1;

            for (int i = 0; i < words.length; i++) {
                if (idx >= words[i].length()) {
                    if (start != -1 && words[i].equals(last)) {
                        return "";
                    }

                    addSeq(words, seqs, start, i - 1, idx);
                    start = -1;
                    last = "";

                    continue;
                }
                cnt++;

                String prefix = words[i].substring(0, idx);
                if (start == -1) {
                    start = i;
                    last = prefix;
                } else if (!prefix.equals(last)) {
                    addSeq(words, seqs, start, i - 1, idx);
                    start = i;
                    last = prefix;
                }

                if (i == words.length - 1) {
                    addSeq(words, seqs, start, i, idx);
                }
            }

            if (cnt == 0) {
                break;
            }
        }

        return getOrder(seqs);
    }

    private String getOrder(ArrayList<String> seqs) {
        String result = "";
        HashMap<Character, HashSet<Character>> incoming = new HashMap<Character, HashSet<Character>>();
        HashMap<Character, HashSet<Character>> outgoing = new HashMap<Character, HashSet<Character>>();

        for (String str : seqs) {
            int sz = str.length();
            for (int i = 1; i < sz; i++) {
                HashSet<Character> pres = incoming.get(str.charAt(i));
                if (pres == null) {
                    pres = new HashSet<Character>();
                }
                pres.add(str.charAt(i-1));
                incoming.put(str.charAt(i), pres);

                HashSet<Character> neighbors = outgoing.get(str.charAt(i-1));
                if (neighbors == null) {
                    neighbors = new HashSet<Character>();
                }
                neighbors.add(str.charAt(i));
                outgoing.put(str.charAt(i-1), neighbors);
            }

            if (!outgoing.containsKey(str.charAt(sz-1))) {
                outgoing.put(str.charAt(sz-1), new HashSet<Character>());
            }
        }

        // topological sort
        Queue<Character> q = new LinkedList<Character>();
        for (Character node : outgoing.keySet()) {
            if (!incoming.containsKey(node)) {
                q.add(node);
                result += node;
            }
        }

        while (!q.isEmpty()) {
            Character cur = q.remove();

            for (Character neighbor : outgoing.get(cur)) {
                HashSet<Character> pres = incoming.get(neighbor);
                pres.remove(cur);

                if (pres.isEmpty()) {
                    q.add(neighbor);
                    result += neighbor;
                }
            }
        }

        if (result.length() != outgoing.size()) {
            return "";
        } else {
            return result;
        }
    }
}
