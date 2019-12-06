/**
 * 
 */
package dataStructures;

import java.util.Comparator;

/**
 * @author AED_19_20
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class Array<E> implements List<E> {
	private static final int DEFAULT_SIZE = 50;

	/**
	 * O array generico
	 */
	protected E[] array;

	/**
	 * O numero de elementos actual
	 */
	protected int counter;

	/** Construtor que define um array com uma dada dimensao inicial */

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@SuppressWarnings("unchecked")
	public Array(int capacity) {
		array = (E[]) new Object[capacity];
		counter = 0;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public Array() {
		this(DEFAULT_SIZE);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(n) Medium case : O(1)
	 */
	public void addLast(E elem) {
		if (isFull())
			resize();
		array[counter++] = elem;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(3n) Medium case : O(n/2)
	 */
	public void add(int pos, E elem) throws InvalidPositionException {
		if (pos < 0 || pos > counter)
			throw new InvalidPositionException("Invalid Position.");
		if (isFull())
			resize();
		for (int i = counter - 1; i >= pos; i--)
			array[i + 1] = array[i];
		array[pos] = elem;
		counter++;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(3n) Medium case : O(2n)
	 */
	public void addFirst(E elem) {
		if (isFull())
			resize();
		add(0, elem);
	}

	/*
	 * Temporal Complexity: best case : O(n) worst case : O(n) Medium case : O(n)
	 */
	/** Metodo auxiliar para duplicar o tamanho do vector. */
	@SuppressWarnings("unchecked")
	private void resize() {
		E[] tmp = (E[]) new Object[counter * 2];
		for (int i = 0; i < counter; i++)
			tmp[i] = array[i];
		array = tmp;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public E removeLast() throws NoElementException {
		if (counter == 0)
			throw new NoElementException("No such element.");
		return array[--counter];
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(2n) Medium case : O(n/2)
	 */
	public E remove(int pos) throws InvalidPositionException {
		if (pos < 0 || pos >= counter)
			throw new InvalidPositionException("Invalid position.");
		E elem = array[pos];
		for (int i = pos; i < counter - 1; i++)
			array[i] = array[i + 1];
		counter--;
		return elem;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(2n) Medium case : O(2n)
	 */
	public E removeFirst() throws NoElementException {
		if (counter == 0)
			throw new NoElementException("No such element.");
		return remove(0);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public int size() {
		return counter;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public E get(int pos) throws InvalidPositionException {
		if (pos < 0 || pos >= counter)
			throw new InvalidPositionException("Invalid position.");
		;
		return array[pos];
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public Iterator<E> iterator() throws NoElementException {
		if (counter == 0)
			throw new NoElementException("Array is empty.");
		return new ArrayIterator<E>(array, counter);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public E getFirst() throws NoElementException {
		if (counter == 0)
			throw new NoElementException("No such element.");
		return get(0);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public E getLast() throws NoElementException {
		if (counter == 0)
			throw new NoElementException("No such element.");
		return get(counter - 1);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(n) Medium case : O(n/2)
	 */
	public int find(E elem) {
		boolean found = false;
		int i = 0;
		while (i < counter && !found)
			if (array[i].equals(elem))
				found = true;
			else
				i++;
		if (found)
			return i;
		else
			return -1;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public boolean isEmpty() {
		return counter == 0;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public boolean isFull() {
		return counter == array.length;
	}
	
	/*
	 * Temporal Complexity: best case : O(1) worst case : O(nlog(n)) Medium case : O(nlog(n))
	 */
	@SuppressWarnings("unchecked")
	public static <E> void xSort(E[] vec, int vecSize, Comparator<E> c) {
		// Variable auxVec declared here to speed up the algorithm.
		// Compiler gives a warning.
		E[] auxVec = (E[]) new Object[vecSize];
		mergeSortR(vec, auxVec, 0, vecSize - 1, c);
	}
	/*
	 * Temporal Complexity: best case : O(1) worst case : O(nlog(n)) Medium case : O(nlog(n))
	 */
	protected static <E> void mergeSortR(E[] vec, E[] auxVec, int firstPos, int lastPos, Comparator<E> c) {
		if (firstPos < lastPos) {
			int centre = (firstPos + lastPos) / 2;
			mergeSortR(vec, auxVec, firstPos, centre, c);
			mergeSortR(vec, auxVec, centre + 1, lastPos, c);
			mergeR(vec, auxVec, firstPos, centre, lastPos, c);
		}
	}
	/*
	 * Temporal Complexity: best case : O(1) worst case : O(auxVec.length) Medium case : O(auxVec.length)
	 */
	protected static <E> void mergeR(E[] vec, E[] auxVec, int firstLeft, int lastLeft, int lastRight, Comparator<E> c) {
		int left = firstLeft;
		int right = lastLeft + 1;
		int result = firstLeft;
		while (left <= lastLeft && right <= lastRight)
			if (c.compare(vec[left], vec[right]) <= 0)
				auxVec[result++] = vec[left++];
			else
				auxVec[result++] = vec[right++];
		// Copy rest of left sequence.
		while (left <= lastLeft)
			auxVec[result++] = vec[left++];
		// Rest of right sequence in right place.
		// Copy from auxVec to vec.
		// Number of elements to be copied: (result-1) - firstLeft + 1.
		System.arraycopy(auxVec, firstLeft, vec, firstLeft, result - firstLeft);
	}

	public int capacity() {
		return array.length;
	}
}
