public final class Matrix {
    /**
     * No need to instantiate a Matrix object.
     */
    private Matrix() {}

    /*********************
     * MATRIX GENERATION *
     *********************/

    /**
     * Generates a matrix with random values in the range [a, b).
     */
    public static double[][] random(int rows, int columns, double a, double b) {
        double[][] matrix = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                matrix[r][c] = a + Math.random() * b;
            }
        }

        return matrix;
    }

    /**
     * Generates a matrix filled with the given value.
     */
    public static double[][] fill(int rows, int columns, double value) {
        double[][] matrix = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                matrix[r][c] = value;
            }
        }

        return matrix;
    }

    /*********************
     * MATRIX OPERATIONS *
     *********************/

    /**
     * Transposes xs.
     */
    public static double[][] transpose(double[][] xs) {
        double[][] transposed = new double[xs[0].length][xs.length];

        for (int row = 0; row < transposed.length; row++) {
            for (int col = 0; col < transposed[row].length; col++) {
                transposed[row][col] = xs[col][row];
            }
        }

        return transposed;
    }

    /**
     * Performs matrix multiplication with xs and ys.
     */
    public static double[][] multiply(double[][] xs, double[][] ys) {
        double[][] multiplied = new double[xs.length][ys[0].length];

        for (int row = 0; row < multiplied.length; row++) {
            for (int col = 0; col < multiplied[row].length; col++) {
                for (int i = 0; i < xs[row].length; i++) {
                    multiplied[row][col] += xs[row][i] * ys[i][col];
                }
            }
        }

        return multiplied;
    }

    /*************************
     * BY-ELEMENT OPERATIONS *
     *************************/

    /////////////////////////
    // Operation interface //
    /////////////////////////

    interface Operation {
        double apply(double x, double y);
    }

    /**
     * Applies the operation to x and y.
     */
    private static double operate(Operation op, double x, double y) {
        return op.apply(x, y);
    }

    /**
     * Applies the operation over each element in the two arrays.
     */
    public static double[][] operate(Operation op, double[][] xs, double[][] ys) {
        double[][] ans = new double[xs.length][xs[0].length];

        for (int row = 0; row < ans.length; row++) {
            for (int col = 0; col < ans[row].length; col++) {
                ans[row][col] = operate(op, xs[row][col], ys[row][col]);
            }
        }

        return ans;
    }

    public static final Operation addition = (x, y) -> x + y;
    public static final Operation subtraction = (x, y) -> x - y;
    public static final Operation multiplication = (x, y) -> x * y;

    /**
     * Returns a result of multiplying xs with a matrix filled with the given scalar value.
     */
    public static double[][] scale(double scalar, double[][] xs) {
        double[][] scalarMatrix = fill(xs.length, xs[0].length, scalar);

        return operate(multiplication, xs, scalarMatrix);
    }

    ////////////////////////
    // Function interface //
    ////////////////////////

    interface Function {
        double apply(double x);
    }

    /**
     * Applies the function to x.
     */
    private static double operate(Function f, double x) {
        return f.apply(x);
    }

    /**
     * Applies the function over each element in the array.
     */
    public static double[][] operate(Function f, double[][] xs) {
        double[][] ans = new double[xs.length][xs[0].length];

        for (int row = 0; row < ans.length; row++) {
            for (int col = 0; col < ans[row].length; col++) {
                ans[row][col] = operate(f, xs[row][col]);
            }
        }

        return ans;
    }

    // The sigmoid function.
    public static final Function activation = 
    	(x) -> 1 / (1 + Math.pow(Math.E, -x));
    // The derivative of activation.
    public static final Function dActivation = 
    	(x) -> operate(activation, x) * (1 - operate(activation, x));
}
