/**
 * 
 */
package dataStructures;

/**
 * @author AED_19_20
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class ArrayIterator<E> implements Iterator<E> {

	private E[] vector;
	private int counter;
	private int current;

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public ArrayIterator(E[] vector, int counter) {
		this.vector = vector;
		this.counter = counter;
		rewind();
	}
	
	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public boolean hasNext() {
		return current < counter;
	}
	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public E next() throws NoSuchElementException {
		if (!hasNext()) 
			throw new NoSuchElementException("No more elements.");
		return vector[current++];
	}
	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public void rewind() {
		current=0;
	}

}
