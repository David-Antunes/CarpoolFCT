package dataStructures;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 *
 */
public class MapWithJavaHashTableMap<K, V> implements Map<K, V>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.util.Map<K, V> hashMap;

	public MapWithJavaHashTableMap() {
		hashMap = new HashMap<K, V>();
	}

	@Override
	public boolean isEmpty() {
		return hashMap.isEmpty();
	}

	@Override
	public int size() {
		return hashMap.size();
	}

	@Override
	public Iterator<K> keys() throws NoElementException {
		return new IteratorClass<>(hashMap.keySet());
	}

	@Override
	public Iterator<V> values() throws NoElementException {

		return new IteratorClass<>(hashMap.values());
	}

	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {

		return new EntryIteratorClass<>(hashMap.entrySet());
	}

	@Override
	public V find(K key) {
		return hashMap.get(key);
	}

	@Override
	public V insert(K key, V value) {
		return hashMap.put(key, value);
	}

	@Override
	public V remove(K key) {
		return hashMap.remove(key);
	}

}
