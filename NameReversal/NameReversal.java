import java.util.Scanner;

public class NameReversal {

    /*/
     * Michael MacLeod
     * Created 9/13/2016
    /*/

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String response;
        String backwards = "";

        System.out.print("Please enter your name. ");
        response = scan.nextLine().toLowerCase();
        for (int i = 0; i < response.length(); i++) {
            backwards += response.substring(response.length() - i - 1, response.length() - i);
        }
        System.out.println(backwards);
    }
}