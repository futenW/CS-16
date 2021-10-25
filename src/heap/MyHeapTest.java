package heap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.datastructures.EmptyPriorityQueueException;
import net.datastructures.InvalidEntryException;
import net.datastructures.InvalidKeyException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * This class can be used to test the functionality of your MyHeap implementation.
 * You will find a few examples to guide you through the syntax of writing test cases.
 * Each test case uses its own heap instance to ensure that the test cases are independent 
 * of each other. All of the given examples should pass once you've implemented your heap.
 * 
 *
 * The annotation @Test before each test case is JUnit syntax. It basically lets the compiler know
 * that this is a unit test method. Use this annotation for *every* test method. This class is 
 * also like any other java class, so should you need to add private helper methods to use in your 
 * tests, you can do so, simply without the @Test annotation.
 * The general framework of a test case is:
 * 		- Name the test method descriptively, mentioning what is being tested (it is ok to have slightly verbose method names here)
 * 		- Set-up the program state (ex: instantiate a heap and insert K,V pairs into it)
 * 		- Use assertions to validate that the progam is in the state you expect it to be
 * 
 * We've given you four example of test cases below that should help you understand syntax and the
 * general structure of tests.
 */
public class MyHeapTest {

	/**
	 * A simple test to ensure that insert() works.
	 */
	@Test
	public void testInsertOneElement() {
		// set-up
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		assertThat(heap.isEmpty(), is(true));
		assertThat(heap.insert(2, "B"), is(heap.min()));
		
		// Assert that data structure has correct properties
		assertThat(heap.size(), is(1));
		assertThat(heap.min().getKey(), is(2));
		assertThat(heap.isEmpty(), is(false));
		assertThat(heap.min().getValue(), is("B"));
	}

	/**
	 * This is an example to check that the order of the heap is sorted as per the
	 * keys by comparing a list of the actual and expected keys.
	 */
	@Test
	public void testRemoveMinHeapOrderUsingList() {
	MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(11, "A");
		heap.insert(13, "B");
		heap.insert(64, "C");
		heap.insert(16, "D");
		heap.insert(44, "E");
		
		// the expected ordering that keys come in
		List<Integer> expectedKeys = Arrays.asList(11, 13, 16, 44, 64);
		
		// the actual ordering of keys in the heap
		List<Integer> actualKeys = new ArrayList<Integer>();
		while(!heap.isEmpty()) {
			actualKeys.add(heap.removeMin().getKey());
		}
		
		// Check that the actual ordering matches the expected ordering by using one assert.
		// Note that assertThat(actual, is(expected)), when used on lists/ arrays, also checks that the ordering is the same.
		assertThat(actualKeys, is(expectedKeys));
	}

	/**
	 * This is an example of testing heap ordering by ensuring that the min key is always at the root
	 * by checking it explicitly each time, using multiple asserts rather than a list.
	 */
	@Test
	public void testRemoveMinHeapOrder() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(11, "A");
		heap.insert(13, "B");
		heap.insert(64, "C");
		heap.insert(16, "D");
		heap.insert(44, "E");
		
		
		// test the heap ordering by asserting on all elements
		assertThat(heap.removeMin().getKey(), is(11));
		assertThat(heap.removeMin().getKey(), is(13));
		assertThat(heap.removeMin().getKey(), is(16));
		assertThat(heap.removeMin().getKey(), is(44));
		assertThat(heap.removeMin().getKey(), is(64));
	}
	

	/**
	 * This is an example of how to test whether an exception you expect to be thrown on a certain line of code
	 * is actually thrown. As shown, you'd simply add the expected exception right after the @Test annotation.
	 * This test will pass if the exception expected is thrown by the test and fail otherwise.
	 * 
	 * Here, we're checking to see if an IllegalStateException is being correctly thrown after we try to
	 * call setComparator on a non-empty heap.
	 */
	@Test(expected=IllegalStateException.class)
	public void testSetComparatorThrowsIllegalStateException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "A");
		heap.setComparator(new IntegerComparator());
	}


	// TODO: add your tests below! Think of edge cases and testing for exceptions (if applicable) for insert, remove,
	//  min, removeMin, size and your helper methods (if applicable).

	/**
	 * test that setComparator() sets the comparator to a new comparator used for comparing items in the heap to the
	 * comparator passed in after, even after initialization.
	 */
	@Test
	public void testSetComparator() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		IntegerComparator newComparator = new IntegerComparator();
		heap.setComparator(newComparator);
	}

	/**
	 * Check to see if an IllegalStateException is being correctly thrown after a call to setComparator on a non-empty heap.
	 */
	@Test(expected=IllegalStateException.class)
	public void testIfSetComparatorThrowsIllegalStateException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(2, "ttt");
		heap.setComparator(new IntegerComparator());
	}

	/**
	 * Check to see if an IllegalArgumentException is being correctly thrown after passing in a null comparator.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testIfSetComparatorThrowsIllegalArgumentException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.setComparator(null);
	}

	/**
	 * test that size() correctly returns the size of the heap, empty or not.
	 */
	@Test
	public void testSize() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		assertTrue(heap.size() == 0);
		heap.insert(1, "A");
		assertTrue(heap.size() == 1);
		heap.insert(2, "B");
		assertTrue(heap.size() == 2);
		heap.removeMin();
		assertTrue(heap.size() == 1);
		heap.removeMin();
		assertTrue(heap.size() == 0);
	}

	/**
	 * test that isEmpty() correctly asserts whether the heap is empty.
	 */
	@Test
	public void testIsEmpty() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		assertTrue(heap.isEmpty() == true);
		heap.insert(1, "A");
		assertTrue(heap.isEmpty() == false);
		heap.insert(2, "B");
		assertTrue(heap.isEmpty() == false);
		heap.removeMin();
		assertTrue(heap.isEmpty() == false);
		heap.removeMin();
		assertTrue(heap.isEmpty() == true);
	}

	/**
	 * test that min() correctly returns the root, which should be the minimum entry.
	 */
	@Test
	public void testMin() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(5, "B");
		heap.insert(3, "C");
		heap.insert(4, "D");
		assertTrue(heap.min().getKey() == 3);
		heap.removeMin();
		assertTrue(heap.min().getKey() == 4);
		heap.removeMin();
		assertTrue(heap.min().getKey() == 5);
	}

	/**
	 * Check to see if an EmptyPriorityQueueException is being correctly thrown after calling min on an empty heap.
	 */
	@Test(expected= EmptyPriorityQueueException.class)
	public void testMinThrowsEmptyPriorityQueueException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.min();
	}

	/**
	 * Check that insert properly insert element into heap, changes size, and reorders
	 */
	@Test
	public void testInsertElementNotInOrder() {
		// set-up
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		assertThat(heap.isEmpty(), is(true));
		assertThat(heap.insert(2, "B"), is(heap.min()));

		// Assert that data structure has correct properties
		assertThat(heap.size(), is(1));
		assertThat(heap.min().getKey(), is(2));
		assertThat(heap.isEmpty(), is(false));
		assertThat(heap.min().getValue(), is("B"));

		assertThat(heap.insert(1, "A"), is(heap.min()));

		// Assert that data structure has correct properties
		assertThat(heap.size(), is(2));
		assertThat(heap.min().getKey(), is(1));
		assertThat(heap.isEmpty(), is(false));
		assertThat(heap.min().getValue(), is("A"));

		assertTrue(heap.insert(3, "C") != heap.min());

		// Assert that data structure has correct properties
		assertThat(heap.size(), is(3));
		assertThat(heap.min().getKey(), is(1));
		assertThat(heap.isEmpty(), is(false));
		assertThat(heap.min().getValue(), is("A"));
	}

	/**
	 * test that insert() appropriately inserts a key-value pair, upheaps, and returns the entry created.
	 * tries negative key value after all positive, should become the root/min
	 * tries adding a key value the same as one already there, should not become the root/min bc stays right beside it
	 * also tests upHeap() method works as intended
	 */
	@Test
	public void testInsertManyElements() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		assertTrue(heap.insert(1, "A") == heap.min());
		assertTrue(heap.insert(2, "B") != heap.min());
		assertTrue(heap.insert(3, "C") != heap.min());
		assertTrue(heap.insert(-1, "Z") == heap.min());
		assertTrue(heap.insert(-2, "ZZ") == heap.min());
		assertTrue(heap.insert(-99, "ZZ") == heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());

		assertThat(heap.min().getKey(), is(-99));
		assertThat(heap.min().getValue(), is("ZZ"));
	}

	/**
	 * A simple test inserting one element only.
	 */
	@Test
	public void testOneElementInsert() {
		// set-up
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(11, "Abc");

		// Assert that your data structure is consistent using assertThat(actual, is(expected))
		assertThat(heap.size(), is(1));
		assertThat(heap.min().getKey(), is(11));
	}

	/**
	 * Check to see if an InvalidKeyException is being correctly thrown after passing in an invalid key to insert.
	 */
	@Test(expected= InvalidKeyException.class)
	public void testInsertThrowsInvalidKeyException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(null, "adf");
	}

	/**
	 * test that removeMin() appropriately removes the root, downheaps, and returns the entry w/ the minimum key.
	 * the min() should be the value returned in removeMin()
	 * also tests downHeap() and swap() methods works as intended
	 */
	@Test
	public void testRemoveMin() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		assertTrue(heap.insert(-1, "Z") == heap.min());
		assertTrue(heap.insert(-2, "ZZ") == heap.min());
		assertTrue(heap.insert(-99, "ZZ") == heap.min());
		assertTrue(heap.insert(-99, "ZZ") != heap.min());
		// from previous testInsert() method

		assertTrue(heap.min() == heap.removeMin());
		assertTrue(heap.min().getKey() == heap.removeMin().getKey());
		assertTrue(heap.min().getValue() == heap.removeMin().getValue());
		assertThat(heap.min().getKey(), is(-1));
		assertTrue(heap.min().getKey() == heap.removeMin().getKey());
	}

	/**
	 * This is an example to check that the order of the heap is sorted as per the
	 * keys by comparing a list of the actual and expected keys.
	 */
	@Test
	public void testRemoveMinHeapOrderList() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(12, "A");
		heap.insert(14, "B");
		heap.insert(65, "C");
		heap.insert(17, "D");
		heap.insert(45, "E");

		// the expected ordering that keys come in
		List<Integer> expectedKeys = Arrays.asList(12, 14, 17, 45, 65);

		// the actual ordering of keys in the heap
		List<Integer> actualKeys = new ArrayList<Integer>();
		while(!heap.isEmpty()) {
			actualKeys.add(heap.removeMin().getKey());
		}

		// Check that the actual ordering matches the expected ordering by using one assert.
		// Note that assertThat(actual, is(expected)), when used on lists/ arrays, also checks that the ordering is the same.
		assertThat(actualKeys, is(expectedKeys));
	}

	/**
	 * This is an example of testing heap ordering by ensuring that the min key is always at the root
	 * by checking it explicitly each time, using multiple asserts rather than a list.
	 */
	@Test
	public void RemoveMinHeapOrder() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(12, "A");
		heap.insert(14, "B");
		heap.insert(65, "C");
		heap.insert(17, "D");
		heap.insert(45, "E");


		// test the heap ordering by asserting on all elements
		assertThat(heap.removeMin().getKey(), is(12));
		assertThat(heap.removeMin().getKey(), is(14));
		assertThat(heap.removeMin().getKey(), is(17));
		assertThat(heap.removeMin().getKey(), is(45));
		assertThat(heap.removeMin().getKey(), is(65));
	}

	/**
	 * Check to see if an EmptyPriorityQueueException is being correctly thrown after
	 * calling removeMin() on an empty heap.
	 */
	@Test(expected= EmptyPriorityQueueException.class)
	public void testEmptyPriorityQueueException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.removeMin();
	}

	/**
	 * Check to see if an InvalidEntryException is being correctly thrown after passing invalid entry parameter to remove.
	 */
	@Test(expected= InvalidEntryException.class)
	public void testRemoveThrowsInvalidEntryException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "hi");
		heap.remove(null);
	}

	/**
	 * test that remove() appropriately removes the specified entry, downheaps/upheaps appropriately, and returns
	 * the given entry from the heap.
	 * also tests upHeap(), downHeap(), and swap() methods work as intended
	 */
	@Test
	public void testRemove() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "A");
		heap.insert(2, "B");
		heap.insert(3, "C");

		assertThat(heap.min().getKey(), is(1));
		assertThat(heap.remove(heap.min()).getKey(), is(1));
		assertThat(heap.min().getKey(), is(2));
		assertThat(heap.remove(heap.min()).getKey(), is(2));
		assertThat(heap.min().getKey(), is(3));
		assertThat(heap.remove(heap.min()).getKey(), is(3));
	}

	/**
	 * test that replaceKey() appropriately replaces the key of the given entry, downheaps/upheaps appropriately,
	 * and return the old key formerly associated with the entry.
	 */
	@Test
	public void testReplaceKey() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "A");
		heap.insert(2, "B");
		heap.insert(3, "C");
		heap.insert(4, "D");
		heap.insert(5, "E");
		heap.insert(6, "F");
		heap.insert(7, "G");

		assertTrue(heap.replaceKey(heap.min(), 8) == 1);
		assertThat(heap.min().getKey(), is(2));
		assertTrue(heap.replaceKey(heap.min(), 9) == 2);
		assertThat(heap.min().getKey(), is(3));
		assertTrue(heap.replaceKey(heap.min(), 10) == 3);
		assertThat(heap.min().getKey(), is(4));
		assertTrue(heap.replaceKey(heap.min(), 11) == 4);
		assertThat(heap.min().getKey(), is(5));
		assertTrue(heap.replaceKey(heap.min(), 11) == 5);
		assertThat(heap.min().getKey(), is(6));
		assertTrue(heap.replaceKey(heap.min(), 10) == 6);
		assertThat(heap.min().getKey(), is(7));
		assertTrue(heap.replaceKey(heap.min(), 10) == 7);
		assertThat(heap.min().getKey(), is(8));
		assertTrue(heap.replaceKey(heap.min(), 10) == 8);
		assertThat(heap.min().getKey(), is(9));
		assertTrue(heap.replaceKey(heap.min(), 10) == 9);
		assertThat(heap.min().getKey(), is(10));
		assertTrue(heap.replaceKey(heap.min(), 119) == 10);
		assertThat(heap.min().getKey(), is(10));
		assertThat(heap.replaceKey(heap.min(), 10000), is(10));
		assertThat(heap.min().getKey(), is(10));
		assertTrue(heap.replaceKey(heap.min(), 100) == 10);
		assertThat(heap.min().getKey(), is(10));
		heap.replaceKey(heap.min(), 1);
		assertThat(heap.min().getKey(), is(1));
	}

	/**
	 * test that replaceKey() can handle negatives
	 */
	@Test
	public void testReplaceKeyWithNegatives() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "A");
		heap.insert(2, "B");

		assertTrue(heap.replaceKey(heap.min(), -1) == 1);
		assertThat(heap.min().getKey(), is(-1));
		assertTrue(heap.replaceKey(heap.min(), -2) == -1);
		assertThat(heap.min().getKey(), is(-2));
	}

	/**
	 * Check to see if an InvalidKeyException is being correctly thrown after passing in an invalid key to replaceKey().
	 */
	@Test(expected= InvalidKeyException.class)
	public void testReplaceKeyThrowsInvalidKeyException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "adf");
		heap.replaceKey(heap.min(), null);
	}

	/**
	 * Check to see if an InvalidEntryException is being correctly thrown after passing invalid entry
	 * parameter to replaceKey().
	 */
	@Test(expected= InvalidEntryException.class)
	public void testReplaceKeyThrowsInvalidEntryException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "hi");
		heap.replaceKey(null, 1);
	}

	/**
	 * test that replaceValue() appropriately replaces the value of the given entry
	 * and return the old value formerly associated with the entry.
	 */
	@Test
	public void testReplaceValue() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "A");
		heap.insert(2, "B");
		heap.insert(3, "C");
		heap.insert(4, "D");
		heap.insert(5, "E");
		heap.insert(6, "F");
		heap.insert(7, "G");

		assertThat(heap.replaceValue(heap.min(), "Hahahahahahahahahahahahahaha"), is("A"));
		assertThat(heap.replaceValue(heap.min(), "hoho"), is("Hahahahahahahahahahahahahaha"));
	}

	/**
	 * insert node with null value, test replaceValue() with null
	 */
	@Test
	public void testValueNull() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, null);

		assertTrue(heap.replaceValue(heap.min(), null) == null);
	}

	/**
	 * insert node with boolean value, test replaceValue() with boolean
	 */
	@Test
	public void testValueBoolean() {
		MyHeap<Integer, Boolean> heap = new MyHeap<Integer, Boolean>(new IntegerComparator());
		heap.insert(1, true);

		assertTrue(heap.replaceValue(heap.min(), false) == true);
	}

	/**
	 * Check to see if an InvalidEntryException is being correctly thrown after passing invalid
	 * parameter to replaceValue().
	 */
	@Test(expected= InvalidEntryException.class)
	public void testReplaceValueThrowsInvalidEntryException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "hi");
		heap.replaceValue(null, "hi");
	}

	/**
	 * A basic test of insert, remove, size, min, removeMin
	 */
	@Test
	public void genericTest() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(2, "B");
		heap.insert(3, "C");
		heap.insert(4, "D");
		heap.insert(5, "E");
		heap.insert(6, "F");
		heap.insert(7, "G");

		assertThat(heap.size(), is(6));
		assertThat(heap.min().getKey(), is(2));

		heap.insert(1, "A");

		assertThat(heap.size(), is(7));
		assertThat(heap.min().getKey(), is(1));

		assertThat(heap.removeMin().getKey(), is(1));
		assertThat(heap.removeMin().getKey(), is(2));
		assertThat(heap.removeMin().getKey(), is(3));
		assertThat(heap.removeMin().getKey(), is(4));
		assertThat(heap.removeMin().getKey(), is(5));
		assertThat(heap.removeMin().getKey(), is(6));
		assertThat(heap.removeMin().getKey(), is(7));

		assertThat(heap.isEmpty(), is(true));

		heap.insert(1, "A");
		heap.insert(2, "B");
		heap.insert(3, "C");
		heap.insert(4, "D");
		heap.insert(5, "E");
		heap.insert(6, "F");
		heap.insert(7, "G");

		assertThat(heap.min().getKey(), is(1));
		assertThat(heap.remove(heap.min()).getKey(), is(1));
		assertThat(heap.size(), is(6));
		assertThat(heap.min().getKey(), is(2));
		assertThat(heap.removeMin().getKey(), is(2));
		assertThat(heap.removeMin().getKey(), is(3));
		assertThat(heap.removeMin().getKey(), is(4));
		assertThat(heap.removeMin().getKey(), is(5));
		assertThat(heap.removeMin().getKey(), is(6));
		assertThat(heap.removeMin().getKey(), is(7));

		assertThat(heap.isEmpty(), is(true));
	}
}
