/**
 * Created by quzhou on 10/16/17.
 */
import java.util.*;

/*
先将所有floor(x)加起来统计出如果所有都floor的话还差多少，按照ceil以后需要加的价格排序，贪心取最小的补齐即可。代码如下：
 */
public class RoundNumber {
    private class RoundedNumber {
        public double input;
        public double res;
        public int index;
        public int output;

        public RoundedNumber(int index, double input, int output, double res) {
            this.index = index;
            this.input = input;
            this.output = output;
            this.res = res;
        }
    }

    private int[] minimize(double[] input) {
        if (null == input) {
            return new int[0];
        }

        int len = input.length;
        int output[] = new int[len];

        if (len == 1) {
            output[0] = (int)Math.round(input[0]);
            return output;
        }

        List<RoundedNumber> list = new ArrayList<RoundedNumber>();

        double sumInputDouble = 0.0;
        for (double val : input) {
            sumInputDouble += val;
        }
        int sumInput = (int)Math.round(sumInputDouble); //this should be the target

        int sumOutput = 0; //this should eventually == sumInput
        for (int index = 0; index < len; index++) {
            // first: round to get integer
            output[index] = (int)Math.round(input[index]);
            sumOutput += output[index];

            list.add(new RoundedNumber(index, input[index], output[index], Math.abs(input[index] - output[index])));
        }

        int res = sumInput - sumOutput;

        Collections.sort(list, new Comparator<RoundedNumber>() {
            public int compare(RoundedNumber r1, RoundedNumber r2) {
                if (r1.res > r2.res) {
                    return 1;
                } else if (r1.res < r2.res) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        int start = 0, end = len - 1;
        while (res > 0 && end >= 0) {
            int val = list.get(end).output + 1;
            double valRes = Math.abs(val - list.get(end).input);

            if (valRes >= 0 && valRes <= 1) {
                list.set(end, new RoundedNumber(list.get(end).index, list.get(end).input, val, list.get(end).res));
                --res;
            }

            --end;
        }

        while (res < 0 && start < len) {
            int val = list.get(start).output - 1;
            double valRes = Math.abs(val - list.get(start).input);

            if (valRes >= 0 && valRes <= 1) {
                list.set(start,
                        new RoundedNumber(list.get(start).index, list.get(start).input, val, list.get(start).res));
                ++res;
            }

            ++start;
        }

        for (RoundedNumber rNum : list) {
            output[rNum.index] = rNum.output;
        }

        return output;
    }

    public void test() {
        double input1[] = { 2.4, 3.3, 3.9, 20.1, 30.3 };
        double input2[] = { 2.4, 3.3, 5.4, 20.1, 30.4 };
        double input3[] = { 2.5, 3.5, 3.5, 20.5, 30.5 };

        int output[] = minimize(input2);

        for (int val : output) {
            System.out.println(val);
        }
    }
}
