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
public class QueueInList<E> implements Queue<E>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<E> elements;
	
	public QueueInList(){
		elements= new SinglyLinkedList<E>();
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public int size() {
		return elements.size();
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public void enqueue(E element) {
		elements.addLast(element);
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public E dequeue() throws NoElementException {
		if (isEmpty())
			throw new NoElementException("Queue is empty.");
		return elements.removeFirst();
	}

	
	

}
