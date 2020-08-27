package HW3;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int [] bucket=new int [M];
        int count=0;
        for (Oomage b:oomages){
            bucket[(b.hashCode()&0x7FFFFFFF)%M]+=1;
            count++;
        }
        for (int t : bucket){
            if (t>count/50&&t<count/2.5)
                return true;
            System.out.print(t+" ");
        }
        return false;
    }
}
