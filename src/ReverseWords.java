/**
 * Created by qzhou on 5/30/17.
 * http://www.lintcode.com/en/problem/reverse-words-in-a-string/
 */
public class ReverseWords {
    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        String s2 = s.trim();
        int len = s2.length();
        if (len == 0) {
            return s2;
        }

        char[] str = s2.toCharArray();
        reverse(str, 0, len - 1);

        String result = "";
        int start = -1, count = 0;

        for (int i = 0; i < len; i++) {
            if (Character.isWhitespace(str[i])) {
                if (start != -1) {
                    reverse(str, start, start + count - 1);
                    result += new String(str, start, count);
                    result += " ";
                    start = -1;
                    count = 0;
                }
            } else {
                if (start == -1) {
                    start = i;
                }
                count++;
            }
        }
        if (start != -1 && start < len) {
            reverse(str, start, start + count - 1);
            result += new String(str, start, count);
        }

        return result;
    }

    private void reverse(char[] str, int start, int end) {
        int i = start, j = end;
        char temp;
        while (i < j) {
            temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
    }

    //Method 2: use split and StringBuilder
    public String reverseWords2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String[] arr = s.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = arr.length - 1; i >= 0; i--) {
            if (!arr[i].equals("")) {
                sb.append(arr[i]).append(" ");
            }
        }

        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}
