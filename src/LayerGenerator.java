import java.util.ArrayList;

public class LayerGenerator {
    public static Brick[] generateLayer (Brick[] layer1, int length, int width) {
        // generates layer 2
        // initialize arraylist to store layer 2 bricks
        ArrayList<Brick> tempArrList = new ArrayList<Brick>();

        for (int i=0; i<length; i++) {
            for (int j=0; j<width; j++) {
                int[] currentBrick = {i,j};
                int[] rightBrick = {i+1,j};
                int[] lowerBrick = {i,j+1};
                boolean cTaken = false;
                int brickCount = 0;

                // at last position breaks the loop
                if (currentBrick[0]==length-1 && currentBrick[1]==width-1) {
                    break;
                }

                // checks if current coordinate is taken by a generated brick
                if (tempArrList.size()!=0) {
                    for (int k=0; k<tempArrList.size(); k++) {
                        int[] brickC1 = tempArrList.get(k).getC1();
                        int[] brickC2 = tempArrList.get(k).getC2();

                        if ((brickC1[0] == currentBrick[0] && brickC1[1] == currentBrick[1]) || (brickC2[0] == currentBrick[0] && brickC2[1] == currentBrick[1])) {
                            cTaken = true;
                        }
                    }
                }

                if (!cTaken) {
                    Brick currentBrickLayer1 = null;
                    for (int k=0; k<layer1.length; k++) {
                        // find brick from layer1 with current coordinates
                        if (layer1[k].getC1()[0]==currentBrick[0] && layer1[k].getC1()[1]==currentBrick[1] || layer1[k].getC2()[0]==currentBrick[0] && layer1[k].getC2()[1]==currentBrick[1]) {
                            currentBrickLayer1 = layer1[k];
                        }
                    }

                    if (i<length-1 && j<width-1 && currentBrickLayer1.getC1()[0]==currentBrick[0] && currentBrickLayer1.getC1()[1]==currentBrick[1] && currentBrickLayer1.getC2()[0]==rightBrick[0] && currentBrickLayer1.getC2()[1]==rightBrick[1]) {
                        // if brick is horizontal, generate vertical brick
                        Brick newBrick = new Brick(currentBrick, lowerBrick);
                        tempArrList.add(newBrick);
                        brickCount++;
                        continue;
                    } else if (j<width-1 && currentBrickLayer1.getC1()[0]==currentBrick[0] && currentBrickLayer1.getC1()[1]==currentBrick[1] && currentBrickLayer1.getC2()[0]==lowerBrick[0] && currentBrickLayer1.getC2()[1]==lowerBrick[1]) {
                        // if brick is vertical, generate horizontal brick
                        Brick newBrick = new Brick(currentBrick, rightBrick);
                        tempArrList.add(newBrick);
                        brickCount++;
                        continue;
                    } else if (i<length-1 && currentBrickLayer1.getC2()[0]==currentBrick[0] && currentBrickLayer1.getC2()[1]==currentBrick[1]){
                        // when there is space on the right, generates a horizontal brick
                        Brick newBrick = new Brick(currentBrick, rightBrick);
                        tempArrList.add(newBrick);
                        brickCount++;
                        continue;
                    } else if(i==length-1 && currentBrickLayer1.getC2()[0]==currentBrick[0] && currentBrickLayer1.getC2()[1]==currentBrick[1]) {
                        // when there is no more space on the right, generates a vertical brick
                        Brick newBrick = new Brick(currentBrick, lowerBrick);
                        tempArrList.add(newBrick);
                        brickCount++;
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }

        // converts tempArrList to Brick array from arraylist
        Object[] array = tempArrList.toArray();
        Brick[] layer2 = new Brick[tempArrList.size()];

        for (int i=0; i<tempArrList.size();i++) {
            Brick brick = (Brick) array[i];
            layer2[i] = brick;
        }

        System.out.println("\nLayer 2:");

        // checks for non-occupied coordinates
        for (int i=0; i<length; i++) {
            for (int j=0; j<width; j++) {
                boolean found = false;
                for (int k = 0; k < layer2.length; k++) {
                    if (layer2[k].getC1()[0] == i && layer2[k].getC1()[1] == j) {
                        found = true;
                    } else if (layer2[k].getC2()[0] == i && layer2[k].getC2()[1] == j) {
                        found = true;
                    }
                }
                if (!found) {
                    throw new Error("-1");
                }
            }
        }
        return layer2;
    }

    public static void getPrintLayout(Brick[] layer, int length, int width) {
        // prints layout in asterisks table
        ArrayList<String> rows = new ArrayList<>();

        for (int m=0; m<layer.length; m++) {
            layer[m].setNumber(m+1);
        }

        for (int i=0;i<width;i++) {
            String row = "";
            ArrayList<Brick> tempArrList = new ArrayList<>();

            // checks if it is on the current row
            for (int k=0;k<layer.length;k++) {
                int[] C1YCoordinate = layer[k].getC1();
                int[] C2YCoordinate = layer[k].getC2();
                // if it is, add to list
                if (C1YCoordinate[1]==i || C2YCoordinate[1]==i) {
                    tempArrList.add(layer[k]);
                }
            }

            // gets formatted row
            for (int l=0;l<tempArrList.size();l++) {
                int[] C1CurrentBlock = tempArrList.get(l).getC1();
                int[] C2CurrentBlock = tempArrList.get(l).getC2();
                int brickNumber = tempArrList.get(l).getNumber();

                if (C1CurrentBlock[1]==C2CurrentBlock[1]) {
                    if (brickNumber<10) {
                        row = row + "* " + tempArrList.get(l).getNumber() + "   " + tempArrList.get(l).getNumber() + "   ";
                    } else if (brickNumber>=10 && brickNumber<=99) {
                        row = row + "* " + tempArrList.get(l).getNumber() + "  " + tempArrList.get(l).getNumber() + "  ";
                    }
                } else {
                    if (brickNumber<10) {
                        row = row + "* " + tempArrList.get(l).getNumber() + "   ";
                    } else if (brickNumber>=10 && brickNumber<=99) {
                        row = row + "* " + tempArrList.get(l).getNumber() + " ";
                    }
                }

                if (l==tempArrList.size()-1) {
                    row = row + "*";
                }
            }
            rows.add(row);
        }

        // gets horizontal line
        String horizontalLine = "*";

        // gets bricks connected to layer 1
        ArrayList<Brick> layer1bricks = new ArrayList<>();
        for (int i=0; i<layer.length; i++) {
            if (layer[i].getC1()[1]==0) {
                layer1bricks.add(layer[i]);
            }
        }

        for (int i=0; i<layer1bricks.size(); i++) {
            // if brick is horizontal on first layer add 10 asterisks, if vertical then add 6 asterisks
            if (layer1bricks.get(i).getC1()[1]==0 && layer1bricks.get(i).getC2()[1]==0) {
                horizontalLine = horizontalLine + "**********";
                continue;
            } else {
                horizontalLine = horizontalLine + "******";
                continue;
            }
        }
        System.out.println(horizontalLine);

        // gets asterisks in between rows
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
                } else if (Character.isDigit(rows.get(i).charAt(j)) && !Character.isDigit(rows.get(i).charAt(j+1)) && rows.get(i).charAt(j)==rows.get(i+1).charAt(j)) {
                    middle = middle + "     ";
                } else if (Character.isDigit(rows.get(i).charAt(j)) && Character.isDigit(rows.get(i).charAt(j+1)) && rows.get(i).charAt(j)==rows.get(i+1).charAt(j) && rows.get(i).charAt(j+1)==rows.get(i+1).charAt(j+1)) {
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
