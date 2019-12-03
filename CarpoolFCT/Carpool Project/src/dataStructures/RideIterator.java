/**
 * 
 */
package dataStructures;

import Date.Date;
import Rides.Ride;

/**
 * @author Carolina
 *
 */
public class RideIterator<K, V> implements Iterator<Ride> {

	Iterator<SortedMap<String, Ride>> bigIt;
	Iterator<Ride> smallIt;

	/**
	 * 
	 */
	public RideIterator(SortedMap<Date, SortedMap<String, Ride>> map) {

		bigIt = map.values();
		smallIt = bigIt.next().values();

	}

	@Override
	public boolean hasNext() {

		return bigIt.hasNext() || smallIt.hasNext();
	}

	@Override
	public Ride next() throws NoSuchElementException {

		if (smallIt.hasNext())
			return smallIt.next();

		smallIt = bigIt.next().values();
		return smallIt.next();

	}

	@Override
	public void rewind() {
		bigIt.rewind();
		smallIt = bigIt.next().values();

	}

}
