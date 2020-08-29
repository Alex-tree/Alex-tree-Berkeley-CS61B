package Clab8;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    private final PriorityQueue<Flight> queue;

    public FlightSolver(ArrayList<Flight> flights) {
        Comparator<Flight>intcomparator= new Intcomparator();
        queue=new PriorityQueue<Flight>(flights.size(),intcomparator);
        queue.addAll(flights);
    }

    private static class Intcomparator implements Comparator<Flight>{
        public int compare(Flight x, Flight y ){
           return x.endTime()-y.endTime();
        }
    }

    public int solve() {
        //when start time< end time, add passenger
        int[] starttimeset=new int[queue.size()];
        int[] endtimeset= new int[queue.size()];
        int[] passangerset=new int[queue.size()];
        int[] resulpassanger=new int[queue.size()];
        for(int n=0;n< starttimeset.length;n++) {
            Flight t = queue.remove();
            starttimeset[n]=t.startTime;
            endtimeset[n] = t.endTime;
            passangerset[n]=t.passengers;
        }
        for(int n=0;n<starttimeset.length;n++){
            // this funciton run N times.
            int index=0;
            index=binarysearch(endtimeset,starttimeset[n],0,endtimeset.length);
            //binary search logN.
            while (index<=n)
                resulpassanger[n]+=passangerset[index++];
        }
        int max=0;
        for(int t:resulpassanger){
            if (t>max)
                max=t;
        }
        return max;
    }


    private static int binarysearch(int[] q, int search,int left,int right){
        int middle=(right-left)/2+left;
        if(search<q[0])
            return 0;
        if(q[middle]>search)
            return binarysearch(q,search,left,right/2);
        else{
            while (q[middle]<=search)
            {
                middle++;
            }
            return middle;
        }
    }

    }



