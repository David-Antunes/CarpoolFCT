package dataStructures;




public class SortedMapWithJavaClass<K,V> implements SortedMap<K, V> {
	
	java.util.Map<K,V> sortedMap;
	
	public SortedMapWithJavaClass() {
		
		sortedMap = new java.util.TreeMap<>();
	}

	@Override
	public boolean isEmpty() {
		
		return sortedMap.isEmpty();
	}

	@Override
	public int size() {
		return sortedMap.size();
	}

	@Override
	public Iterator<K> keys() throws NoElementException {
		return new IteratorClass<>(sortedMap.keySet());
	}

	@Override
	public Iterator<V> values() throws NoElementException {

		return new IteratorClass<>(sortedMap.values());
	}

	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		
		return new EntryIteratorClass<>(sortedMap.entrySet());
	}

	@Override
	public V find(K key) {
		return sortedMap.get(key);
	}

	@Override
	public V insert(K key, V value) {
		return sortedMap.put(key, value);
	}

	@Override
	public V remove(K key) {
		return sortedMap.remove(key);
	}

	@Override
	public Entry<K, V> minEntry() throws NoElementException {
		return null;
	}

	@Override
	public Entry<K, V> maxEntry() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

}
