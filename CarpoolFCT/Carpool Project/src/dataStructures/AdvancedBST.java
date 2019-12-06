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
public class AdvancedBST<K extends Comparable<K>, V> extends BST<K, V> implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	/**
	 * Performs a single left rotation rooted at Y node. Node X was a right child of
	 * Y before the rotation, then Y becomes the left child of X after the rotation.
	 * 
	 * @param Y - root of the rotation
	 * @pre: Y has a right child
	 */
	protected void rotateLeft(BSTNode<Entry<K, V>> Y) {

		BSTNode<Entry<K, V>> X = Y.getRight();
		BSTNode<Entry<K, V>> Z = Y.getParent();

		if (Y == root) {
			root = X;
			X.parent = null;
		}

		else if (Y.isLeftChild()) {
			X.parent = Z;
			Z.left = X;
		}

		else {
			X.parent = Z;
			Z.right = X;
		}

		BSTNode<Entry<K, V>> B = X.left;
		Y.right = B;
		if (B != null)
			B.parent = Y;
		X.left = Y;
		Y.parent = X;

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	/**
	 * Performs a single right rotation rooted at Y node. Node X was a left child of
	 * Y before the rotation, then Y becomes the right child of X after the
	 * rotation.
	 * 
	 * @param Y - root of the rotation
	 * @pre: Y has a left child
	 */
	protected void rotateRight(BSTNode<Entry<K, V>> Y) {
		BSTNode<Entry<K, V>> X = Y.getLeft();
		BSTNode<Entry<K, V>> Z = Y.getParent();

		if (Y == root) {
			root = X;
			X.parent = null;
		}

		else if (Y.isLeftChild()) {
			X.parent = Z;
			Z.left = X;
		}

		else {
			X.parent = Z;
			Z.right = X;
		}

		BSTNode<Entry<K, V>> B = X.right;
		Y.left = B;
		if (B != null)
			B.parent = Y;
		X.right = Y;
		Y.parent = X;

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	/**
	 * Performs a tri-node restructuring (a single or double rotation rooted at X
	 * node). Assumes the nodes are in one of following configurations:
	 *
	 * @param X - root of the rotation
	 * 
	 *          <pre>
	 *          z=c       z=c        z=a         z=a
	 *         /  \      /  \       /  \        /  \
	 *       y=b  t4   y=a  t4    t1  y=c     t1  y=b
	 *      /  \      /  \           /  \         /  \
	 *    x=a  t3    t1 x=b        x=b  t4       t2 x=c
	 *   /  \          /  \       /  \             /  \
	 *  t1  t2        t2  t3     t2  t3           t3  t4
	 *          </pre>
	 * 
	 * @return the new root of the restructured subtree
	 */
	protected BSTNode<Entry<K, V>> restructure(BSTNode<Entry<K, V>> x) {

		BSTNode<Entry<K, V>> y = x.parent;
		BSTNode<Entry<K, V>> z = y.parent;
		if (y.isLeftChild()) {
			if (x.isRightChild()) {
				rotateLeft(y);
				rotateRight(z);
				return x;
			} else {
				rotateRight(z);
				return y;
			}
		}

		else {
			if (x.isLeftChild()) {
				rotateRight(y);
				rotateLeft(x.parent);
				return x;
			} else
				rotateLeft(z);
			return y;
		}

	}
}
