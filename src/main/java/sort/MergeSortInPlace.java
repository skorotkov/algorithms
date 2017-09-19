package sort;

import java.util.LinkedList;
import java.util.Queue;

public class MergeSortInPlace {

    static class InPlaceQueue {
        private int start;
        private int end;
        private int[] array;

        // get from head
        private int head;
        // put to tail
        private int tail;

        public InPlaceQueue(int[] array) {
            this.array = array;
        }

        public void init(int start, int end) {
            this.start = start;
            this.end = end;
            this.tail = start;
            this.head = -1;
        }

        public int add(int n) {
            int temp = array[tail];
            array[tail] = n;
            tail += 1;
            return temp;
        }

        public int remove() {
            int temp = array[head];
            head += 1;
            return temp;
        }

        public boolean isEmpty() {
            return head == -1;
        }

        public Integer peek() {
            if (head == -1)
                return null;
            else
                return array[head];
        }

    }
    private static InPlaceQueue queue;
//    private static int maxQueueLength = 0;
    public static void merge(int[] array, int start, int pivot, int end) {
//        int[] debug = Arrays.copyOfRange(array, start, end+1);
//        assert(queue.peek() == null);
//        try {

            queue.init(pivot + 1, end);

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
                } else if (queue.isEmpty()) {
                    if (array[insertPos] <= array[highIndex]) {
                        insertPos++;
                    } else {
                        if (insertPos <= pivot) {
                            queue.add(array[insertPos]);
                            array[insertPos] = array[highIndex];
                            insertPos++;
                            highIndex++;
                        } else {
                            insertPos = end + 1;
                        }
                    }
                } else {
                    if (queue.peek() <= array[highIndex]) {
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
//        } catch (ArrayIndexOutOfBoundsException ex) {
//            System.out.println("start: " + start + " pivot: " + pivot + " end: " + end);
//            System.out.println(Arrays.toString(debug));
//            throw ex;
//        }
//        assert(queue.peek() == null);
    }

    static private void mergeSort(int[] array, int start, int end) {
        if (end == start)
            return;
        int pivot = (start + end) / 2;
        if (end > start + 1 ) {
            mergeSort(array, start, pivot);
            mergeSort(array, pivot + 1, end);
        }
        merge(array, start, pivot, end);
    }

    static public int[] sort(int[] array) {
        queue = new InPlaceQueue(array);
        mergeSort(array, 0, array.length - 1);
        return array;
    }
}
