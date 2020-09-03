package Proj2AB.bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;


public class ArrayHeapMinPQTest {
    @Test
    public void TestAdd(){
        ArrayHeapMinPQ<String> t = new ArrayHeapMinPQ<>();
        t.add("a",1.1);
        t.add("b",1.2);
        t.add("c",1.3);
        assertEquals(3,t.size());
        assertTrue(t.contains("a"));
    }
    @Test
    public void Testgetsmallest(){
        ArrayHeapMinPQ<String> t = new ArrayHeapMinPQ<>();
        t.add("a",1.1);
        t.add("b",1.2);
        t.add("c",1.3);
        assertEquals("a",t.getSmallest());
    }
    @Test
    public void TestResize(){
        ArrayHeapMinPQ<String> t = new ArrayHeapMinPQ<>();
        t.add("a",1.1);
        t.add("b",1.2);
        t.add("c",1.3);
        t.add("e",2.0);
        t.add("f",0.8);
        t.add("q",4.0);
        assertEquals(6,t.size());
        assertEquals("f",t.getSmallest());
        assertTrue(t.contains("q"));
    }

    @Test
    public void TestChangePriority(){
        ArrayHeapMinPQ<String> t = new ArrayHeapMinPQ<>();
        t.add("q",4.0);
        t.add("f",0.8);
        t.add("c",1.3);
        t.add("e",2.0);
        t.add("b",1.2);
        t.add("a",1.1);
        assertEquals("f",t.getSmallest());
        t.changePriority("q",0.1);
        assertEquals("q",t.getSmallest());
        t.changePriority("q",1.5);
        assertEquals("f",t.getSmallest());
    }
    @Test
    public void TestRemove(){
        ArrayHeapMinPQ<String> t = new ArrayHeapMinPQ<>();
        for(double w =0.0;w<5.0;w=w+0.2){
            t.add("hi"+w,w);
            t.contains("hi"+w);
            assertTrue(t.contains("hi"+w));
        }
        for(double w=0.0;w<5.0;w=w+0.2){
            String m= (String) t.removeSmallest();
            assertEquals("hi"+w,m);
        }
        for(double w =0.0;w<5.0;w=w+0.2){
            t.add("hi"+w,w);
            t.contains("hi"+w);
            assertTrue(t.contains("hi"+w));
        }
        t.add("a",-0.3);
        assertEquals("a",t.getSmallest());
        t.changePriority("a",1.1);
        assertEquals("hi0.0",t.getSmallest());
        t.changePriority("a",-0.5);
        assertEquals("a",t.removeSmallest());
        assertEquals("hi0.0",t.removeSmallest());
    }

}
