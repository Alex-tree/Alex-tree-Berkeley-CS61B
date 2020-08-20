package PJ1B;

import PJ1A.ALD;

public class ArrayDeque<item> implements Deque<item> {

    public item [] array;

    private int initial=10;
    public int pre=initial/2;
    public int next=initial/2+1;
    public int sizepre;
    public int sizenext;
    public static double rate=0.25;


    public ArrayDeque(){
        array=(item[])new Object[10];
        sizepre=0;
        sizenext=0;

    }
    public ArrayDeque(ArrayDeque other){
        array=(item[])new Object[other.array.length];
        System.arraycopy(other.array,0,array,0,other.array.length);
        pre=other.pre;
        next=other.next;
        sizepre=other.sizepre;
        sizenext=other.sizenext;
    }

    public void resize(int x){
        item [] a=(item [])new Object[x];
        System.arraycopy(array,pre-sizepre+1,a,a.length/2-sizepre,sizenext+sizepre);
        pre=a.length/2-1;
        next=pre+1;
        array=a;
    }

    @Override
    public void addLast(item x){
        if(sizenext>=(array.length-next))
            this.resize(array.length*2);
        array[sizenext+next]=x;
        sizenext++;
    }

    @Override
    public void addFirst(item x){
        if(sizepre>=(array.length-pre+1))
            this.resize(array.length*2);
        array[pre-sizepre]=x;
        sizepre++;

    }

    @Override
    public int getsize (){
        return sizenext+sizepre;
    }

    public void resizecut(int x){
        item [] a=(item[])new Object[x];
        System.arraycopy(array,pre-sizepre+1,a,0,sizenext+sizepre);
        pre=sizepre-1;
        next=pre+1;
        array=a;

    }


    @Override
    public void removeFirst(){
        if((sizepre+sizenext)<array.length*0.8) {
            array[pre-sizepre+1]=null;
            sizepre--;
            this.resizecut(sizenext + sizepre);
        }
        else {
            array[pre-sizepre+1]=null;
            sizepre--;
        }
    }


    @Override
    public void removeLast(){
        if((sizepre+sizenext)<array.length*0.8) {
            array[next + sizenext-1]=null;
            sizenext--;
            this.resizecut(sizenext + sizepre);
        }
        else {
            array[next + sizenext - 1]=null;
            sizenext--;
        }
    }

    @Override
    public item get(int x){
        return  this.array[pre-sizepre+x];
    }


    @Override
    public  void printDeque(){
        for(int t=1;t<sizepre+sizenext;t++ ){
            System.out.print(this.get(t)+" ");
        }
    }
    @Override
    public boolean isEmpty(int x){
        if(this.getsize()==0)
            return true;
        return false;
    }

    public static void main (String[] args){
        ArrayDeque <Integer> L=new ArrayDeque<>();
        L.addFirst(4);
        L.addFirst(3);
        L.addFirst(2);
        L.addFirst(1);
        L.addFirst(0);
        L.addLast(5);
        L.addLast(6);
        L.addLast(7);
        L.addLast(8);
        L.removeLast();
        L.removeFirst();
        L.removeLast();
        L.removeFirst();
        L.addLast(7);
        L.addLast(8);
        L.addFirst(1);
        L.addFirst(0);
        L.addFirst(0);
        L.addLast(9);
        L.addLast(9);
        L.removeLast();
        L.removeFirst();
        L.removeLast();
        L.removeFirst();
        L.printDeque();
        ArrayDeque<Integer> M=new ArrayDeque<>(L);

        M.printDeque();


    }


}
