import sorting.*;

import org.junit.*;
import java.util.Random;

public class HybridSortTest {

    public final static int NUM_ITERS = 5500; // how many times to test it
    public final static int SIZE = 32; // number of elements in the list


    @Test
    public void randomHybridBetterThanQuickSort() {
        SortingAlgorithms listSorterQuick = new SortingAlgorithms();
        long randomTimeQuick = timeRandomSort(listSorterQuick, "randomizedQuick");

        SortingAlgorithms listSorterHybrid = new SortingAlgorithms();
        long randomTimeHybrid = timeRandomSort(listSorterHybrid, "hybrid");

        if (!hybridIsBetter(randomTimeQuick, randomTimeHybrid, "random")) {
            Assert.fail();
        }
    }

    @Test
    public void sortedHybridBetterThanQuickSort() {
        SortingAlgorithms listSorterQuick = new SortingAlgorithms();
        long sortedQuick = timeSortedSort(listSorterQuick, "randomizedQuick");

        SortingAlgorithms listSorterHybrid = new SortingAlgorithms();
        long sortedHybrid = timeSortedSort(listSorterHybrid, "hybrid");

//        hybridIsBetter(sortedQuick, sortedHybrid, "sorted");

        if (!hybridIsBetter(sortedQuick, sortedHybrid, "sorted")) {
            Assert.fail();
        }
    }

    @Test
    public void inverseHybridBetterThanQuickSort() {
        SortingAlgorithms listSorterQuick = new SortingAlgorithms();
        long inverseQuick = timeInverseSort(listSorterQuick, "randomizedQuick");

        SortingAlgorithms listSorterHybrid = new SortingAlgorithms();
        long inverseHybrid = timeInverseSort(listSorterHybrid, "hybrid");

//        hybridIsBetter(inverseQuick, inverseHybrid, "inverse");

        if (!hybridIsBetter(inverseQuick, inverseHybrid, "inverse")) {
            Assert.fail();
        }
    }

    @Test
    public void hybridBetterThanQuickSort() {
        SortingAlgorithms listSorterQuick = new SortingAlgorithms();

        long randomTimeQuick = timeRandomSort(listSorterQuick, "randomizedQuick");
        long sortedTimeQuick = timeSortedSort(listSorterQuick, "randomizedQuick");
        long inverseTimeQuick = timeInverseSort(listSorterQuick, "randomizedQuick");
        long totalTimeQuick = randomTimeQuick + sortedTimeQuick + inverseTimeQuick;


        SortingAlgorithms listSorterHybrid = new SortingAlgorithms();

        long randomTimeHybrid = timeRandomSort(listSorterHybrid, "hybrid");
        long sortedTimeHybrid = timeSortedSort(listSorterHybrid, "hybrid");
        long inverseTimeHybrid = timeInverseSort(listSorterHybrid, "hybrid");
        long totalTimeHybrid = randomTimeHybrid + sortedTimeHybrid + inverseTimeHybrid;

        if (!hybridIsBetter(totalTimeQuick, totalTimeHybrid, "overall")) {
            Assert.fail();
        }




    }

    // Helper methods ------------
    // Part 1 tests ------------------
    public static void testRandomizedQuicksort(){
        SortingAlgorithms listSorterQuick = new SortingAlgorithms();
        long randomTimeQuick = timeRandomSort(listSorterQuick, "randomizedQuick");
        long sortedTimeQuick = timeSortedSort(listSorterQuick, "randomizedQuick");
        long inverseTimeQuick = timeInverseSort(listSorterQuick, "randomizedQuick");

//        System.out.println("The total elapsed time of randomized quick sort on " + NUM_ITERS +  " randomly generated \n" +
//                "unsorted arrays of size " + SIZE + " was: " + durationQuicksort + "ms (" + (durationQuicksort * 1000) / NUM_ITERS + " microseconds per execution)");
//        System.out.println();
//        return durationQuicksort;
    }

    public static void testHybrid() {
        SortingAlgorithms listSorterHybrid = new SortingAlgorithms();
        long randomTimeHybrid = timeRandomSort(listSorterHybrid, "hybrid");
        long sortedTimeHybrid = timeSortedSort(listSorterHybrid, "hybrid");
        long inverseTimeHybrid = timeInverseSort(listSorterHybrid, "hybrid");
//        System.out.println("The total elapsed time of hybrid sort on " + NUM_ITERS + " randomly generated \n" +
//                "unsorted arrays of size " + SIZE + " was: " + durationHybridSort + "ms (" + (durationHybridSort * 1000) / NUM_ITERS + " microseconds per execution)");
//        System.out.println();

    }

    /**
     * Calculates total time for algorithm on random list
     * @param listSorter object of class SortingAlgorithms
     * @param methodName type of sort
     * @return total elapsed time
     */
    public static long timeRandomSort(SortingAlgorithms listSorter, String methodName) {
        Comparable[] random1 = new Comparable[SIZE]; // full random list
        Comparable[] random2 = new Comparable[SIZE]; // sublist of random list
        long totalTime = 0;

        Random r = new Random();
        for (int i = 0; i < NUM_ITERS; i++) {
            for (int j = 0; j < SIZE; j++) {
                random1[j] = r.nextInt(101);
                random2[j] = r.nextInt(101);


            }

            // Randomly generate low and high
            // Not trying to explore the full range of lowindex/highindex, just
            // some range
            int lowindex = r.nextInt(random2.length / 2);
            int highindex = random2.length / 2 + r.nextInt(random2.length / 2);
            // System.out.println("lowindex and highindex: " + lowindex + " " +
            // highindex);
            long start = System.currentTimeMillis();
            switch (methodName) {

                case "hybrid":
                    listSorter.hybridSort(random1, 0, random1.length - 1);
                    listSorter.hybridSort(random2, lowindex, highindex);
                    break;

                case "randomizedQuick":
                    listSorter.randomizedQuickSort(random1, 0, random1.length - 1);
                    listSorter.randomizedQuickSort(random2, lowindex, highindex);
                    break;
            }
            long elapsed = System.currentTimeMillis() - start;
            totalTime += elapsed;
        }
        return totalTime;
    }

    /**
     * Calculates total time for algorithm on sorted list
     * @param listSorter object of class SortingAlgorithms
     * @param methodName type of sort
     * @return total elapsed time
     */
    public static long timeSortedSort(SortingAlgorithms listSorter, String methodName) {
        Comparable[] sort1 = new Comparable[SIZE]; // full random list
        Comparable[] sort2 = new Comparable[SIZE]; // sublist of random list
        long totalTime = 0;

        Random r = new Random();
        for (int i = 0; i < NUM_ITERS; i++) {
            for (int j = 0; j < SIZE; j++) {
                sort1[j] = j;
                sort2[j] = j;
            }

            int lowindex = r.nextInt(sort2.length / 2);
            int highindex = sort2.length / 2 + r.nextInt(sort2.length / 2);

            long start = System.currentTimeMillis();
            switch (methodName) {

                case "hybrid":
                    listSorter.hybridSort(sort1, 0, sort1.length - 1);
                    listSorter.hybridSort(sort2, lowindex, highindex);
                    break;

                case "randomizedQuick":
                    listSorter.randomizedQuickSort(sort1, 0, sort1.length - 1);
                    listSorter.randomizedQuickSort(sort2, lowindex, highindex);
                    break;
            }
            long elapsed = System.currentTimeMillis() - start;
            totalTime += elapsed;
        }
        return totalTime;
    }

    /**
     * Calculates total time for algorithm on inverse sorted list
     * @param listSorter object of class SortingAlgorithms
     * @param methodName type of sort
     * @return total elapsed time
     */
    public static long timeInverseSort(SortingAlgorithms listSorter, String methodName) {
        Comparable[] inverse1 = new Comparable[SIZE]; // full random list
        Comparable[] inverse2 = new Comparable[SIZE]; // sublist of random list
        long totalTime = 0;

        Random r = new Random();
        for (int i = 0; i < NUM_ITERS; i++) {
            for (int j = SIZE - 1; j >= 0 ; j--) {
                inverse1[j] = j;
                inverse2[j] = j;
            }

            int lowindex = r.nextInt(inverse2.length / 2);
            int highindex = inverse2.length / 2 + r.nextInt(inverse2.length / 2);

            long start = System.currentTimeMillis();
            switch (methodName) {

                case "hybrid":
                    listSorter.hybridSort(inverse1, 0, inverse1.length - 1);
                    listSorter.hybridSort(inverse2, lowindex, highindex);
                    break;

                case "randomizedQuick":
                    listSorter.randomizedQuickSort(inverse1, 0, inverse1.length - 1);
                    listSorter.randomizedQuickSort(inverse2, lowindex, highindex);
                    break;
            }
            long elapsed = System.currentTimeMillis() - start;
            totalTime += elapsed;
        }
        return totalTime;
    }


    /**
     * Checks to see if hybrid sort outperformed randomized quicksort
     * @return true if hybrid sort performed better than quicksort
     */
    public static boolean hybridIsBetter(long durationQuicksort, long durationHybridSort, String listType) {
        long diff = durationQuicksort - durationHybridSort;
        if (durationHybridSort <= durationQuicksort) {
            System.out.println("Hybrid sort outperformed randomized quick sort for " + listType + " lists, great job!");
            System.out.println("Hybrid was faster by " + diff + "ms.");
            System.out.println();
            return true;
        }
        if (diff < 0) {
            System.out.println("Hybrid was not faster than quick sort for " + listType + " lists.");
            System.out.println("Hybrid was slower by " + diff + "ms.");
            System.out.println();
        }
        return false;
    }

}
