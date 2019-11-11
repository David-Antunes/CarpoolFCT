package dataStructures;

import java.util.Map.Entry;

public class EntryIteratorClass<K,V> implements Iterator<dataStructures.Entry<K,V>> {
	
	java.util.Collection<Entry<K, V>> entries;
	java.util.Iterator<Entry<K,V>> it;
	
	public EntryIteratorClass(java.util.Collection<Entry<K,V>> entries) {
		this.entries = entries;
		it = entries.iterator();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public dataStructures.Entry<K,V> next() throws NoSuchElementException {
		
		java.util.Map.Entry<K,V> currEntry;
		currEntry = (Entry<K, V>) it.next();
		
		dataStructures.Entry<K,V> entry = new EntryClass<K,V>(currEntry.getKey(),currEntry.getValue());
		return entry;
	}

	@Override
	public void rewind() {
		it = entries.iterator();
		
	}

}
