import java.util.Scanner;

class FlowCharts {
    static long startTime, endTime;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Select level (1/2/3): ");
        String level = scan.nextLine();

        System.out.print("What is your number? ");
        long n = scan.nextLong();

        switch (level) {
            case "1":
                level1(n);
            break;
            case "2":
                level2(n);
            break;
            case "3":
                level3(n);
            break;
            default:
                System.out.println("That's not a level.");
            break;
        }
    }

    static void timeStart() {
        startTime = System.currentTimeMillis();
    }

    static void timeEnd() {
        endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("\nIt took " + (elapsedTime / 1000.0) + " to run.");
    }

    static void level1(long n) {
        timeStart();

        long d = 1;
        
        do {
            if (n % d == 0) {
                System.out.print(d + " ");
            }

            d++;
        } while (d <= n / 2);

        System.out.print(n);

        timeEnd();
    }

    static void level2(long n) {
        timeStart();

        long half = n / 2;

        long d = 1;
        
        do {
            if (n % d == 0) {
                System.out.print(d + " ");
            }

            d++;
        } while (d <= half);

        System.out.print(n);

        timeEnd();
    }

    static void level3(long n) {
        timeStart();

        long sqrt = (long) Math.sqrt(n);

        long d = 1;
        
        do {
            if (n % d == 0) {
                System.out.print(d + " " + (n / d) + " ");
            }

            d++;
        } while (d <= sqrt);

        timeEnd();
    }
}