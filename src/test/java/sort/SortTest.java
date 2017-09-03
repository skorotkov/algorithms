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
}
