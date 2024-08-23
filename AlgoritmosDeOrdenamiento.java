import java.util.Arrays;
import java.util.Random;

public class AlgoritmosDeOrdenamiento {

    // Método para probar los algoritmos
    public static void main(String[] args) {
        Random rand = new Random();
        int[] arr = rand.ints(10, 1, 100).toArray(); 

        System.out.println("Arreglo original: " + Arrays.toString(arr));

        int[] arr1 = arr.clone();
        bubbleSort(arr1);
        System.out.println("Bubblesort: " + Arrays.toString(arr1));

        int[] arr2 = arr.clone();
        mergeSort(arr2, 0, arr2.length - 1);
        System.out.println("Mergesort: " + Arrays.toString(arr2));

        int[] arr3 = arr.clone();
        quickSort(arr3, 0, arr3.length - 1);
        System.out.println("Quicksort: " + Arrays.toString(arr3));

        int[] arr4 = countingSort(arr);
        System.out.println("Counting Sort: " + Arrays.toString(arr4));
    }

    // Bubblesort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Mergesort
    public static void mergeSort(int[] arr, int izq, int der) {
        if (izq < der) {
            int medio = (izq + der) / 2;
            mergeSort(arr, izq, medio);
            mergeSort(arr, medio + 1, der);
            merge(arr, izq, medio, der);
        }
    }

    private static void merge(int[] arr, int izq, int medio, int der) {
        int n1 = medio - izq + 1;
        int n2 = der - medio;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[izq + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[medio + 1 + j];
        int i = 0, j = 0;
        int k = izq;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Quicksort
    public static void quickSort(int[] arr, int bajo, int alto) {
        if (bajo < alto) {
            int pi = particion(arr, bajo, alto);
            quickSort(arr, bajo, pi - 1);
            quickSort(arr, pi + 1, alto);
        }
    }

    private static int particion(int[] arr, int bajo, int alto) {
        int pivote = arr[alto];
        int i = (bajo - 1);
        for (int j = bajo; j < alto; j++) {
            if (arr[j] < pivote) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = temp;
        return i + 1;
    }

    // Counting Sort
    public static int[] countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int rango = max - min + 1;
        int[] count = new int[rango];
        int[] output = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        return output;
    }
}
