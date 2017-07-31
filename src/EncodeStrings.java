/**
 * Created by qzhou on 3/3/17.
 */
import java.util.List;
import java.util.ArrayList;

public class EncodeStrings {
    //3##$%0#2#12
    //0#

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        String result = "";
        int len = 0;
        for (String str : strs) {
            len = str.length();
            result += len; // NO += len + '#' + str
            result += '#' + str;
        }

        return result;
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<String>();
        int n = s.length();
        if (n == 0) {
            return result;
        }

        int i = 0;
        while (i < n) {
            int len = 0;
            while (i < n && s.charAt(i) != '#') {
                len = len * 10 + s.charAt(i) - '0';
                i++;
            }

            i++;
            String str = s.substring(i, i + len);
            result.add(str);
            i += len;
        }

        return result;
    }
}
