package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;


public class Clorus extends Creature {
    private int r=34;
    private int g=0;
    private int b=231;
    private double movelost=0.03;
    private double staylost=0.01;

    public Clorus(double e){
        super("clorus");
        energy=e;
    }
    public Color color(){
        return color(r,g,b);
    }
    public void attack(Creature c) {
        energy+=c.energy();
    }


    public void move() {
        energy-=movelost;
    }

    public void stay() {
        energy-=staylost;
    }


    public Clorus replicate() {
        double babyclours=0.5*energy;
        energy*=0.5;
        return new Clorus(babyclours);
    }
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipneighbors = new ArrayDeque<>();
        for(Direction t:neighbors.keySet()){
            if(neighbors.get(t).name().equals("empty"))
                emptyNeighbors.addFirst(t);
            else if(neighbors.get(t).name().equals("plip"))
                plipneighbors.addFirst(t);
        }


        if(emptyNeighbors.isEmpty()){
            return new Action(Action.ActionType.STAY);
        }

        else if(!plipneighbors.isEmpty()){
                int k= new Random().nextInt(plipneighbors.size());
                for(int j=0;j<k-1;j++)
                    plipneighbors.removeFirst();
                    Direction dir=plipneighbors.removeFirst();
                    return new Action(Action.ActionType.ATTACK,dir);
            }
        else if (this.energy>=1.0){
            int k=new Random().nextInt(emptyNeighbors.size());
            for(int i=0; i<k-1; i++){
                emptyNeighbors.removeFirst();
            }
            Direction dir=emptyNeighbors.removeFirst();
            return new Action(Action.ActionType.REPLICATE,dir);
        }
       int k=new Random().nextInt(emptyNeighbors.size());
        for(int i=0;i<k-1;i++)
            emptyNeighbors.removeFirst();
        Direction dir=emptyNeighbors.removeFirst();
        return new Action(Action.ActionType.MOVE,dir);
    }

}
