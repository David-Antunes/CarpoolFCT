package dataStructures;

public class StackInList<E> implements Stack<E> {

	List<E> elements;
	
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public StackInList() {
		elements = new SinglyLinkedList<E>();
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
	public E top() throws NoElementException {
		if (isEmpty())
			throw new NoElementException("Stack is empty.");
			return elements.getFirst();
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public void push(E element) {
		elements.addFirst(element);

	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public E pop() throws NoElementException {
		if (isEmpty())
			throw new NoElementException("Stack is empty.");
			return elements.removeFirst();
	}

}
