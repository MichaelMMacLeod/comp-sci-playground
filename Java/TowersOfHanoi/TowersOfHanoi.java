import java.util.Arrays;    
import java.util.stream.Collectors;

// i would make this in awt/swing, but i kinda want to work on my game
// sry

public class TowersOfHanoi {
    public static void main(String... args) {
        boolean exit = false;

        if (args.length != 1) {
            exit = true;
            System.out.println("Please supply one argument.");
        } else if (!isInt(args[0])) {
            exit = true;
            System.out.println("Please supply as integer as the argument.");
        } else if (Integer.parseInt(args[0]) <= 0) {
            exit = true;
            System.out.println("Please supply an integer greater than or equal to one as the argument.");
        }

        if (exit)
            System.exit(0);

        System.out.println(solve(Integer.parseInt(args[0]), 1, 2, 3));
    }

    static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static String solve(int n, int start, int empty, int end) {
        if (n == 1)
            return "\nMove the piece on stack #" + start + " to stack #" + end;

        // i cant decide if this way of spacing arguments looks nice or yucky
        return
          solve(n - 1, start,   end, empty)
        + solve(    1, start, empty,   end)
        + solve(n - 1, empty, start,   end);
    }
}