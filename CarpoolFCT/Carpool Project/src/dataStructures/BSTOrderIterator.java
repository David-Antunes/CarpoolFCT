package dataStructures;

import dataStructures.BST.BSTNode;

public class BSTOrderIterator<K,V> implements Iterator<Entry<K,V>> {

	Stack<BSTNode<Entry<K, V>>> elements;
	BSTNode<Entry<K,V>> root;

	public BSTOrderIterator(BSTNode<Entry<K, V>> root) {
		elements = new StackInList<BSTNode<Entry<K, V>>>();
		this.root = root;
		
		BSTNode<Entry<K,V>> aux = root;
		
		while(aux != null) {
			elements.push(aux);
			aux = aux.getLeft();
		}
	}

	@Override
	public boolean hasNext() {
		return !elements.isEmpty();
	}

	@Override
	public Entry<K,V> next() throws NoSuchElementException {
		
		BSTNode<Entry<K,V>> next = elements.pop();
		
		if(next.right != null) {
			BSTNode<Entry<K,V>> aux = next.right;
			while (aux != null) {
				elements.push(aux);
				aux = aux.getLeft();
			}
		}
		
		return next.element;
	}

	@Override
	public void rewind() {
		BSTNode<Entry<K,V>> aux = root;
		
		while(aux != null) {
			elements.push(aux);
			aux = aux.getLeft();
		}
	}

}
