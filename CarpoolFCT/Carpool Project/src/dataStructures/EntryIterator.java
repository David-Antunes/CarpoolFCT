package dataStructures;
/**
 * @author AED_19_20
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class EntryIterator<K,V> implements Iterator<Entry<K,V>> {

	ArrayIterator<Map<K,V>> table;
	Map<K,V> currTable;
	Iterator<Entry<K,V>> listIt;
	
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(n)
	 * Medium case : O(n/2)
	 */
	public EntryIterator( Map<K, V>[] itTable) {
		table = new ArrayIterator<>(itTable, itTable.length);
		
		currTable = table.next();
		while(currTable.isEmpty())
			currTable = table.next();
		
		listIt = currTable.iterator();
		
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case :O(n)
	 * Medium case : O(n/2)
	 */
	@Override
	public boolean hasNext() {
		if(listIt.hasNext())
			return true;
		boolean found = false;
		
		while(!found && table.hasNext() ) {
			
			currTable = table.next();
			if(!currTable.isEmpty()) {
				found = true;
			}
			
		}
		return found;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case :O(1)
	 * Medium case : O(1)
	 */
	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		if ( !this.hasNext() )
			throw new NoSuchElementException();
		
		if(listIt.hasNext())
			return listIt.next();
		
		listIt = currTable.iterator();
		return listIt.next();
		
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case :O(n)
	 * Medium case : O(n/2)
	 */
	@Override
	public void rewind() {
		table.rewind();
		currTable = table.next();
		while(currTable.isEmpty())
			currTable = table.next();
		
		listIt = currTable.iterator();
		
		
	}

}
