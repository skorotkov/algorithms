package util;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class SortUtils {
    private static int[] getRandomArray(int n) {
        Random random = new Random();
//        int[] array = new int[random.nextInt(n)];
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(100000);
        return array;
    }

    public static void sortTester(Function<int[], int[]> sorter) {
        sortTester(sorter, 100000);
    }

    public static void sortTester(Function<int[], int[]> sorter, int n) {
        int[] array = getRandomArray(n);
        int[] sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);
        Assert.assertArrayEquals("sort " + array.length + " elements array", sorted, sorter.apply(array));

        array = getRandomArray(1);
        sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);
        Assert.assertArrayEquals("sort 1 element array", sorted, sorter.apply(array));
    }

    public static void sortTester(int[] array, Function<int[], int[]> sorter) {
        int[] sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);
        Assert.assertArrayEquals("sort " + array.length + " elements array", sorted, sorter.apply(array));
    }

    public static boolean isSorted(int[] array) {
        int[] sorted = Arrays.copyOf(array, array.length);
        Arrays.sort(sorted);
        return Arrays.equals(sorted, array);
    }
}