class NetMatrix {
    public static void main(String[] args) {
        double[][] input = new double[][] {
            new double[] {1, 1, 0, 0},
            new double[] {1, 0, 1, 0}
        };

        double[][] target = new double[][] {
            new double[] {1, 0, 0, 0}
        };

        double[][][] weights = getRandomWeights(2, 3, 1);

        double learningRate = 0.25;

        for (int epoch = 0; epoch < 100000; epoch++) {
            double[][][] output = calculateOutput(weights, input);

            double[][][] error = calculateErrors(weights, output, target);

            double[][][] delta = calculateDeltas(learningRate, error, output, weights);

            weights = updateWeights(weights, delta);

            print(output[output.length - 1]);
        }

        for (int i = 0; i < weights.length; i++) {
            System.out.println("Weights[" + i + "]:");
            print(weights[i]);
        }
    }

    static double[][][] calculateOutput(double[][][] weights, double[][] input) {
        double[][][] output = new double[weights.length + 1][][];
        output[0] = appendOnes(input);

        for (int i = 1; i < output.length; i++) {
            output[i] = activate(dot(weights[i - 1], output[i - 1]));

            if (i != output.length - 1) {
                output[i] = appendOnes(output[i]);
            }
        }
        
        return output;
    }
    static double[][][] calculateErrors(double[][][] weights, double[][][] output, double[][] target) {
        double[][][] error = new double[weights.length][][];

        error[error.length - 1] = operate(
            multiplication,
            dActivate(output[output.length - 1]),
            operate(
                subtraction,
                output[output.length - 1],
                target));

        for (int i = error.length - 2; i >= 0; i--) {
            error[i] = operate(
                multiplication,
                dActivate(output[i + 1]),
                dot(
                    transpose(weights[i + 1]),
                    error[i + 1]));
        }

        return error;
    }

    static double[][][] updateWeights(double[][][] weights, double[][][] delta) {
        double[][][] newWeights = new double[weights.length][][];

        for (int i = 0; i < newWeights.length; i++) {
            newWeights[i] = operate(
                addition,
                weights[i],
                delta[i]);
        }

        return newWeights;
    }

    static double[][][] calculateDeltas(double learningRate, double[][][] error, double[][][] output, double[][][] weights) {
        double[][][] delta = new double[weights.length][][];

        for (int i = delta.length - 1; i >= 0; i--) {
            delta[i] = scale(
                -learningRate,
                dotDeltas(
                    error[i],
                    transpose(output[i])));
        }

        return delta;
    }

    static double[][] dotDeltas(double[][] error, double[][] output) {
        if (error[0].length != output.length)
            System.out.println("Illegal matrix multiplication (error)");
        
        double[][] dot = new double[error.length][output[0].length];
        for (int row = 0; row < dot.length - 1; row++) {
            for (int col = 0; col < dot[row].length; col++) {
                for (int i = 0; i < output.length; i++) {
                    dot[row][col] += error[row][i] * output[i][col];
                }
            }
        }

        for (int col = 0; col < dot[dot.length - 1].length; col++) {
            dot[dot.length - 1][col] = error[dot.length - 1][col];
        }

        return dot;
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