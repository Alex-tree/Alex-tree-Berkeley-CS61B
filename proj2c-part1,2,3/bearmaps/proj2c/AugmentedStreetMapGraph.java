package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import edu.princeton.cs.algs4.TrieST;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private Map<Point,Long> mappingnodes= new HashMap<>();
    private Map<String,Node> p3=new HashMap<>();
    private TrieST<String> trieset= new TrieST();
    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
         List<Node> nodes = this.getNodes();
         for(Node n : nodes) {
             mappingnodes.put((new Point(n.lon(), n.lat())),n.id());
             if(n.name()!=null) {
                 trieset.put(cleanString(n.name()), n.name());
                 p3.put(cleanString(n.name()),n);
             }
         }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        List<Point> points=new ArrayList<>();
        for(Map.Entry<Point,Long> p : mappingnodes.entrySet())
            points.add(p.getKey());
        WeirdPointSet weirdset = new WeirdPointSet(points);
        Point cloest = weirdset.nearest(lon,lat);
        Long id = mappingnodes.get(cloest);
        while(neighbors(id).size()==0){
            mappingnodes.remove(cloest);
            points.remove(cloest);
            weirdset=new WeirdPointSet(points);
            cloest=weirdset.nearest(lon,lat);
            id=mappingnodes.get(cloest);
        }
        return id;
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        prefix = cleanString(prefix);
        List<String> result= new LinkedList<>();
        for(String s :trieset.keysWithPrefix(prefix))
            result.add(s);
        return result;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        locationName=cleanString(locationName);
        List<Map<String,Object>> result= new LinkedList<>();
        for(String s : trieset.keysThatMatch(locationName)){
            Map<String,Object> adding = new HashMap<>();
            adding.put("name",p3.get(s).name());
            adding.put("lat",p3.get(s).lat());
            adding.put("lon",p3.get(s).lon());
            adding.put("id",p3.get(s).id());
            result.add(adding);
        }
        return result;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
