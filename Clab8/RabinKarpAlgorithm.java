package Clab8;
public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int hashp=pattern.hashCode();
        if(pattern.length()>input.length())
            return -1;
        for(int i=0;i<(input.length()-pattern.length()+1);i++){
            int hashi=input.substring(i,i+pattern.length()).hashCode();
            if (hashi==hashp)
                if (input.substring(i,i+pattern.length()).equals(pattern))
                    return i;
        }
        return -1;
    }

}
