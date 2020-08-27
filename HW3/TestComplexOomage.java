package HW3;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

  @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* TODO: Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */

    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();
        int N = 100;
        for (int i = 0; i < N; i += 1) {
            List<Integer> blast = new ArrayList<>();
            for (int w = 0; w < 5; w += 1)
                blast.add(StdRandom.uniform(100, 200));
            blast.add(1);// at here 1*256^4, every number higher than this position will be 0.
            // because 245^4 = 2^32, in computer system 4294967296=0;
            // the hashcode always same, no matter how many numbers it has
            blast.add(2);
            blast.add(3);
            blast.add(4);
            System.out.println(blast);
            deadlyList.add(new ComplexOomage(blast));
            System.out.println(deadlyList.get(i).hashCode());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
