/**
 There is a board without left and right boundaries. void drop(float x, float size) drops squares
 with size of (size*size) starting from x. It will finally locate on the top of existing squares.
 For example, drop(0, 2) will get:(a represent a square with size 1*1)
 *
 * Maintain the board after each call to drop(). We may run the function -
 * int getMaxHeight() at any time. We should get the maximum height of the board using O(1) time.
 */
import java.util.*;

public class DropSquare {
    class Rec {
        float width;
        float height;
        Rec(float width, float height) {
            this.width = width;
            this.height = height;
        }
    }

    public TreeMap<Float, Rec> map = new TreeMap<Float, Rec>();
    public float max = 0;

    public void drop(float x, float size) {
        Float l = map.lowerKey(x);
        Float u = map.higherKey(x);
        Rec rec = new Rec(size, size);
        float leftRaise = 0, rightRaise = 0;

//        // overlap at left side
//        if (l != null && this.map.get(l).width + l > x) {
//            Rec left = this.map.get(l);
//            leftRaise = left.height;
//
//            if (x + size < l + left.width) {
//                left.width = x - l;
//
//                rec.height = left.height + size;
//                this.map.put(x, rec);
//
//                Rec rec2 = new Rec(lower + left.)
//            }
//        }
//
//        // overlap at right side
//        if (higher != null && higher < x + size) {
//            Rec right = this.map.get(higher);
//            rightRaise = right.height;
//
//            // remove right, add a new one
//        }




    }

    public float getMaxHeight() {
        return max;
    }
}
