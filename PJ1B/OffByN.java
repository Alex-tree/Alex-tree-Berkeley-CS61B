package PJ1B;

public class OffByN implements CharacterComparator {
    public int N;
    public OffByN(int n){
        N=n;
    }

    public  boolean equalChars(char x, char y){
        if(Math.abs(x-y)==N)
            return true;
        return false;
    }

}
