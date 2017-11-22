/**
 * Created by qzhou on 9/20/16.
 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Random rand = new Random();
        //int randNum = rand.nextInt(100); //0-99

//        int val = (2 << 30) - 1;
//        System.out.println(val);
//        System.out.println(Integer.MAX_VALUE);

        // read file
//        List<String> cmds = new ArrayList<String>();
//        try {
//            //File file = new File("input.txt");
//            //FileReader fileReader = new FileReader(file);
//            FileReader fileReader = new FileReader("input.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                cmds.add(line);
//            }
//            fileReader.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(cmds);

        // write to file
//        try {
//            FileWriter fw = new FileWriter("output.txt");
//
//            for (String line : res) {
//                fw.write(line);
//                fw.write("\n");
//            }
//
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /*
        ["RangeModule","addRange","removeRange","queryRange","queryRange","queryRange"]
        [[],[10,20],[14,16],[10,14],[13,15],[16,17]]
         */
        LevelTraversal ins = new LevelTraversal();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        List<Integer> res = ins.levelSum(root);
        System.out.println(res);
    }
}
