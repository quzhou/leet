/**
 * Created by quzhou on 10/31/17.
 */
import java.util.*;
import java.io.*;

public class Netflix {
    void printFromFile(String fileName, int percentage) {
        final int SZ = 8;
        char[] buf = new char[SZ];

        try {
            File file = new File(fileName);
            Long len = file.length();
            len = Math.round(len * percentage / 100.0);

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            // now read
            int num = 0;
            while (num < len) {
                int cnt = reader.read(buf, 0, SZ);
                if (cnt == -1) {
                    break;
                }

                // print
                for (int i = 0; i < cnt; i++) {
                    System.out.print(buf[i]);
                }

                num += cnt;
            }

            // close file
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1 0 0 1 1 1 0 0 1 0 1
    int[] countOnes(int[] arr) {
        int[] res = new int[2];
        int cnt = 0, maxLen = 0;

        int i = 0, j = 0;
        while (i < arr.length) {
            j = i;

            while(j < arr.length && arr[j] == 1) {
                j++;
            }

            cnt += j - i;
            maxLen = Math.max(maxLen, j - i);

            i = Math.max(i+1, j);
        }

        res[0] = cnt;
        res[1] = maxLen;
        return res;
    }
}
