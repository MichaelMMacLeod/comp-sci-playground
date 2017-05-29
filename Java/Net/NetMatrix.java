class NetMatrix {
    static final String FILE_INPUT = "input.serialized";
    static final String FILE_TARGET = "target.serialized";
    static final String FILE_WEIGHTS = "weights.serialized";

    public static void main(String[] args) {
        double[][] input = new double[][] {
            new double[] {1, 1, 0, 0},
            new double[] {1, 0, 1, 0}
        };

        double[][] target = new double[][] {
            new double[] {1, 0, 0, 0}
        };
        double[][][] weights = getRandomWeights(2, 4, 1);

        double learningRate = 0.25;

        for (int epoch = 0; epoch < 100000; epoch++) {
            double[][][] output = calculateOutput(weights, input);

            double[][][] partials = calculatePartials(weights, output, target);

            weights = updateWeights(learningRate, weights, partials);

            print(output[output.length - 1]);
        }

        for (int i = 0; i < weights.length; i++) {
            System.out.println("Weights[" + i + "]:");
            print(weights[i]);
        }
    }

    static double[][][] updateWeights(double learningRate, double[][][] weights, double[][][] partials) {
        double[][][] updated = new double[weights.length][][];

        for (int i = 0; i < updated.length; i++) {
            updated[i] = operate(
                addition,
                weights[i],
                scale(
                    -learningRate,
                    partials[i]));
        }

        return updated;
    }

    static double[][] multiplyExceptLastRow(double[][] xs, double[][] ys) {
        if (!isRectangular(xs) || !isRectangular(ys))
            System.out.println("dot: Can't multiply matricies; they aren't rectangular.");

        if (xs[0].length != ys.length)
            System.out.println("dot: Can't multiply matricies; illegal dimensions.");

        double[][] multiplied = new double[xs.length - 1][ys[0].length];

        for (int row = 0; row < multiplied.length - 1; row++) {
            for (int col = 0; col < multiplied[row].length; col++) {
                for (int i = 0; i < xs[row].length; i++) {
                    multiplied[row][col] += xs[row][i] * ys[i][col];
                }
            }
        }

        for (int i = 0; i < multiplied[multiplied.length - 1].length; i++) {
            multiplied[multiplied.length - 1][i] = xs[xs.length - 1][i];
        }

        return multiplied;
    }

    static double[][][] calculatePartials(double[][][] weights, double[][][] outputs, double[][] targets) {
        double[][][] s = new double[weights.length][][];

        s[s.length - 1] = operate(
            multiplication,
            dActivate(outputs[outputs.length - 1]),
            operate(
                subtraction,
                outputs[outputs.length - 1],
                targets));

        for (int i = s.length - 2; i >= 0; i--) {
            s[i] = operate(
                multiplication,
                dActivate(outputs[i + 1]),
                multiply(
                    transpose(weights[i + 1]),
                    s[i + 1]));
        }

        double[][][] partials = new double[weights.length][][];

        for (int i = 0; i < partials.length; i++) {
            if (i != partials.length - 1)
                partials[i] = multiplyExceptLastRow(s[i], transpose(outputs[i]));
            else
                partials[i] = multiply(s[i], transpose(outputs[i]));
        }

        return partials;
    }

    static boolean isRectangular(double[][] xs) {
        boolean rectangular = true;

        for (int i = 1; i < xs.length; i++) 
            rectangular = rectangular && xs[i].length == xs[i - 1].length;

        return rectangular;
    }

    static double[][] multiply(double[][] xs, double[][] ys) {
        if (!isRectangular(xs) || !isRectangular(ys))
            System.out.println("dot: Can't multiply matricies; they aren't rectangular.");

        if (xs[0].length != ys.length)
            System.out.println("dot: Can't multiply matricies; illegal dimensions.");

        double[][] multiplied = new double[xs.length][ys[0].length];

        for (int row = 0; row < multiplied.length; row++) {
            for (int col = 0; col < multiplied[row].length; col++) {
                for (int i = 0; i < xs[row].length; i++) {
                    multiplied[row][col] += xs[row][i] * ys[i][col];
                }
            }
        }

        return multiplied;
    }

    static double[][][] calculateOutput(double[][][] weights, double[][] inputs) {
        double[][][] output = new double[weights.length + 1][][];

        output[0] = appendOnes(inputs);

        for (int i = 1; i < output.length; i++) {
            output[i] = activate(
                multiply(
                    weights[i - 1], 
                    output[i - 1]));

            if (i != output.length - 1) {
                output[i] = appendOnes(output[i]);
            }
        }

        return output;
    }

    static double[][] appendOnes(double[][] xs) {
        double[][] appended = new double[xs.length + 1][xs[0].length];

        for (int row = 0; row < xs.length; row++) {
            for (int col = 0; col < xs[row].length; col++) {
                appended[row][col] = xs[row][col];
            }
        }

        for (int col = 0; col < appended[appended.length - 1].length; col++) {
            appended[appended.length - 1][col] = 1;
        }

        return appended;
    }

    static double[][][] getRandomWeights(int... nodes) {
        double[][][] weights = new double[nodes.length - 1][][];

        for (int i = 0; i < weights.length; i++)
            weights[i] = new double[nodes[i + 1]][nodes[i] + 1];

        randomize(weights);

        return weights;
    }

    static void randomize(double[][][] xs) {
        for (int i = 0; i < xs.length; i++) {
            for (int j = 0; j < xs[i].length; j++) {
                for (int k = 0; k < xs[i][j].length; k++) {
                    xs[i][j][k] = Math.random();
                }
            }
        }
    }

    static double[][] scale(double scalar, double[][] xs) {
        double[][] scaled = new double[xs.length][xs[0].length];
        for (int row = 0; row < xs.length; row++) {
            for (int col = 0; col < xs[row].length; col++) {
                scaled[row][col] = scalar * xs[row][col];
            }
        }
        return scaled;
    }

    static double[][] dActivate(double[][] xs) {
        double[][] dActivation = new double[xs.length][xs[0].length];
        for (int row = 0; row < dActivation.length; row++) {
            for (int col = 0; col < dActivation[row].length; col++) {
                dActivation[row][col] = dActivate(xs[row][col]);
            }
        }
        return dActivation;
    }

    static double[][] activate(double[][] xs) {
        double[][] activation = new double[xs.length][xs[0].length];
        for (int row = 0; row < activation.length; row++) {
            for (int col = 0; col < activation[row].length; col++) {
                activation[row][col] = activate(xs[row][col]);
            }
        }
        return activation;
    }

    static double activate(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    static double dActivate(double x) {
        return activate(x) * (1 - activate(x));
    }

    static double[][] transpose(double[][] xs) {
        double[][] ans = new double[xs[0].length][xs.length];
        for (int row = 0; row < ans.length; row++) {
            for (int col = 0; col < ans[row].length; col++) {
                ans[row][col] = xs[col][row];
            }
        }
        return ans;
    }

    static double[][] dot(double[][] xs, double[][] ys) {
        if (xs[0].length != ys.length)
            System.out.println("Illegal matrix multiplication");
        
        double[][] dot = new double[xs.length][ys[0].length];
        for (int row = 0; row < dot.length; row++) {
            for (int col = 0; col < dot[row].length; col++) {
                for (int i = 0; i < ys.length; i++) {
                    dot[row][col] += xs[row][i] * ys[i][col];
                }
            }
        }
        return dot;
    }

    static Operation addition = (x, y) -> x + y;
    static Operation subtraction = (x, y) -> x - y;
    static Operation multiplication = (x, y) -> x * y;

    static double[][] operate(Operation op, double[][] xs, double[][] ys) {
        double[][] ans = new double[xs.length][xs[0].length];
        for (int row = 0; row < ans.length; row++) {
            for (int col = 0; col < ans[row].length; col++) {
                ans[row][col] = operate(op, xs[row][col], ys[row][col]);
            }
        }
        return ans;
    }

    static double operate(Operation op, double x, double y) {
        return op.apply(x, y);
    }

    interface Operation {
        double apply(double x, double y);
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