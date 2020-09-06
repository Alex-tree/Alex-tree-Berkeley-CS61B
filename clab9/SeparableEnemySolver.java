import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SeparableEnemySolver {

    Graph g;

    /**
     * Creates a SeparableEnemySolver for a file with name filename. Enemy
     * relationships are biderectional (if A is an enemy of B, B is an enemy of A).
     */
    SeparableEnemySolver(String filename) throws java.io.FileNotFoundException {
        this.g = graphFromFile(filename);
    }

    /** Alterntive constructor that requires a Graph object. */
    SeparableEnemySolver(Graph g) {
        this.g = g;
    }

    /**
     * Returns true if input is separable, false otherwise.
     */
    public boolean isSeparable() {
        /** if graph is connected then no node can appear more than twice in neighbor
         * if graph is disconnected then only rectangle shape or a line can be divided into two groups
         */
        Set<String> Labels;
        Object [] S;
        Labels=g.labels();
        ArrayList<String> neighbor = new ArrayList<>();
        ArrayList<String> connect = new ArrayList<>();
        S=Labels.toArray();
        boolean connected=true;
        int node=0;

        isconnected((String)S[0],connect,0);
        if(connect.size()!=Labels.size())
            //from one node to all the potential neighbor smaller than full size, meaning it is unconnected
            connected=false;
        for (Object o : S) {
            Set<String> neighbors = g.neighbors((String) o);
            neighbor.addAll(neighbors);
        }
        for(Object o:S){
            int count=0;
            for (String s : neighbor) {
                if (((String) o).equals(s))
                    count++;
                if (count > 2)
                    return false;
            }
            if(count==2)
                node+=1;
        }
        if(node==3&&!connected)
            return false;
        return true;
    }
    private void isconnected(String s ,ArrayList<String> connect,int index){
        //return a set from random node to all its neighbor and neighbor's neighbor
        Set<String> neighbors = g.neighbors(s);
        for(Object t:neighbors)
        if(!connect.contains((String)t))
            connect.add((String)t);
        if(index==connect.size())
            return;
        isconnected(connect.get(index),connect,index+1);
    }


    /* HELPERS FOR READING IN CSV FILES. */

    /**
     * Creates graph from filename. File should be comma-separated. The first line
     * contains comma-separated names of all people. Subsequent lines each have two
     * comma-separated names of enemy pairs.
     */
    private Graph graphFromFile(String filename) throws FileNotFoundException {
        List<List<String>> lines = readCSV(filename);
        Graph input = new Graph();
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                for (String name : lines.get(i)) {
                    input.addNode(name);
                }
                continue;
            }
            assert(lines.get(i).size() == 2);
            input.connect(lines.get(i).get(0), lines.get(i).get(1));
        }
        return input;
    }

    /**
     * Reads an entire CSV and returns a List of Lists. Each inner
     * List represents a line of the CSV with each comma-seperated
     * value as an entry. Assumes CSV file does not contain commas
     * except as separators.
     * Returns null if invalid filename.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<List<String>> readCSV(String filename) throws java.io.FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        return records;
    }

    /**
     * Reads one line of a CSV.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(",");
        while (rowScanner.hasNext()) {
            values.add(rowScanner.next().trim());
        }
        return values;
    }

    /* END HELPERS  FOR READING IN CSV FILES. */

}
