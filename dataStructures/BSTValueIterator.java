package dataStructures;

public class BSTValueIterator<K,V> implements Iterator<V> {

	Iterator<Entry<K, V>> values;
	
	public BSTValueIterator(Iterator<Entry<K, V>> values) {
		this.values = values;
	}

	@Override
	public boolean hasNext() {
		return values.hasNext();
	}

	
	public V next() throws NoSuchElementException {
		
		return values.next().getValue();
	}

	@Override
	public void rewind() {
		values.rewind();

	}

}
