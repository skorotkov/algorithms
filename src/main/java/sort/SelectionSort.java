package sort;

public class SelectionSort {

    static private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    static public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++)
                if (array[minIndex] > array[j])
                    minIndex = j;
            swap(array, i, minIndex);
        }
        return array;
    }
}
