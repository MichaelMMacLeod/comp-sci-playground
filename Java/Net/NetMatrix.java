class NetMatrix {
    public static void main(String[] args) {
        double[][] input = new double[][] {
            new double[] {1, 1, 0, 0},
            new double[] {1, 0, 1, 0}
        };

        double[][] target = new double[][] {
            new double[] {1, 1, 1, 0}
        };

        double[][][] weights = getRandomWeights(2, 3, 1);

        double learningRate = 0.25;

        for (int epoch = 0; epoch < 100000; epoch++) {
            double[][][] output = new double[weights.length + 1][][];
            output[0] = input;

            for (int i = 1; i < output.length; i++) {
                output[i] = activate(dot(weights[i - 1], output[i - 1]));
            }

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

            double[][][] delta = new double[weights.length][][];
            delta[delta.length - 1] = scale(
                -learningRate,
                dot(
                    error[error.length - 1],
                    transpose(output[output.length - 2])));

            for (int i = delta.length - 2; i >= 0; i--) {
                delta[i] = scale(
                    -learningRate,
                    dot(
                        error[i],
                        transpose(output[i])));
            }

            for (int i = 0; i < weights.length; i++) {
                weights[i] = operate(
                    addition,
                    weights[i],
                    delta[i]);
            }

            print(output[output.length - 1]);
        }

        for (int i = 0; i < weights.length; i++) {
            System.out.println("Weights[" + i + "]:");
            print(weights[i]);
        }
    }

    static double[][][] getRandomWeights(int... nodes) {
        double[][][] weights = new double[nodes.length - 1][][];

        for (int i = 0; i < weights.length; i++)
            weights[i] = new double[nodes[i + 1]][nodes[i]];

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
        // return x * (1 - x);
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
    static Operation cost = (x, y) -> (1 / 2) * (x - y) * (x - y);

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
                // String str = ("" + xs[row][col]).length() > 7 ? ("" + xs[row][col]).substring(0, 7) : ("" + xs[row][col]);
                // System.out.print(str + "\t");
                System.out.print(xs[row][col] + "\t");
            }
            System.out.println();
        }
    }
}



// class NetMatrix {
//     public static void main(String[] args) {
//         double[][][] input = new double[][][] {
//             new double[][] {
//                 new double[] {1},
//                 new double[] {1}
//             },
//             new double[][] {
//                 new double[] {1},
//                 new double[] {0}
//             },
//             new double[][] {
//                 new double[] {0},
//                 new double[] {1}
//             },
//             new double[][] {
//                 new double[] {0},
//                 new double[] {0}
//             }
//         };

//         double[][][] expected = new double[][][] {
//             new double[][] {
//                 new double[] {1}
//             },
//             new double[][] {
//                 new double[] {0}
//             },
//             new double[][] {
//                 new double[] {0}
//             },
//             new double[][] {
//                 new double[] {0}
//             }
//         };

//         double[][][] weights = getRandomWeights(new int[] {2, 3, 3, 1});

//         double learningRate = 0.5;

//         for (int epoch = 1; epoch <= 10000; epoch++) {
//             for (int set = 0; set < input.length; set++) {
//                 double[][] x_1 = activate(dot(weights[0], input[set]));
//                 double[][] x_2 = activate(dot(weights[1], x_1));
//                 double[][] x_3 = activate(dot(weights[2], x_2));

//                 double[][] w_1 = weights[0];
//                 double[][] w_2 = weights[1];
//                 double[][] w_3 = weights[2];

//                 double[][] e_3 = operate(multiplication,
//                     operate(cost, x_3, expected[set]),
//                     dActivate(dot(w_3, x_2)));
//                 double[][] e_2 = operate(multiplication,
//                     dot(transpose(w_3), e_3),
//                     dActivate(dot(w_2, x_1)));
//                 double[][] e_1 = operate(multiplication,
//                     dot(transpose(w_2), e_2),
//                     dActivate(dot(w_1, input[set])));

//                 double[][] d_e_w_1 = dot(e_1, transpose(input[set]));

//                 weights[0] = operate(subtraction, w_1, scale(-learningRate, d_e_w_1));

//                 double[][] d_e_w_2 = dot(e_2, transpose(x_1));

//                 weights[1] = operate(subtraction, w_2, scale(-learningRate, d_e_w_2));

//                 double[][] d_e_w_3 = dot(e_3, transpose(x_2));

//                 weights[2] = operate(subtraction, w_3, scale(-learningRate, d_e_w_3));
            
//                 System.out.println("Epoch #" + epoch + ", Set #" + set);
//                 print(input[set]);
//                 System.out.println();
//                 print(x_3);
//             }
//         }
//     }
// }