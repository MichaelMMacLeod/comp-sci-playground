import java.io.File;
import java.io.IOException;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class ConvertTraining {
    private static char[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static double[] characterNums = new double[characters.length];

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("letter-recognition.data.txt"));

        ArrayList<List<Double>> values = new ArrayList<>();

        ArrayList<List<Double>> targets = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            char letter = line.charAt(0);

            int pos;
            for (pos = 0; pos < characters.length; pos++)
                if (characters[pos] == letter)
                    break;

            double[] nums = new double[26];
            nums[pos] = 1;

            targets.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));

            values.add(
                Arrays.asList(
                    line
                    .split(","))
                .stream()
                .filter(a -> isInteger(a))
                .map(a -> Matrix.operate(Matrix.activation, Integer.parseInt(a)))
                .collect(Collectors.toList()));
        }

        double[][] inputArray = new double[values.size()][];
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = new double[values.get(i).size()];

            for (int j = 0; j < inputArray[i].length; j++) {
                inputArray[i][j] = values.get(i).get(j);
            }
        }

        double[][] targetArray = new double[targets.size()][];
        for (int i = 0; i < targetArray.length; i++) {
            targetArray[i] = new double[targets.get(i).size()];

            for (int j = 0; j < targetArray[i].length; j++) {
                targetArray[i][j] = targets.get(i).get(j);
            }
        }

        Serializer.serialize(Matrix.transpose(inputArray), NetMatrix.FILE_INPUT);

        Serializer.serialize(Matrix.transpose(targetArray), NetMatrix.FILE_TARGET);
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}