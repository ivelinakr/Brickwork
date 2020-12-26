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
                                int[] firstC1 = {length-2,j};
                                int[] firstC2 = {length-2,j+1};
                                int[] secondC1 = {length-1,j};
                                int[] secondC2 = {length,j};
                                int[] thirdC1 = {length-1,j+1};
                                int[] thirdC2 = {length,j+1};

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
            bricks2[m].setNumber(m+1);
            System.out.println("BRICK NUMBER "+(m+1)+"="+Arrays.toString(bricks2[m].getC1())+" "+Arrays.toString(bricks2[m].getC2()));
        }

        return bricks2;
    }

    public static void getPrintLayout(Brick[] layer, int length, int width) {
        ArrayList<String> rows = new ArrayList<>();

        for (int m=0; m<layer.length; m++) {
            layer[m].setNumber(m+1);
        }

        for (int i=0;i<width;i++) {
            String row = "";
            ArrayList<Brick> tempArrList = new ArrayList<>();

            // check if its on the current row
            for (int k=0;k<layer.length;k++) {
                int[] C1YCoordinate = layer[k].getC1();
                int[] C2YCoordinate = layer[k].getC2();
                // if it is, add to list
                if (C1YCoordinate[1]==i || C2YCoordinate[1]==i) {
                    tempArrList.add(layer[k]);
                }
            }

            // for getting formatted row
            for (int l=0;l<tempArrList.size();l++) {
                int[] C1CurrentBlock = tempArrList.get(l).getC1();
                int[] C2CurrentBlock = tempArrList.get(l).getC2();
                int brickNumber = tempArrList.get(l).getNumber();

                if (C1CurrentBlock[1]==C2CurrentBlock[1]) {
                    if (brickNumber<10) {
                        row = row + "* " + tempArrList.get(l).getNumber() + "   " + tempArrList.get(l).getNumber() + "   ";
                    } else if (brickNumber>=10 && brickNumber<=99) {
                        row = row + "* " + tempArrList.get(l).getNumber() + "  " + tempArrList.get(l).getNumber() + "   ";
                    }
                } else {
                    if (brickNumber<10) {
                        row = row + "* " + tempArrList.get(l).getNumber() + "   ";
                    } else if (brickNumber>=10 && brickNumber<=99) {
                        row = row + "* " + tempArrList.get(l).getNumber() + "  ";
                    }
                }

                if (l==tempArrList.size()-1) {
                    row = row + "*";
                }
            }
            rows.add(row);
        }

        // for getting horizontal line
        String horizontalLine = "*";
        for (int i=0; i<layer.length; i++) {
            // skips last
            if (i == layer.length-1) {
                break;
            }
            // if brick is horizontal on first layer add 10 asterisks, if vertical 6
            if (layer[i].getC1()[0]==0 && layer[i].getC2()[0]==0) {
                horizontalLine = horizontalLine + "**********";
            } else {
                horizontalLine = horizontalLine + "******";
            }
        }
        System.out.println(horizontalLine);

        // for getting asterisks in between rows
        for (int i=0; i<rows.size(); i++) {
            System.out.println(rows.get(i));
            String middle = "";

            for (int j=0; j<rows.get(i).length(); j++) {
                if (i==rows.size()-1) {
                    break;
                } else if (rows.get(i).charAt(j)=='*') {
                    middle = middle + "*";
                } else if (rows.get(i).charAt(j)==' ') {
                    continue;
                } else if (Character.isDigit(rows.get(i).charAt(j)) && rows.get(i).charAt(j)==rows.get(i+1).charAt(j)) {
                    middle = middle + "     ";
                } else {
                    middle = middle + "*********";
                    j=j+7;
                }
            }
            if (middle!="") {
                System.out.println(middle);
            }
        }
        System.out.println(horizontalLine);
    }
}
