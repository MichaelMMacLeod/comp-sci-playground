import java.util.Scanner;
import java.util.ArrayList;

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
        
        scan.useDelimiter("\n");

        ArrayList<String> full = new ArrayList<>();
        while (scan.hasNext())
            full.add(scan.next());

        ArrayList<Integer> lengths = new ArrayList<>();
        for (int i = 0; i < full.size(); i++) {
            for (int j = 0; j < full.get(i).length(); j++) {
                if (isInt(full.get(i).substring(j, j + 1))) {
                    lengths.add(j);
                    break;
                }
            }
        }

        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < lengths.size(); i++) {
            names.add(full.get(i).substring(0, lengths.get(i)).trim());
        }

        sort(names, lengths);
    }

    static boolean isInt(String test) {
        try {
            Integer.parseInt(test);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static void sort(ArrayList<String> a, ArrayList<Integer> b) {
        String min;
        int minIndex;

        for (int i = 0; i < a.size(); i++) {
            min = a.get(i);
            minIndex = i;

            for (int j = i + 1; j < a.size(); j++) {
                if (a.get(j).compareTo(min) < 0
                    || a.get(j).compareTo(min) == 0
                    && b.get(j) < b.get(minIndex)) {
                    min = a.get(j);
                    minIndex = j;
                }
            }

            a.set(minIndex, a.get(i));
            a.set(i, min);
        }
    }
}