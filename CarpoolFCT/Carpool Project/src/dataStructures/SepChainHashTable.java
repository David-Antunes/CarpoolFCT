package dataStructures;

import java.io.Serializable;

public class SepChainHashTable<K, V> extends MapWithHashTable<K, V> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// The array of maps.
	protected Map<K, V>[] table;

	public SepChainHashTable() {
		this(DEFAULTCAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SepChainHashTable(int capacity) {
		// Load factor is 1/1.1 (0.91)
		int arraySize = MapWithHashTable.nextPrime((int) (1.1 * capacity));
		// Compiler gives a warning.
		table = (Map<K, V>[]) new Map[arraySize];
		for (int i = 0; i < arraySize; i++)
			table[i] = new MapWithSinglyLinkedList<K, V>();
		maxSize = capacity;
		currentSize = 0;
	}

	// Returns the hash value of the specified key.
	protected int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	// If there is an entry in the map whose key is the specified key,
	// returns its value; otherwise, returns null.
	@Override
	public V find(K key) {
		return table[this.hash(key)].find(key);
	}

	// If there is an entry in the map whose key is the specified key,
	// replaces its value by the specified value and returns the old value;
	// otherwise, inserts the entry (key, value) and returns null.
	@Override
	public V insert(K key, V value) {
		if (this.isFull())
			rehash();
		V valueOld = table[this.hash(key)].insert(key, value);
		if (valueOld == null)
			currentSize++;
		return valueOld;
	}

	private void rehash() {
		int arraySize = MapWithHashTable.nextPrime((int) (1.1 * maxSize * 2));
		// Compiler gives a warning.
		Map<K, V>[] newTable = (Map<K, V>[]) new Map[arraySize];
		for (int i = 0; i < arraySize; i++) {
			newTable[i] = new MapWithSinglyLinkedList<K, V>();
		}
		Iterator<Entry<K, V>> it = iterator();
		while (it.hasNext()) {
			Entry<K, V> entry = it.next();
			table[this.hash(entry.getKey())].insert(entry.getKey(), entry.getValue());
		}
		maxSize *= 2;
	}

	@Override
	public V remove(K key) {

		V value = table[this.hash(key)].remove(key);
		if (value != null)
			currentSize--;
		return value;
	}

	@Override
	public Iterator<K> keys() throws NoElementException {

		if (currentSize == 0)
			throw new NoElementException();

		Iterator<Entry<K, V>> it = iterator();
		List<K> keys = new Array<K>();
		while (it.hasNext())
			keys.addLast(it.next().getKey());
		return keys.iterator();
	}

	@Override
	public Iterator<V> values() throws NoElementException {

		if (currentSize == 0)
			throw new NoElementException();

		Iterator<Entry<K, V>> it = iterator();
		List<V> val = new Array<V>();
		while (it.hasNext())
			val.addLast(it.next().getValue());
		return val.iterator();
	}

	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException();

		List<Entry<K, V>> entries = new Array<Entry<K, V>>();
		for (int i = 0; i < currentSize; i++) {
			Iterator<Entry<K, V>> it = table[i].iterator();
			while (it.hasNext())
				entries.addLast(it.next());
		}
		return entries.iterator();
	}

}
