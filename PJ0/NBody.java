
public class NBody {
        public static double readRadius(String fname){
        In in= new In(fname);
        int plants=in.readInt();
        double radius=in.readDouble();
        return radius;
    }
        public static Body[] readBodies(String fname){
        In in = new In(fname);
        int plants=in.readInt();
        double radius=in.readDouble();
        int count=0;
        Body [] plant=new Body[plants];
        while(count<plants){
            double xP=in.readDouble();
            double yP=in.readDouble();
            double xV=in.readDouble();
            double yV=in.readDouble();
            double m=in.readDouble();
            String img=in.readString();
            plant[count]=new Body(xP,yP,xV,yV,m,img);
            count++;
            }
        return plant;
        }


        public static void main (String[] args) {
            double T = 157788000.0;//Double.parseDouble(args[0]);
            double dt = 25000.0;//Double.parseDouble(args[1]);
            String filename = "data/planets.txt";//args[2];
            double radius = readRadius(filename);
            Body[] b = readBodies(filename);
            StdDraw.setScale(-radius,radius);
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Body t:b)
                t.draw();

            StdDraw.enableDoubleBuffering();

            double time=0.0;
            while(time<=T){
                double []xForces=new double [b.length];
                double []yForces=new double [b.length];
                for(int i=0;i<b.length;i++){
                    xForces[i]=b[i].calcNetForceExertedByX(b);
                    yForces[i]=b[i].calcNetForceExertedByY(b);
                }
                for(int i=0;i<b.length;i++){
                    b[i].update(dt,xForces[i],yForces[i]);
                }
                StdDraw.picture(0,0,"images/starfield.jpg");
               for(Body t:b)
                   t.draw();
                StdDraw.show();
                StdDraw.pause(10);
                time+=dt;
            }
            StdOut.printf("%d\n", b.length);
            StdOut.printf("%.2e\n",radius);
            for(int i=0;i<b.length;i++){
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",b[i].xxPos,b[i].yyPos,b[i].xxVel,b[i].yyVel,b[i].mass,b[i].imgFileName);
            }

        }
    }
