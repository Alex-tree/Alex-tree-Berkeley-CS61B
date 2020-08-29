
package Clab8;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    private Queue<Character> listchar;
    private int length=0;
    public RollingString(String s, int length) {
        assert(s.length() == length);
        listchar=new LinkedList<>();
        for(int i=0;i<length;i++)
            listchar.add(s.charAt(i));
        this.length=length;
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        listchar.poll();
        listchar.add(c);
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        for(char t:listchar)
            strb.append(t);
        return String.valueOf(strb);
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        return length;
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        if(this.getClass()!=o.getClass())
            return false;
        if(this.length!=((RollingString) o).length)
            return false;
        Iterator<Character> t1= this.listchar.iterator();
        Iterator<Character> t2=((RollingString) o).listchar.iterator();
        while(t1.hasNext()&&t2.hasNext()) {
           Character a =t1.next();
           Character b =t2.next();
           if(!a.equals(b))
               return false;
       }
        return true;
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        int start=0;
        int n=length;
        String hashstring=this.listchar.toString();
        for(int i=n-1;i>=0;i--)
        {
            start=start*UNIQUECHARS;
            start=start+(int)hashstring.charAt(i);
        }
        return start%PRIMEBASE;
    }

    public static void main(String[] args){
        RollingString s=new RollingString("abcdefg",6);
        RollingString o=new RollingString("abcdefg",6);
        System.out.println(s.equals(o));
        System.out.println(s.hashCode()==o.hashCode());
        s.addChar('n');
        System.out.println(s.toString());
        System.out.println(s.equals(o));
        System.out.println(s.hashCode()==o.hashCode());
    }
}
