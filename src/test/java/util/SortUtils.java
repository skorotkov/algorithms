package util;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class SortUtils {
    private static int[] getRandomArray(int n) {
        Random random = new Random();
        int[] array = new int[random.nextInt(n)];
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(n);
        return array;
    }

    public static void sortTester(Function<int[], int[]> sorter) {
        int[] array = getRandomArray(100000);
        int[] sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);
        Assert.assertArrayEquals("sort " + array.length + " elements array", sorted, sorter.apply(array));

        array = getRandomArray(1);
        sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);
        Assert.assertArrayEquals("sort 1 element array", sorted, sorter.apply(array));
    }
}
