package Lab9;

import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private static final int R=128;

    private Node root;
    private int size;


    public class Node{
        private Node[] next=new Node[R];
        private char value;
        private boolean end=false;
    }
    public MyTrieSet() {
        root=new Node();
        size=0;
    }

    @Override
    public void clear() {
        Node t=new Node();
        root=t;
        size=0;
    }

    @Override
    public boolean contains(String key) {
        return contains(key,root);
    }
    private boolean contains(String key, Node r){
        if(key.length()==0&&r.end)
            return true;
        if(key.length()==0)
            return false;
        char t= key.charAt(0);
        if(r.next[t]==null)
            return false;
        if(r.next[t].value!=t)
            return false;
        return contains(key.substring(1),r.next[t]);

    }

    @Override
    public void add(String key) {
        add(key,root);
        size++;
    }
    private void add(String key,Node r){
        if(key.length()==0){
            r.end = true;
            return;
        }
        char v= key.charAt(0);
        if(r.next[v]==null) {
            r.next[v] = new Node();
            r.next[v].value = v;
        }
        add(key.substring(1),r.next[v]);
    }

    public int size(){
        return size;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        Node find=root;
        for(int t=0;t<prefix.length();t++){
            char c = prefix.charAt(t);
            if(c!=find.next[c].value||find.next[c]==null)
                return result;
            find=find.next[c];
        }
        KeysWithPrefix(prefix,result,find);
        return result;
    }
    private void KeysWithPrefix(String p, List<String> s,Node n){
        for(int i=0;i<R;i++) {
            if (n.next[i] != null && n.next[i].end) {
                StringBuilder t = new StringBuilder();
                t.append(p);
                t.append(n.next[i].value);
                s.add(t.toString());
                KeysWithPrefix(t.toString(),s,n.next[i]);
            }
            else if(n.next[i]!=null)
                KeysWithPrefix(p,s,n.next[i]);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        StringBuilder t=new StringBuilder();
        longestPrefixOf(key,root,t);
        return t.toString();

    }
    private void longestPrefixOf(String k, Node t, StringBuilder result){
        char c = k.charAt(0);
        if(t.next[c].end||t.next[c]==null)
            return;
        result.append(t.next[c].value);
        longestPrefixOf(k.substring(1),t.next[c],result);
    }
}
