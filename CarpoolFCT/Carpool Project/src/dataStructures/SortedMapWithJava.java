package dataStructures;

public class SortedMapWithJava<K,V> implements Map<K, V> {

	private java.util.Map<K, V> sortedMap;
	
	public SortedMapWithJava() {
		sortedMap = new java.util.TreeMap<>();
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<K> keys() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> values() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V find(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V insert(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

}
