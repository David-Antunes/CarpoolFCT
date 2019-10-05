package dataStructures;

import dataStructuresClass.IteratorEntriesClass;
import dataStructuresClass.IteratorKeysClass;
import dataStructuresClass.IteratorValuesClass;

public class MapWithJavaClass<K,V> implements Map<K,V> {
	
	protected java.util.Map<K,V> elementos;
	protected int capPrevista;

	public MapWithJavaClass(int prevusers) {
		elementos = new java.util.HashMap<K,V>(prevusers);
		capPrevista =prevusers;
	}

	@Override
	public boolean isEmpty() {
		return elementos.isEmpty();
	}

	@Override
	public int size() {
		return elementos.size();
	}

	@Override
	public Iterator<K> keys() throws NoElementException {
		return new IteratorKeysClass<K>(elementos.keySet());
	}

	@Override
	public Iterator<V> values() throws NoElementException {
		return new IteratorValuesClass<V>(elementos.values());
	}

	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		return new IteratorEntriesClass<K,V>(elementos.entrySet());
	}

	@Override
	public V find(K key) {
		return elementos.get(key);
	}

	@Override
	public V insert(K key, V value) {
		return elementos.put(key, value);
	}

	@Override
	public V remove(K key) {
		return elementos.remove(key);
	}

}
