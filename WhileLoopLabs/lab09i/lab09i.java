import java.util.Scanner;
import java.util.ArrayList;

public class lab09i {

    /*/
     * Michael MacLeod
     * Created 9/16/2016
    /*/

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int num = 0, base = 0, power = 1;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        String[] extraNums = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        // Get input
        while (num <= 0) {
            System.out.print("Enter a base 10 number: ");
            num = scan.nextInt();
            if (num <= 0) System.out.println("Use a number larger than zero.");
        }
        while (base < 2) {
            System.out.print("Enter a new base: ");
            base = scan.nextInt();
            if (base < 2) System.out.println("Use base two or higher.");
        }
        // Find max power of base less than or equal to the number
        while (power * base <= num) {
            power *= base;
        }
        // Calculate the digits of the answer
        int remainder = num;
        while (power >= 1) {
            ans.add(remainder / power);
            remainder %= power;
            power /= base;
        }
        // Print out answer
        if (power >= 0) { // Check for overflow
            if (base > extraNums.length) {
                System.out.println("Since base " + base + " requires more symbols than the alphabet can provide, some digits will be represented in base 10 and separated by braces.");
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
            System.out.println(" in base " + base + ".");
            System.out.println("Used " + ans.size() + " digits.");
        } else {
            System.out.println("Overflow error");
        }
    }
}