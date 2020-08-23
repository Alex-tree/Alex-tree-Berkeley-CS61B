package HW1.es.datastructur.synthesizer;
import set.ArraySet;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

//Make sure to that this class and all of its methods are public
//Make sure to add the override tag for all overridden methods
//Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements  BoundedQueue<T>{
    /* Index for the next dequeue or peek. Least recently inserted element*/
    private int first;
    /* Index for the next enqueue. One beyond the most recently inserted item.*/
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
    }

    @Override
    public int capacity() {
        return this.rb.length;
    }

    @Override
    public int fillCount() {
        return this.fillCount;
    }
    /*
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (fillCount==capacity())
            throw new RuntimeException("Ring buffer overflow");
        rb[last%capacity()]=x;
        last=(last+1)%rb.length;
        fillCount++;
        //  Enqueue the item. Don't forget to increase fillCount and update
        //       last.

    }

    /*
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if(fillCount==0)
            throw new RuntimeException("Ring buffer underflow");
        T result=rb[first%rb.length];
        fillCount--;
        first=(first+1)%rb.length;
        // Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        return result;
    }

    /*
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount==0)
            throw new RuntimeException("Ring buffer underflow");
        // Return the first item. None of your instance variables should
        //       change.
        return rb[first];
    }

    public Iterator<T> iterator(){
        return new ArrayRingBufferiterator(first);
    }

    private class ArrayRingBufferiterator implements Iterator<T> {
        private int index;
        private int count;

        public ArrayRingBufferiterator(int start){
            index=start;
        }

        public boolean hasNext() {
            return count<rb.length;
        }

        @Override
        public T next() {
            T item = rb[index];
            index=(index+1)%rb.length;
            count++;
            return item;
        }

    }
    @Override
    public boolean equals(Object other){
        if(other.getClass()!=this.getClass())
            return false;
        if(this.capacity()!=((ArrayRingBuffer<?>) other).capacity())
            return false;
        for(int t =0;t<this.capacity();t++) {
            if (this.rb[t] != ((ArrayRingBuffer<?>) other).rb[t])
                return false;
        }
        return true;
    }
    public static void main(String[] args){
        ArrayRingBuffer<Integer>arb=new ArrayRingBuffer<>(5);
        for (int i=0;i<5;i++){
            arb.enqueue(i);
        }
        for (int i:arb){
            System.out.println(i);
        }
        ArrayRingBuffer<Integer>arb1=new ArrayRingBuffer<>(5);
        for (int i=0;i<5;i++){
            arb1.enqueue(i);
        }
        System.out.println(arb.equals(arb1));
        ArraySet<Integer>arb3=new ArraySet<Integer>();
        System.out.print(arb.equals(arb3));
    }
}
