import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
		List<String> input = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String strTrial = new String();
        Random rand = new Random();
        
        for (int i = 0; i < 1; i++) {
        	input.add(scanner.next());
        }

        scanner.close();
        
        for (int i = 0; i < Integer.parseInt(input.get(0)); i++) {
        	strTrial += rand.nextInt(6) + 1;
        }

        System.out.println(strTrial); 
        Dice dice = new Dice(input.get(0), strTrial);
        dice.result();
    }
}