package dataStructures;

import java.io.Serializable;

public class MapWithSinglyLinkedList<K, V> implements Map<K, V>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Entry<K, V>> list;

	public MapWithSinglyLinkedList() {
		list = new SinglyLinkedList<Entry<K, V>>();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Iterator<K> keys() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();

		Iterator<Entry<K, V>> it = list.iterator();
		List<K> keys = new Array<K>();
		while (it.hasNext())
			keys.addLast(it.next().getKey());
		return keys.iterator();
	}

	@Override
	public Iterator<V> values() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();

		Iterator<Entry<K, V>> it = list.iterator();
		List<V> values = new Array<V>();
		while (it.hasNext())
			values.addLast(it.next().getValue());
		return values.iterator();
	}

	@Override
	public V find(K key) {

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

	@Override
	public V insert(K key, V value) {
		Entry<K, V> entry = new EntryClass<K, V>(key, value);

		if (list.size() == 0) {
			list.addLast(entry);
			return value;
		}

		Iterator<Entry<K, V>> it = list.iterator();
		int i = 0;
		boolean found = false;
		while (it.hasNext() && !found) {
			if (it.next() == key)
				found = true;
			else
				i++;
		}
		if (i < list.size())
			list.add(i, entry);
		else
			list.addLast(entry);

		return value;
	}

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

	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		return list.iterator();
	}

}
