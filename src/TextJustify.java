/**
 * Created by qzhou on 10/3/17.
 * https://leetcode.com/problems/text-justification/description/
 */
import java.util.List;
import java.util.ArrayList;

public class TextJustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        int len = 0;
        boolean first = true;
        int startIdx = 0;

        int i = 0;
        while (i < words.length) {
            if (!first) {
                len++;
            } else {
                first = false;
            }
            len += words[i].length();

            if (len > maxWidth) { //this word should go to next line
                String line = buildLine(words, startIdx, i-1,
                        len - 1 - words[i].length(), maxWidth);
                res.add(line);

                len = 0;
                first = true;
                startIdx = i;
            } else {
                i++;
            }
        }

        // for last line
        StringBuilder sb = new StringBuilder(words[startIdx]);
        for (int j = startIdx + 1; j < words.length; j++) {
            sb.append(" ").append(words[j]);
        }
        for (int j = sb.length(); j < maxWidth; j++) {
            sb.append(" ");
        }
        res.add(sb.toString());

        return res;
    }

    private String buildLine(String[] words, int start, int end,
                             int len, int maxWidth) {
        int extra = maxWidth - len;
        int num = end - start; //number of space holders

        StringBuilder sb = new StringBuilder(words[start]);
        if (num == 0) {
            for (int i = words[start].length(); i < maxWidth; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }

        int numOfSpaces = 1 + extra/num;
        String spaceStr = "";
        for (int i = 0; i < numOfSpaces; i++) {
            spaceStr += " ";
        }
        int extraSpaceCnt = extra % num;

        for (int i = start + 1; i <= end; i++) {
            sb.append(spaceStr);
            if (i - start <= extraSpaceCnt) {
                sb.append(" ");
            }
            sb.append(words[i]);
        }

        return sb.toString();
    }
}
