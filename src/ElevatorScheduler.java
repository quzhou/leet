/**
 * Created by qzhou on 9/28/17.
 * TODO: 1. add elevator direction
 * 2. dispatch has direction too
 * 3. capacity limit
 * 4. after entering, select target level
 * 5. multiple requests (100 people wants to take elevators)
 */
public class ElevatorScheduler {
    private class Elevator {
        private int floor;

        public Elevator() {
            this.floor = 0;
        }
        public Elevator(int floor) {
            this.floor = floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public int getFloor() {
            return this.floor;
        }
    }

    private int numFloor;
    private int numElev;
    private Elevator[] elems;

    public ElevatorScheduler(int numF, int numE) {
        this.numFloor = numF;
        this.numElev = numE;
        this.elems = new Elevator[numE];
    }

    public int dispatch(int floor) {
        int theOne = -1, min = this.numFloor;

        for (int i = 0; i < this.numElev; i++) {
            if (Math.abs(elems[i].getFloor() - floor) < min) {
                theOne = i;
                min = Math.abs(elems[i].getFloor() - floor);
            }
        }

        elems[theOne].setFloor(floor);
        return theOne;
    }
}
