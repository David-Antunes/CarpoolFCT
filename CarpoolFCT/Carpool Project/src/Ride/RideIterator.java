/**
 * 
 */
package Ride;

import Date.Date;
import dataStructures.Iterator;
import dataStructures.NoSuchElementException;
import dataStructures.SortedMap;

/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class RideIterator<K, V> implements Iterator<RideWrapper> {

	/*
	 * Iterator for rides that iterates the sortedMap of sortedMaps
	 */

	Iterator<SortedMap<String, Ride>> bigIt;
	Iterator<Ride> smallIt;

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(D + RID) average case :
	 * O(D + RID)
	 */
	public RideIterator(SortedMap<Date, SortedMap<String, Ride>> map) {

		bigIt = map.values();
		smallIt = bigIt.next().values();

	}

	/*
	 * Temporal Complexity: best case : O(1 * complexity of it.hasNext()) worst case
	 * : O(1 * complexity of it.hasNext()) Medium case : O(1 * complexity of
	 * it.hasNext())
	 */
	@Override
	public boolean hasNext() {

		return bigIt.hasNext() || smallIt.hasNext();
	}

	/*
	 * Temporal Complexity: best case : O(1 * complexity of it.hasNext()) worst case
	 * : O(1 * complexity of it.hasNext()) Medium case : O(1 * complexity of
	 * it.hasNext())
	 */
	@Override
	public RideWrapper next() throws NoSuchElementException {

		if (!hasNext())
			throw new NoSuchElementException();

		if (smallIt.hasNext())
			return smallIt.next();

		smallIt = bigIt.next().values();
		return smallIt.next();

	}

	/*
	 * Temporal Complexity: best case : O(1 * complexity of it.hasNext()) worst case
	 * : O(1 * complexity of it.hasNext()) Medium case : O(1 * complexity of
	 * it.hasNext())
	 */
	@Override
	public void rewind() {
		bigIt.rewind();
		smallIt = bigIt.next().values();

	}

}
