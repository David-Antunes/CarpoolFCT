package dataStructures;

public class KeyIterator<K,V> implements Iterator<K> {

	/*
	 * The complexities of the methods used in this class
	 * are going to depend on the complexities of the iterator 
	 * that is sent to the constructor
	 */
	Iterator<Entry<K,V>> it;
	
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public KeyIterator(Iterator<Entry<K,V>> it) {
		this.it = it;
	}
	
	/*Temporal Complexity:
	 * best case : O(1 * complexity of it.hasNext())
	 * worst case : O(1 * complexity of it.hasNext())
	 * Medium case : O(1 * complexity of it.hasNext())
	 */
	@Override
	public boolean hasNext() {
		
		return it.hasNext();
	}
	
	/*Temporal Complexity:
	 * best case : O(1 * complexity of it.next())
	 * worst case : O(1 * complexity of it.next())
	 * Medium case : O(1 * complexity of it.next())
	 */
	@Override
	public K next() throws NoSuchElementException {
		
		return it.next().getKey();
	}
	
	/*Temporal Complexity:
	 * best case : O(1 * complexity of it.rewind())
	 * worst case : O(1 * complexity of it.rewind())
	 * Medium case : O(1 * complexity of it.rewind())
	 */
	@Override
	public void rewind() {
		it.rewind();
		
	}

}
