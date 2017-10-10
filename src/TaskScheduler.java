/**
 * Created by qzhou on 10/8/17.
 * https://leetcode.com/problems/task-scheduler/description/
 */
/*
我们首先应该处理的出现次数最多的那个任务，先确定好这些高频任务，然后再来安排那些低频任务。如果任务F的出现频率最高，
为k次，那么我们用n个空位将每两个F分隔开，然后我们按顺序加入其他低频的任务，来看一个例子：

AAAABBBEEFFGG 3

我们发现任务A出现了4次，频率最高，于是我们在每个A中间加入三个空位，如下：

A---A---A---A

AB--AB--AB--A   (加入B)

ABE-ABE-AB--A   (加入E)

ABEFABE-ABF-A   (加入F，每次尽可能填满或者是均匀填充)

ABEFABEGABFGA   (加入G)
 */

import java.util.Arrays;

public class TaskScheduler {
    // high freqency type at both ends
    // (hNum - 1) * (n+1) + numOfHFchars
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26]; //number of tasks for each type
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        Arrays.sort(map);

        int hNum = map[25];
        int hf = 1;
        for (int i = 24; i >= 0; i--) {
            if (map[i] == hNum) {
                hf++;
            } else {
                break;
            }
        }

        return Math.max((hNum - 1) * (n+1) + hf, tasks.length);
    }
}
