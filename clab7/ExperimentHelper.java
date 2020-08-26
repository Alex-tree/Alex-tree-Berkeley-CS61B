package src.clab7;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        return countpath(N,0,1);
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double)countpath(N,0,1)/(double)N;
    }

    private static int countpath(int N,int t, int i){
       if(N-i<=0)
           return t*N;
        return t*i+countpath(N-i,t+1,i*2);
    }

    public static void changeInt(BST<Integer>T){
        int tmp=RandomGenerator.getRandomInt(5000);
        Integer k=T.getRandomKey();
        T.deleteTakingSuccessor(k);
        while (true){
            if(!T.contains(tmp)) {
                T.add(tmp);
                break;
            }
            tmp=RandomGenerator.getRandomInt(5000);
        }
    }
    public static void randomInt(BST<Integer>T){
        int tmp=RandomGenerator.getRandomInt(5000);
        Integer k=T.getRandomKey();
        T.deleteTakingRandom(k);
        while (true){
            if(!T.contains(tmp)) {
                T.add(tmp);
                break;
            }
            tmp=RandomGenerator.getRandomInt(5000);
        }
    }

    public static void main (String[] args){
        for(int i =0;i<9;i++)
            System.out.println(optimalAverageDepth(i));
    }
}
