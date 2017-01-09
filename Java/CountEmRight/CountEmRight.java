import java.util.Scanner;

public class CountEmRight {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = "";

        while (!input.equals("EXIT&")) {
            System.out.print("Type in a sentence and press ENTER. ");
            input = scan.nextLine().toUpperCase();

            input += "&";

            String[] sp = input.split("SA");

            System.out.println("There "
                + (sp.length == 2 ? "is " : "are ") 
                + (sp.length - 1)
                + (sp.length == 2 ? " occurence." : " occurences."));
        }
    }
}