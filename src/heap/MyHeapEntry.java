package heap;

import net.datastructures.Entry;
import net.datastructures.Position;

/**********************************************************************************************************************
 * Represents a key/value pair to be stored in a data structure, such as a heap. Entry<K,V> is a very limited accessing
 * interface, so you may wish to add additional methods. In particular, think about the relationship of the Entry<K,V>
 * to its location in the heap's binary tree. All methods must run in O(1) time. Feel free to add additional comments.
 **********************************************************************************************************************/

/**
 *  each entry within the heap entry, stores Key, Value data
 */
public class MyHeapEntry<K,V> implements Entry<K,V> {

	private K _key; // key of the entry
	private V _value; // value of the entry
	private Position<MyHeapEntry<K,V>> _position; // position within the heap

	/**
	 * Initializes key and value, sets position to null initially bc may not be known yet
 	 */
	public MyHeapEntry(K key, V value) {
		_key = key;
		_value = value;
		_position = null;
	}

	/**
	 * @return the key stored in this entry
 	 */
	public K getKey() {
		return _key;
	}

	/**
	/* set a new key to the one provided
	 */
	public void setKey(K key) {
		_key = key;
	}

	/**
	 * @return the value stored in this entry
 	 */
	public V getValue() {
		return _value;
	}

	/**
	 * set a new value
 	 */
	public void setValue(V value) {
		_value = value;
	}

	/**
	 * @return the position stored in this entry
 	 */
	public Position<MyHeapEntry<K,V>> getPosition() {
		return _position;
	}

	/**
	 * set a new position
	 */
	public void setPosition(Position<MyHeapEntry<K,V>> p) {
		_position = p;
	}
}
