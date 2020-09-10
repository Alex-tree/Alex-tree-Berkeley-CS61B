import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Random R = new Random();
        Queue<Integer> test=new Queue<>();
        Queue<Integer> quicksort=new Queue<>();
        Stopwatch sw = new Stopwatch();
        for(int t=0; t <20000; t++)
            test.enqueue(R.nextInt(20000));
        quicksort=QuickSort.quickSort(test);
        while (quicksort.size()>1){
            int q1= quicksort.dequeue();
            assertTrue(q1<=quicksort.peek());
        }

        System.out.println("Total time elapsed for SelectionSort: " + sw.elapsedTime() +  " seconds.");
    }


    @Test
    public void testMergeSort() {
        Random R = new Random();
        Queue<Integer> test=new Queue<>();
        Queue<Integer> Mergetest=new Queue<>();
        Stopwatch sw = new Stopwatch();
        for(int t=0; t <20000; t++)
            test.enqueue(R.nextInt(20000));
        Mergetest=MergeSort.mergeSort(test);
        while (Mergetest.size()>1){
            int q1= Mergetest.dequeue();
            assertTrue(q1<=Mergetest.peek());
        }
        System.out.println("Total time elapsed for MergeSort: " + sw.elapsedTime() +  " seconds.");
    }

    @Test
    public void testSelectionSort(){
        Random R = new Random();
        Queue<Integer> test=new Queue<>();
        Queue<Integer> selectionsort=new Queue<>();
        Stopwatch sw = new Stopwatch();
        for(int t=0; t <20000; t++)
            test.enqueue(R.nextInt(20000));
        selectionsort=SelectionSort.SelectionSort(test);
        while (selectionsort.size()>1){
            int q1= selectionsort.dequeue();
            assertTrue(q1<=selectionsort.peek());
        }
        System.out.println("Total time elapsed for SelectionSort: " + sw.elapsedTime() +  " seconds.");
    }


    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
