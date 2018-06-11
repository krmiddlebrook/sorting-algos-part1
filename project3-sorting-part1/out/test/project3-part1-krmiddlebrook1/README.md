**Hybrid Sort:** 

The hybrid sort algorithm is very similar to the quick sort algorithm.
Hybrid sort differs from quick sort by implementing insertion sort when 
the size of the sublist is less than 16. Hybrid sort, on average, out
performs quick sort when we have "small" lists. This is because quick sort
must sort many sublists when the list becomes small (i.e. the list size is less
than 16). 

**Tests:**

The tests include comparing the total elapsed time needed to sort random, sorted, and
inverse sorted lists. The most informative test is _hybridBetterThanQuick_, 
which compares the overall speed of both sorting methods on all three list
types. The tests sort each list type 5,500 times and returns the total elapsed time 
that passed between the start of the algorithm to the end of the algorithm, at which
point, the list has been sorted. Additionally, each list contains 32 elements.   
  