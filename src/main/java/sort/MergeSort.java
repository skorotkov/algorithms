package sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MergeSort {

    private static Queue<Integer> queue = new LinkedList<>();
    private static void merge(int[] array, int start, int pivot, int end) {
        int[] debug = Arrays.copyOfRange(array, start, end);
        try {

            int insertPos = start;
            int highIndex = pivot + 1;
            do {
                if (highIndex > end) {
                    if (insertPos <= pivot)
                        queue.add(array[insertPos]);
                    while (queue.peek() != null) {
                        array[insertPos] = queue.remove();
                        insertPos++;
                    }
                } else if (queue.peek() == null) {
                    if (array[insertPos] <= array[highIndex]) {
                        insertPos++;
                    } else {
                        queue.add(array[insertPos]);
                        array[insertPos] = array[highIndex];
                        insertPos++;
                        highIndex++;
                    }
                } else {
                    if (queue.element() <= array[highIndex]) {
                        if (insertPos <= pivot)
                            queue.add(array[insertPos]);
                        array[insertPos] = queue.remove();
                        insertPos++;
                    } else {
                        if (insertPos <= pivot)
                            queue.add(array[insertPos]);
                        array[insertPos] = array[highIndex];
                        insertPos++;
                        highIndex++;
                    }
                }
            } while (insertPos <= end);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("start: " + start + " pivot: " + pivot + " end: " + end);
            System.out.println(Arrays.toString(debug));
            throw ex;
        }
    }

    static private void mergeSort(int[] array, int start, int end) {
        if (end == start)
            return;
        int pivot = start + (end - start) / 2;
        if (end > start + 1 ) {
            mergeSort(array, start, pivot);
            mergeSort(array, pivot + 1, end);
        }
        merge(array, start, pivot, end);
    }

    static public int[] sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
        return array;
    }
}
