package dataStructures;

public class ValueIterator<K,V> implements Iterator<V> {

	/*
	 * The complexities of the methods used in this class
	 * are going to depend on the complexities of the iterator 
	 * that is sent to the constructor
	 */
	
	Iterator<Entry<K,V>> it;
	
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * average case : O(1)
	 */
	public ValueIterator(Iterator<Entry<K,V>> it) {
		this.it = it;
	}

	/*Temporal Complexity:
	 * best case : O(1 * complexity of it.hasNext())
	 * worst case : O(1 * complexity of it.hasNext())
	 * average case : O(1 * complexity of it.hasNext())
	 */
	@Override
	public boolean hasNext() {
		
		return it.hasNext();
	}

	/*Temporal Complexity:
	 * best case : O(1 * complexity of it.next())
	 * worst case : O(1 * complexity of it.next())
	 * average case : O(1 * complexity of it.next())
	 */
	@Override
	public V next() throws NoSuchElementException {
		
		return it.next().getValue();
	}

	/*Temporal Complexity:
	 * best case : O(1 * complexity of it.rewind())
	 * worst case : O(1 * complexity of it.rewind())
	 * average case : O(1 * complexity of it.rewind())
	 */
	@Override
	public void rewind() {
		it.rewind();
		
	}

}
