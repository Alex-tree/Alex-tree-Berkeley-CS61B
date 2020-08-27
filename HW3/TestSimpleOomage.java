package HW3;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        SimpleOomage ooA;
        SimpleOomage ooB;
        SimpleOomage ooC;
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        for(int t=0;t<255;t=t+5){
            ooA = new SimpleOomage(t,(t+5)%255,(t+10)%255);
            ooB = new SimpleOomage((t+5)%255,(t+10)%255,t);
            ooC = new SimpleOomage((t+10)%255,t,(t+5)%255);
            hashSet.add(ooA);
            assertNotEquals(ooA.hashCode(),ooB.hashCode());
            assertNotEquals(ooA.hashCode(),ooC.hashCode());
        }
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        SimpleOomage ooA3 = new SimpleOomage(5, 10, 20);
        assertEquals(ooA, ooA2);
        assertEquals(ooA2, ooA);
        assertEquals(ooA2, ooA3);
        assertEquals(ooA, ooA3);
        assertEquals(ooA, ooA);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
        //default hashcode only return the hashcode of memory address
        //The Java specification for equals mentions this danger as well:
        // “Note that it is generally necessary to override the hashCode method
        // whenever the equals method is overridden,
        // so as to maintain the general contract for the hashCode method,
        // which states that equal objects must have equal hash codes.”
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCodeSpread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
