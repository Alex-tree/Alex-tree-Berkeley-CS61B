package src.PJ1GA;
import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;


public class TestArrayDequeGold {
    public static String message="";

    public void add(double random,int i, StudentArrayDeque<Integer>s,ArrayDequeSolution<Integer>a){
        if(random<0.5){
            s.addFirst(i);
            a.addFirst(i);
            message+="addFirst("+i+")\n";
        }
        else {
            s.addLast(i);
            a.addLast(i);
            message+="addLast("+i+")\n";
        }
    }

   public boolean remove(double random, StudentArrayDeque<Integer>s,ArrayDequeSolution<Integer>a){
        Integer expected;
        Integer actual;
        if(random<0.5){
            expected=s.removeFirst();
            actual=a.removeFirst();
            message+="removeFirst()\n";
        }
        else{
            expected=s.removeLast();
            actual=a.removeLast();
            message+="removeLast()\n";
        }
       s.printDeque();
       System.out.println(a);
        if(expected!=actual){
            assertEquals(message,expected, actual);
            return false;
        }
        return true;
    }


    @Test
    public void TestDisagree() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ad1 = new ArrayDequeSolution<>();
        int i = 0;
        boolean t=true;
        while (t) {
            if (sad1.isEmpty()){
                Double random = StdRandom.uniform();
                add(random, i, sad1, ad1);
            }
            else {
                Double random1 = StdRandom.uniform();
                Double random2 = StdRandom.uniform();
                if (random1 < 0.5)
                    add(random2, i, sad1, ad1);
                else
                   t=remove(random2, sad1, ad1);
            }
            i++;
        }
    }


}



