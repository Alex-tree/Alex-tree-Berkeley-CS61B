package PJ1B;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffeone(){
    // Your tests go here.
    char x='a';
    char y='b';
    assertTrue(offByOne.equalChars(x,y));
    x='y';
    assertFalse(offByOne.equalChars(x,y));
    }

    @Test
    public void testisPalindrome(){
        String word1="flake";
        CharacterComparator cc=new OffByOne();
        assertTrue(Palindrome.isPalindrome(word1,cc));
        String word2="flmalke";
        assertTrue(Palindrome.isPalindrome(word2,cc));
        String word3="almalke";
        assertFalse(Palindrome.isPalindrome(word3,cc));
    }
}