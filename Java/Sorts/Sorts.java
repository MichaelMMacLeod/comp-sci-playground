// implying someone can actually copy something out of a pdf without loosing 
// their minds over the messed-up spacing

import java.util.Arrays;

public class Sorts {
    public static void main(String[] args) {
        int[] dis = new int[] {4, 2, 5, 1, 3, 18, 0, 9, 6}; // disarray
        
        System.out.println("BubbleSort");
        int[] bs = Arrays.copyOf(dis, dis.length);
        bubbleSort(bs);
        for (int i : bs)
            System.out.print(i + " ");
        System.out.println();

        System.out.println("SelectionSort");
        int[] ss = Arrays.copyOf(dis, dis.length);
        selectionSort(ss);
        for (int i : ss)
            System.out.print(i + " ");
        System.out.println();

        System.out.println("QuickSort");
        int[] qs = Arrays.copyOf(dis, dis.length);
        quickSort(qs);
        for (int i : qs)
            System.out.print(i + " ");
        System.out.println();
    }

    static void quickSort(int[] array) {
        
    }

    static void selectionSort(int[] dis) {
        int min, minIndex;

        for (int i = 0; i < dis.length; i++) {
            min = dis[i];
            minIndex = i;

            for (int j = i + 1; j < dis.length; j++) {
                if (dis[j] < min) {
                    min = dis[j];
                    minIndex = j;
                }
            }

            dis[minIndex] = dis[i];
            dis[i] = min;
        }
    }

    static void bubbleSort(int[] dis) {
        boolean sorting;

        do {
            sorting = false;

            for (int i = 0; i < dis.length - 1; i++) {
                if (dis[i] > dis[i + 1]) {
                    int temp = dis[i];

                    dis[i] = dis[i + 1];
                    dis[i + 1] = temp;

                    sorting = true;
                }
            }
        } while (sorting);
    }
}