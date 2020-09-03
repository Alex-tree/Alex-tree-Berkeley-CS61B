package Proj2AB.bearmaps;

import Proj2AB.bearmaps.Point.*;

import java.util.List;

import static Proj2AB.bearmaps.Point.distance;

public class NaivePointSet implements PointSet {
    private List<Point> P;

    public NaivePointSet(List<Point> points){
        P=points;
    }


    @Override
    public Point nearest(double x, double y) {
        double distance;
        Point find=new Point(x,y);
        distance=distance(P.get(0),find);
        int m=0;
        int index=0;
        for(Point t:P){
            double temp;
            temp=distance(t,find);
            if(temp<distance) {
                distance = temp;
                index=m;
            }
            m++;
        }
        return P.get(index);
    }

    public static void main(String[] args){

        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4,5);
        Point p4= new Point(3,3);
        Point p5= new Point(1,5);
        Point p6=new Point(4,4);
    NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3,p4,p5,p6));
    Point ret = nn.nearest(0, 7); // returns p2
    System.out.println(ret.getX()+" "+ret.getY()); // evaluates to 3.3

    }
}
