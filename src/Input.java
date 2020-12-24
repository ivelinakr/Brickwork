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

    public static String[] getLayout(int width) {
        String[] layout = new String[width];
        for (int i=0; i<width; i++) {
            String layoutLine = getInput();
            layout[i] = layoutLine;
        }

        System.out.println("Layout is:");
        for (int k=0; k<width; k++) {
            System.out.println(layout[k]);
        }

        return layout;
    }

    public static void main(String[] args) {
        System.out.println("Enter size (ex. '2 4')");
        Integer[] size = getSize();
        System.out.println("Enter layout lines one by one (ex. '1 1 2 2' then '3 3 4 4')");
        String[] layout = getLayout(size[0]);

    }
}
