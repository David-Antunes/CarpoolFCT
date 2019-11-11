package dataStructures;



public class IteratorClass<E> implements Iterator<E> {

	java.util.Collection<E> elements;
	java.util.Iterator<E> it;
	
	public IteratorClass(java.util.Collection<E> elements) {
		this.elements = elements;
		it = elements.iterator();
	}
	
	
	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public E next() throws NoSuchElementException {
		return it.next();
	}

	@Override
	public void rewind() {
		it = elements.iterator();
		
	}

}
