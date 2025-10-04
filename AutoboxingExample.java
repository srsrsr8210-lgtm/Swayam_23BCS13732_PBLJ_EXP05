import java.util.ArrayList;
import java.util.Scanner;

public class AutoboxingExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers (type 'done' to finish):");
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            int num = Integer.parseInt(input);
            numbers.add(num);
        }

        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        System.out.println("Sum of integers: " + sum);
        sc.close();
    }
}
