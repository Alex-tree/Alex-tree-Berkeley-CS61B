package lab7_treemap;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class BSTMap<K extends Comparable<K>,V>  implements Map61B<K,V> {
    private Node root;

    private class Node{
        private K k;
        private V value;
        private int size;
        private Node left;
        private Node right;

        public Node(K k, V v, int size) {
            this.k = k;
            this.value = v;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        root = put_node(root, key, value);
    }

    private Node put_node(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.k);
        if (cmp < 0) {
            x.left = put_node(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put_node(x.right, key, value);
        } else
            x.value = value;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void clear() {
        this.root = new Node(null, null, 0);
        this.root.left = null;
        this.root.right = null;
    }

    public boolean containsKey(K key) {
        return containsKey(key, root);
    }

    private boolean containsKey(K k, Node x) {
        if (x == null || x.k == null)
            return false;
        int cmp = k.compareTo(x.k);
        if (cmp < 0) {
            return containsKey(k, x.left);
        } else if (cmp > 0) {
            return containsKey(k, x.right);
        } else return true;
    }


    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, Node x) {
        if (x == null || x.k == null) return null;
        int cmp = key.compareTo(x.k);
        if (cmp < 0) {
            return get(key, x.left);
        } else if (cmp > 0) {
            return get(key, x.right);
        } else return x.value;
    }

    public String printInorder() {
        StringBuilder t = new StringBuilder();
        this.printInorder(this.root, t);
        System.out.print(t);
        return t.toString();
    }

    private StringBuilder printInorder(Node x, StringBuilder t) {
        if (x == null)
            return t;
        printInorder(x.left, t);
        t.append(x.value);
        t.append(", ");
        printInorder(x.right, t);
        return t;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.size;
    }


    public Set<K> keySet() {
        Set<K> keys=new HashSet<>();
        keySet(keys,root);
        return keys;
    }
    private Set<K> keySet(Set<K> x,Node root){
        if (root==null)
            return x;
        keySet(x,root.left);
        x.add(root.k);
        keySet(x,root.right);
        return x;
    }

    public V remove(K key) {
        V t = removechild(root,key);
        root.size--;
        return t;
    }

    private Node findmin(Node x){
        Node temp=x;
        while(temp.left!=null)
            temp=temp.left;
        return temp;
    }
    private Node findmax(Node x){
        while(true){
            if(x.right==null){
                return x;
            }
            x=x.right;
        }
    }
    private V removechild(Node root,K key){
        if(root==null)
            throw new IllegalArgumentException("do not find the key");
        int cmp=key.compareTo(root.k);
        if(cmp<0) {
            return removechild(root.left, key);
        }
        else if(cmp>0) {
            return removechild(root.right, key);
        }

        else {
            if (root.left==null&&root.right==null){
                V result= root.value;
                root.k=null;
                root.value=null;
                return result;
            }
            if(root.left==null){
                V result= root.value;
                root.k=root.right.k;
                root.value= root.right.value;
                root=root.right;
                return result;
            }
            if(root.right==null){
                V result= root.value;
                root.k=root.left.k;
                root.value= root.left.value;
                root.left=root.left.left;
                return result;
            }
            else{
                Node t=findmin(root.right);
                V result= root.value;
                root.k=t.k;
                root.value=t.value;
                removechild(t,root.k);
                return result;
            }

        }
    }

    public V remove(K key, V value) {
        if(get(key)==value)
            return remove(key);
        throw new IllegalArgumentException("do not have the value");
    }

    @Override
    public Iterator<K> iterator(){
        return new BSTMapiter();
    }

    private class BSTMapiter implements Iterator<K>{
        private final Set<K> Iterarray;

        private int index=0;
        public BSTMapiter(){
            System.out.println(root.size);
            Iterarray=new HashSet<>();
            toflat(root,Iterarray);
        }

        public void toflat(Node x,Set<K> Iterarray){
            if (x!=null) {
                toflat(x.left, Iterarray);
                Iterarray.add(x.k);
                toflat(x.right,Iterarray);
            }
        }

        @Override
        public boolean hasNext(){
              return index< root.size;
        }

        @Override
        public K next(){
            K temp= (K)Iterarray.toArray()[index];
            index++;
            return temp;
        }
    }

    public static void main(String[] args) {
        BSTMap<Integer, Integer> b = new BSTMap<Integer, Integer>();
        b.put(7, 7);
        b.put(3, 3);
        b.put(2, 2);
        b.put(1, 1);
        b.put(0, 0);
        b.put(5, 5);
        b.put(4, 4);
        b.put(6, 6);
        b.put(8, 8);
        System.out.print(b.keySet());
        for(Integer k:b){
            System.out.print(k+" ");
        }
    }
}
