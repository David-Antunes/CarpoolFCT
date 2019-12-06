/**
 * 
 */
package Ride;

import dataStructures.Iterator;
import dataStructures.NoSuchElementException;

/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class RideInMainIterator implements Iterator<RideWrapper> {

	Iterator<Ride> it;

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	public RideInMainIterator(Iterator<Ride> rides) {
		it = rides;
	}

	/*
	 * Temporal Complexity: best case : O(1 * complexity of it.hasNext()) worst case
	 * : O(1 * complexity of it.hasNext()) average case : O(1 * complexity of
	 * it.hasNext())
	 */
	@Override
	public boolean hasNext() {

		return it.hasNext();
	}

	/*
	 * Temporal Complexity: best case : O(1 * complexity of it.next()) worst case :
	 * O(1 * complexity of it.next()) average case : O(1 * complexity of it.next())
	 */
	@Override
	public RideWrapper next() throws NoSuchElementException {

		return it.next();
	}

	/*
	 * Temporal Complexity: best case : O(1 * complexity of it.rewind()) worst case
	 * : O(1 * complexity of it.rewind()) average case : O(1 * complexity of
	 * it.rewind())
	 */
	@Override
	public void rewind() {
		it.rewind();

	}

}
