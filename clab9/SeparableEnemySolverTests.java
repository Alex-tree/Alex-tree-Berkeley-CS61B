import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class SeparableEnemySolverTests {

    @Test
    public void triangleEnemies() {
        Graph g = new Graph();
        g.connect("A", "B");
        g.connect("A", "C");
        g.connect("A", "D");
        g.connect("C", "D");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        for(Object t :solver.g.labels())
            System.out.println((String)t+" " +solver.g.neighbors((String) t));
        assertEquals(false, solver.isSeparable());
    }

    @Test
    public void disconnected() {
        Graph g = new Graph();
        g.connect("A", "B");
        g.connect("C", "D");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        for(Object t :solver.g.labels())
            System.out.println((String)t+" " +solver.g.neighbors((String) t));
        assertEquals(true, solver.isSeparable());
    }

    @Test
    public void disconnected2() {
        Graph g = new Graph();
        g.connect("A", "B");
        g.connect("C", "D");
        g.connect("E", "D");
        g.connect("E", "C");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        for(Object t :solver.g.labels())
            System.out.println((String)t+" " +solver.g.neighbors((String) t));
        assertEquals(false, solver.isSeparable());
    }

    @Test
    public void connected2() {
        Graph g = new Graph();
        g.connect("LP", "HS");
        g.connect("HS", "NH");
        g.connect("LP", "ZM");
        g.connect("ZM", "LT");
        g.connect("LT","MN");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        for(Object t :solver.g.labels())
            System.out.println((String)t+" " +solver.g.neighbors((String) t));
        assertEquals(true, solver.isSeparable());
    }

    @Test
    public void input1() throws FileNotFoundException {
        SeparableEnemySolver solver = new SeparableEnemySolver("C:\\Java\\clab9\\input\\party1");
        for(Object t :solver.g.labels())
            System.out.println((String)t+" " +solver.g.neighbors((String) t));
        assertEquals(true, solver.isSeparable());
    }

    @Test
    public void input2() throws FileNotFoundException {
        SeparableEnemySolver solver = new SeparableEnemySolver("C:\\Java\\clab9\\input\\party2");
        for(Object t :solver.g.labels())
            System.out.println((String)t+" " +solver.g.neighbors((String) t));
        assertEquals(true, solver.isSeparable());
    }

    @Test
    public void input3() throws FileNotFoundException {
        SeparableEnemySolver solver = new SeparableEnemySolver("C:\\Java\\clab9\\input\\party3");
        for(Object t :solver.g.labels())
            System.out.println((String)t+" " +solver.g.neighbors((String) t));
        assertEquals(false, solver.isSeparable());
    }

    @Test
    public void input4() throws FileNotFoundException {
        SeparableEnemySolver solver = new SeparableEnemySolver("C:\\Java\\clab9\\input\\party4");
        assertEquals(false, solver.isSeparable());
    }

}
