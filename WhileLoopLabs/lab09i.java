import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Math.pow;

public class lab09i {

    /*/
     * Michael MacLeod
     * Created 9/16/2016
    /*/

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int num = 0;
        int numRemainder;
        int reqBase = 0;
        int currentPow = 0;
        int powCount = 0;
        int currentDigit = 0;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        String[] extraNums = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        // Get input
        while (num <= 0) {
            System.out.print("Enter a base 10 number: ");
            num = scan.nextInt();
            if (num <= 0) System.out.println("Use a number larger than zero.");
        }
        while (reqBase < 2) {
            System.out.print("Enter a new base: ");
            reqBase = scan.nextInt();
            if (reqBase < 2) System.out.println("Use base two or higher.");
        }
        // Find the maximum power of the base which is less than or equal to 
        // the given number
        while (reqBase * currentPow <= num) {
            currentPow = (int) Math.pow(reqBase, powCount);
            powCount++;
        }
        // Calculate each digit
        numRemainder = num;
        while (currentPow >= 1) {
            currentDigit = numRemainder / currentPow;
            numRemainder %= currentPow;
            ans.add((int) currentDigit); // Ignore the decimals
            currentPow /= reqBase;
        }
        // Print out answer
        if (reqBase > extraNums.length) {
            System.out.println("Since base " + reqBase + " requires more symbols than the alphabet can provide, some digits will be represented in base 10 and separated by spaces.");
        }
        System.out.print(num + " in base 10 is ");
        for (int i = 0; i < ans.size(); i++) {
            if (ans.get(i) > 9 && ans.get(i) - 10 < extraNums.length) { 
                // Use special characters for bases larger than 10
                System.out.print(extraNums[ans.get(i) - 10]);
            } else if (ans.get(i) < 10) { 
                // Use normal characters for digits 1 through 9
                System.out.print(ans.get(i));
            } else { 
                // Since our array only lists A through Z, it's possible that 
                // we encounter digits which we can't represent. To fix this, 
                // we represent them as base 10 numbers separated by braces.
                System.out.print("{" + ans.get(i) + "}");
            }
        }
        System.out.println(" in base " + reqBase + ".");
        System.out.println("Used " + ans.size() + " digits.");
    }
}