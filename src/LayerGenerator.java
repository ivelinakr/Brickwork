import java.util.ArrayList;
import java.util.Arrays;

public class LayerGenerator {
    public static Brick[] generateLayer2 (Brick[] layer1, int length, int width) {
        int brickCount = layer1.length;
        Brick[] bricks2 = new Brick[brickCount];
        ArrayList<Brick> tempArrList = new ArrayList<>();
        int currentBrick = 0;
        int brL1Num = 0;
        int[] lastFirstRow = {length-1,0};
        int[] lastSecondRow = {length-1,1};

        for (int i=0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                boolean cTaken = false;
                int[] c1l2 = {i, j};
                int[] rightB = {i+1, j};
                int[] lowerB = {i, j+1};

                if (!(tempArrList.isEmpty())) {
                    for (int k=0; k<tempArrList.size(); k++) {
                        int[] brickC1 = tempArrList.get(k).getC1();
                        int[] brickC2 = tempArrList.get(k).getC2();

                        if ((brickC1[0] == c1l2[0] && brickC1[1] == c1l2[1]) || (brickC2[0] == c1l2[0] && brickC2[1] == c1l2[1])) {
                            cTaken = true;
                        }
                    }
                }

                if (!cTaken) {
                    if (i==length-3) {
                        for (Brick brick : layer1) {
                            if (Arrays.equals(brick.getC1(),lastFirstRow) && Arrays.equals(brick.getC2(),lastSecondRow)) {
                                // |=
                                int[] firstC1 = {5,0};
                                int[] firstC2 = {5,1};
                                int[] secondC1 = {6,0};
                                int[] secondC2 = {7,0};
                                int[] thirdC1 = {6,1};
                                int[] thirdC2 = {7,1};

                                Brick firstBrick = new Brick(firstC1, firstC2);
                                Brick secondBrick = new Brick(secondC1, secondC2);
                                Brick thirdBrick = new Brick(thirdC1, thirdC2);
                                tempArrList.add(firstBrick);
                                tempArrList.add(secondBrick);
                                tempArrList.add(thirdBrick);
                                currentBrick= currentBrick+3;
                                //brL1Num++;
                                System.out.println("CURRENT BRICK " + currentBrick);
                                break;
                            }
                        }
                    }
                    String C2 = Arrays.toString(layer1[brL1Num].getC2());
                    String right = Arrays.toString(rightB);
                    if ((Arrays.equals(layer1[brL1Num].getC1(), c1l2) && Arrays.equals(layer1[brL1Num].getC2(), rightB)) || i == length - 1) {
                        Brick newBrick = new Brick(c1l2, lowerB);
                        tempArrList.add(newBrick);
                        currentBrick++;
                        brL1Num++;
                        System.out.println("CURRENT BRICK " + currentBrick);
                        continue;
                    } else if (!(Arrays.equals(layer1[brL1Num].getC1(), c1l2) && Arrays.equals(layer1[brL1Num].getC2(), rightB))) {
                        Brick newBrick = new Brick(c1l2, rightB);
                        tempArrList.add(newBrick);
                        currentBrick++;
                        brL1Num++;
                        System.out.println("CURRENT BRICK " + currentBrick);
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        bricks2 = tempArrList.toArray(bricks2);

        System.out.println("LAYER 2:");
        for (int m=0; m<brickCount; m++) {
            System.out.println("BRICK NUMBER "+(m+1)+"="+Arrays.toString(bricks2[m].getC1())+" "+Arrays.toString(bricks2[m].getC2()));
        }

        return bricks2;
    }
}
