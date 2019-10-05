package dataStructuresClass;

import java.util.Collection;

import dataStructures.NoSuchElementException;

public class IteratorValuesClass<E> implements dataStructures.Iterator<E>{

	private java.util.Iterator<E> elements;
	private Collection<E> list;
	public IteratorValuesClass(Collection<E> list) {
		this.list = list;
		elements = list.iterator();
	}
	@Override
	public boolean hasNext() {
		return elements.hasNext();
	}

	@Override
	public E next() throws NoSuchElementException {
		if(!hasNext())
			throw new NoSuchElementException();
		return elements.next();
	}

	@Override
	public void rewind() {
		elements = list.iterator();
	}
}
