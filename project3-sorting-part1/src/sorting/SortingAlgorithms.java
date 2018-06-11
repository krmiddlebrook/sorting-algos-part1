package sorting;
import java.util.Random; // to generate random indice values for quicksort

/**  A class that implements SortInterface. Has various methods
 *   to sort an array of Comparable-s */
public class SortingAlgorithms  implements SortInterface {

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using insertion sort
     * @param array array of Comparable-s
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     * @param reversed if true, the list should be sorted in descending order
     */
    @Override
    public void insertionSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        Comparable curr;
        int j;
        // sorts sublist in ascending order
        if (!reversed) {
            for (int i = lowindex + 1; i <= highindex; i++) {
                curr = array[i];
                j = i - 1;
                while (j >= lowindex && array[j].compareTo(curr) > 0) {
                    array[j + 1] = array[j]; // shift elems to the right
                    j--;
                }
                array[j + 1] = curr;
            }
        }
        // sorts sublist in descending order
        else {
            for (int i = lowindex; i <= highindex; i++) {
                curr = array[i];
                j = i -1;
                while (j >= lowindex && array[j].compareTo(curr) < 0) {
                    array[j + 1] = array[j]; // shifts elems to the left
                    j--;
                }
                array[j + 1] = curr;
            }
        }
    }

    /**
     * Sorts a given array of 2^k elements using iterative
     * (non-recursive) merge sort.
     * @param array array to sort
     */
    @Override
    public void iterativeMergeSort(Comparable[] array) {
        int n = array.length;
        int right;
        int rightEnd;
        int i;
        int j;
        int index;
        Comparable[] tmp = new Comparable[n];

        for (int step = 1; step < n; step *=2) {
            for (int left = 0; left + step < n; left += step * 2) {
                right = left + step;
                rightEnd = right + step;
                index = left;
                i = left;
                j = right;

                // compared elements in left list and right list and insert smaller element into tmp array
                while (i < right && j < rightEnd) {
                    if (array[i].compareTo(array[j]) <= 0) {
                        tmp[index] = array[i];
                        i++;
                    } else {
                        tmp[index] = array[j];
                        j++;
                    }
                    index++;
                }

                // all elems in right list have been inserted into tmp, insert remaining left elems into tmp
                while (i < right) {
                    tmp[index] = array[i];
                    i++;
                    index++;
                }

                // all elems in left list have been inserted into tmp, insert remaining right elems into tmp
                while (j < rightEnd) {
                    tmp[index] = array[j];
                    j++;
                    index++;
                }

                // copy sorted sublists to OG array
                for (index = left; index < rightEnd; index++) {
                    array[index] = tmp[index];
                }
            }
        }
    }


    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using in-place heap sort
     *
     * @param array array to sort
     * @param lowindex  the beginning index of a sublist
     * @param highindex the end index of a sublist
     * @param reversed If true, the list should be sorted in descending order.
     */
    @Override
    public void heapSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        MaxHeap heap =  new MaxHeap(array, lowindex, highindex, reversed);
        heap.buildHeapSort(); // uses in-place heap sort to sort the sublist
    }

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using the randomizedQuickSort
     * @param array array to sort
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     */
    @Override
    public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex) {
        int pivot; // index of the pivot
        if (lowindex < highindex) {
            pivot = partition(array, lowindex, highindex);
            randomizedQuickSort(array, lowindex, pivot - 1);
            randomizedQuickSort(array, pivot + 1, highindex);
        }
    }

    /**
     * Helper method for quickSort.
     * @param arr array of comparables
     * @param low the starting value of i
     * @param high the starting value of j
     * @return
     */
    private int partition(Comparable[] arr, int low, int high) {
        Comparable pivot;
        Comparable tmp;
        int max = high;
        int median = getMedianIndex(arr, low, high); // returns the index of the median value

        // swaps elem at pivot with last elem in list
        tmp = arr[median];
        arr[median] = arr[high];
        arr[high] = tmp;
        pivot = arr[high];
        low--;
        do {
            while ((low < high) && (arr[++low].compareTo(pivot) < 0)) // increments low index when low elem is smaller than pivot
                ;
            while ((low < high) && (arr[--high].compareTo(pivot) > 0)) // decrements high index when high elem is larger than pivot
                ;
            // swap values at low and high
            tmp = arr[low];
            arr[low] = arr[high];
            arr[high] = tmp;
        } while (low < high);

        // swap pivot elem with elem at low index
        tmp = arr[low];
        arr[low] = arr[max];
        arr[max] = tmp;
        return low;
    }

    /**
     * Helper method for partition. Generates three random indices and finds the index
     * of the median value.
     * @param arr array of comparables
     * @param lowIndex starting index of the sublist
     * @param highIndex ending index of the sublist
     * @return index of the median
     */
    private int getMedianIndex(Comparable[] arr, int lowIndex, int highIndex) {
        int[] randomIndices = new int[3]; // to store the random indices
        Comparable[] randomElems = new Comparable[3]; // to store the elements at each random index
        Comparable medianElem; // to store the median element
        int medianIndex = 0; // to store the median index

        for (int i = 0; i < randomIndices.length; i++) {
            randomIndices[i] = generateRandom(lowIndex, highIndex); // add random integers to the randoms array
            randomElems[i] = arr[randomIndices[i]]; // add element at the random index to randomElems
        }
        // sorts randomElems list using insertion sort method
        int j;
        for (int i = 1; i < randomElems.length; i++) {
            j = i - 1;
            while (j >= 0 && randomElems[j].compareTo(randomElems[i]) > 0) {
                randomElems[j + 1] = randomElems[j]; // shifts larger elems to the right
                j--;
            }
            randomElems[j + 1] = randomElems[i]; // insert first elem of unsorted list into the sorted list at the appropriate spot
        }

        if (randomIndices[2] == 0) { // checks if sublist only contains 2 elements
            Random r = new Random();
            medianElem = randomElems[r.nextInt(2)]; // randomly pick medianElem
        } else {
            medianElem = randomElems[1]; // assigns the middle element to medianElem
        }

        for (int i = 0; i < randomIndices.length; i++) { // finds the index of the median element
            if (arr[randomIndices[i]].compareTo(medianElem) == 0) {
                medianIndex = randomIndices[i];
                return medianIndex;
            }
        }
        return medianIndex;
    }

    /**
     * Generates a random number within range (range = highIndex - lowIndex).
     * @param lowIndex minimum integer value to generate
     * @param highIndex maximum integer value to generate
     * @return a random integer between lowIndex (inclusive) and highIndex (inclusive)
     */
    private int generateRandom(int lowIndex, int highIndex) {
        Random random = new Random();
        return random.nextInt((highIndex - lowIndex) + 1) + lowIndex;
    }

    /**
     * Sorts a given sublist using hybrid sort, where the list is sorted
     * using randomized quick sort; when sublists get small (size=10 ?),
     * they are sorted using insertion sort.
     * @param array array of Comparable-s to sort
     * @param lowindex the beginning index of the sublist
     * @param highindex the end index of the sublist
     */
    @Override
    public void hybridSort(Comparable[] array, int lowindex, int highindex) {
        int pivot; // index of the pivot
        int size = highindex - lowindex + 1;
        if (lowindex < highindex && size > 16) {
            pivot = partition(array, lowindex, highindex);
            randomizedQuickSort(array, lowindex, pivot - 1);
            randomizedQuickSort(array, pivot + 1, highindex);
        }
        insertionSort(array, lowindex, highindex, false);
    }

    /**
     *  Prints all elements of the list
     *  @param array the array to print
     *  @param sortType string with the name of the sort method used on the list
     */
    public void printList(Comparable[] array, String sortType) {
        System.out.println("-----------" + sortType + "-----------");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();
    }

}
