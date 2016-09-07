import java.util.Scanner;

public class Tester {

    /*/
     * Created by Michael MacLeod
     * 9/2/2016, 2:23 PM
     *
     * Last updated:
     * 9/6/2016, 10:21 PM
    /*/

    public static void main(String[] args) {

        // Since we can only submit one file, here's both of the practices
        // mashed together in one Frankenstein's monster of a program

        practice1();
        practice2();
    }

    private static void practice1() {
        
        Scanner scan = new Scanner(System.in);
        int answer; // I like declaring all variables at the top of the scope
        String practiceTwoText = "[Enter 0 to go to practice two] ";

        while (true) { // Doesn't have a specified end point, so...
            System.out.print(practiceTwoText + "Enter an Integer: ");
            answer = scan.nextInt();
            if (answer == 0) {
                return;
            }
            if (answer % 2 == 0) {
                System.out.println(practiceTwoText + "The integer " + answer + " is even.");
            } else {
                System.out.println(practiceTwoText + "The integer " + answer + " is odd.");
            }
        }
    }

    private static void practice2() {
        
        // Pulled these colors off of Stack Overflow
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        int i = 30000;
        int currentColor = 0;
        String whiteSpace = " ";
        String[] colors = {ANSI_BLACK, ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_WHITE};

        epilepsyWarning();
        // The elusive and also nonexistent "goes to" operator.
        // please no hate (rip for loop)
        while (i --> 0) {
            if (currentColor < colors.length - 1) {
                currentColor++;
            } else {
                currentColor = 0;
            }
            if (i % 500 == 0) {
                whiteSpace += " ";
            }
            try {
                System.out.print(colors[currentColor] + "your name is cool!" + whiteSpace);
            } catch (Exception e) { // I hope colors work on Linux
                System.out.print("your name is cool!" + whiteSpace);
            }
            try {
                Thread.sleep(1); // 1 ms because everything else took forever to run
            } catch (InterruptedException e) {
                System.out.println("You can't take the sky from me, " + e);
            }
        }
    }

    private static void epilepsyWarning() {
        System.out.println("Flashing text! Epilepsy warning...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("You can't take the sky from me, " + e);
        }
    }
}