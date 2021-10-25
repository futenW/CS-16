package heap;

import net.datastructures.CompleteBinaryTree;
import net.datastructures.EmptyTreeException;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;
import net.datastructures.NodeDeque;

/**********************************************************************************************************************
 * An implementation of a complete binary tree by means of a linked structure (LinkedBinaryTree). The LinkedBinaryTree
 * class takes care of most of the mechanics of modifying the tree (you should read through the NDS4 documentation in
 * order to fully understand how this class works. There's a link on the website), but you will need to think about how
 * to implement a CompleteBinaryTree such that additions and removals operate *only* on the last node (hint: think about
 * other useful data structures). You must also ensure that you do not violate the assignment runtime requirements when
 * deciding how you will track nodes within the tree.
 **********************************************************************************************************************/

/**
 * extension of a LinkedBinaryTree, implements left-completeness
 */
public class MyLinkedHeapTree<E> extends LinkedBinaryTree<E> implements CompleteBinaryTree<E> {

	private NodeDeque<Position<E>> _deque; // data structure to help ensure left-completeness

	/**
	 * Default constructor. The tree begins empty.
 	 */
	public MyLinkedHeapTree() {
		super();
		_deque = new NodeDeque<Position<E>>(); // initialize deque
	}

	/**
	 * Adds an element to the tree just after the last node. Returns the newly created position for the element.
	 * Note: You don't need to instantiate a new Position<E> as a local variable. Look at the NDS4 documentation for
	 * LinkedBinaryTree for how to add a new Position<E> to the tree. This method must run in constant O(1) worst-case time.
	 * @param element to be added to the tree as the new last node |||| @return the Position of the newly inserted element
	 * Assumes LinkedBinaryTree methods run in reasonable constant time, as well as deque functions
 	 */
	@Override
	public Position<E> add(E element) {
		Position<E> newNode = null; // variable pointing to node that will be added
		if (this.isEmpty() == true) {
			newNode = this.addRoot(element); // add as root
			_deque.addLast(newNode); // first element of deque
		} else { // root already there
			Position<E> addHere = _deque.getFirst();
			if (this.hasLeft(addHere) == false) { // has no children yet
				newNode = this.insertLeft(addHere, element);
				_deque.addLast(newNode);
			} else { // already has left child
				newNode = this.insertRight(addHere, element);
				_deque.addLast(newNode);
				_deque.removeFirst();
			}
		}
		return newNode;
	}

	/** Removes and returns the element stored in the last node of the tree.
	 * This method must run in constant O(1) worst-case time.
	 * @return the element formerly stored in the last node (prior to its removal)
	 * @throws EmptyTreeException if the tree is empty and no last node exists
	 * Assumes LinkedBinaryTree methods run in reasonable constant time, as well as deque functions
	 */
	@Override
	public E remove() throws EmptyTreeException {
		if (this.isEmpty() == true) {
			throw new EmptyTreeException("Tree is empty :(");
		}
		Position<E> removePosition = _deque.removeLast(); // remove last item from deque
		if (this.isRoot(removePosition) == false && this.hasRight(this.parent(removePosition)) == true) {
			_deque.addFirst(this.parent(removePosition)); // if removeThis is a right child
		}
		E removedElement = this.remove(removePosition); // remove from tree
		return removedElement;
	}

	/**
	 * returns but does not remove the last item added to back of deque, i.e. last position added to tree
	 * input: none |||| output: a position of type E
	 */
	public Position<E> getLast() {
		return _deque.getLast();
	}
}
