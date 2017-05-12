class Net {
    public static void main(String[] args) {
        double[][] net = new double[3][];
        net[0] = new double[2];
        net[1] = new double[1];
        net[2] = new double[1];

        double[][][] weights = getRandomWeights(net);

        printWeights(weights);

        propagate(new int[] {1,1}, net, weights);

        printNet(net);
    }

    static void propagate(int[] inputs, double[][] net, double[][][] weights) {
        for (int i=0; i<net[0].length; i++) {
            net[0][i] = inputs[i];
        }

        for (int i=1; i<net.length; i++) {
            for (int j=0; j<net[i].length; j++) {
                int w = 0;
                for (int k=0; k<net[i-1].length; k++) {
                    net[i][j] += net[i-1][k] * weights[i-1][j][w++];
                }
                net[i][j] = act(net[i][j]);
            }
        }
    }

    static double[][][] getRandomWeights(double[][] net) {
        double[][][] weights = new double[net.length - 1][][];

        for (int i=0; i<weights.length; i++) {
            weights[i] = new double[net[i+1].length][];
            for (int j=0; j<weights[i].length; j++) {
                weights[i][j] = new double[net[i].length];
                for (int k=0; k<weights[i][j].length; k++) {
                    weights[i][j][k] = Math.random();
                }
            }
        }


        return weights;
    }

    static double act(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    static void printNet(double[][] xs) {
        for (int i=0; i<xs.length; i++) {
            for (int j=0; j<xs[i].length; j++) {
                System.out.print(xs[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static void printWeights(double[][][] xs) {
        for (int i=0; i<xs.length; i++) {
            for (int j=0; j<xs[i].length; j++) {
                for (int k=0; k<xs[i][j].length; k++) {
                    System.out.print(xs[i][j][k] + "\t");
                }
            }
            System.out.println();
        }
    }
}