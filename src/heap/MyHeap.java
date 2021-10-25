package heap;

import java.util.Comparator;
import net.datastructures.CompleteBinaryTree;
import net.datastructures.DefaultComparator;
import net.datastructures.EmptyPriorityQueueException;
import net.datastructures.Entry;
import net.datastructures.InvalidEntryException;
import net.datastructures.InvalidKeyException;
import net.datastructures.Position;
import net.datastructures.AdaptablePriorityQueue;
import support.heap.HeapWrapper;

/**********************************************************************************************************
 * An implementation of an adaptable priority queue by means of a heap. Be certain that your running times
 * match those specified in the program documentation, and remember that the running time of a "called"
 * method sets the minimum running time of the "calling" method. Feel free to add additional comments.
 **********************************************************************************************************/

 /**
  * implements functionality of a heap using a MyLinkedHeapTree data structure
  */
public class MyHeap<K,V> implements HeapWrapper<K,V>, AdaptablePriorityQueue<K,V> {

	private MyLinkedHeapTree<MyHeapEntry<K,V>> _tree; // This the underlying data structure of heap
	private Comparator<K> _comparator; // used to compare values of the keys

	 /**
	  * Creates an empty heap with the given comparator
	  * @param the comparator to be used for heap keys
 	  */
	public MyHeap(Comparator<K> comparator) {
		_tree = new MyLinkedHeapTree();
		this.setComparator(comparator); // initialize and check for exceptions
	}

	/**
	 * Sets the comparator used for comparing items in the heap to the comparator passed in.
	 * @param comparator, the comparator to be used for heap keys
	 * @throws IllegalStateException if priority queue is not empty
	 * @throws IllegalArgumentException if null comparator is passed in
	 */
	public void setComparator(Comparator<K> comparator) throws IllegalStateException, IllegalArgumentException {
		if (_tree.isEmpty() == false) { // tree is empty
			throw new IllegalStateException("Priority queue is not empty :(");
		}
		if (comparator == null) { // null comparator
			throw new IllegalArgumentException("Comparator is null :(");
		}
		_comparator = comparator;
	}

	 /**
	  * Returns the size of the heap. This method must run in O(1) time.
	  * @return an int representing the number of entries stored
	  * assumes size() runs in reasonable constant time
	  */
	public int size() {
		return _tree.size();
	}

	 /**
	  * Returns whether the heap is empty. This method must run in O(1) time.
	  * @return true if the heap is empty; false otherwise
	  * assumes isEmpty() runs in reasonable constant time
	  */
	public boolean isEmpty() {
		return _tree.isEmpty();
	}

	/**
	 * Returns but does not remove the entry with minimum key. This method must run in O(1) time.
	 * @return the entry with the minimum key in the heap |||| @throws EmptyPriorityQueueException if the heap is empty
	 * assumes isEmpty(), root(), and element() run in reasonable constant time
	 */
	public Entry<K,V> min() throws EmptyPriorityQueueException {
		if (_tree.isEmpty()) {
			throw new EmptyPriorityQueueException("Heap is empty :(");
		}
		return _tree.root().element();
	}

	 /**
	  * Inserts a key-value pair and returns the entry created. This method must run in O(log n) time.
	  * @param key to be used as the key the heap is sorting with |||| @param value stored with the associated key in the heap
	  * @return the entry created using the key/value parameters |||| @throws InvalidKeyException if the key is not suitable for this heap
	  * Assumes upHeap() runs in worst-case O(log n) time, as well as all other external methods called
	  */
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		try {
			if (key == null) {
				throw new InvalidKeyException("Key is not suitable for this heap :(");
			}
			_comparator.compare(key, key); // ensure key is valid, throws ClassCastException otherwise
			MyHeapEntry<K,V> newEntry = new MyHeapEntry<K,V>(key, value);
			newEntry.setPosition(_tree.add(newEntry)); // add while ensuring left-completeness
			this.upHeap(newEntry); // upheap in O(log n) time
			return newEntry;
		} catch (ClassCastException e) { // invalid key passed in
			throw new InvalidKeyException("Key is not suitable for this heap :(");
		}
	}

	 /**
	  * Removes and returns the entry with the minimum key. This method must run in O(log n) time.
	  * @return the entry with the with the minimum key, now removed
	  * @throws EmptyPriorityQueueException if the heap is empty
	  * Assumes downHeap() runs in worst-case O(log n) time, as well as all other external methods called
	  */
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		if (_tree.isEmpty()) {
			throw new EmptyPriorityQueueException("Heap is empty :(");
		}
		this.swap(_tree.getLast(), _tree.root()); // swap root with last entry
		MyHeapEntry<K,V> removeThis = _tree.remove();
		if (_tree.size() > 1) { // tree has more than just root
			this.downHeap(_tree.root().element()); // downheap in O(log n) time
		}
		return removeThis;
	}

	 /**
	  * Removes and returns the given entry from the heap. This method must run in O(log n) time.
	  * @param entry to be removed from the heap |||| @return the entry specified for removal by the parameter, now removed
	  * @throws InvalidEntryException if the entry cannot be removed from this heap
	  * Assumes upHeap() and downHeap() run in worst-case O(log n) time, as well as all other external methods called
	  */
	public Entry<K,V> remove(Entry<K,V> entry) throws InvalidEntryException {
		try {
			MyHeapEntry<K,V> checkedEntry = this.checkAndConvertEntry(entry); // throws InvalidEntryException if invalid
			MyHeapEntry<K,V> wasLast = _tree.getLast().element(); // last node's entry
			this.swap(wasLast.getPosition(), checkedEntry.getPosition());
			MyHeapEntry<K,V> removeThis = _tree.remove();
			if (_tree.size() > 1) {
				if (_tree.isRoot(wasLast.getPosition()) == false && // if is not root
						_comparator.compare(wasLast.getKey(),
								_tree.parent(wasLast.getPosition()).element().getKey()) < 0) { // if smaller than parent
					this.upHeap(wasLast);
				} else { // equal to or larger than children
					this.downHeap(wasLast);
				}
			}
			return removeThis;
		} catch (InvalidEntryException e) { // invalid entry
			throw new InvalidEntryException("Entry cannot be removed from this heap :(");
		}
	}

	 /**
	  * Replaces the key of the given entry. This method must run in O(log n) time.
	  * @param entry within which the key will be replaced |||| @param key to replace the existing key in the entry
	  * @return the old key formerly associated with the entry
	  * @throws InvalidEntryException if the entry is invalid |||| @throws InvalidKeyException if the key is invalid
	  * Assumes upHeap() and downHeap() run in worst-case O(log n) time, as well as all other external methods called
	  */
	public K replaceKey(Entry<K,V> entry, K key) throws InvalidEntryException, InvalidKeyException {
		try {
			if (key == null) { // key invalid
				throw new InvalidKeyException("Key is invalid :(");
			}
			_comparator.compare(key, key); // throws ClassCastException if invalid
			MyHeapEntry<K,V> checkedEntry = this.checkAndConvertEntry(entry); // throws InvalidEntryException if invalid
			K oldKey = checkedEntry.getKey();
			checkedEntry.setKey(key);
			if (_tree.size() > 1) { // not just root
				if (_tree.isRoot(checkedEntry.getPosition()) == false && // if not root
						_comparator.compare(checkedEntry.getKey(),
								_tree.parent(checkedEntry.getPosition()).element().getKey()) < 0) { // if smaller than parent
					this.upHeap(checkedEntry);
				} else { // equal to or larger than children
					this.downHeap(checkedEntry);
				}
			}
			return oldKey;
		} catch (ClassCastException e) {
			throw new InvalidKeyException("Key is invalid :(");
		} catch (InvalidEntryException e) {
			throw new InvalidEntryException("Entry is invalid :(");
		}
	}

	 /**
	  * Replaces the value of the given entry. This method must run in O(1) time.
	  * @param entry within which the value will be replaced |||| @param value to replace the existing value in the entry
	  * @return the old value formerly associated with the entry
	  * @throws InvalidEntryException if the entry cannot have its value replaced
	  * Assumes all external calls to methods run in reasonable worst-case O(1) time
	  */
	public V replaceValue(Entry<K,V> entry, V value) throws InvalidEntryException {
		try {
			MyHeapEntry<K,V> checkedEntry = this.checkAndConvertEntry(entry); // throws InvalidEntryException if invalid
			V oldValue = checkedEntry.getValue();
			checkedEntry.setValue(value); // reset value
			return oldValue;
		} catch (InvalidEntryException e) {
			throw new InvalidEntryException("Entry cannot have its value replaced :(");
		}
	}

	/*******************************************************************************************************************
	You may find it useful to add some helper methods here. Think about actions that may be executed often in the rest
	 * of your code. For example, checking key validity, upheaping and downheaping, swapping or replacing elements, etc.
	 * Writing helper methods instead of copying and pasting helps segment your code, makes it easier to understand, and
	 * avoids problems in keeping each occurrence "up-to-date."
	 *******************************************************************************************************************/

	 /**
	  * repeatedly swaps entry with parent entry until its key is larger than its parent
	  * input: a MyHeapEntry<K,V> |||| output: none
	  */
	private void upHeap(MyHeapEntry<K,V> newEntry) {
		while (_tree.isRoot(newEntry.getPosition()) == false && // while is not root
				_comparator.compare(newEntry.getKey(),
						_tree.parent(newEntry.getPosition()).element().getKey()) < 0) { // while smaller than parent
			_tree.swapElements(newEntry.getPosition(), _tree.parent(newEntry.getPosition()));
			newEntry.getPosition().element().setPosition(newEntry.getPosition()); // reconfigure entry's internal position variable
			_tree.parent(newEntry.getPosition()).element().setPosition(_tree.parent(newEntry.getPosition()));
		}
	}

	 /**
	  * repeatedly swaps entry with smaller of two children's entries (if possible) until its key is smaller than its children
	  * input: a MyHeapEntry<K,V> |||| output: none
	  */
	private void downHeap(MyHeapEntry<K,V> newEntry) {
		while ((_tree.hasLeft(newEntry.getPosition()) && _comparator.compare(newEntry.getKey(), // while larger than at least one
				_tree.left(newEntry.getPosition()).element().getKey()) > 0) || // of its children's entries
				(_tree.hasRight(newEntry.getPosition()) && _comparator.compare(newEntry.getKey(),
						_tree.right(newEntry.getPosition()).element().getKey()) > 0)) {
			if (_tree.hasLeft(newEntry.getPosition()) == true && _tree.hasRight(newEntry.getPosition()) == false) { // no right
				_tree.swapElements(newEntry.getPosition(), _tree.left(newEntry.getPosition()));
				newEntry.getPosition().element().setPosition(newEntry.getPosition());
				_tree.left(newEntry.getPosition()).element().setPosition(_tree.left(newEntry.getPosition()));
			} else if (_tree.hasLeft(newEntry.getPosition()) == false && _tree.hasRight(newEntry.getPosition()) == true) { // no left
				_tree.swapElements(newEntry.getPosition(), _tree.right(newEntry.getPosition()));
				newEntry.getPosition().element().setPosition(newEntry.getPosition());
				_tree.right(newEntry.getPosition()).element().setPosition(_tree.right(newEntry.getPosition()));
			} else if (_tree.hasLeft(newEntry.getPosition()) == true && _tree.hasRight(newEntry.getPosition()) == true) { // has both
				if (_comparator.compare(_tree.left(newEntry.getPosition()).element().getKey(),
						_tree.right(newEntry.getPosition()).element().getKey()) < 0) { // if left entry is smaller
					_tree.swapElements(newEntry.getPosition(), _tree.left(newEntry.getPosition())); // swap with left
					newEntry.getPosition().element().setPosition(newEntry.getPosition());
					_tree.left(newEntry.getPosition()).element().setPosition(_tree.left(newEntry.getPosition()));
				} else { // right entry is smaller or they are equal
					_tree.swapElements(newEntry.getPosition(), _tree.right(newEntry.getPosition())); // swap with right
					newEntry.getPosition().element().setPosition(newEntry.getPosition());
					_tree.right(newEntry.getPosition()).element().setPosition(_tree.right(newEntry.getPosition()));
				}
			}
		}
	}

	 /**
	  * swap the entries at two given positions
	  * input: two positions |||| output: none
	  */
	private void swap(Position<MyHeapEntry<K,V>> lower, Position<MyHeapEntry<K,V>> upper) {
		_tree.swapElements(lower, upper);
		lower.element().setPosition(lower);
		upper.element().setPosition(upper);
	}






///////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////     DON'T TOUCH      ////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
	/** Determines whether a given entry is valid and converts it to a
	 * MyHeapEntry. Don't change this method.
	 * @param entry to be checked for validity with respect to the heap
	 * @return the entry cast as a MyHeapEntry if considered valid
	 * @throws InvalidEntryException if the entry is not of the proper class */
	public MyHeapEntry<K,V> checkAndConvertEntry(Entry<K,V> entry) throws InvalidEntryException {
		if (entry == null || !(entry instanceof MyHeapEntry)) {
			throw new InvalidEntryException("Invalid entry");
		}
		return (MyHeapEntry<K, V>) entry;
	}

	/**
	 * Returns a CompleteBinaryTree that will allow the visualizer access to private members, shattering encapsulation, but
	 * allowing visualization of the heap. This is the only method needed to satisfy HeapWrapper interface implementation.
	 * Do not modify or call this method. It is solely necessary for the visualizer to work properly.
	 * @return the underlying binary tree on which the heap is based */
	public CompleteBinaryTree<MyHeapEntry<K,V>> getTree() {
		return _tree;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////     DON'T TOUCH      ////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

}
