package dataStructures;



public class BSTKeyIterator<K,V> implements Iterator<K> {

	Iterator<Entry<K, V>> keys;
	
	public BSTKeyIterator(Iterator<Entry<K, V>> keys) {
		this.keys = keys;
	}

	@Override
	public boolean hasNext() {
		return keys.hasNext();
	}

	
	public K next() throws NoSuchElementException {
		
		return keys.next().getKey();
	}

	@Override
	public void rewind() {
		keys.rewind();

	}

}
