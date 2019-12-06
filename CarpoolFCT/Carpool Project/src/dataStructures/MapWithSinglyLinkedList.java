package dataStructures;

import java.io.Serializable;

/**
 * @author AED_19_20
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class MapWithSinglyLinkedList<K, V> implements Map<K, V>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Entry<K, V>> list;

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public MapWithSinglyLinkedList() {
		list = new SinglyLinkedList<Entry<K, V>>();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public int size() {
		return list.size();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public Iterator<K> keys() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();
		/*
		 * Iterator<Entry<K, V>> it = list.iterator(); List<K> keys = new Array<K>();
		 * while (it.hasNext()) keys.addLast(it.next().getKey()); return
		 * keys.iterator();
		 */
		return new KeyIterator<>(iterator());
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public Iterator<V> values() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();
		/**
		 * Iterator<Entry<K, V>> it = list.iterator(); List<V> values = new Array<V>();
		 * while (it.hasNext()) values.addLast(it.next().getValue()); return
		 * values.iterator();
		 */
		return new ValueIterator<>(iterator());
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(n) Medium case : O(n/2)
	 */
	@Override
	public V find(K key) {

		if (list.size() == 0) {
			return null;
		}
		Iterator<Entry<K, V>> it = list.iterator();

		Entry<K, V> aux;
		boolean found = false;
		int i = 0;
		while (it.hasNext() && !found) {
			aux = it.next();
			if (aux.getKey().equals(key))
				found = true;
			else
				i++;

		}

		return found ? list.get(i).getValue() : null;

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(n) Medium case : O(n/2)
	 */
	@Override
	public V insert(K key, V value) {
		Entry<K, V> entry = new EntryClass<K, V>(key, value);
		V v = null;

		if (list.size() == 0) {
			list.addLast(entry);
			return v;
		}

		Iterator<Entry<K, V>> it = list.iterator();
		int i = 0;
		boolean found = false;
		while (it.hasNext() && !found) {
			Entry<K, V> aux = it.next();
			if (aux.getKey() == key) {
				found = true;
				v = aux.getValue();
			} else
				i++;
		}
		if (i < list.size() && found == true)
			list.remove(i);

		list.addLast(entry);

		return v;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(n) Medium case : O(n/2)
	 */
	@Override
	public V remove(K key) {

		if (list.size() == 0) {
			return null;
		}

		Iterator<Entry<K, V>> it = list.iterator();
		Entry<K, V> aux = it.next();
		boolean found = false;
		int i = 0;
		while (it.hasNext() && !found) {
			if (aux.getKey().equals(key))
				found = true;
			else
				i++;
			aux = it.next();
		}

		return found ? list.get(i).getValue() : null;

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		return list.iterator();
	}

}