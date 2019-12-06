package dataStructures;

import java.io.Serializable;
/**
 * @author AED_19_20
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class BST<K extends Comparable<K>, V> implements SortedMap<K, V>, Serializable {
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

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		BSTNode(E elem, BSTNode<E> parent, BSTNode<E> left, BSTNode<E> right) {
			this.parent = parent;
			this.left = left;
			this.right = right;
			element = elem;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		BSTNode(E elem) {
			this(elem, null, null, null);
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		E getElement() {
			return element;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		BSTNode<E> getLeft() {
			return left;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		BSTNode<E> getRight() {
			return right;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		BSTNode<E> getParent() {
			return parent;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		boolean isInternal() {
			return (left != null || right != null);
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		boolean isLeftChild() {
			return parent.left == this;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		boolean isRightChild() {
			return parent.right == this;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		int numberOfChildren() {
			int n = 0;
			if (left != null)
				n++;
			if (right != null)
				n++;
			return n;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		BSTNode<E> getSon() {
			if (numberOfChildren() == 2)
				return left;
			else if (left == null)
				return right;
			else if (right == null)
				return left;
			else
				return null;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		void removeChild(BSTNode<E> child) {
			if (left == child)
				left = null;
			else
				right = null;
		}

	}

	// The root
	protected BSTNode<Entry<K, V>> root;

	// Number of elements
	protected int currentSize;

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	public BST() {
		root = null;
		currentSize = 0;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(n) average case : O(n)
	 */
	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new BSTOrderIterator<K, V>(root);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(n) average case : O(n)
	 */
	@Override
	public Iterator<K> keys() throws NoElementException {
		return new KeyIterator<>(iterator());
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(n) average case : O(n)
	 */
	@Override
	public Iterator<V> values() throws NoElementException {
		return new ValueIterator<>(iterator());
	}

	/*
	 * Temporal Complexity if Balanced Tree: best case : O(1) worst case : O(n) if
	 * not balanced, if it is then O(log(n)) average case : O(h). h is the height of
	 * the node to search and n the total number of nodes
	 */
	protected BSTNode<Entry<K, V>> findNode(BSTNode<Entry<K, V>> n, K key) {
		BSTNode<Entry<K, V>> res = null;
		if (n != null) {
			int num = key.compareTo(n.getElement().getKey());
			if (num == 0)
				res = n;
			else if (num < 0)
				res = findNode(n.getLeft(), key);
			else
				res = findNode(n.getRight(), key);
		}
		return res;
	}

	/*
	 * Temporal Complexity if Balanced Tree: best case : O(1) worst case : O(n) if
	 * not balanced, if it is then O(log(n)) average case : O(h). h is the height of
	 * the node to search and n the total number of nodes
	 */
	protected BSTNode<Entry<K, V>> findFatherNode(K key) {
		return findFatherNode(null, root, key);
	}

	/*
	 * Temporal Complexity if Balanced Tree: best case : O(1) worst case : O(n) if
	 * not balanced, if it is then O(log(n)) average case : O(h). h is the height of
	 * the node to search and n the total number of nodes
	 */
	protected BSTNode<Entry<K, V>> findFatherNode(BSTNode<Entry<K, V>> p, BSTNode<Entry<K, V>> n, K key) {
		BSTNode<Entry<K, V>> res = p;
		if (n != null) {
			int num = key.compareTo(n.getElement().getKey());
			;
			if (num == 0)
				res = n.getParent();
			else if (num < 0)
				res = findFatherNode(n, n.getLeft(), key);
			else
				res = findFatherNode(n, n.getRight(), key);
		}
		return res;
	}

	/*
	 * Temporal Complexity if Balanced Tree: best case : O(1) worst case : O(n) if
	 * not balanced, if it is then O(log(n)) average case : O(h). h is the height of
	 * the node to search and n the total number of nodes
	 */
	@Override
	public V find(K key) {
		BSTNode<Entry<K, V>> res = findNode(root, key);
		if (res == null)
			return null;
		return res.getElement().getValue();
	}

	/*
	 * Temporal Complexity if Balanced Tree: best case : O(1) worst case : O(n)
	 * average case : O(h). h is the height of the node to search and n the total
	 * number of nodes
	 */
	@Override
	public V insert(K key, V value) {
		// TODO
		V v = null;
		if (isEmpty()) {
			root = new BSTNode<Entry<K, V>>(new EntryClass<K, V>(key, value), null, null, null);
			currentSize++;
			return v;

		}

		BSTNode<Entry<K, V>> father = findFatherNode(key);
		int num = key.compareTo(father.getElement().getKey());

		if (num < 0) {
			if (father.left == null) {
				father.left = new BSTNode<Entry<K, V>>(new EntryClass<K, V>(key, value), father, null, null);
				currentSize++;
				return v;
			} else {
				BSTNode<Entry<K, V>> res = father.left;
				v = res.getElement().getValue();
				Entry<K, V> s = new EntryClass<K, V>(key, value);
				res.element = s;
				return v;
			}
		} else {
			if (father.right == null) {
				father.right = new BSTNode<Entry<K, V>>(new EntryClass<K, V>(key, value), father, null, null);
				currentSize++;
				return v;
			} else {
				BSTNode<Entry<K, V>> res = father.right;
				v = res.getElement().getValue();
				Entry<K, V> s = new EntryClass<K, V>(key, value);
				res.element = s;
				return v;
			}
		}

	}

	/*
	 * Temporal Complexity if Balanced Tree: best case : O(1) worst case : O(n)
	 * average case : O(h). h is the height of the node to search and n the total
	 * number of nodes
	 */
	@Override
	public V remove(K key) {

		V v = null;
		BSTNode<Entry<K, V>> remove = findNode(root, key);
		if (remove == null) {
			return v;
		}

		v = remove.getElement().getValue();

		if (root == remove) {
			if (currentSize == 1) {
				root = null;
				currentSize--;
				return v;
			} else if (root.numberOfChildren() == 1) {
				root = root.getSon();
				currentSize--;
				return v;
			}
		}

		else { // root != remove
			BSTNode<Entry<K, V>> parent = remove.parent;

			if (remove.numberOfChildren() == 0) {
				parent.removeChild(remove);
				currentSize--;
				return v;
			} else if (remove.numberOfChildren() == 1) {
				if (remove.isRightChild()) {
					parent.right = remove.getSon();
				} else { // remove.isLeftChild()
					parent.left = remove.getSon();
				}
				remove.getSon().parent = parent;
				currentSize--;
				return v;
			}
		}

		BSTNode<Entry<K, V>> replace = maxNode(remove.left);
		remove(replace.getElement().getKey());
		remove.element = replace.element;
		currentSize--;
		return v;

	}

	/*
	 * Temporal Complexity if Balanced Tree: best case : O(1) worst case : O(n)
	 * average case : O(h). h is the height of the node to search and n the total
	 * number of nodes
	 */
	@Override
	public Entry<K, V> minEntry() throws NoElementException {
		if (this.isEmpty())
			throw new NoElementException();
		return this.minNode(root).getElement();
	}

	/*
	 * Temporal Complexity if Balanced Tree: best case : O(1) worst case : O(n)
	 * average case : O(h). h is the height of the node to search and n the total
	 * number of nodes
	 */
	@Override
	public Entry<K, V> maxEntry() throws NoElementException {
		if (this.isEmpty())
			throw new NoElementException();
		return this.maxNode(root).getElement();
	}

	/*
	 * Temporal Complexity if Balanced Tree: best case : O(1) worst case : O(n)
	 * average case : O(h). h is the height of the node to search and n the total
	 * number of nodes
	 */
	protected BSTNode<Entry<K, V>> minNode(BSTNode<Entry<K, V>> node) {
		if (node.getLeft() == null)
			return node;
		return this.minNode(node.getLeft());
	}

	/*
	 * Temporal Complexity if Balanced Tree: best case : O(1) worst case : O(n)
	 * average case : O(h). h is the height of the node to search and n the total
	 * number of nodes
	 */
	// Precondition: node != null.
	protected BSTNode<Entry<K, V>> maxNode(BSTNode<Entry<K, V>> node) {
		if (node.getRight() == null)
			return node;
		return this.maxNode(node.getRight());
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public int size() {
		return currentSize;
	}

}
