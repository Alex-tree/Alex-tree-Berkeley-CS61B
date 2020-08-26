package HW2.hw2;
import edu.princeton.cs.introcs.StdRandom;
import  edu.princeton.cs.introcs.StdStats;
public class PercolationStats {

    private int time;
    private double[]result;
    public PercolationStats(int n,int T,PercolationFactory pf){
        result=new double[T];
        time=T;

        for(int i=0;i<T;i++) {
            Percolation t=pf.make(n);
            while (!t.percolates()) {
                int temprow = StdRandom.uniform(0, n);
                int tempcol = StdRandom.uniform(0,n);
                if(t.isOpen(temprow,tempcol))
                    continue;
                t.open(temprow,tempcol);
            }
            result[i]=(double)t.numberOfOpenSites()/(double)(n*n);
        }
    }

    public double mean(){
        double total_factor=0.0;
        for(double t:result){
            total_factor+=t;
        }
        return total_factor/time;
    }

    public double stddev(){
        double total_factor=0.0;
        double x=mean();
        double finalvalue=0.0;
        for(double t:result){
            total_factor+=Math.pow(t-x,2.0);
        }
        finalvalue=total_factor/(time-1);
        return Math.sqrt(finalvalue);
    }

    public double confidenceLow(){
        double mean=mean();
        double xige=stddev();
        double t= mean - 1.96*xige/Math.sqrt(time);
        return t;
    }

    public double confidenceHigh(){
        double mean=mean();
        double xige=stddev();
        double t= mean + 1.96*xige/Math.sqrt(time);
        return t;
    }
    public static void main(String[] args){
        PercolationStats test=new PercolationStats(30,30, new PercolationFactory());
        System.out.println(test.mean());
        System.out.println(test.stddev());
        System.out.println(test.confidenceLow());
        System.out.println(test.confidenceHigh());

    }


}
