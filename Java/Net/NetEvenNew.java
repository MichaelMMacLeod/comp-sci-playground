public class NetEvenNew {
    public static void main(String[] args) {
        double[][] xs = new double[][]
        {
            new double[] {0,1,2},
            new double[] {3,4,5},
            new double[] {6,7,8}
        };
        double[][] ys = new double[][]
        {
            new double[] {0,1,2},
            new double[] {3,4,5},
            new double[] {6,7,8}
        };
        print(transpose(multiply(xs, ys)));
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