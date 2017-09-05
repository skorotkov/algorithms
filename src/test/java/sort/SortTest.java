package sort;

import org.junit.Test;
import util.SortUtils;

public class SortTest {
    @Test
    public void testSelectionSort() {
        SortUtils.sortTester(SelectionSort::sort);
    }

    @Test
    public void testInsertionSort() {
        SortUtils.sortTester(InsertionSort::sort);
    }

    @Test
    public void testMergeSort() {
//        SortUtils.sortTester(new int[] {1, 2}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {2, 1}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {1, 2, 3}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {1, 3, 2}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {3, 1, 2}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {3, 2, 1}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {2, 3, 1}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {1, 1, 2}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {2, 2, 1}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {3, 4, 1, 2}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {4, 3, 1, 2}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {1, 3, 2, 4}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {4, 1, 2, 3}, MergeSort::sort);
//        SortUtils.sortTester(new int[] {6, 5, 1, 2, 3}, MergeSort::sort);

        SortUtils.sortTester(MergeSort::sort, 50);
    }

}
