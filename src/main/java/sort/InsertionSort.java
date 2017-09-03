package sort;

public class InsertionSort {
    static private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    static public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int posToSwap = i;
            while (posToSwap > 0 && array[posToSwap - 1] > array[posToSwap]) {
                swap(array, posToSwap-1, posToSwap);
                posToSwap--;
            }
        }
        return array;
    }
}
