package src.PJ1A;
public class LinkedListDeque <instance> implements ALD<instance>{
    public class DList {
        public DList pre;
        public instance item;
        public DList next;

        public DList(DList p, instance i, DList n) {
            item = i;
            pre = p;
            next = n;
            this.pre = this;
            this.next = this;
        }
    }

    public DList sentinel;
    private int size;



    public LinkedListDeque() {
        sentinel = new DList(null, null, null);
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque<instance> other){
        sentinel = new DList(null, (instance)other.getRecursive(0), null);
        size=0;
        for(int t=1;t<=other.getsize();t++){
            this.addLast((instance)other.getRecursive(t));
        }
    }

    public LinkedListDeque(instance x) {
        sentinel = new DList(null, x, null);

        size = 0;
    }
    @Override
    public void addLast(instance x) {
        size += 1;
        DList t = new DList(null, x, null);
        if (size == 0) {
            sentinel.next = t;
            t.pre = sentinel;
            sentinel.pre = t.next;
            t.next = sentinel;
        } else {
            t.pre = sentinel.pre;
            sentinel.pre.next = t;
            t.next = sentinel;
            sentinel.pre = t;
        }
    }

    @Override
    public int getsize () {
        return size;
    }

    @Override
    public void addFirst(instance x) {
        size += 1;
        DList q = new DList(null, x, null);
        if (size == 0) {
            sentinel.next = q;
            q.pre = sentinel;
            sentinel.pre = q.next;
            q.next = sentinel;
        } else {
           q.next=sentinel.next;
           sentinel.next.pre=q;
           sentinel.next=q;
           q.pre=sentinel;
        }
    }
    @Override
    public instance removeFirst(){
        size--;
        instance t=sentinel.next.next.pre.item;
        sentinel.next.next.pre=sentinel;
        sentinel.next=sentinel.next.next;
        return t;
    }
    @Override
    public  void printDeque(){
        int m = this.getsize();
        for(int t=1;t<=m;t++) {
            System.out.print(this.get(t)+" ");
        }
    }

    @Override
    public instance removeLast(){
        size--;
        instance t=sentinel.pre.pre.next.item;
        sentinel.pre.pre.next=sentinel;
        sentinel.pre=sentinel.pre.pre;
        return t;
    }
    @Override
    public instance get(int x){
        DList temp=this.sentinel;
        if(x>size)
            x=size;
        for(int t=0;t<x;t++)
        {
            temp=temp.next;
        }
        return (instance)temp.item;
    }
    public DList helper(DList t,int index){
        if (index == 0)
            return t;
        else
            return helper(t.next,index-1);

    }

    public Object getRecursive(int index) {
        DList temp=this.sentinel;
        return helper(temp,index).item;
    }










}