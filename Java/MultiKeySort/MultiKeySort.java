import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Comparator;

import java.io.File;
import java.io.FileNotFoundException;

class MutliKeySort {
    public static void main(String[] args) {
        Scanner scan;

        try {
            scan = new Scanner(new File("Names_ages.txt"));
        } catch (FileNotFoundException e) {
            scan = new Scanner("thanks java");
            System.exit(0);
        }

        ArrayList<String> names = new ArrayList<>();
        while (scan.hasNext())
            names.add(scan.nextLine());

        Comparator<String> sorter = (a, b) -> {
            int i;
            for (i = 0; i < a.length(); i++) {
                if (Character.isDigit(a.charAt(i)))
                    break;
            }
            int ageA = Integer.parseInt(a.substring(i, a.length()));

            int j;
            for (j = 0; j < b.length(); j++) {
                if (Character.isDigit(b.charAt(j)))
                    break;
            }
            int ageB = Integer.parseInt(b.substring(j, b.length()));

            if (a.compareTo(b) < 0 || a.compareTo(b) == 0 && ageA < ageB)
                return -1;
            return 1;
        };

        names.sort(sorter);

        // i love java 8
        names
            .stream()
            .map(s -> s.substring(0, extractAgeLocation(s) - 1) + ", " + s.substring(extractAgeLocation(s), s.length()))
            .collect(Collectors.toList())
            .forEach(System.out::println);
    }

    // returns the index of the first number in the string s
    static int extractAgeLocation(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)))
                return i;
        }

        return -1; // no number in s
    }

    // applies a selection sort to a, sorting lexographically, and then by age if the names are the same.
    // each element of a is in the form "name age", ex: "Michael 16"
    static void sort(ArrayList<String> a) {
        String min;
        int minIndex;

        for (int i = 0; i < a.size(); i++) {
            min = a.get(i);
            minIndex = i;

            for (int j = i + 1; j < a.size(); j++) {
                String current = a.get(j);

                String name1 = current.substring(0, extractAgeLocation(current));
                String name2 = min.substring(0, extractAgeLocation(min));

                int age1 = Integer.parseInt(current.substring(extractAgeLocation(current), current.length()));
                int age2 = Integer.parseInt(min.substring(extractAgeLocation(min), min.length()));

                if (name1.compareTo(name2) < 0
                    || name1.compareTo(name2) == 0 && age1 < age2) {
                    min = a.get(j);
                    minIndex = j;
                }
            }

            a.set(minIndex, a.get(i));
            a.set(i, min);
        }
    }
}