package PJ1B;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator OffByN = new OffByN(3);

    @Test
    public void testOffeN(){
        // Your tests go here.
        char x='f';
        char y='i';
        assertTrue(OffByN.equalChars(x,y));
        x='y';
        assertFalse(OffByN.equalChars(x,y));
    }
    @Test
    public void testisPalindrome(){
        String word1="flaoi";
        CharacterComparator cc=new OffByN(3);
        assertTrue(Palindrome.isPalindrome(word1,cc));
        String word2="flmajoi";
        assertTrue(Palindrome.isPalindrome(word2,cc));
        String word3="almalke";
        assertFalse(Palindrome.isPalindrome(word3,cc));
    }



}
