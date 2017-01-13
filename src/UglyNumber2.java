/**
 * Created by qzhou on 11/23/16.
 * http://www.lintcode.com/en/problem/ugly-number-ii/
 */
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Comparator;

public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        ArrayList<Integer> uglys = new ArrayList<Integer>();
        uglys.add(1);

        int p2 = 0, p3 = 0, p5 = 0;

        for (int i = 1; i < n; i++) {
            int last = uglys.get(i-1);

            while (uglys.get(p2) * 2 <= last) {
                p2++;
            }
            while (uglys.get(p3) * 3 <= last) {
                p3++;
            }
            while (uglys.get(p5) * 5 <= last) {
                p5++;
            }

            uglys.add(Math.min(Math.min(uglys.get(p2) * 2, uglys.get(p3) * 3),
                    uglys.get(p5) * 5));
        }

        return uglys.get(n-1);
    }

    public int nthUglyNumber2(int n) {
        Queue<Long> Q = new PriorityQueue<Long>();
        HashSet<Long> inQ = new HashSet<Long>();
        Long[] primes = new Long[3];
        primes[0] = Long.valueOf(2);
        primes[1] = Long.valueOf(3);
        primes[2] = Long.valueOf(5);
        for (int i = 0; i < 3; i++) {
            Q.add(primes[i]);
            inQ.add(primes[i]);
        }
        Long number = Long.valueOf(1);
        for (int i = 1; i < n; i++) {
            number = Q.poll();
            for (int j = 0; j < 3; j++) {
                if (!inQ.contains(primes[j] * number)) {
                    Q.add(number * primes[j]);
                    inQ.add(number * primes[j]);
                }
            }
        }
        return number.intValue();
    }

    // https://www.hrwhisper.me/leetcode-count-primes/
    // NloglogN
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] isPrimer = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrimer[i] = true;
        }

        // 3 places to optimize
        for (int i = 2; i * i < n; i++){
            if (isPrimer[i]) {
                for (int j = i; j * i < n; j++) {
                    isPrimer[j * i] = false;
                }
            }
        }

        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimer[i]) {
                cnt++;
            }
        }

        return cnt;
    }

    // https://leetcode.com/problems/add-digits/
    // num - 9 * ((num - 1) / 9);
    public int addDigits(int num) {
        //return num - 9 * ((num - 1) / 9);
//        if (num <= 0) {
//            return 0;
//        }
//
//        int cur = digits(num);
//        while (cur > 9) {
//            cur = digits(cur);
//        }
//        return cur;

        if (num == 0) {
            return 0;
        } else if (num % 9 == 0) {
            return 9;
        } else {
            return num % 9;
        }
    }
    private int digits(int n) {
        int ans = 0;
        while (n > 0) {
            ans += n % 10;
            n = n / 10;
        }
        return ans;
    }

    // https://leetcode.com/problems/super-ugly-number/
    public int nthSuperUglyNumber(int n, int[] primes) {
        ArrayList<Integer> uglys = new ArrayList<Integer>();
        int[] p = new int[primes.length];
        uglys.add(1);

        for (int i = 1; i < n; i++) {
            int last = uglys.get(i-1);

            long temp, minVal = Long.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                temp = primes[j] * uglys.get(p[j]);
                if (temp <= last) {
                    p[j] = p[j] + 1;
                    temp = primes[j] * uglys.get(p[j]);
                }
                minVal = Math.min(minVal, temp);
            }

            uglys.add((int)minVal);
        }

        return uglys.get(n-1);
    }
}
