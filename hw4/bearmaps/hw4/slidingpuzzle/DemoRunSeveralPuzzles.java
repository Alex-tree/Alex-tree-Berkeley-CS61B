package bearmaps.hw4.slidingpuzzle;

import bearmaps.hw4.AStarSolver;
import bearmaps.hw4.ShortestPathsSolver;
import bearmaps.hw4.SolutionPrinter;

/**
 * Showcases how the AStarSolver can be used for solving sliding puzzles.
 * Runs several puzzles in a row.
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Created by hug.
 */
public class DemoRunSeveralPuzzles {
    private static String[] basicPuzzles = {"C:\\Java\\hw4\\bearmaps\\hw4\\input\\BasicPuzzle1.txt", "C:\\Java\\hw4\\bearmaps\\hw4\\input\\BasicPuzzle2.txt",
        "C:\\Java\\hw4\\bearmaps\\hw4\\input\\BasicPuzzle3.txt", "C:\\Java\\hw4\\bearmaps\\hw4\\input\\BasicPuzzle4.txt"};

    private static String[] hardPuzzles = {"C:\\Java\\hw4\\bearmaps\\hw4\\input\\HardPuzzle1.txt", "C:\\Java\\hw4\\bearmaps\\hw4\\input\\HardPuzzle2.txt",
        "C:\\Java\\hw4\\bearmaps\\hw4\\input\\HardPuzzle3.txt"};

    private static String[] elitePuzzles = {"C:\\Java\\hw4\\bearmaps\\hw4\\input\\ElitePuzzle1.txt", "C:\\Java\\hw4\\bearmaps\\hw4\\input\\ElitePuzzle2.txt",
        "C:\\Java\\hw4\\bearmaps\\hw4\\input\\ElitePuzzle3.txt"};

    public static void main(String[] args) {

        String[] puzzleFiles = hardPuzzles;

        System.out.println(puzzleFiles.length + " puzzle files being run.");
        for (int i = 0; i < puzzleFiles.length; i += 1) {
            Board start = Board.readBoard(puzzleFiles[i]);
            int N = start.size();
            Board goal = Board.solved(N);

            BoardGraph spg = new BoardGraph();
            System.out.println(puzzleFiles[i] + ":");
            ShortestPathsSolver<Board> solver = new AStarSolver<>(spg, start, goal, 30);
            SolutionPrinter.summarizeOutcome(solver);
        }

    }
}
