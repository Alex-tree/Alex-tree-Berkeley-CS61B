package Proj2AB.bearmaps;

import java.util.ArrayList;
import edu.princeton.cs.introcs.Stopwatch;
import java.util.Random;
import org.junit.Test;



import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    private static Random r=new Random(500);
    @Test
    public void BasicNearestTest(){
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4,5);
        Point p4= new Point(3,3);
        Point p5= new Point(1,5);
        Point p6=new Point(4,4);
        NaivePointSet np=new NaivePointSet(List.of(p1,p2,p3,p4,p5,p6));
        KDTree tp=new KDTree(List.of(p1,p2,p3,p4,p5,p6));
        Point actual;
        Point expected;
        for(int t=1;t<1000;t++){
            Random r= new Random();
            double x = r.nextDouble()*t;
            double y = r.nextDouble()*t;
            Point goal= new Point(x,y);
            actual=np.nearest(x,y);
            expected=tp.nearest(goal);
            assertEquals(actual,expected);
        }
    }
    private  Point randomPoints(){
        double x= r.nextDouble();
        double y= r.nextDouble();
        return new Point(x,y);
    }
    private List<Point> randomPoints(int N){
        ArrayList<Point> points=new ArrayList<>();
        for(int i=0;i<N;i++){
            points.add(randomPoints());
        }
        return points;
    }

    private void StrongNearestTest(int pointcount, int query){
        List<Point> points=randomPoints(pointcount);
        NaivePointSet np=new NaivePointSet(points);
        KDTree tp=new KDTree(points);
        List<Point> goals=randomPoints(query);
        for(Point p : goals){
            Point expected=np.nearest(p.getX(),p.getY());
            Point actual=tp.nearest(p);
            assertEquals(actual,expected);
        }
    }
    @Test
    public void StrongNearestTest(){
        timewithNearestTestforKDTree(100000,20000);
        timewithNearestTestforNaivePointSet(100000,20000);
    }

    private void timewithNearestTestforKDTree(int pointcounts, int queries){
        List<Point> points=randomPoints(pointcounts);
        KDTree tp=new KDTree(points);
        Stopwatch sw=new Stopwatch();
        List<Point> goals=randomPoints(queries);
        for(Point p:goals){
            tp.nearest(p);
        }
        System.out.println("time elapsed for "+ pointcounts+" points and "+ queries+" queries is "+sw.elapsedTime());
    }
    private void timewithNearestTestforNaivePointSet(int pointcounts, int queries){
        List<Point> points=randomPoints(pointcounts);
        NaivePointSet np=new NaivePointSet(points);
        Stopwatch sw=new Stopwatch();
        List<Point> goals=randomPoints(queries);
        for(Point p:goals){
            np.nearest(p.getX(),p.getY());
        }
        System.out.println("time elapsed for "+ pointcounts+" points and "+ queries+" queries is "+sw.elapsedTime());
    }
}
