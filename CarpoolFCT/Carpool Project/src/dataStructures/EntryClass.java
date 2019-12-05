/**
 * 
 */
package dataStructures;

import java.io.Serializable;

/**
 * @author AED_19_20
 *
 */
public class EntryClass<K, V> implements Entry<K, V>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private K key;
	private V value;
	
	public EntryClass(K k, V v){
		key=k;
		value=v;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public K getKey() {
		return key;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public V getValue() {
		return value;
	}

}
