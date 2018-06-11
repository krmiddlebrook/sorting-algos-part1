package sorting;
import java.lang.Math;
public class MaxHeap {
    private Comparable[] heap; // the array to heap sort
    private int low; // the starting index of sublist
    private int high; // the ending index of sublist
    private int size; // the current number of elements in the array
    private boolean descending; // sort heap in descending order if true

    /**
     * Constructor
     *
     * @param arr the array we want to sort
     * @param lowIndex the beginning index of sublist
     * @param highIndex the ending index of sublist
     */
    public MaxHeap(Comparable[] arr, int lowIndex, int highIndex, boolean order) {
        heap = arr;
        size = highIndex - lowIndex + 1;
        low = lowIndex;
        high = highIndex;
        descending = order;

    }

    /**
     * Sorts the sublist of a given list (from low to high) using in-place heap sort
     *
     */
    public Comparable[] buildHeapSort() {
        // build a valid heap from the sublist
        for (int i = (int) Math.floor(high/2); i >= low; i--) {
            pushdown(i);
        }
        // sort the heap
        for (int i = high; i >= low; i--) {
            removeMax();
        }

        return heap;
    }

    /** Print the array that stores the heap */
    public void print() {
        int i;
        for (i = 1; i <= size; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }

    /**
     * Remove maximum element (it is at the top of the maxHeap)
     * and place it at the end of the heap. Finally, build valid heap.
     */
    private void removeMax() {
        swap(low, high); // swap the end of the heap into the root
        // fix the heap property - push down as needed
        high--; // removed the end of the heap
        if (high != low) {
            pushdown(low); // move the root to the proper place in the tree and fix the tree
        }
    }

    /** Push the value down the heap if it does not satisfy the heap property
     *
     * @param position the index of the element to push down
     */
    private void pushdown(int position) {
        int largestChild = position; // index of the parent node
        while (!isLeaf(position)) {
            int leftChild = leftChild(position - 1);
            int rightChild = rightChild(position - 1);
            if (low == 0) {
                leftChild = leftChild(position);
                rightChild = rightChild(position);
            }

            // if the left child is larger than the right child than left child is largest child
            if ((leftChild <= high) && (heap[leftChild].compareTo(heap[largestChild]) > 0)) {
                largestChild = leftChild;
            }
            // if the right child is in range and larger than left child, largest child is right child
            if ((rightChild <= high) && (heap[rightChild].compareTo(heap[largestChild]) > 0)) {
                largestChild = rightChild;
            }

            // swap the parent with the largestChild if largestChild is greater than parent
            if (largestChild != position) {
                swap(position, largestChild);
                position = largestChild;
            }
            // the parent is larger than children, break loop
            else {
                return;
            }
        }
    }

    /**
     * Swap given elements: one at index pos1, another at index pos2
     *
     * @param pos1 the index of the first element in the heap
     * @param pos2 the index of the second element in the heap
     */
    private void swap(int pos1, int pos2) {
        Comparable tmp;
        tmp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tmp;
    }

    /** Returns true if the node in a given position is a leaf
     *
     * @param pos the index of the element in the heap array
     * @return true if the node is a leaf, false otherwise
     */
    private boolean isLeaf(int pos) {
        return ((pos > Math.floorDiv(high, 2)) && (pos <= high));
    }

    /** Return the index of the left child of the element at index pos
     *
     * @param pos the index of the element in the heap array
     * @return the index of the left child
     */
    private int leftChild(int pos) {
        return 2 * pos + 1;
    }

    /** Return the index of the right child
     *
     * @param pos the index of the element in the heap array
     * @return the index of the right child
     */
    private int rightChild(int pos) {
        return 2 * pos + 2;
    }
}
