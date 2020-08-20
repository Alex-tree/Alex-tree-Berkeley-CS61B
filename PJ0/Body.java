
public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    String imgFileName;
    static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body d) {
        xxPos = d.xxPos;
        yyPos = d.yyPos;
        xxVel = d.xxVel;
        yyVel = d.yyVel;
        mass = d.mass;
        imgFileName = d.imgFileName;
    }

    public double calcDistance(Body a) {
        double dx = a.xxPos - xxPos;
        double dy = a.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Body a) {
        double r = calcDistance(a);
        return G * mass * a.mass / (r * r);
    }

    public double calcForceExertedByX(Body a) {
        double f = calcForceExertedBy(a);
        double dx = a.xxPos - xxPos;
        double r = calcDistance(a);
        return f * dx / r;

    }

    public double calcForceExertedByY(Body a) {
        double f = calcForceExertedBy(a);
        double dy = a.yyPos - yyPos;
        double r = calcDistance(a);
        return f * dy / r;
    }

    public double calcNetForceExertedByX(Body[] a) {
        double y = 0.0;
        for (Body b: a) {
            if (!this.equals(b)) {
                y += this.calcForceExertedByX(b);
            }
        }
        return y;
    }

    public double calcNetForceExertedByY(Body[] a) {
        double y = 0.0;
        for (Body t : a) {
            if (!this.equals(t)) {
                    y += this.calcForceExertedByY(t);
            }
        }
        return y;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public  void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}