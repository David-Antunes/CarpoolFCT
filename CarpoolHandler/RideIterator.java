/**
 * 
 */
package CarpoolHandler;


import dataStructures.Iterator;
import dataStructures.NoSuchElementException;
import dataStructures.SortedMap;

/**
 * @author Carolina
 *
 */
public class RideIterator<K,V> implements Iterator<RideWrapper> {
	
	/*
	 * Iterator for rides that iterates the sortedMap of sortedMaps
	 */
	
	Iterator<SortedMap<String, Ride>> bigIt;
	Iterator<Ride> smallIt;

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(log(n))
	 * average case : O(log(n))
	 */
	public RideIterator( SortedMap<Date, SortedMap<String, Ride>>  map) {
		
		
		bigIt = map.values();
		smallIt = bigIt.next().values();
		
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * average case : O(1)
	 */
	@Override
	public boolean hasNext() {
		
		
		return bigIt.hasNext() || smallIt.hasNext();
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(log(n))
	 * average case : O(log(n))
	 */
	@Override
	public RideWrapper next() throws NoSuchElementException {
		
		if(!hasNext())
			throw new NoSuchElementException();
		
		if(smallIt.hasNext())
			return smallIt.next();
		
		
			smallIt = bigIt.next().values();
			return smallIt.next();
			
		
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(log(n))
	 * average case : O(log(n))
	 */
	@Override
	public void rewind() {
		bigIt.rewind();
		smallIt = bigIt.next().values();
		
	}

}
