package HW2.hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
    private WeightedQuickUnionUF map;
    private boolean [][] grid;
    private boolean BLOCKED=false;
    private boolean OPEN=true;
    private int size;

    public Percolation(int N){
        grid=new boolean[N][N];
        map=new WeightedQuickUnionUF(N*N+2);//two extra number as beginning and ending node
        for(int t=0;t<N;t++){
            map.union(t,N*N);
            for(int m=0;m<N;m++) {
                grid[t][m] = BLOCKED;
            }
        }

    }
    private int xyto1D(int t,int m,int N){
        return m+t*N;
    }

    public void open(int row, int col){
        /*** open a site, if there is site open around it, then connected them**/
        if(row> grid.length||col> grid.length)
            throw new IndexOutOfBoundsException();
        grid[row][col]=OPEN;
        size++;
        if (row+1< grid.length&&grid[row+1][col])
            map.union(xyto1D(row+1,col, grid.length),xyto1D(row,col, grid.length));
        if(col+1<grid.length&&grid[row][col+1]){
            map.union(xyto1D(row,col+1, grid.length),xyto1D(row,col, grid.length));
        }
        if(col-1>=0&&grid[row][col-1]){
            map.union(xyto1D(row,col-1, grid.length),xyto1D(row,col, grid.length));
        }
        if(row-1>=0&&grid[row-1][col]){
            map.union(xyto1D(row-1,col, grid.length),xyto1D(row,col, grid.length));
        }
        if(row== grid.length-1&&this.isFull(row,col)){
            //tackle backwash, only when the bottom grid is full, then connect it to the virtual node
            map.union(xyto1D(row,col, grid.length), grid.length* grid.length+1);
        }
    }
    public int numberOfOpenSites(){
        return size;
    }

    public boolean isOpen(int row,int col){
        if(row> grid.length||col> grid.length)
            throw new IndexOutOfBoundsException();
        return grid[row][col]==OPEN;
    }

    public boolean percolates(){
        /***whether virtual top site is connected to the virtual bottom site*/
        return map.connected(grid.length* grid.length, grid.length* grid.length+1);
    }

    public boolean isFull(int row, int col){
        if(row> grid.length||col> grid.length)
            throw new IndexOutOfBoundsException();
        return map.connected(xyto1D(row,col, grid.length), grid.length* grid.length);
    }

    public static void main(String[] args) {
        Percolation b = new Percolation(5);
        for (int t = 0; t < 5; t++) {
            System.out.println(b.map.connected(t, 25));
        }
        System.out.println();
        for (int t = 20; t < 25; t++) {
            System.out.println(b.map.connected(t, 26));
        }
        System.out.println();
        b.open(0,0);
        b.open(1,0);
        System.out.println(b.isFull(1,0));
        b.open(2,0);
        b.open(3,0);
        b.open(4,0);
        System.out.println(b.map.connected(20,26));
        System.out.println(b.map.connected(24,26));
        b.open(4,3);
        System.out.println(b.map.connected(22,26));
        System.out.println(b.map.connected(23,26));
        System.out.println(b.percolates());
    }

}
