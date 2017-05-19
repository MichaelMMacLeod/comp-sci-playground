class NetMatrix {
    public static void main(String[] args) {
        double[][][] input = new double[][][] {
            new double[][] {
                new double[] {1},
                new double[] {1}
            },
            new double[][] {
                new double[] {1},
                new double[] {0}
            },
            new double[][] {
                new double[] {0},
                new double[] {1}
            },
            new double[][] {
                new double[] {0},
                new double[] {0}
            }
        };

        double[][][] expected = new double[][][] {
            new double[][] {
                new double[] {1}
            },
            new double[][] {
                new double[] {0}
            },
            new double[][] {
                new double[] {0}
            },
            new double[][] {
                new double[] {0}
            }
        };

        double[][][] weights = new double[][][] {
            new double[][] {
                new double[] {0.5, 0.5},
                new double[] {0.5, 0.5},
                new double[] {0.5, 0.5}
            },
            new double[][] {
                new double[] {0.5, 0.5, 0.5},
                new double[] {0.5, 0.5, 0.5},
                new double[] {0.5, 0.5, 0.5},
            },
            new double[][] {
                new double[] {0.5, 0.5, 0.5}
            }
        };

        double learningRate = 0.5;

        int set = 0;

        double[][] x_0 = input[set];
        double[][] t = expected[set];

        double[][] w_1 = weights[0];
        double[][] w_2 = weights[1];
        double[][] w_3 = weights[2];

        double[][] x_1 = activate(dot(w_1, x_0));
        double[][] x_2 = activate(dot(w_2, x_1));
        double[][] x_3 = activate(dot(w_3, x_2));

        double[][] e_3 = operate(multiplication,
            operate(subtraction, x_3, t),
            dActivate(dot(w_3, x_2)));
        double[][] e_2 = operate(multiplication,
            dot(transpose(w_3), e_3),
            dActivate(dot(w_2, x_1)));
        double[][] e_1 = operate(multiplication,
            dot(transpose(w_2), e_2),
            dActivate(dot(w_1, x_0)));

        double[][] d_e_w_1 = dot(e_1, transpose(x_0));
        double[][] d_e_w_2 = dot(e_2, transpose(x_1));
        double[][] d_e_w_3 = dot(e_3, transpose(x_2));

        w_1 = operate(subtraction, w_1, scale(-learningRate, d_e_w_1));
        w_1 = operate(subtraction, w_2, scale(-learningRate, d_e_w_2));
        w_1 = operate(subtraction, w_3, scale(-learningRate, d_e_w_3));

        print(w_1);
        System.out.println();
        print(w_2);
        System.out.println();
        print(w_3);
        System.out.println();
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
                String str = ("" + xs[row][col]).length() > 7 ? ("" + xs[row][col]).substring(0, 7) : ("" + xs[row][col]);
                System.out.print(str + "\t");
            }
            System.out.println();
        }
    }
}