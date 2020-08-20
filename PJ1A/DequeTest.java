package src.PJ1A;

/** Performs some basic linked list tests. */

public class DequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	public static boolean isEmpty(ArrayDeque t){
		if(t.getsize()==0)
			return true;
		return false;

	}




	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");

		ArrayDeque<String> lld1 = new ArrayDeque<String>();

		boolean passed = checkEmpty(true, isEmpty(lld1));

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.getsize()) && passed;
		passed = checkEmpty(false,isEmpty(lld1)) && passed;

		lld1.addLast("middle");

		passed = checkSize(2, lld1.getsize()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.getsize()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");


		ArrayDeque<String> lld1 = new ArrayDeque<>();

		boolean passed = checkEmpty(true, isEmpty(lld1));


		lld1.addFirst("second");
		lld1.addFirst("first");
		passed = checkEmpty(false, isEmpty(lld1)) && passed;

		lld1.removeFirst();
		lld1.removeFirst();
		passed = checkEmpty(true, isEmpty(lld1)) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();
		printTestStatus(passed);

	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
	}
} 