package dataStructures;

public class SinglyLinkedList<E> implements List<E> {
	// Node at the head of the list.
	protected SListNode<E> head;

	// Node at the tail of the list.
	protected SListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;

	public SinglyLinkedList() {

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
	public Iterator<E> iterator() throws NoElementException {
		return new SinglyLLIterator<E>(head);
	}

	@Override
	public int find(E element) {
		int pos = 0;
		if (isEmpty())
			return -1;

		SListNode<E> auxNo = head.getNext();
		boolean found = false;
		while (!found && auxNo.getNext() != null) {
			if (auxNo.getNext().equals(element))
				found = true;
			else {
				pos++;
				SListNode<E> aux = auxNo.getNext();
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
		return get(0);
	}

	@Override
	public E getLast() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
		return get(currentSize - 1);
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		SListNode<E> aux = head;
		for (int i = 1; i <= position; i++)
			aux = aux.getNext();
		return aux.getElement();
	}

	@Override
	public void addFirst(E element) {
		if (head == null) {
			SListNode<E> first = new SListNode<E>(element);
			head = first;
			tail = first;
		} else {
			SListNode<E> first = new SListNode<E>(element, head);
			head = first;
		}
		currentSize++;
	}

	@Override
	public void addLast(E element) {
		if (tail == null) {
			SListNode<E> last = new SListNode<E>(element);
			head = last;
			tail = last;
		} else {
			SListNode<E> last = new SListNode<E>(element, null);
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

	@Override
	public E removeFirst() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
		SListNode<E> removed = head;

		if (currentSize == 1) {
			head = null;
			tail = null;
		} else {
			head = head.getNext();
		}
		currentSize--;
		return removed.getElement();
	}

	@Override
	public E removeLast() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("No such element.");
		SListNode<E> removed = tail;

		if (currentSize == 1) {
			head = null;
			tail = null;
		} else {
			tail = getNode(currentSize - 1);
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

	private void addMiddle(int position, E element) {
		SListNode<E> nodeInPos = getNode(position - 1);
		SListNode<E> newNode = new SListNode<E>(element, nodeInPos.getNext());
		nodeInPos.setNext(newNode);
		currentSize++;
	}

	private E removeMiddle(int position) {
		SListNode<E> node = getNode(position - 1);
		SListNode<E> nodeToRemove = node.getNext();

		node.setNext(nodeToRemove.getNext());
		currentSize--;

		return nodeToRemove.getElement();
	}

	private SListNode<E> getNode(int position) {
		SListNode<E> aux = head;
		for (int i = 1; i <= position; i++)
			aux = aux.getNext();
		return aux;
	}

}
