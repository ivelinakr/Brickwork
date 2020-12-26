import java.util.ArrayList;

public class Brick {
    public int[] coordinate1 = new int[2];
    public int[] coordinate2 = new int[2];
    public int number;

    public Brick(int[] coordinate1, int[] coordinate2) {
        this.coordinate1 = coordinate1;
        this.coordinate2 = coordinate2;
        this.number = number;
    }

    public int[] getC1() {
        return coordinate1;
    }

    public int[] getC2() {
        return coordinate2;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int newNumber) {
        this.number = newNumber;
    }

    public static Brick[] getBricks(int[][] layout, int length, int width) {
        int brickCount = (length*width)/2;
        Brick[] bricks = new Brick[brickCount];
        ArrayList<Brick> tempArrList = new ArrayList<>();
        for (int i=0; i<length; i++) {
            for (int j=0; j<width; j++) {
                int current = layout[i][j];
                if (j != width-1) {
                    int down = layout[i][j+1];
                    if (current == down) {
                        int[] c1 = {i,j};
                        int[] c2 = {i,j+1};
                        Brick downB = new Brick(c1,c2);
                        tempArrList.add(downB);
                    }
                }
                if (i != length-1) {
                    int right = layout[i+1][j];
                    if (current == right) {
                        int[] c1 = {i,j};
                        int[] c2 = {i+1,j};
                        Brick rightB = new Brick(c1,c2);
                        tempArrList.add(rightB);
                    }
                }
            }
        }
        bricks = tempArrList.toArray(bricks);

        return bricks;
    }
}
