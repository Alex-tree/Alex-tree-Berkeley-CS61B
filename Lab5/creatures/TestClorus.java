package creatures;

import huglife.*;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }
    @Test
    public void testReplicate(){
        Clorus c=new Clorus(2);
        Clorus coffspring=c.replicate();
        assertEquals(1,c.energy(),0.01);
        assertEquals(1,coffspring.energy(),0.01);
        c.replicate();
        assertEquals(0.5,c.energy(),0.01);
    }

    @Test
    public void testAttack(){
        Clorus c=new Clorus(2);
        Plip p =new Plip();
        c.attack(p);
        assertEquals(3,c.energy(),0.01);
        //assertEquals(0,p.energy(),0.01);
    }
    @Test
    public void testChoose() {
        // No empty adjacent spaces; stay.
        Clorus p = new Clorus(1);
        /*HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        //No empty with one plip
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> stay = new HashMap<Direction, Occupant>();
        stay.put(Direction.TOP, new Impassible());
        stay.put(Direction.BOTTOM, new Plip());
        stay.put(Direction.LEFT, new Impassible());
        stay.put(Direction.RIGHT, new Impassible());

        Action actual1 = p.chooseAction(stay);
        Action expected1 = new Action(Action.ActionType.STAY);
        assertEquals(expected1, actual1);*/


        //empty with plip
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> attack = new HashMap<Direction, Occupant>();
        attack.put(Direction.TOP, new Empty());
        attack.put(Direction.BOTTOM, new Plip());
        attack.put(Direction.LEFT, new Impassible());
        attack.put(Direction.RIGHT, new Impassible());

        Action actual2 = p.chooseAction(attack);
        Action expected2 = new Action(Action.ActionType.ATTACK,Direction.BOTTOM);
        assertEquals(expected2, actual2);

        //replicate
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> replicate = new HashMap<Direction, Occupant>();
        replicate.put(Direction.TOP, new Empty());
        replicate.put(Direction.BOTTOM, new Empty());
        replicate.put(Direction.LEFT, new Impassible());
        replicate.put(Direction.RIGHT, new Impassible());

        Action actual3 = p.chooseAction(replicate);
        Action unexpected3 = new Action(Action.ActionType.STAY);
        assertNotEquals(unexpected3, actual3);

        //stay
        p = new Clorus(0.8);
        HashMap<Direction, Occupant> stay = new HashMap<Direction, Occupant>();
        stay.put(Direction.TOP, new Plip());
        stay.put(Direction.BOTTOM, new Plip());
        stay.put(Direction.LEFT, new Plip());
        stay.put(Direction.RIGHT, new Plip());

        Action actual4 = p.chooseAction(stay);
        Action expected4 = new Action(Action.ActionType.STAY);
        assertEquals(expected4, actual4);

    }
}
