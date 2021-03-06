/**
 * Training set is from http://archive.ics.uci.edu/ml/machine-learning-databases/letter-recognition/
 */

class NetMatrix {
    public static final String FILE_INPUT = "input.serialized";
    public static final String FILE_TARGET = "target.serialized";
    public static final String FILE_WEIGHTS = "weights.serialized";

    public static void main(String[] args) {
        double[][][] ws = new double[][][] {
            Matrix.random(20, 16, -1, 1),
            Matrix.random(26, 21, -1, 1)};

        Serializer.serialize(ws, FILE_WEIGHTS);
        
        double[][] input = (double[][]) Serializer.deSerialize(FILE_INPUT);
        
        double[][] target = (double[][]) Serializer.deSerialize(FILE_TARGET);

        double[][][] weights = (double[][][]) Serializer.deSerialize(FILE_WEIGHTS);

        double learningRate = 0.25;

        for (int epoch = 0; epoch < 500000; epoch++) {
            double[][][] output = propagate(input, weights);

            double[][][] partials = partials(output, weights, target);

            weights = updateWeights(learningRate, weights, partials);

            if (epoch % 100 == 0) {
                System.out.println("epoch: " + epoch);
                printOut(output[output.length - 1]);
            }
        }

        // for (int i = 0; i < weights.length; i++) {
        //     System.out.println("Weights[" + i + "]:");
        //     print(weights[i]);
        // }

        Serializer.serialize(weights, FILE_WEIGHTS);
    }

    static double[][][] partials(double[][][] output, double[][][] weights, double[][] target) {
        double[][][] partials = new double[weights.length][][];

        for (int p = 0; p < partials.length; p++) {
            partials[p] = new double[weights[p].length][];
        }

        double[][] s_k = Matrix.operate(
            Matrix.multiplication,
            Matrix.operate(
                Matrix.dActivation, 
                output[output.length - 1]),
            Matrix.operate(
                Matrix.subtraction,
                output[output.length - 1],
                target));

        partials[partials.length - 1] = Matrix.multiply(
            s_k,
            Matrix.transpose(output[output.length - 2]));

        for (int p = partials.length - 2; p >= 0; p--) {
            double[][] s_p = Matrix.operate(
                Matrix.multiplication,
                Matrix.operate(
                    Matrix.dActivation,
                    output[p + 1]),
                Matrix.multiply(
                    Matrix.transpose(weights[p + 1]),
                    partials[p + 1]));

            partials[p] = Matrix.multiply(
                s_p,
                Matrix.transpose(output[p]));

            partials[p] = Matrix.removeLastRow(partials[p]);
        }

        return partials;
    }

    static double[][][] propagate(double[][] input, double[][][] weights) {
        double[][][] output = new double[weights.length + 1][][];

        output[0] = Matrix.appendToEnd(input, 1.0);

        for (int i = 1; i < output.length; i++) {
            output[i] = Matrix.operate(
                Matrix.activation,
                Matrix.multiply(
                    weights[i - 1], 
                    output[i - 1]));

            if (i != output.length - 1) {
                output[i] = Matrix.appendToEnd(output[i], 1.0);
            }
        }

        return output;
    }

    static double[][][] updateWeights(double learningRate, double[][][] weights, double[][][] partials) {
        double[][][] updated = new double[weights.length][][];

        for (int i = 0; i < updated.length; i++) {
            updated[i] = Matrix.operate(
                Matrix.addition,
                weights[i],
                Matrix.scale(
                    -learningRate,
                    partials[i]));
        }

        return updated;
    }

    static String getColor(double x) {
        if (x < 0.1)
            return "\u001B[0m";
        if (x < 0.3)
            return "\u001B[35m";
        if (x < 0.5)
            return "\u001B[34m";
        if (x < 0.7)
            return "\u001B[36m";
        return "\u001B[32m";
    }

    static void printOut(double[][] xs) {
        for (int row = 0; row < xs.length; row++) {
            System.out.print("\u001B[0m" + (char) (row + 65) + " ");
            for (int col = 0; col < xs[row].length; col++) {
                System.out.print(getColor(xs[row][col]) + /*xs[row][col]*/"[]" + "\t" + "\u001B[0m");
            }
            System.out.println();
        }
    }

    static void print(double[][] xs) {
        for (int row = 0; row < xs.length; row++) {
            for (int col = 0; col < xs[row].length; col++) {
                System.out.print(xs[row][col] + "\t");
            }
            System.out.println();
        }
    }
}