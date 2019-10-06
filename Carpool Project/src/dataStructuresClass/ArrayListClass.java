package dataStructuresClass;

import dataStructures.InvalidPositionException;
import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.NoElementException;

public class ArrayListClass<E> implements List<E> {

	private static final int INIT_CAPACITY = 20;
	private static final int GROWTH = 5;

	protected int counter;
	protected Object[] elements;

	public ArrayListClass(int capacity) {

		elements = new Object[capacity];
		counter = 0;

	}

	public ArrayListClass() {
		elements = new Object[INIT_CAPACITY];
		counter = 0;
	}

	@Override
	public boolean isEmpty() {
		return counter == 0;
	}

	@Override
	public int size() {
		return counter;
	}

	@Override
	public Iterator<E> iterator() throws NoElementException {
		if (counter == 0)
			throw new NoElementException();
		return new IteratorClass<E>(elements);
	}

	@Override
	public int find(E element) {
		int index = 0;
		if (counter == 0)
			return -1;

		boolean found = false;
		for (int i = 0; i < counter && !found; i++) {
			E aux = (E) elements[i];
			if (aux == element) {
				found = true;
				index = i;
			} else {
				index++;
			}
		}
		if (!found)
			index = -1;

		return index;
	}

	@Override
	public E getFirst() throws NoElementException {
		if (counter == 0)
			throw new NoElementException();

		return (E) elements[0];
	}

	@Override
	public E getLast() throws NoElementException {
		if (counter == 0)
			throw new NoElementException();
		return (E) elements[counter - 1];
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		if (position < 0 || counter <= position)
			throw new InvalidPositionException();

		return (E) elements[position];

	}

	@Override
	public void addFirst(E element) {

	}

	@Override
	public void addLast(E element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {

	}

	private void openAt(int index) {

	}

	@Override
	public E removeFirst() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeLast() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	private void removeAt(int index) {

	}

	private boolean isFull() {
		if (elements.length == counter)
			return true;
		else
			return false;
	}

	private void resize() {
		Object[] temp = new Object[elements.length * GROWTH];
		for (int i = 0; i < counter; i++) {
			temp[i] = elements[i];
		}
		elements = temp;
	}

}
