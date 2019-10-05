package dataStructuresClass;

import java.util.Map.Entry;
import java.util.Set;

import dataStructures.EntryClass;
import dataStructures.NoSuchElementException;

public class IteratorEntriesClass<K,V> implements dataStructures.Iterator<dataStructures.Entry<K,V>>{

	private java.util.Iterator<Entry<K,V>> elements;
	private Set<Entry<K,V>> list;
	public IteratorEntriesClass(Set<Entry<K,V>> list) {
		this.list = list;
		elements = list.iterator();
	}
	@Override
	public boolean hasNext() {
		return elements.hasNext();
	}

	@Override
	public dataStructures.Entry<K, V> next() throws NoSuchElementException {
		if(!hasNext())
			throw new NoSuchElementException();
		Entry<K,V> entry = elements.next();
		dataStructures.Entry<K, V> toReturn = new EntryClass<K,V>(entry.getKey(),entry.getValue());
		return toReturn;
	}

	@Override
	public void rewind() {
		elements = list.iterator();
	}
}
