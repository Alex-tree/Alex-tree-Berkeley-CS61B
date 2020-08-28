package Lab8;

import java.util.*;

public class MyHashMap<K,V> implements Map61B {
    private int size = 0;
    private List<entry> hashtable;
    private entry num;
    private int initialSize=16;
    private double loadFactor=0.75;

    public MyHashMap(){
        this.hashtable= new ArrayList<>();
        for(int n = 0 ; n < initialSize;n++){
            entry value=new entry(null,0,null);
            hashtable.add(value);
        }
    }
    public MyHashMap(int t){
        this.hashtable= new ArrayList<>();
        this.initialSize=t;
        for(int n = 0 ; n < initialSize;n++){
            entry value=new entry(null,0,null);
            hashtable.add(value);
        }
    }
    public MyHashMap(int t, double f){
        this.hashtable= new ArrayList<>();
        this.initialSize=t;
        this.loadFactor=f;
        for(int n = 0 ; n < initialSize;n++){
            entry value=new entry(null,0,null);
            //create a sentinel to store size and point to actual data
            hashtable.add(value);
        }
    }

    public class entry{
        private K k;
        private Object value;
        private entry n;
        public entry(K k,Object v,entry n){
            this.k=k;
            this.n=n;
            value=v;
        }

    }

    @Override
    public void put(Object key, Object value) {
        if (size>loadFactor*initialSize)
            resize(initialSize*2);
        entry hashvalue=new entry((K)key,value,null);
        int bucketnumber=(key.hashCode()&0x7FFFFFFF)%initialSize;
        entry next= hashtable.get(bucketnumber);
            while (next.n!= null) {
                if (next.k!=null&&next.k.equals((K) key)) {
                    next.value = value;
                    return;
                }
                next = next.n;
            }

        if (next.k!=null&&next.k.equals((K) key)) {
            next.value = value;
            return;
        }
        next.n = hashvalue;
        size += 1;
        hashtable.get(bucketnumber).value = (int) hashtable.get(bucketnumber).value + 1;
    }


    private void resize(int t){
        List<entry> newone= new ArrayList<>();
        for(int n = 0 ; n < t;n++){
            if(n>initialSize-1)
                newone.add(new entry(null,0,null));
            else
                newone.add(hashtable.get(n));
        }
        initialSize=t;
        hashtable=newone;
    }


    @Override
    public void clear() {
        List<entry> newone=new ArrayList<>();
        for(int n=0;n<initialSize;n++){
            newone.add(new entry(null,0,null));
        }
        hashtable=newone;
        size=0;
    }

    @Override
    public boolean containsKey(Object key) {
        int bucketnumber = (key.hashCode() & 0x7FFFFFFF) % initialSize;
        entry check = hashtable.get(bucketnumber).n;
        while (check != null) {
            if (check.k.equals((K) key)) {
                return true;
            }
            check = check.n;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int bucketnumber = (key.hashCode() & 0x7FFFFFFF) % initialSize;
        entry get=hashtable.get(bucketnumber).n;
        while(get!=null){
            if(get.k.equals((K)key))
                return (V)get.value;
            get=get.n;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }



    @Override
    public Set keySet() {
        Set<K> ks=new HashSet<>();
        for(int n = 0; n < initialSize;n++) {
            entry getkey= hashtable.get(n).n;
            while (getkey!=null) {
                ks.add(getkey.k);
                getkey = getkey.n;
            }
        }
        return ks;
    }


    @Override
    public V remove(Object key) {
        int bucketnumber=(key.hashCode()&0x7FFFFFFF)%initialSize;
        entry m= hashtable.get(bucketnumber);
        V value;
        while(m!=null){
            if(m.n!=null&&m.n.k.equals((K)key)){
                value= (V) m.n.value;
                m.n=m.n.n;
                hashtable.get(bucketnumber).value=(int)hashtable.get(bucketnumber).value-1;
                size--;
                return value;
            }
            m=m.n;
        }
        return null;
    }

    @Override
    public V remove(Object key, Object value) {
        int bucketnumber=(key.hashCode()&0x7FFFFFFF)%initialSize;
        entry m= hashtable.get(bucketnumber);
        V value2;
        while(m!=null){
            if(m.n!=null&&m.n.k.equals((K)key)&&m.n.value.equals(value)){
                value2= (V) m.n.value;
                m.n=m.n.n;
                hashtable.get(bucketnumber).value=(int)hashtable.get(bucketnumber).value-1;
                size--;
                return value2;
            }
            m=m.n;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapiterator();
    }

    public class MyHashMapiterator implements Iterator<K>{
        private final Set<K> keyset;
        private int index=0;
        private int count=0;
        public MyHashMapiterator(){
            keyset=new HashSet<>();
            for(int n = 0; n < initialSize;n++) {
                entry getkey= hashtable.get(n).n;
                while (getkey!=null) {
                    this.keyset.add(getkey.k);
                    getkey = getkey.n;
                    count++;
                }

            }
        }

        @Override
        public boolean hasNext() {

            return index<count;
        }

        @Override
        public K next() {
            K temp= (K) keyset.toArray()[index];
            index++;
            return temp;
        }
    }

    public static void main (String[] args){
        MyHashMap<Integer,String> k=new MyHashMap<>(20);
        k.put(1,"a");
        k.put(2,"b");
        k.put(3,"c");
        k.put(4,"d");
        k.put(5,"f");
        k.put(6,"e");
        k.put(7,"q");
        k.put(8,"w");
        k.put(26,"e1");
        k.put(46,"e2");
        k.put(66,"e3");
        System.out.println(k.remove(66,"e3"));
    }
}
