public class lab08k {

    /*/
     * Michael MacLeod
     * Created 9/16/2016
    /*/

    public static void main(String[] args) {

        int carDoor = 0; // Holds temporary values
        final int[] data1 = {5, 4, 8, 15, 9, 3};
        final int[] data2 = {25, 400, 80, 45, 9, 543};

        for (int i = 0; i < 6; i++) {
            for (int j = 1; j < data2[i]; j++) {
                if (data1[i] % j == 0 && data2[i] % j == 0) {
                    carDoor = j;
                }
            }
            System.out.println("The gcd of " + data1[i] + " and " + data2[i] + " is " + carDoor);
        }
    }
}