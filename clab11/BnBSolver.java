import java.util.LinkedList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    private List<Bear> equalbear = new LinkedList<>();
    private List<Bed> equalbed = new LinkedList<>();

//    public BnBSolver(List<Bear> bears, List<Bed> beds) {
//        for(Bear bear:bears){
//            for(Bed bed:beds){
//                if(bear.compareTo(bed)==0) {
//                    resultbears.add(bear);
//                    resultbeds.add(bed);
//                    continue;
//                }
//            }
//        }
//    }

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        int povit =(int)Math.random()*bears.size();
        Bedshelper(bears, beds, povit);
        return;
    }

    private void Bedshelper(List<Bear> bears, List<Bed> beds, int povit) {
        if(bears.size()<=1)
            return;
        Bear b = bears.get(povit);
        List<Bed> bedgreater = new LinkedList<>();
        List<Bed> bedless = new LinkedList<>();
        List<Bear> beargreater = new LinkedList<>();
        List<Bear> bearless = new LinkedList<>();
        Bed t = null;
        for (Bed bed : beds) {
            if (b.compareTo(bed) < 0)
                bedgreater.add(bed);
            else if (b.compareTo(bed) > 0)
                bedless.add(bed);
            else {
                equalbear.add(b);
                t = bed;
            }
        }

        for (Bear bear : bears) {
            if (t.compareTo(bear) < 0)
                beargreater.add(bear);
            else if (t.compareTo(bear) > 0)
                bearless.add(bear);
            else
                equalbed.add(t);
        }
//        System.out.println("bedshelper "+bedless+" || "+bedgreater+" || "+equalbear);
//        System.out.println("bearshelper "+bearless+" || "+beargreater+" || "+equalbed);
        Bedshelper(bearless,bedless,(int)Math.random() *bearless.size());
        Bedshelper(beargreater,bedgreater,(int)Math.random() *bearless.size());
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return equalbear;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return equalbed;
    }
}
