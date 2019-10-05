/**
 * 
 */
package dataStructures;

/**
 * @author AED_19_20
 * @param <K>
 *
 */
public interface IteratorEntry<K,V> {

	// Returns true iff the iteration has more elements.
	// In other words, returns true if next would return an element.
	boolean hasNext( );
	 
	// Returns the next element in the iteration.
	// @throws NoSuchElementException if !hasNext()
	java.util.Map.Entry<K,V> next( ) throws NoSuchElementException;
	 
	// Restarts the iteration.
	// After rewind, if the iteration is not empty, next will return the first element.
	void rewind( );
}
