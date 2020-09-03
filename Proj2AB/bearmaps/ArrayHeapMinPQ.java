package Proj2AB.bearmaps;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ {
    private double [] priorityarray;
    private T [] itemarray;
    private Set<Integer> key;
    private int size;
    public ArrayHeapMinPQ(){
        this.priorityarray= new double[5];
        this.itemarray=(T[])new Object[5];
        size=0;
    }

    @Override
    public void add(Object item, double priority) {
        if(size>((int)(0.75*priorityarray.length)))
            resize(priorityarray.length*2);
        priorityarray[size]=priority;
        itemarray[size]=(T)item;
        reorganize(size);
        size++;

    }
    private void reorganize(int n){
        int parent=parent(n);
        if(priorityarray[n]<priorityarray[parent]){
            double temp1=priorityarray[parent];
            T temp2 = itemarray[parent];
            priorityarray[parent]=priorityarray[n];
            itemarray[parent]=itemarray[n];
            priorityarray[n]=temp1;
            itemarray[n]=temp2;
            reorganize(parent);
        }
        return;
    }
    private int parent(int n){
        return (n-1)/2;
    }

    private void resize(int n){
        double [] new1=new double[n];
        T[] new2=(T [])new Object[n];
        System.arraycopy(priorityarray,0,new1,0,size);
        System.arraycopy(itemarray,0,new2,0,size);
        priorityarray=new1;
        itemarray=new2;
    }

    @Override
    public boolean contains(Object item) {
        int t=0;
        int m=size;
        while(t<=m){
            if (itemarray[t].equals((T)item)||itemarray[m-1].equals((T)item))
                return true;
            t++;
            m--;
        }

        return false;
    }

    @Override
    public Object getSmallest() {
        return itemarray[0];
    }

    @Override
    public Object removeSmallest() {
        if(size==0)
            throw new IllegalArgumentException();
        T result=itemarray[0];
        itemarray[0]=itemarray[size-1];
        priorityarray[0]=priorityarray[size-1];
        itemarray[size-1]=null;
        priorityarray[size-1]=0.0;
        size--;
        reorganizechild(0);
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(Object item, double priority) {
        int t=0;
        int m=size;
        int index=0;
        while(t<=m){
            if (itemarray[t].equals((T)item))
            {
                index=t;
                break;
            }
            if(itemarray[m-1].equals((T)item)){
                index=m-1;
                break;
            }
            t++;m--;
        }
        priorityarray[index]=priority;
            change(index);
    }

    private void change(int n){
        if(priorityarray[n]<priorityarray[parent(n)])
            reorganize(n);
        else if (priorityarray[n]>priorityarray[leftchild(n)]||priorityarray[n]>priorityarray[rightchild(n)])
            reorganizechild(n);

    }

    private void reorganizechild(int n){
        int left=leftchild(n);
        int right=rightchild(n);
        if(size==2){
            //only have one child, right child always larges than size
            if(priorityarray[0]>priorityarray[1]){
                T temp=itemarray[0];
                double temp2=priorityarray[0];
                itemarray[0]=itemarray[1];
                priorityarray[0]=priorityarray[1];
                itemarray[1]=temp;
                priorityarray[1]=temp2;
            }
        }
        // when parent is smaller than both kids, or do not have right child.
        if(priorityarray[n]<=priorityarray[left]&&priorityarray[n]<=priorityarray[right]||right>=size)
            return;
        //choice the smaller child
        else if(priorityarray[n]>priorityarray[left]&&priorityarray[left]<=priorityarray[right]){
            double temp1=priorityarray[left];
            T temp2 = itemarray[left];
            priorityarray[left]=priorityarray[n];
            itemarray[left]=itemarray[n];
            priorityarray[n]=temp1;
            itemarray[n]=temp2;
            reorganizechild(left);
        }
        else if(priorityarray[n]>priorityarray[right]){
            double temp1=priorityarray[right];
            T temp2 = itemarray[right];
            priorityarray[right]=priorityarray[n];
            itemarray[right]=itemarray[n];
            priorityarray[n]=temp1;
            itemarray[n]=temp2;
            reorganizechild(right);
        }
    }
    private int leftchild(int n){
        return n*2+1;
    }
    private int rightchild(int n){
        return n*2+2;
    }
}
