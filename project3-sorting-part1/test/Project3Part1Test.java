import sorting.*;

import org.junit.*;
import java.util.Arrays;
import java.util.Random;

/** Test file for Project 3 part 1, sorting. */
public class Project3Part1Test {

    public final static int NUM_ITERS = 10; // how many times to test it
    public final static int SIZE = 32; // number of elements in the list

    @Test
    public void testInsertionSort() {
        SortingAlgorithms listSorter = new SortingAlgorithms();
        boolean isSorted = testComparisonSortMethod(listSorter, "insertion");
        if (!isSorted)
            Assert.fail();
    }

    @Test
    public void testIterativeMergeSort() {
        SortingAlgorithms listSorter = new SortingAlgorithms();
        boolean isSorted = testComparisonSortMethod(listSorter, "iterativeMerge");
        if (!isSorted)
            Assert.fail();
    }

    @Test
    public void testHeapSort() {
        SortingAlgorithms listSorter = new SortingAlgorithms();
        boolean isSorted = testComparisonSortMethod(listSorter, "heap");
        if (!isSorted)
            Assert.fail();
    }

    @Test
    public void testRandomizedQuicksort(){
        SortingAlgorithms listSorter = new SortingAlgorithms();
        boolean isSorted = testComparisonSortMethod(listSorter, "randomizedQuick");
        if (!isSorted)
            Assert.fail();
    }

    @Test
    public void testHybrid() {
        SortingAlgorithms listSorter = new SortingAlgorithms();
        boolean isSorted = testComparisonSortMethod(listSorter, "hybrid");
        if (!isSorted)
            Assert.fail();
    }

    // Helper methods ------------
    // Part 1 tests ------------------
    public static boolean testComparisonSortMethod(SortingAlgorithms listSorter, String methodName) {
        Comparable[] arr1 = new Comparable[SIZE];
        Comparable[] arr2 = new Comparable[SIZE];
        Comparable[] arr1Copy = new Comparable[SIZE];
        Comparable[] arr2Copy = new Comparable[SIZE];
        Random r = new Random();
        for (int i = 0; i < NUM_ITERS; i++) {
            for (int j = 0; j < SIZE; j++) {
                arr1[j] = r.nextInt(40);
                arr1Copy[j] = arr1[j];
                arr2[j] = r.nextInt(40);
                arr2Copy[j] = arr2[j];

            }

            // Randomly generate low and high
            // Not trying to explore the full range of lowindex/highindex, just
            // some range
            int lowindex = r.nextInt(arr2.length / 2);
            int highindex = arr2.length / 2 + r.nextInt(arr2.length / 2);
            // System.out.println("lowindex and highindex: " + lowindex + " " +
            // highindex);
            switch (methodName) {
                case "iterativeMerge":
                    listSorter.iterativeMergeSort(arr1);
                    break;

                case "hybrid":
                    listSorter.hybridSort(arr1, 0, arr1.length - 1);
                    listSorter.hybridSort(arr2, lowindex, highindex);
                    break;
                case "insertion":
                    listSorter.insertionSort(arr1, 0, arr1.length - 1, false);
                    listSorter.insertionSort(arr2, lowindex, highindex, false);
                    // System.out.println(Arrays.toString(arr2Copy));
                    // System.out.println("after: " + Arrays.toString(arr2));
                    break;

                case "randomizedQuick":
                    listSorter.randomizedQuickSort(arr1, 0, arr1.length - 1);
                    listSorter.randomizedQuickSort(arr2, lowindex, highindex);
                    break;

                case "heap":
                    listSorter.heapSort(arr1, 0, arr1.length - 1, false);
                    listSorter.heapSort(arr2, lowindex, highindex, false);
                    break;
            }

            if (!isSorted(arr1, 0, arr1.length - 1)) {
                System.out.println("------- Not sorted correctly---------");
                System.out.println("Before " + methodName + " sort: arr1 = " + Arrays.toString(arr1Copy));
                System.out.println("After " + methodName + " sort, for range: low =  0, high = " + (arr1.length - 1)
                        + ", arr1 =" + Arrays.toString(arr1));

                return false;
            }
            if (!methodName.equals("iterativeMerge"))
                if (!isSorted(arr2, lowindex, highindex)) {
                    System.out.println("------- Not sorted correctly---------");
                    System.out.println("Before " + methodName + " sort: arr2 = " + Arrays.toString(arr2Copy));
                    System.out.println("After " + methodName + " sort, for range: low = " + +lowindex + " high = "
                            + highindex + ", arr2 = " + Arrays.toString(arr2));

                    return false;
                }

        }
        return true;

    }


    /**
     * Checks if the part of the array from startIndex to endIndex (inclusive)
     * is sorted
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static boolean isSorted(Comparable[] arr, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            System.out.println("startIndex < endIndex");
            return false;
        }
        if (startIndex == endIndex - 1)
            return true;

        if ((arr[startIndex].compareTo(arr[startIndex + 1]) <= 0)) {
            return isSorted(arr, startIndex + 1, endIndex);
        } else
            return false;

    }
}
