public class RandomizeWeights {
    public static void main(String[] args) {
        double[][][] weights = new double[][][] {
            Matrix.random(
                Constants.NODES_HIDDEN, 
                Constants.NODES_INPUT, 
                -0.5, 
                0.5),
            Matrix.random(
                Constants.NODES_OUTPUT, 
                52, 
                -0.5, 
                0.5)
        };

        Serializer.serialize(weights, Constants.FILE_WEIGHTS);
    }
}