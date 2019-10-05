package dataStructuresClass;

import dataStructures.NoSuchElementException;
import dataStructures.TwoWayIterator;

public class TwoWayIteratorClass<E> implements TwoWayIterator<E>{
	
	private int counter;
	private Object[] elements;
	
	public TwoWayIteratorClass(Object[] list) {
		
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

	@Override
	public boolean hasPrevious() {
		return 0 <= counter;
	}

	@Override
	public E previous() throws NoSuchElementException {
		if(!hasPrevious())
			throw new NoSuchElementException();
		return (E) elements[--counter];
	}

	@Override
	public void fullForward() {
		counter = elements.length;
	}

}
