public class Net {

    static double[] getSums(double[] inputs, double[] weights) {
        double[] sums = new double[weights.length / inputs.length];

        int weight = 0;
        for (int i = 0; i < inputs.length; i++)
            sums[i] = inputs[i] * weights[weight++];

        return sums;
    }

    public static void main(String[] args) {

        // SETUP

        double[] input = new double[] {1, 0};

        double target = 0.5;

        double[] weights = new double[9];
        for (int i = 0; i < weights.length; i++)
            weights[i] = Math.random();

        double output = -1;

        for (int j = 0; j < 10000; j++) {

            // FORWARD PROPAGATION

            double[] w1s = new double[6];
            for (int w = 0; w < w1s.length; w++)
                w1s[w] = weights[w];

            double[] w2s = new double[3];
            for (int w = 0; w < w2s.length; w++) 
                w2s[w] = weights[w1s.length + w];
            
            double[] nodeSums = getSums(input, w1s);

            double[] nodes = new double[3];
            for (int i = 0; i < nodes.length; i++)
                nodes[i] = activate(nodeSums[i]);

            double outputSum = 0;
            for (int i = 0; i < nodes.length; i++)
                outputSum += weights[i + 6] * nodes[i];

            output = activate(outputSum);

            // BACK PROPAGATION, HIDDEN -> OUTPUT WEIGHTS

            double outputError = target - output;

            double deltaOutput = deactivate(outputSum) * outputError;

            double[] deltaHiddenWeights = new double[9];
            for (int i = 6; i < 9; i++)
                deltaHiddenWeights[i] = deltaOutput * nodes[i - 6];

            for (int i = 6; i < 9; i++)
                weights[i] += deltaHiddenWeights[i];

            // BACK PROPAGATION, INPUT -> HIDDEN WEIGHTS

            double[] deltaHidden = new double[3];
            for (int i = 0; i < deltaHidden.length; i++)
                deltaHidden[i] = (deltaOutput * weights[i + 6]) * activate(nodeSums[i]);

            double[] deltaInputWeights = new double[6];
            deltaInputWeights[0] = input[0] * deltaHidden[0];
            deltaInputWeights[1] = input[0] * deltaHidden[1];
            deltaInputWeights[2] = input[0] * deltaHidden[2];
            deltaInputWeights[3] = input[1] * deltaHidden[0];
            deltaInputWeights[4] = input[1] * deltaHidden[1];
            deltaInputWeights[5] = input[1] * deltaHidden[2];

            for (int i = 0; i < 6; i++)
                weights[i] += deltaInputWeights[i];
        }

        System.out.print("Input: ");
        out(input);
        System.out.println("Target: " + target);
        System.out.println("Actual: " + output);
        System.out.print("Weights: ");
        out(weights);
    }

    /**
     * @return the value 'd' put through the derivative of the activation
     * function, f'(x)
     * where f'(x) = (e ^ x) / (e ^ x + 1) ^ 2
     */
    static double deactivate(double d) {
        // return Math.pow(Math.E, d) / Math.pow(Math.pow(Math.E, d) + 1, 2);
        return activate(d) * (1 - activate(d));
    }

    /**
     * @return the value 'd' put through the activation function, f(x)
     * where f(x) = 1 / (1 + e ^ (-x))
     */
    static double activate(double d) {
        return 1 / (1 + Math.pow(Math.E, -d));
    }

    /**
     * Prints out 'nums', where each item is separated by the String 'between'.
     */
    static void out(String between, double... nums) {
        for (int i = 0; i < nums.length - 1; i++)
            System.out.print(nums[i] + between);
        System.out.println(nums[nums.length - 1]);
    }

    /**
     * Prints out 'nums', where each item is separated by a ", ".
     */
    static void out(double... nums) {
        for (int i = 0; i < nums.length - 1; i++)
            System.out.print(nums[i] + ", ");
        System.out.println(nums[nums.length - 1]);
    }
}