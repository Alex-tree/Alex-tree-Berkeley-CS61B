package src.lab6;

public class UnionFind {

    private int[] id;
    private int[] size;
    private int count;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        id=new int[n];
        count=n;
        size=new int[n];
        for(int i=0;i<n;i++) {
            id[i] = i;
            size[i]=1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex<0||vertex>id.length)
            throw new IllegalArgumentException(vertex+" larger than length or less than 0");
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
            return id[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        int t=id[v1];
        if(v1==t)
            return -1;
        return t;

    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        if(v1==v2)
        return find(v1)==find(v2);//change the internal structure of the data
        else{
            int rootv1=parent(v1);
            int finnalrootv1=v1;
            int rootv2=parent(v2);
            int finnalrootv2=v2;
            while(rootv1>0) {
                finnalrootv1=rootv1;
                rootv1 = parent(rootv1);
            }
            while(rootv2>0) {
                finnalrootv2=rootv2;
                rootv2 = parent(rootv2);
            }
            if(finnalrootv1==finnalrootv2)
                return find(v1)==find(v2);//if two values have been connected, then change the internal structure
            else
                return false;//do not connect.
        }
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if(connected(v1,v2))
            return;
        int rootv1=parent(v1);
        int finnalrootv1=v1;
        int rootv2=parent(v2);
        int finnalrootv2=v2;
        while(rootv1>0) {
            finnalrootv1=rootv1;
            rootv1 = parent(rootv1);
        }
        while(rootv2>0) {
            finnalrootv2=rootv2;
            rootv2 = parent(rootv2);
        }
        if(size[finnalrootv1]<=size[finnalrootv2]){
            id[finnalrootv1]=finnalrootv2;
            size[finnalrootv2]+=size[finnalrootv1];

        }
        else{
            id[finnalrootv2]=finnalrootv1;
            size[finnalrootv1]+=size[finnalrootv2];
        }
        count--;

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root=vertex;
        while(root!=id[root])
            root=id[root];
        while(vertex!=root){
            int newver=id[vertex];
            id[vertex]=root;
            vertex=newver;
        }
        return root;

    }

    public static void main(String[] args){
        UnionFind t=new UnionFind(10);
        t.union(0,1);
        t.union(0,2);
        t.union(1,3);
        t.union(4,5);
        t.union(5,6);
        t.union(7,8);
        t.union(8,9);
        t.union(6,9);
        t.union(0,4);
        t.connected(4,4);
    }

}
