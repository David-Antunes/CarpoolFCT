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
public class AVL<K extends Comparable<K>, V> extends AdvancedBST<K, V> implements SortedMap<K, V>, Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	static class AVLNode<E> extends BSTNode<E> implements Serializable {
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;
		// Height of the node
		protected int height;

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		public AVLNode(E elem) {
			super(elem);
			height = 1;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		public AVLNode(E element, AVLNode<E> parent, AVLNode<E> left, AVLNode<E> right) { // char balance,
			super(element, parent, left, right);
			height = 1 + Math.max(getHeight((AVLNode<E>) left), getHeight((AVLNode<E>) right));
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		protected int getHeight(AVLNode<E> no) {
			if (no == null)
				return 0;
			return no.getHeight();
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		public int getHeight() {
			return height;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		public boolean isBalance() {
			int dif = getHeight((AVLNode<E>) left) - getHeight((AVLNode<E>) right);
			return dif == 0 || dif == -1 || dif == 1;
		}

		/*
		 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
		 */
		public int setHeight() {
			height = 1 + Math.max(getHeight((AVLNode<E>) left), getHeight((AVLNode<E>) right));
			return height;
		}
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	protected AVL(AVLNode<Entry<K, V>> n) {
		root = n;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	public AVL() {
		this(null);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	/**
	 * Return a child of p with greater height
	 */
	protected AVLNode<Entry<K, V>> tallerChild(AVLNode<Entry<K, V>> p) {
		// TODO
		int height = 0;
		AVLNode<Entry<K, V>> nodeLeft = (AVLNode<Entry<K, V>>) p.getLeft();
		if (nodeLeft != null) {
			height = nodeLeft.getHeight();
		}

		int heightRight = 0;
		AVLNode<Entry<K, V>> nodeRight = (AVLNode<Entry<K, V>>) p.getRight();
		if (nodeRight != null) {
			heightRight = nodeRight.getHeight();
		}
		if (heightRight > height) {
			height = heightRight;
			return nodeRight;
		}

		return nodeLeft;
	}

	/**
	 * Rebalance method called by insert and remove. Traverses the path from zPos to
	 * the root. For each node encountered, we recompute its height and perform a
	 * trinode restructuring if it's unbalanced. the rebalance is completed with
	 * O(log n)running time
	 */
	protected void rebalance(AVLNode<Entry<K, V>> zPos) {
		if (zPos.isInternal())
			zPos.setHeight();

		while (zPos != null && zPos.getParent() != null) { // traverse up the tree towards the root
			zPos = (AVLNode<Entry<K, V>>) zPos.getParent();
			zPos.setHeight();
			if (!zPos.isBalance()) {
				// perform a trinode restructuring at zPos's tallest grandchild
				// If yPos (tallerChild(zPos)) denote the child of zPos with greater height.
				// Finally, let xPos be the child of yPos with greater height
				AVLNode<Entry<K, V>> xPos = tallerChild((AVLNode<Entry<K, V>>) tallerChild(zPos));
				zPos = (AVLNode<Entry<K, V>>) restructure(xPos); // tri-node restructure (from parent class)
				((AVLNode<Entry<K, V>>) zPos.getLeft()).setHeight(); // recompute heights
				((AVLNode<Entry<K, V>>) zPos.getRight()).setHeight();
				zPos.setHeight();
			}
		}
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(n)) Medium case :
	 * O(log(n))
	 */
	@Override
	public V insert(K key, V value) {
		// TODO

		V valueToReturn = null;

		AVLNode<Entry<K, V>> newNode = null;

		if (isEmpty()) {
			newNode = new AVLNode<Entry<K, V>>(new EntryClass<K, V>(key, value), null, null, null);
			root = newNode;
			currentSize++;
			return valueToReturn;

		}

		AVLNode<Entry<K, V>> father = (AVLNode<Entry<K, V>>) findFatherNode(key);
		int num = key.compareTo(father.getElement().getKey());

		if (num < 0) {
			if (father.left == null) {
				newNode = new AVLNode<Entry<K, V>>(new EntryClass<K, V>(key, value), father, null, null);
				father.left = newNode;
				currentSize++;

			} else {
				AVLNode<Entry<K, V>> res = (AVLNode<Entry<K, V>>) father.left;
				valueToReturn = res.getElement().getValue();
				Entry<K, V> s = new EntryClass<K, V>(key, value);
				res.element = s;
				newNode = res;

			}
		} else {
			if (father.right == null) {
				newNode = new AVLNode<Entry<K, V>>(new EntryClass<K, V>(key, value), father, null, null);
				father.right = newNode;
				currentSize++;

			} else {
				AVLNode<Entry<K, V>> res = (AVLNode<Entry<K, V>>) father.right;
				valueToReturn = res.getElement().getValue();
				Entry<K, V> s = new EntryClass<K, V>(key, value);
				res.element = s;
				newNode = res;

			}
		}

		newNode = (AVLNode<Entry<K, V>>) newNode.parent;

		if (newNode != null)
			rebalance(newNode); // rebalance up from the insertion node
		return valueToReturn;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(n)) Medium case :
	 * O(log(n))
	 */
	@Override
	public V remove(K key) {
		// TODO
		V valueToReturn = null;
		AVLNode<Entry<K, V>> node = null;
		AVLNode<Entry<K, V>> remove = (AVLNode<Entry<K, V>>) findNode(root, key);
		if (remove == null) {
			return valueToReturn;
		}

		valueToReturn = remove.getElement().getValue();

		if (root == remove) {
			if (currentSize == 1) {
				root = null;
				currentSize--;

			} else if (root.numberOfChildren() == 1) {
				root = root.getSon();
				root.parent = null;
				currentSize--;

			}
		}

		else { // root != remove

			node = (AVLNode<Entry<K, V>>) remove.parent; // father of node where the key was
			if (remove.numberOfChildren() == 0) {
				node.removeChild(remove);
				currentSize--;

			} else if (remove.numberOfChildren() == 1) {
				if (remove.isRightChild()) {
					node.right = remove.getSon();
				} else { // remove.isLeftChild()
					node.left = remove.getSon();
				}
				remove.getSon().parent = node;
				currentSize--;

			}
		}
		if (remove.left != null && remove.right != null) {
			BSTNode<Entry<K, V>> replace = maxNode(remove.left);
			remove(replace.getElement().getKey());
			remove.element = replace.element;
			currentSize--;
		}
		if (node != null) 
			rebalance(node); // rebalance up from the node
		return valueToReturn;
	}

}
