package bearmaps.hw4;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;


public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionweight=0.0;
    private ArrayHeapMinPQ<Vertex> Fringe;
    private List<Vertex> solution=new LinkedList<>();
    private List<Vertex> solutionreverse=new LinkedList<>();
    private int numberofdequeue=0;
    private double timeSpent=0;
    private Map<Vertex,Double> disto1;
    private Map<Vertex, Vertex> edgeto1;
    private Map<Vertex,Double> h1;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        edgeto1=new HashMap<>();
        h1=new HashMap<>();
        Fringe=new ArrayHeapMinPQ<>(100);
        h1.put(start,input.estimatedDistanceToGoal(start,end));
        // set the 1st value in heuristic array
        disto1.put(start,0.0);
        edgeto1.put(start,null);

        helper(input,start,end,timeout);
    }

    private void helper(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        Stopwatch sw = new Stopwatch();
        if(start==null){ // remove all the item in the MinPQ but no one equals to end so it got null
            outcome=SolverOutcome.UNSOLVABLE;
            return;
        }
        if(start.equals(end)){
            solutionconstruct(end);
            for(int t = solution.size(); t>0;t--)
                solutionreverse.add(solution.get(t-1));
            outcome=SolverOutcome.SOLVED;
            solutionweight=disto1.get(end);
            timeSpent+=sw.elapsedTime();
            return;
        }
        if(timeSpent>timeout){
            outcome=SolverOutcome.TIMEOUT;
            return;
        }
        List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(start);
        //find the edges to the next node
        for (WeightedEdge<Vertex> e : neighborEdges) {
            //adding the estimate values from neighbor node
            //abs handle the negative value for integerHopPuzzle
            h1.put(e.to(),input.estimatedDistanceToGoal(e.to(),end));
            relax(e);
        }
        start=Fringe.removeSmallest();
        numberofdequeue++;
        timeSpent+=sw.elapsedTime();
        helper(input,start,end,timeout);
    }

    private void relax(WeightedEdge<Vertex> v) {
        Vertex p = v.from();
        Vertex q = v.to();
        double w = v.weight();
        if (!disto1.containsKey(q) || disto1.get(p) + w < disto1.get(q)) {
            edgeto1.put(q, p);
            disto1.put(q, disto1.get(p) + w);
            if (!Fringe.contains(q))
                Fringe.add(q, disto1.get(q) + h1.get(q));
            else
                Fringe.changePriority(q, disto1.get(q));
        }
    }

    private void solutionconstruct(Vertex v){
        if(edgeto1.get(v)==null) {
            solution.add(v);
            return;
        }
        solution.add(v);
        solutionconstruct(edgeto1.get(v));
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solutionreverse;
    }

    @Override
    public double solutionWeight() {
        return solutionweight;
    }

    @Override
    public int numStatesExplored() {
        return numberofdequeue;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
