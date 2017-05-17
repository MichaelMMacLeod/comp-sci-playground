class Net {
    public static void main(String[] args) {
        double[][] net = new double[3][];
        net[0] = new double[2];
        net[1] = new double[4];
        net[2] = new double[4];

        double[][] weights = getRandomWeights(net);

        double[][] inputs = new double[][] {
            new double[] {0,0},
            new double[] {0,0},
            new double[] {0,0},
            new double[] {0,0}
        };

        double[] outputs = new double[] {0,0,0,0};

        // double[][] outputs = new double[][] {
        //     new double[] {0},
        //     new double[] {0},
        //     new double[] {0},
        //     new double[] {0}
        // };

        int trials = 100;

        for (int i=0; i<trials; i++) {
            for (int j=0; j<inputs.length; j++) {
                propagate(inputs[j], net, weights);
    
                // System.out.println("Propagation #" + (i+1));
                // print(net);
                System.out.println("Weights #" + (i+1));
                print(weights);

                double[][] propagation = deepCopy(net);

                weightErrors(net, weights, outputs);

                adjustWeights(0.5, weights, net, propagation);
            }
        }
    }

    static double[][] deepCopy(double[][] xs) {
        double[][] ans = new double[xs.length][];

        for (int i=0; i<xs.length; i++) {
            ans[i] = new double[xs[i].length];

            for (int j=0; j<xs[i].length; j++) {
                ans[i][j] = xs[i][j];
            }
        }

        return ans;
    }

    static void adjustWeights(double learningRate, double[][] weights, double[][] errors, double[][] net) {
        for (int i=0; i<weights.length; i++) {
            int input = 0;

            for (int j=0; j<weights[i].length; j++) {
                weights[i][j] += learningRate * errors[i][input] * net[i][input];

                if (j == weights.length / 2)
                    j++;
            }
        }
    }

    static void weightErrors(double[][] net, double[][] weights, double[] expected) {
        for (int i=0; i<net[net.length-1].length; i++) {
            net[net.length-1][i] = expected[i] - net[net.length-1][i];
        } 

        for (int i=net.length-2; i>=0; i--) {
            for (int j=0; j<net[i].length; j++) {
                int val = 0;

                for (int w=0; w<net[i+1].length; w++) {
                    val += weights[i][w] * net[i+1][w] * dAct(net[i][j]);
                }

                // val *= dAct(net[i][j]);
            }
        }
    }

    static void propagate(double[] inputs, double[][] net, double[][] weights) {
        for (int i=0; i<net[0].length; i++) {
            net[0][i] = inputs[i];
        }

        for (int i=1; i<net.length; i++) {
            for (int j=0; j<net[i].length; j++) {
                net[i][j] = 0;

                int w = 0;

                for (int k=0; k<net[i-1].length; k++) {
                    net[i][j] += weights[i-1][w] * net[i-1][w];
                    w++;
                }

                net[i][j] = act(net[i][j]);
            }
        }
    }

    static double[][] getRandomWeights(double[][] net) {
        double[][] weights = new double[net.length - 1][];

        for (int i=0; i<weights.length; i++) {
            weights[i] = new double[net[i].length * net[i+1].length];

            for (int j=0; j<weights[i].length; j++) {
                weights[i][j] = Math.random();
            }
        }

        return weights;
    }

    static double act(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    static double dAct(double x) {
        return act(x) * (1 - act(x));
    }

    static void print(double[] xs) {
        for (int i=0; i<xs.length; i++) {
            System.out.print(xs[i] + "\t");
        }
        System.out.println();
    }

    static void print(double[][] xs) {
        for (int i=0; i<xs.length; i++) {
            for (int j=0; j<xs[i].length; j++) {
                System.out.print(xs[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void print(double[][][] xs) {
        for (int i=0; i<xs.length; i++) {
            for (int j=0; j<xs[i].length; j++) {
                for (int k=0; k<xs[i][j].length; k++) {
                    System.out.print(xs[i][j][k] + " ");
                }
                System.out.print(" ### ");
            }
            System.out.println();
        }
    }
}