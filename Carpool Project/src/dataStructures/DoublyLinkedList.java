package dataStructures;

public class DoublyLinkedList<E> implements TwoWayList<E> {
	// Node at the head of the list.
	protected DListNode<E> head;

	// Node at the tail of the list.
	protected DListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;

	public DoublyLinkedList() {
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public TwoWayIterator<E> iterator() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("List is empty.");
		return new DoublyLLIterator<E>(head, tail);
	}

	@Override
	public int find(E element) {
		int pos = 0;
		if (isEmpty())
			return -1;

		DListNode<E> auxNo = head.getNext();
		boolean found = false;
		while (!found && auxNo.getNext() != null) {
			if (auxNo.getNext().equals(element))
				found = true;
			else {
				pos++;
				DListNode<E> aux = auxNo.getNext();
				auxNo = aux;
			}
		}
		if (!found)
			pos = -1;
		return pos;
	}

	@Override
	public E getFirst() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
		return getNode(0).getElement();
	}

	@Override
	public E getLast() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
		return getNode(currentSize - 1).getElement();
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize)
			throw new InvalidPositionException("Invalid position.");
		return getNode(position).getElement();
	}

	@Override
	public void addFirst(E element) {
		if (head == null) {
			DListNode<E> first = new DListNode<E>(element);
			head = first;
			tail = first;
		} else {
			DListNode<E> first = new DListNode<E>(element, null, head);
			head.setPrevious(first);
			head = first;
		}
		currentSize++;
	}

	@Override
	public void addLast(E element) {
		if (tail == null) {
			DListNode<E> last = new DListNode<E>(element);
			head = last;
			tail = last;
		} else {
			DListNode<E> last = new DListNode<E>(element, tail, null);
			tail.setNext(last);
			tail = last;
		}
		currentSize++;
	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position < 0 || position > currentSize)
			throw new InvalidPositionException("Invalid Position.");
		if (position == 0)
			addFirst(element);
		else if (position == currentSize)
			addLast(element);
		else {
			addMiddle(position, element);
		}

	}

	private void addMiddle(int position, E element) {
		DListNode<E> nodeInPos = getNode(position);
		DListNode<E> newNode = new DListNode<E>(element, nodeInPos.getPrevious(), nodeInPos);
		nodeInPos.getPrevious().setNext(newNode);
		nodeInPos.setPrevious(newNode);
		currentSize++;
	}

	private E removeMiddle(int position) {
		DListNode<E> nodeToRemove = getNode(position);
		
		nodeToRemove.getPrevious().setNext(nodeToRemove.getNext());
		nodeToRemove.getNext().setPrevious(nodeToRemove.getPrevious());
		currentSize--;
		return nodeToRemove.getElement();
	}

	private DListNode<E> getNode(int position) {
		DListNode<E> aux = head;
		for (int i = 1; i <= position; i++)
			aux = aux.getNext();
		return aux;
	}

	@Override
	public E removeFirst() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
		DListNode<E> removed = head;

		if (currentSize == 1) {
			head = null;
			tail = null;
		} else {
			head = head.getNext();
			head.setPrevious(null);
		}
		currentSize--;
		return removed.getElement();
	}

	@Override
	public E removeLast() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
		DListNode<E> removed = tail;

		if (currentSize == 1) {
			head = null;
			tail = null;
		} else {
			tail = tail.getPrevious();
			tail.setNext(null);
		}
		currentSize--;
		return removed.getElement();

	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize)
			throw new InvalidPositionException("Invalid position.");
		if (position == 0)
			return removeFirst();
		if (position == currentSize - 1)
			return removeLast();
		return removeMiddle(position);
	}

}
