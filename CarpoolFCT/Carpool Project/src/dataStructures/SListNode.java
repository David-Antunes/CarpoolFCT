/**
 * 
 */
package dataStructures;

import java.io.Serializable;
/**
 * @author AED_19_20
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
class SListNode<E> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Element stored in the node.
	protected E element;
	// (Pointer to) the next node.
	protected SListNode<E> next;

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public SListNode(E elem, SListNode<E> theNext) {
		element = elem;
		next = theNext;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public SListNode(E theElement) {
		this(theElement, null);
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public E getElement() {
		return element;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public SListNode<E> getNext() {
		return next;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public void setElement(E newElement) {
		element = newElement;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public void setNext(SListNode<E> newNext) {
		next = newNext;
	}

}
