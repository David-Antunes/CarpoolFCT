package dataStructures;
/**
 * @author AED_19_20
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class SinglyLLIterator<E> implements Iterator<E> {

	// Node with the first element in the iteration.
	private SListNode<E> firstNode;
	// Node with the next element in the iteration.
	private SListNode<E> nextToReturn;
	
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public SinglyLLIterator(SListNode<E> head) {
		firstNode = head;
		rewind();
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public boolean hasNext() {
		
		return nextToReturn != null;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public E next() throws NoSuchElementException {
		
		if ( !this.hasNext() )
			throw new NoSuchElementException("No more elements.");
		E element = nextToReturn.getElement();
		
		nextToReturn = nextToReturn.getNext();
	
		
		return element;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public void rewind() {
		
		nextToReturn = firstNode;

	}

}
