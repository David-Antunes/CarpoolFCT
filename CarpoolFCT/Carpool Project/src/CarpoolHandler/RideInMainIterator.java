/**
 * 
 */
package CarpoolHandler;

import dataStructures.Iterator;
import dataStructures.NoSuchElementException;

/**
 * @author Carolina
 *
 */
public class RideInMainIterator implements Iterator<RideWrapper> {
	
	Iterator<Ride> it;

	/**
	 * 
	 */
	public RideInMainIterator(Iterator<Ride> rides) {
		it = rides;
	}

	@Override
	public boolean hasNext() {
		
		return it.hasNext();
	}

	@Override
	public RideWrapper next() throws NoSuchElementException {
		
		return it.next();
	}

	@Override
	public void rewind() {
		it.rewind();
		
	}

}
