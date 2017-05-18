public class NetEvenNew {
    public static void main(String[] args) {
        double[][] inputs = new double[][] {
            new double[] {1,1},
            new double[] {0,1},
            new double[] {1,0},
            new double[] {0,0}
        };
        double[][] expected = new double[][] {
            new double[] {1},
            new double[] {0},
            new double[] {0},
            new double[] {0}
        };

        double[][][] weights = new double[][][] {
            new double[][] {
                new double[] {0.5322138329422694,0.026839079797428347,0.6119719842740842},
                new double[] {0.9893799589756366,0.21128285121720003,0.0322080335187267}
            },
            new double[][] {
                new double[] {-0.3327270557886068},
                new double[] {0.24235706094710702},
                new double[] {0.1768785669717708}
            }
        };

        for (int epoch = 0; epoch < 100; epoch++) {
            for (int set = 0; set < inputs.length; set++) {
                double[][] input = new double[][] {inputs[set]};
                double[][] hidden = multiply(input, weights[0]);
                double[][] output = activate(multiply(activate(hidden), weights[1]));

                double[][] error = subtract(new double[][] {expected[set]}, activate(output));

                double[][] deltaOutput = multiply(deActivate(output), error);

                double[][] deltaHiddenWeights = multiply(deltaOutput, activate(hidden));

                weights[1] = transpose(add(transpose(weights[1]), deltaHiddenWeights));
                
                double[][] deltaHidden = multiply(multiply(transpose(deltaOutput), transpose(weights[1])), activate(hidden));
            
                
                double[][] deltaInputWeights = multiply(multiply(transpose(deltaOutput), weights[0]), activate(hidden));

                System.out.println("deltaInputWeights");
                print(deltaInputWeights);
                weights[0] = add(weights[0], deltaInputWeights);
            }
        }
    }

    static double[][] multByItem(double[][] xs, double[][] ys) {
        double[][] ans = new double[xs.length][];

        for (int i = 0; i < xs.length; i++) {
            ans[i] = new double[xs[i].length];

            for (int j = 0; j < xs[i].length; j++) {
                ans[i][j] = xs[i][j] * ys[i][j];
            }
        }

        return ans;
    }

    static double[][] add(double[][] xs, double[][] ys) {
        double[][] ans = new double[xs.length][];

        for (int i = 0; i < xs.length; i++) {
            ans[i] = new double[xs[i].length];

            for (int j = 0; j < xs[i].length; j++) {
                ans[i][j] = xs[i][j] + ys[i][j];
            }
        }

        return ans;
    }

    static double[][] subtract(double[][] xs, double[][] ys) {
        double[][] ans = new double[xs.length][];

        for (int i = 0; i < xs.length; i++) {
            ans[i] = new double[xs[i].length];

            for (int j = 0; j < xs[i].length; j++) {
                ans[i][j] = xs[i][j] - ys[i][j];
            }
        }

        return ans;
    }

    static double[][] deActivate(double[][] xs) {
        double[][] activation = new double[xs.length][];

        for (int i = 0; i < activation.length; i++) {
            activation[i] = new double[xs[i].length];

            for (int j = 0; j < activation[i].length; j++) {
                activation[i][j] = deActivate(xs[i][j]);
            }
        }

        return activation;
    }

    static double[][] activate(double[][] xs) {
        double[][] activation = new double[xs.length][];

        for (int i = 0; i < activation.length; i++) {
            activation[i] = new double[xs[i].length];

            for (int j = 0; j < activation[i].length; j++) {
                activation[i][j] = activate(xs[i][j]);
            }
        }

        return activation;
    }

    static double deActivate(double x) {
        return activate(x) * (1 - activate(x));
    }

    static double activate(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    static double[][] multiply(double[] xs, double[][] ys) {
        double[][] array = new double[][] {xs};
        return multiply(array, ys);
    }

    static double[][] multiply(double[][] xs, double[][] ys) {
        double[][] ans = new double[xs.length][ys[0].length];
        for (int row = 0; row < ans.length; row++) {
            for (int col = 0; col < ans[row].length; col++) {
                for (int i = 0; i < ys.length; i++) {
                    ans[row][col] += xs[row][i] * ys[i][col];
                }
            }
        }
        return ans;
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

    static void print(double[][] xs) {
        for (int i = 0; i < xs.length; i++) {
            for (int j = 0; j < xs[i].length; j++) {
                System.out.print(xs[i][j] + "\t");
            }
            System.out.println();
        }
    }
}