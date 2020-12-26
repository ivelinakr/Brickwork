import java.util.Scanner;

public class Input {
    public static String getInput() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }

    public static Integer[] getSize() throws Exception {
        String input = getInput();
        String[] size = input.split(" ");
        Integer[] nums = new Integer[2];
        // width
        nums[0]= Integer.valueOf(size[0]);
        // length
        nums[1]= Integer.valueOf(size[1]);

        if (nums[0]>99 || nums[1]>99) {
            throw new Exception("Area has to be less than 100 lines and less than 100 columns");
        } else if (nums[0]%2!=0 || nums[1]%2!=0) {
            throw new Exception("Numbers must be even");
        }

        System.out.println("Size is: "+nums[0]+" by "+nums[1]);
        return nums;
    }

    public static void validateNumbersCount(String str) throws Exception {
        int count[] = new int[110];
        int len = str.length();

        for (int i=0; i<len; i++) {
            count[str.charAt(i)]++;
        }

        char number[] = new char[str.length()];
        for (int i=0; i<len; i++) {
            number[i] = str.charAt(i);
            int found = 0;
            for (int j=0; j<=i; j++) {
                if (str.charAt(i) == number[j]) {
                    found++;
                }
            }

            if (found==1 && str.charAt(i)!=' ' && count[str.charAt(i)]>2) {
                throw new Exception("Bricks cannot be spanning 3 rows/columns");
            }
        }
    }

    public static int[][] getLayout(int width, int length) throws Exception {
        int[][] intLayout = new int[length][width];
        String[] layout = new String[width];
        String layoutInOneLine = "";

        for (int i=0; i<width; i++) {
            String layoutLine = getInput();
            layout[i] = layoutLine;
            String[] layoutNums = layout[i].split(" ");
            for (int j=0; j<length; j++) {
                intLayout[j][i] = Integer.parseInt(layoutNums[j]);
            }
        }

        for (int i=0; i<layout.length; i++) {
            if (i==0) {
                layoutInOneLine = layoutInOneLine + layout[i];
            } else {
                layoutInOneLine = layoutInOneLine + " " + layout[i];
            }
        }

        validateNumbersCount(layoutInOneLine);

        System.out.println("Layout is:");
        for (int k=0; k<width; k++) {
            System.out.println(layout[k]);
        }
        return intLayout;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Enter size (ex. '2 4')");
        Integer[] size = getSize();
        System.out.println("Enter layout lines one by one (ex. '1 1 2 2' then '3 3 4 4')");
        int[][] layout = getLayout(size[0],size[1]);
        Brick[] layer1 = Brick.getBricks(layout, size[1], size[0]);
        LayerGenerator.getPrintLayout(layer1, size[1], size[0]);
        Brick[] layer2 = LayerGenerator.generateLayer2(layer1, size[1], size[0]);
        LayerGenerator.getPrintLayout(layer2, size[1], size[0]);

    }
}
