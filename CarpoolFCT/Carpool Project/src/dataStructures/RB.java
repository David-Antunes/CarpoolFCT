package dataStructures;

public class RB<K extends Comparable<K>, V> extends AdvancedBST<K, V> implements SortedMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static class RBNode<E> extends BSTNode<E> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected boolean isRed;// we add a color field to a BTNode

		public RBNode(E elem) {
			super(elem);
			isRed = true;
		}

		public RBNode(E element, RBNode<E> parent, RBNode<E> left, RBNode<E> right) {
			super(element, parent, left, right);
			isRed = true;
		}

		public void setColour(boolean colour) {
			isRed = colour;
		}

		public boolean isRed() {
			return isRed;
		}

	}

	protected RB(RBNode<Entry<K, V>> n) {
		root = n;
	}

	public RB() {
		this(null);
	}

	@Override
	public V insert(K key, V value) {
		// TODO
		V valueToReturn = null;
		RBNode<Entry<K, V>> newNode = null;

		if (isEmpty()) {
			newNode = new RBNode<Entry<K, V>>(new EntryClass<K, V>(key, value), null, null, null);
			root = newNode;
			currentSize++;
			newNode.setColour(false);
			return valueToReturn;

		}
		RBNode<Entry<K, V>> father = (RBNode<Entry<K, V>>) findFatherNode(key);
		if (father == null)
			father = (RBNode<Entry<K, V>>) root;

		int num = key.compareTo(father.getElement().getKey());

		if (num < 0) {
			if (father.left == null) {
				newNode = new RBNode<Entry<K, V>>(new EntryClass<K, V>(key, value), father, null, null);
				father.left = newNode;
				currentSize++;

			} else {
				newNode = (RBNode<Entry<K, V>>) father.left;
				valueToReturn = newNode.getElement().getValue();
				newNode.element = new EntryClass<K, V>(key, value);

			}
		} else {
			if (father.right == null) {
				newNode = new RBNode<Entry<K, V>>(new EntryClass<K, V>(key, value), father, null, null);
				father.right = newNode;
				currentSize++;

			} else {
				newNode = (RBNode<Entry<K, V>>) father.right;
				valueToReturn = newNode.getElement().getValue();
				newNode.element = new EntryClass<K, V>(key, value);
			}
		}

		// insert the new Entry (if find(key)==null)
		// or set the new value (if find(key)!=null)
		newNode.setColour(true); // Red
		if (newNode.getParent() == null)
			newNode.setColour(false); // Black
		else
			remedyDoubleRed(newNode); // fix a double-red color violation
		return valueToReturn;
	}

	// pre: !isRoot(posZ)
	private void remedyDoubleRed(RBNode<Entry<K, V>> posZ) {
		// TODO
		boolean isUncleBlack = true;
		RBNode<Entry<K, V>> uncle, node = null;
		RBNode<Entry<K, V>> posV = (RBNode<Entry<K, V>>) posZ.getParent();
		if (posV.isRed()) {
			if (posV.isLeftChild()) {
				uncle = (RBNode<Entry<K, V>>) (posV.getParent().getRight());
				if (uncle != null)
					isUncleBlack = !uncle.isRed();
			} else {
				uncle = (RBNode<Entry<K, V>>) (posV.getParent().getLeft());
				if (uncle != null)
					isUncleBlack = !uncle.isRed();
			}
			if (isUncleBlack) {
				if (posV.getParent() == root) {
					restructure(posZ);
					((RBNode<Entry<K, V>>) root).setColour(false);
					((RBNode<Entry<K, V>>) root.getLeft()).setColour(true);
					((RBNode<Entry<K, V>>) root.getRight()).setColour(true);
				} else {
					node = (RBNode<Entry<K, V>>) restructure(posZ);
					node.setColour(false);
					((RBNode<Entry<K, V>>) node.getLeft()).setColour(true);
					((RBNode<Entry<K, V>>) node.getRight()).setColour(true);
				}
			} else {
				uncle.setColour(false);
				posV.setColour(false);
				if (posV.getParent() != root) {
					((RBNode<Entry<K, V>>) posV.getParent()).setColour(true);
					RBNode<Entry<K, V>> grandParent = (RBNode<Entry<K, V>>) posV.getParent();
					if (((RBNode<Entry<K, V>>) grandParent).isRed())
						remedyDoubleRed((RBNode<Entry<K, V>>) grandParent);
				}
			}
		}

	}

	// we have a double red: posZ and posV
	// Case black uncle ou null: trinode restructuring
	// Case red uncle: recoloring

	@Override
	public V remove(K key) {
//		// TODO
//		// Remove as BST remove
//		RBNode<Entry<K, V>> node = ;
//
//		if (node == null)
//			return null;
//
//		V valueToReturn = node.element.getValue();
//		super.remove(key);
//		if (!node.isRed()) {
//			if (!node.isInternal()) {
//				remedyDoubleBlack(node);
//			} else {
//				if (node.left != null)
//					((RBNode<Entry<K, V>>) node.left).setColour(false);
//				if (node.right != null)
//					((RBNode<Entry<K, V>>) node.right).setColour(false);
//			}
//		}

		V valueToReturn = null;
		RBNode<Entry<K, V>> node = null;
		RBNode<Entry<K, V>> remove = (RBNode<Entry<K, V>>) findNode(root, key);
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

			node = (RBNode<Entry<K, V>>) remove.parent; // father of node where the key was
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

		if (!remove.isRed()) {
			if (!remove.isInternal()) {
				remedyDoubleBlack(remove);
			} else {
				if (node.left != null)
					((RBNode<Entry<K, V>>) node.left).setColour(false);
				if (node.right != null)
					((RBNode<Entry<K, V>>) node.right).setColour(false);
			}
		}

		// case red node: end
		// case black node with a red children: recoloring (set children black)
		// case black node without child: remedyDoubleBlack(node)
		return valueToReturn;
	}

	/** Remedies a double black violation at a given node caused by removal. */
	protected void remedyDoubleBlack(RBNode<Entry<K, V>> posR) {
		RBNode<Entry<K, V>> node, sibling, father;
		sibling = null;
		node = null;
		father = (RBNode<Entry<K, V>>) posR.getParent();

		if (posR.getLeft() == (father.left)) {
			sibling = (RBNode<Entry<K, V>>) father.right;
		} else {
			if (posR.getRight() == (father.right)) {
				sibling = (RBNode<Entry<K, V>>) father.left;
			}
		}
		if (sibling == null) {
			if (father == root) {
				father.setColour(false);
			} else {
				remedyDoubleBlack(father);
			}
		} else if (!sibling.isRed()) {
			if (sibling.numberOfChildren() == 1) {
				if (sibling.left != null) {
					if (((RBNode<Entry<K, V>>) sibling.left).isRed()) {
						node = (RBNode<Entry<K, V>>) restructure(sibling.left);
					}
				} else if (sibling.right != null) {
					if (((RBNode<Entry<K, V>>) sibling.right).isRed()) {
						node = (RBNode<Entry<K, V>>) restructure(sibling.right);
					}
				}
				node.setColour(father.isRed());
				((RBNode<Entry<K, V>>) node.right).setColour(false);
				((RBNode<Entry<K, V>>) node.left).setColour(false);
			} else {
				sibling.setColour(true);
				father.setColour(false);
				if (father == root) {
					father.setColour(false);
				} else {
					remedyDoubleBlack(father);
				}
			}
		} else {
			if(sibling.isLeftChild())
			restructure(sibling.left);
			else
				restructure(sibling.right);
			sibling.setColour(false);
			father.setColour(true);
			remedyDoubleBlack(posR);
		}

//		RBNode<Entry<K, V>> node, sibling, father;
//		node = posR;
//		father = (RBNode<Entry<K, V>>) node.getParent();
//		sibling = (RBNode<Entry<K, V>>) ((node.isLeftChild()) ? father.right : father.left);
//		boolean isSiblingBlack = !sibling.isRed;
//
//		if (isSiblingBlack) {
//			if (!sibling.isInternal()) {
//				sibling.setColour(true);
//			} else {
//				if (sibling.left != null) {
//					if (((RBNode<Entry<K, V>>) sibling.left).isRed()) {
//						restructure(sibling.left);
//					} else if (sibling.right != null) {
//						if (((RBNode<Entry<K, V>>) sibling.right).isRed())
//							restructure(sibling.right);
//					} else {
//						sibling.setColour(true);
//					}
//
//				} else if (sibling.right != null) {
//					if (((RBNode<Entry<K, V>>) sibling.right).isRed()) {
//						restructure(sibling.right);
//						((RBNode<Entry<K, V>>) sibling).setColour(false);
//						((RBNode<Entry<K, V>>) father).setColour(false);
//					} else
//						sibling.setColour(true);
//				} else {
//					sibling.setColour(true);
//				}
//			}
//		} else {
//			restructure(sibling);
//			sibling.setColour(false);
//			father.setColour(true);
//			remedyDoubleBlack(father);
//		}

		// TODO
		// Case sibling is black and has a red children: trinode restructuring
		// Case sibling is black and has two black children: recoloring
		// Case red sibling: adjustment + remedyDoubleBlack(posR)
	}
}
