package PJ1B;

public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque <Character> d =new LinkedListDeque('1');
        for(int t=0;t<word.length();t++)
            d.addLast(word.charAt(t));
        return d;
    }


    public static boolean isPalindrome(String word){
        if(word.length()<=1)
            return true;
        if(word.charAt(0)!=word.charAt(word.length()-1))
            return false;
        else
            return isPalindrome(word.substring(1,word.length() - 1));

    }

    public static boolean isPalindrome(String word,CharacterComparator cc){
        if(word.length()<=1)
            return true;
        if(!cc.equalChars(word.charAt(0),word.charAt(word.length()-1)))
            return false;
        else
            return isPalindrome(word.substring(1,word.length() - 1),cc);
    }


}
