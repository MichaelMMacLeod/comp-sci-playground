// implying someone can actually copy something out of a pdf without loosing 
// their minds over the messed-up spacing

import java.util.Arrays;

public class Sorts {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide an integer argument denoting the length of array to sort.");
            System.exit(0);

            // let's just hope they gave us an integer; too lazy to deal with that right now.
        }

        int[] dis = new int[Integer.parseInt(args[0])]; // disarray
        for (int i = 0; i < dis.length; i++)
            dis[i] = (int) (Math.random() * dis.length);

        long start;

        System.out.println("Initial array:");
        for (int i : dis)
            System.out.print(i + " ");
        System.out.println();

        System.out.println("\nSorting an array of " + dis.length + " random numbers...\n");
        
        System.out.print("BubbleSort ");
        int[] bs = Arrays.copyOf(dis, dis.length);
        start = System.nanoTime();
        bubbleSort(bs);
        System.out.println("in " + (System.nanoTime() - start) + " nanoseconds");
        for (int i : bs)
            System.out.print(i + " ");
        System.out.println("\n");

        System.out.print("SelectionSort ");
        int[] ss = Arrays.copyOf(dis, dis.length);
        start = System.nanoTime();
        selectionSort(ss);
        System.out.println("in " + (System.nanoTime() - start) + " nanoseconds");
        for (int i : ss)
            System.out.print(i + " ");
        System.out.println("\n");

        System.out.print("InsertionSort ");
        int[] is = Arrays.copyOf(dis, dis.length);
        start = System.nanoTime();
        insertionSort(is);
        System.out.println("in " + (System.nanoTime() - start) + " nanoseconds");
        for (int i : is)
            System.out.print(i + " ");
        System.out.println("\n");

        System.out.print("QuickSort ");
        int[] qs = Arrays.copyOf(dis, dis.length);
        start = System.nanoTime();
        quickSort(qs, 0, qs.length - 1);
        System.out.println("in " + (System.nanoTime() - start) + " nanoseconds");
        for (int i : qs)
            System.out.print(i + " ");
        System.out.println("\n");

        System.out.print("MergeSort ");
        int[] ms = Arrays.copyOf(dis, dis.length);
        start = System.nanoTime();
        mergeSort(ms, 0, ms.length - 1);
        System.out.println("in " + (System.nanoTime() - start) + " nanoseconds");
        for (int i : ms)
            System.out.print(i + " ");
        System.out.println("\n");
    }

    // called by mergeSort()
    static void merge(int a[], int left, int middle, int right) {
        int[] tempArray = new int[right - left + 1];

        int index1 = left;
        int index2 = middle + 1;
        int indx = 0;

        while (index1 <= middle && index2 <= right) {
            if (a[index1] < a[index2]) {
                tempArray[indx] = a[index1];
                index1++;
            } else {
                tempArray[indx] = a[index2];
                index2++;
            }

            indx++;
        }

        while (index1 <= middle) {
            tempArray[indx] = a[index1];
            index1++;
            indx++;
        }

        while (index2 <= right) {
            tempArray[indx] = a[index2];
            index2++;
            indx++;
        }

        for (indx = 0; indx < tempArray.length; indx++) {
            a[left + indx] = tempArray[indx];
        }
    }

    static void mergeSort(int[] a, int left, int right) {
        if (right == left)
            return;

        int middle = (left + right) / 2;

        mergeSort(a, left, middle);
        mergeSort(a, middle + 1, right);

        merge(a, left, middle, right);
    }

    static void quickSort(int[] a, int left, int right) {
        if (left >= right)
            return;

        int k = left;
        int j = right;

        int pivotValue = a[(left + right) / 2];

        while (k < j) {
            while (a[k] < pivotValue) {
                k++;
            }

            while (pivotValue < a[j]) {
                j--;
            }

            if (k <= j) {
                int temp = a[k];
                a[k] = a[j];
                a[j] = temp;

                k++;
                j--;
            }
        }

        quickSort(a, left, j);
        quickSort(a, k, right);
    }

    static void insertionSort(int[] a) {
        int itemToInsert, j;
        boolean sorting;

        for (int k = 0; k < a.length; k++) {
            itemToInsert = a[k];
            j = k - 1;

            sorting = true;

            while (j >= 0 && sorting) {
                if (itemToInsert < a[j]) {
                    a[j + 1] = a[j];

                    j--;
                    if (j == -1)
                        a[0] = itemToInsert;
                } else {
                    sorting = false;
                    a[j + 1] = itemToInsert;
                }
            }
        }
    }

    static void selectionSort(int[] a) {
        int min, minIndex;

        for (int i = 0; i < a.length; i++) {
            min = a[i];
            minIndex = i;

            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    minIndex = j;
                }
            }

            a[minIndex] = a[i];
            a[i] = min;
        }
    }

    static void bubbleSort(int[] a) {
        boolean sorting;

        do {
            sorting = false;

            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];

                    a[i] = a[i + 1];
                    a[i + 1] = temp;

                    sorting = true;
                }
            }
        } while (sorting);
    }
}