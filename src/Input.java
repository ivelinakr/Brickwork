import java.util.Scanner;

public class Input {
    public static String getInput() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input;
    }

    public static Integer[] getSize() {
        String input = getInput();
        String[] size = input.split(" ");
        Integer[] nums = new Integer[2];
        // width
        nums[0]= Integer.valueOf(size[0]);
        // length
        nums[1]= Integer.valueOf(size[1]);
        System.out.println("Size is: "+nums[0]+" by "+nums[1]);
        return nums;
    }

    public static int[][] getLayout(int width, int length) {
        int[][] intLayout = new int[length][width];
        String[] layout = new String[width];
        for (int i=0; i<width; i++) {
            String layoutLine = getInput();
            layout[i] = layoutLine;
            String[] layoutNums = layout[i].split(" ");
            for (int j=0; j<length; j++) {
                intLayout[j][i] = Integer.parseInt(layoutNums[j]);
            }
        }

        //System.out.println("2D ARRAY "+ Arrays.deepToString(intLayout));

        System.out.println("Layout is:");
        for (int k=0; k<width; k++) {
            System.out.println(layout[k]);
        }

        return intLayout;
    }

    public static void main(String[] args) {
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
