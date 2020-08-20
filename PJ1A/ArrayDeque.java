package src.PJ1A;

public class ArrayDeque<item> implements ALD<item> {

    public item [] array;

    private int initial=10;
    private int size;
    private int front;// first entry of the array


    public ArrayDeque(){
        array=(item[])new Object[5];
        this.size=0;
        this.front=0;
    }
    public ArrayDeque(ArrayDeque other){
        array=(item[])new Object[other.array.length];
        System.arraycopy(other.array,0,array,0,other.array.length);
        size=other.size;
        front=other.front;
    }

    public void resize(int x){
        item [] old=this.array;
        this.array=(item [])new Object[x];
        int walk=this.front;
        for (int k =0;k<this.size;k++){
            array[k]=old[walk% old.length];
            walk=(walk+1)%old.length;
        }
        this.front=0;
    }

    @Override
    public void addLast(item x){
        if(size==this.array.length)
            this.resize(array.length*2);
        array[(front+size)%array.length]=x;
        size++;
    }

    @Override
    public void addFirst(item x){
        if(size==this.array.length)
            this.resize(array.length*2);
        if(size==0){
            array[front]=x;
        }
        else if((front-1)%array.length<0){
            front=array.length-1;
        }
        else
            front=(front-1)% array.length;
        array[front]=x;
        size++;

    }

    @Override
    public int getsize (){
        return size;
    }



    @Override
    public item removeFirst(){
        item t=null;
        if((size)<(int)(array.length*0.5))
            this.resize((int)(array.length*0.5));
        t=this.array[front];
        this.array[front]=null;
        front=(front+1)%array.length;
        size--;
        return t;
    }


    @Override
    public item removeLast(){
        item t=null;
        if(size<(int)(array.length*0.8))
            this.resize((int)(array.length*0.5));
        t=this.array[(front+size-1)%array.length];
        this.array[(front+size-1)%array.length]=null;
        size--;
        return t;
    }

    @Override
    public item get(int x){
        return  this.array[(front+x)% array.length];
    }


    @Override
    public  void printDeque(){
        for(int t=0;t<size;t++ ){
            System.out.print(this.get(t)+" ");
        }
        System.out.println();
    }

    public static void main (String[] args){
        ArrayDeque <Integer> d=new ArrayDeque<>();
        d.addFirst(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        System.out.println(d.get(2));
        d.printDeque();
        d.addFirst(6);
        System.out.println(d.removeFirst());
        System.out.println(d.removeLast());
        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());
        System.out.println(d.removeLast());
        System.out.println(d.removeFirst());
    }



}
