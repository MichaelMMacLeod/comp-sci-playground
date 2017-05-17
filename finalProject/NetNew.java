class NetNew {
    public static void main(String[] args) {
        double[][] net = new double[4][];
        net[0] = new double[2];
        net[1] = new double[3];
        net[2] = new double[4];
        net[3] = new double[1];

        double[][] weights = getRandomWeights(net);

        double[][] inputs = new double[][] {
            new double[] {1,1},
            new double[] {1,0},
            new double[] {0,1},
            new double[] {0,0}
        };

        double[][] outputs = new double[][] {
            new double[] {1},
            new double[] {0},
            new double[] {0},
            new double[] {0},
        };

        for (int i=0; i<100; i++) {
            for (int set=1; set<2; set++) {
                double[][] sums = sums(net, weights, inputs[set]);
                propagate(net, weights, inputs[set]);

                // propagate(net, weights, inputs[set]);
                // System.out.println("Propagation #" + i);
                // print(net);
                // double[][] cpy = deepCopy(net);
                // backPropagate(net, weights, outputs[set]);
                // // System.out.println("Weight Errors:");
                // // print(net);
                // adjustWeights(net, weights, cpy, 0.5);
            }
        }
    }

    static double neuronValue(double[] inputs, double[] weights) {
        double neuron = 0;
        for (int i = 0; i < inputs.length; i++)
            neuron += inputs[i] * weights[i];
        return neuron;
    }

    static void adjustWeights(double[][] errors, double[][] weights, double[][] net, double rate) {
        for (int i = 0; i < weights.length; i++) {
            double[] layerAbove = net[i];
            double[] connectingWeights = weights[i];
            double[] layerBelow = net[i + 1];
            double[] outputDeltas = errors[i + 1];

            for (int j = 0; j < connectingWeights.length; j++) {
                connectingWeights[j] = rate /** layerBelow[j]*/ * act(neuronValue(layerAbove, connectingWeights));
            }
        }
    }

    static void backPropagate(double[][] net, double[][] weights, double[] expected) {
        double[] outputLayer = net[net.length - 1];
        for (int i = 0; i < outputLayer.length; i++)
            outputLayer[i] = (expected[i] - outputLayer[i]) * dAct(outputLayer[i]);

        for (int i = net.length - 2; i >= 0; i--) {
            double[] currentLayer = net[i];
            double[] layerBelow = net[i + 1];
            double[] connectingWeights = weights[i];

            double sum = 0;
            int weight = 0;
            for (int j = 0; j < layerBelow.length; j++)
                sum += layerBelow[j] * connectingWeights[weight++];

            currentLayer[i] = dAct(currentLayer[i]) * sum;
        }
    }

    // static double[][] deltaWeights(double[][] )
    static double[][] sums(double[][] net, double[][] weights, double[] inputs) {
        double[][] sums = deepCopy(net);

        double[] inputLayer = sums[0];
        for (int i = 0; i < inputLayer.length; i++)
            inputLayer[i] = inputs[i];

        for (int i = 1; i < sums.length; i++) {
            double[] layerAbove = net[i - 1];
            double[] currentLayer = sums[i];
            double[] connectingWeights = weights[i - 1];

            for (int j = 0; j < currentLayer.length; j++) {
                currentLayer[j] = neuronValue(layerAbove, connectingWeights);
            }
        }

        return sums;
    }

    static void propagate(double[][] net, double[][] weights, double[] inputs) {
        double[] inputLayer = net[0];
        for (int i = 0; i < inputLayer.length; i++)
            inputLayer[i] = inputs[i];

        for (int i = 1; i < net.length; i++) {
            double[] layerAbove = net[i - 1];
            double[] currentLayer = net[i];
            double[] connectingWeights = weights[i - 1];

            for (int j = 0; j < currentLayer.length; j++) {
                currentLayer[j] = act(neuronValue(layerAbove, connectingWeights));
            }
        }
    }

    static void print(double[][] xs) {
        for (int i=0; i<xs.length; i++) {
            for (int j=0; j<xs[i].length; j++) {
                int len = ("" + xs[i][j]).length() > 7 ? 7 : ("" + xs[i][j]).length();
                System.out.print(("" + xs[i][j]).substring(0,len) + "\t");
            }
            System.out.println();
        }
    }

    static double act(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    static double dAct(double x) {
        return x * (1 - x);
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
                weights[i][j] += learningRate * errors[i][input] * act(net[i][input]);

                if (j == weights.length / 2)
                    j++;
            }
        }
    }

    static double[][] getRandomWeights(double[][] net) {
        double[][] weights = new double[net.length - 1][];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = new double[net[i].length * net[i+1].length];

            for (int j = 0; j < weights[i].length; j++) {
                weights[i][j] = Math.random();
            }
        }

        return weights;
    }
}