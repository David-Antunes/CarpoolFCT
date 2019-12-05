package dataStructures;

import java.io.Serializable;

public class BST<K extends Comparable<K>,V> implements SortedMap<K,V>, Serializable {
	 /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	static class BSTNode<E> implements Serializable {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		protected BSTNode<E> parent;
		protected BSTNode<E> left;
		protected BSTNode<E> right;
		protected E element;


		BSTNode(E elem,BSTNode<E> parent,BSTNode<E> left,BSTNode<E> right){
			this.parent=parent;
			this.left=left;
			this.right=right;
			element=elem;
		}
		BSTNode(E elem){
			this(elem,null,null,null);
		}

		E getElement() {
			return element;
		}
		//...
		BSTNode<E> getLeft() {
			return left;
		}

		BSTNode<E> getRight() {
			return right;
		}

		BSTNode<E> getParent() {
			return parent;
		}
		boolean isInternal() {
			return (left != null || right != null);
		}

		boolean isLeftChild() {
			return parent.left == this;
		}

		boolean isRightChild() {
			return parent.right == this;
		}

		int numberOfChildren() {
			int n = 0;
			if(left != null)
				n++;
			if(right != null)
				n++;
			return n;
		}

		BSTNode<E> getSon() {
			if(numberOfChildren() == 2)
				return left;
			else if(left == null)
				return right;
			else if(right == null)
				return left;
			else
				return null;
		}

		void removeChild(BSTNode<E> child) {
			if(left == child)
				left = null;
			else
				right = null;
		}

	}

	//The root
	protected BSTNode<Entry<K,V>> root;

	//Number of elements
	protected int currentSize;

	public BST() {
		root = null;
		currentSize = 0;
	}

	@Override
	public Iterator<Entry<K,V>> iterator() {
		return new BSTOrderIterator<K,V>(root);
	}

	@Override
	public Iterator<K> keys() throws NoElementException {

		return new KeyIterator<>(iterator());
	}
	@Override
	public Iterator<V> values() throws NoElementException {
		return new ValueIterator<>(iterator());
	}

	protected BSTNode<Entry<K,V>> findNode(BSTNode<Entry<K,V>> n, K key) {
		BSTNode<Entry<K,V>> res=null;
		if (n!=null) {
			int num= key.compareTo(n.getElement().getKey());
			if (num==0)
				res=n;
			else if (num<0)
				res=findNode(n.getLeft(),key);
			else
				res=findNode(n.getRight(),key);
		}
		return res;
	}


	protected BSTNode<Entry<K,V>> findFatherNode(K key) {
		return findFatherNode(null,root,key);
	}

	protected BSTNode<Entry<K,V>> findFatherNode(BSTNode<Entry<K,V>> p, BSTNode<Entry<K,V>> n, K key) {
		BSTNode<Entry<K,V>> res=p;
		if (n!=null) {
			int num= key.compareTo(n.getElement().getKey());;
			if (num==0)
				res=n.getParent();
			else if (num<0)
				res=findFatherNode(n,n.getLeft(),key);
			else
				res=findFatherNode(n,n.getRight(),key);
		}
		return res;
	}

	@Override
	public V find(K key) {
		BSTNode<Entry<K,V>> res=findNode(root,key);
		if (res==null)
			return null;
		return res.getElement().getValue();
	}
	@Override
	public V insert(K key, V value) {
		// TODO
		V v = null;
		if (isEmpty()) {
			root = new BSTNode<Entry<K, V>>(new EntryClass<K,V>(key,value), null, null, null);
			currentSize ++;
			return v;

		}

		BSTNode<Entry<K,V>> father = findFatherNode(key);
		int num = key.compareTo(father.getElement().getKey());

		if(num < 0) {
			if(father.left == null) {
				father.left = new BSTNode<Entry<K,V>>(new EntryClass<K,V>(key,value),father,null,null);
				currentSize ++;
				return v;
			}
			else {
				BSTNode<Entry<K,V>> res = father.left;
				v = res.getElement().getValue();
				Entry<K,V> s = new EntryClass<K,V>(key, value);
				res.element = s;
				return v;
			}
		}
		else {
			if(father.right == null) {
				father.right = new BSTNode<Entry<K,V>>(new EntryClass<K,V>(key,value),father,null,null);
				currentSize ++;
				return v;
			}
			else {
				BSTNode<Entry<K,V>> res = father.right;
				v = res.getElement().getValue();
				Entry<K,V> s = new EntryClass<K,V>(key, value);
				res.element = s;
				return v;
			}
		}


	}
	@Override
	public V remove(K key) {

		V v = null;
		BSTNode<Entry<K,V>> remove = findNode(root, key);
		if(remove == null){
			return v;
		}

		v = remove.getElement().getValue();

		if(root == remove) {
			if(currentSize == 1) {
				root = null;
				currentSize --;
				return v;
			}
			else if(root.numberOfChildren() == 1){
				root = root.getSon();
				currentSize --;
				return v;
			}
		}

		else { // root != remove
			BSTNode<Entry<K, V>> parent = remove.parent;

			if(remove.numberOfChildren() == 0) {
				parent.removeChild(remove);
				currentSize--;
				return v;
			}
			else if(remove.numberOfChildren() == 1) {
				if(remove.isRightChild()) {
					parent.right = remove.getSon();
				}
				else { //remove.isLeftChild()
					parent.left = remove.getSon();
				}
				remove.getSon().parent = parent;
				currentSize--;
				return v;
			}
		}

		BSTNode<Entry<K,V>> replace = maxNode(remove.left);
		remove(replace.getElement().getKey());
		remove.element = replace.element;
		currentSize--;
		return v;

	}
	@Override
	public Entry<K, V> minEntry() throws NoElementException {
		if ( this.isEmpty() )
			throw new NoElementException();
		return this.minNode(root).getElement();
	}
	@Override
	public Entry<K, V> maxEntry() throws NoElementException {
		if ( this.isEmpty() )
			throw new NoElementException();
		return this.maxNode(root).getElement();
	}

	protected BSTNode<Entry<K,V>> minNode(BSTNode<Entry<K,V>> node) {
		if ( node.getLeft() == null )
			return node;
		return this.minNode(node.getLeft());
	}

	// Precondition: node != null.
	protected BSTNode<Entry<K,V>> maxNode( BSTNode<Entry<K,V>> node ){
		if ( node.getRight() == null )
			return node;
		return this.maxNode(node.getRight());
	}

	@Override
	public boolean isEmpty() {
		return currentSize==0;
	}

	@Override
	public int size() {
		return currentSize;
	}


}
