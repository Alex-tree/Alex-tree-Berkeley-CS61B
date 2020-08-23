package HW1.es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void constructorTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertEquals(0,arb.fillCount());
        assertEquals(10,arb.capacity());
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
    }
    @Test
    public void queueTest(){
        ArrayRingBuffer<Integer>arb=new ArrayRingBuffer<>(5);
        for (int i=0;i<5;i++){
            arb.enqueue(i);
        }
        assertTrue(arb.isFull());
        assertEquals(0,(int)arb.peek());
        assertEquals(0,(int)arb.dequeue());
        assertEquals(1,(int)arb.peek());
        assertEquals(1,(int)arb.dequeue());
        assertEquals(2,(int)arb.peek());
        assertEquals(2,(int)arb.dequeue());
        assertEquals(3,(int)arb.peek());
        assertEquals(3,(int)arb.dequeue());
        assertEquals(4,(int)arb.peek());
        assertEquals(4,(int)arb.dequeue());

    }


}
