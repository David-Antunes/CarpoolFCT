package dataStructuresClass;

import dataStructures.Iterator;
import dataStructures.NoSuchElementException;

public class IteratorClass<E> implements Iterator<E>{

	private int counter;
	private Object[] elements;
	
	public IteratorClass(Object[] list) {
		elements = list;
		counter = 0;
	}
	@Override
	public boolean hasNext() {
		return counter < elements.length;
	}

	@Override
	public E next() throws NoSuchElementException {
		if(!hasNext())
			throw new NoSuchElementException();
		return (E) elements[counter++];
	}

	@Override
	public void rewind() {
		counter = 0;
	}

}
