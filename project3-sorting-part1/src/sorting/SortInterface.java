package sorting;

/** An interface that describes several algorithms for sorting a list */
public interface SortInterface {

    public void insertionSort(Comparable[] array, int lowindex, int highindex,
                              boolean reversed);

    public void iterativeMergeSort(Comparable[] array);

    public void heapSort(Comparable[] array, int lowindex, int highindex, boolean 	reversed);

    public void randomizedQuickSort(Comparable[] array, int lowindex, int 	highindex);

    public void hybridSort(Comparable[] array, int lowindex, int highindex);

    public void printList(Comparable[] array, String sortType);
}
