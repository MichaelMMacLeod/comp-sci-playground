public class Classifier<T> {
    private double[][][] weights;
    private T[] classifications;

    /** 
     * Creates a Classifier with the specified network weights and 
     * classifications.
     */
    public Classifier(double[][][] weights, T[] classifications) {
        this.weights = weights;
        this.classifications = classifications;
    }

    /**
     * Classifies the input.
     *
     * @return The classification with the same index as the largest value in 
     *         the last row of the output values of the neural network.
     */
    public T classify(double[][] input) {
        double[][][] output = NetMatrix.calculateOutput(weights, input);

        double[][] last = output[output.length - 1];

        int largest = 0;
        for (int i = 0; i < last.length; i++) {
            if (last[i][0] > last[largest][0]) {
                largest = i;
            }
        }

        return classifications[largest];
    }
}