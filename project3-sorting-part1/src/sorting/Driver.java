package sorting;

public class Driver {
    public static void main(String[] args) {
        Comparable[] arr = {6, 17, 1, 8, 2, 9, 14, 3};
        SortInterface algo = new SortingAlgorithms();
//        algo.insertionSort(arr, 2, arr.length - 1, true);
//        algo.printList(arr, "Insertion Sort");



        // create another array, test another range of indices, etc.
        // test other methods


        Comparable[] array = {38,35, 38, 31, 29, 36, 38, 27, 27, 9, 19, 35, 31, 34, 30, 23, 16, 1, 2, 8, 4, 10, 6, 29, 8, 17, 17, 22, 6, 16, 9, 26};

        algo.heapSort(arr, 0, arr.length-1, false);
        algo.printList(arr, "Heap Sort");
//        algo.printList(array, "Heap Sort");
//        algo.insertionSort(array, 4, 15, true);
//        algo.printList(array, "Insertion Sort");



//        System.out.println("-------Merge Sort---------");
//        Comparable[] iterMerge = {6, 17, 1, 8, 2, 9, 14, 3};
//
//        algo.iterativeMergeSort(iterMerge);

//        algo.randomizedQuickSort(arr, 0, arr.length - 1);
//        algo.printList(arr, "Randomized Quicksort");
    }
}
