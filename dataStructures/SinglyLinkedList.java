package dataStructures;

import java.io.Serializable;

public class SinglyLinkedList<E> implements List<E>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Node at the head of the list.
	protected SListNode<E> head;

	// Node at the tail of the list.
	protected SListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;
		
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public SinglyLinkedList() {
		head = null;
		tail = null;
		currentSize = 0;
	}
	
	@Override
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public int size() {
		return currentSize;
	}

	@Override
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public Iterator<E> iterator() throws NoElementException {
		
		if (currentSize==0) throw new NoElementException("List is empty.");
		return new SinglyLLIterator<E>(head);
	
	}

	@Override
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(n)
	 * Medium case : O(n/2)
	 */
	public int find(E element) {
		
		int pos=0;
		SListNode<E> auxNo;
		boolean found=false;
		//TODO
		Iterator<E> it = iterator();
		while(it.hasNext() && found == false) {
		auxNo = new SListNode<E>(it.next());
			if(auxNo.getElement().equals(element)) {
				found = true;
			}
			pos++;
		}
		
		return pos;
		
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public E getFirst() throws NoElementException {
		if (currentSize==0) {
			throw new NoElementException("No such element.");
		}
		return head.getElement();
		
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public E getLast() throws NoElementException {
		if (currentSize==0) {
			throw new NoElementException("No such element.");
		}
		return tail.getElement();
		
	}
	
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(n)
	 * Medium case : O(n/2)
	 */
	@Override
	public E get(int position) throws InvalidPositionException {

		if (position<0 || position>=currentSize) {
			throw new InvalidPositionException("Invalid position.");
		}
		return getNode(position).getElement();
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public void addFirst(E element) {
		
		if(!isEmpty()) {
			SListNode<E> s = new SListNode<E>(element, head);
			head = s;
			
		}
		else {
			SListNode<E> s = new SListNode<E>(element,null);
			head = s;
			tail = s;
			
		}
		currentSize++;

	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public void addLast(E element) {
		
		if(!isEmpty()) {
			SListNode<E> s = new SListNode<E>(element,null);
			tail.setNext(s);
			tail = s;
			
		}
		else {
			SListNode<E> s = new SListNode<E>(element,null);
			head = s;
			tail = s;
		}
		currentSize++;

	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(n)
	 * Medium case : O(n/2)
	 */
	@Override
	public void add(int position, E element) throws InvalidPositionException {
		
		if (position<0 || position >currentSize) {
			throw new InvalidPositionException("Invalid Position.");
		}
		
		if (position==0) { 
			addFirst(element);
		}
		else if (position==currentSize) { 
				addLast(element);
		}
			else {
				SListNode<E> aux=getNode(position-1);
	
				SListNode<E> s = new SListNode<E>(element,aux.getNext());
				aux.setNext(s);
				currentSize++;
			}
		
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public E removeFirst() throws NoElementException {
		
		if (currentSize==0) {
			throw new NoElementException("No such element.");
		}
		SListNode<E> s = head;
		if(currentSize == 1) {
			head = null;
			tail = null;	
		}
		else {
			head = s.getNext();
		}
		currentSize --;
		return s.getElement();
	}

	/*Temporal Complexity:
	 * best case : O(n)
	 * worst case : O(n)
	 * Medium case : O(n)
	 */
	@Override
	public E removeLast() throws NoElementException {
		
		if (currentSize==0) {
			throw new NoElementException("No such element.");
		}
		SListNode<E> s = tail;
		if(currentSize == 1) {
			head = null;
			tail = null;
		}
		else {
			SListNode<E> nodeTail = getNode(currentSize-2);
			nodeTail.setNext(null);
			tail = nodeTail;
		}
		currentSize --;
		return s.getElement();
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(n)
	 * Medium case : O(n/2)
	 */
	@Override
	public E remove(int position) throws InvalidPositionException {
		
		if(position<0 || position>=currentSize) {
			throw new InvalidPositionException("Invalid position.");
		}
		if (position==0) {
			return removeFirst();
		}
		if (position==currentSize-1) {
			return removeLast();
		}
		SListNode<E> aux = getNode(position-1);
		SListNode<E> s = aux.getNext();
		
		aux.setNext(s.getNext());
		
		currentSize--;
		return s.getElement();
	
	}
	
	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(n)
	 * Medium case : O(n/2)
	 */
	private SListNode<E> getNode(int position){
		SListNode<E> aux=head;
		for(int i=0;i<position;i++) {
			aux=aux.getNext();
		}
		return aux;
	}

}
