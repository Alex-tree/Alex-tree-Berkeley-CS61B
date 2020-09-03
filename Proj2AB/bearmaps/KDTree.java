package Proj2AB.bearmaps;

import java.util.List;

import static Proj2AB.bearmaps.Point.distance;

public class KDTree{

    private static class Node {
        private Node left;
        private Node right;
        private Point p;
        private int depth=0;
        private Node(int m, Point p){
            depth=m;
            this.p=p;
        }
        private int getdepth(){
            return depth;
        }
        private double getX(){
            return p.getX();
        }
        private double getY(){
            return p.getY();
        }
    }
    public Node root;

    public KDTree(List<Point> points){
        root= new Node(0, points.get(0));
        for (int m=1;m<points.size();m++) {
            PutPoints(root.getdepth(),points.get(m),root);
        }
    }
    private Node PutPoints(int  d,Point p, Node x){
        if(x==null) {
            return new Node(d, p);
        }
        if(x.getdepth()%2==0) {
            double cmp = 0.0;
            cmp = Double.compare(x.getX(), p.getX());
            if(cmp>0)
                x.left=PutPoints(d+1,p,x.left);
            else if(cmp<0||(cmp==0&&Double.compare(x.getY(),p.getY())!=0))// larger than and not equal
                x.right=PutPoints(d+1,p,x.right);
            else if(cmp==0&&Double.compare(x.getY(),p.getY())==0)// larger than and equal
                x.p=p;
        }
        if(x.getdepth()%2==1){
            double cmp=0.0;
            cmp=Double.compare(x.getY(),p.getY());
            if(cmp>0)
                x.left=PutPoints(d+1,p,x.left);
            else if(cmp<0||(cmp==0&&Double.compare(x.getX(),p.getX())!=0))// larger than and not equal
                x.right=PutPoints(d+1,p,x.right);
            else if(cmp==0&&Double.compare(x.getX(),p.getX())==0)// larger than and equal
                x.p=p;
        }
        return x;
    }

    public Point nearest(Point goal){
        Node best=root;
        return nearest(root,goal,best).p;
    }


    private Node nearest(Node n, Point goal,Node best) {
        if(n==null)
            return best;
        double com=0.0;
        com=Double.compare(distance(n.p,goal),distance(best.p,goal));
        if(com<0)
            best=n;
        Node goodside;
        Node badside;
        if(n.getdepth()%2==0) {
            if (goal.getX() < n.getX()) {
                goodside = n.left;
                badside = n.right;
            } else {
                goodside = n.right;
                badside=n.left;
            }
        }
        else{
            if (goal.getY() < n.getY()) {
                goodside = n.left;
                badside = n.right;
            } else {
                goodside = n.right;
                badside=n.left;
            }
        }
        best=nearest(goodside,goal,best);
        double radio=distance(best.p, goal);
        if(n.getdepth()%2==0) {
            double x;
            x = Math.pow(Math.abs(n.getX() - goal.getX()),2);// because radio is quadratic
            if (Double.compare(radio,x)>=0)//badside p is in the circle
                best = nearest(badside, goal, best);
        }
        else
        {
            double y;
            y = Math.pow(Math.abs(n.getY() - goal.getY()),2);
            if (Double.compare(radio,y)>=0)
                best = nearest(badside, goal, best);
        }
        return best;
    }

    public static void main(String[] args){
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p8 = new Point(4, 2);
        Point p3 = new Point(4,5);
        Point p4= new Point(3,3);
        Point p5= new Point(1,5);
        Point p6=new Point(4,4);
        KDTree nn = new KDTree(List.of(p1, p2, p8,p3,p4,p5,p6));
        Point goal=new Point(0,7);
        Point result=nn.nearest(goal);
        System.out.print(result.toString());
    }
}
