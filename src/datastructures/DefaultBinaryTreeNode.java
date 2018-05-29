package datastructures;

/**
 * Implement a binary tree node
 * 
 * @author Emma Nguyen
 * @version April 11, 2018
 */
public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T> {

	/* instance variables */

	// data that the node holds
	private T data;

	// the left child of the node
	private BinaryTreeNode<T> left;

	// the right child of the node
	private BinaryTreeNode<T> right;

	/**
	 * Construct a binary tree node
	 */
	public DefaultBinaryTreeNode() {
	}

	/**
	 * Construct a binary tree node with data parameter
	 * 
	 * @param data
	 *            the data the the node holds
	 */
	public DefaultBinaryTreeNode(T data) {

		// set the data
		this.data = data;

		// set the left and right child to be null
		left = null;
		right = null;
	}

	/**
	 * Get the data of the node
	 */
	@Override
	public T getData() {

		// return the data
		return data;
	}

	/**
	 * Set the node's data
	 */
	@Override
	public void setData(T data) {

		// set the data
		this.data = data;

	}

	/**
	 * Get the node's left child
	 */
	@Override
	public BinaryTreeNode<T> getLeftChild() {

		// return the left child
		return left;
	}

	/**
	 * Get the node's right child
	 */
	@Override
	public BinaryTreeNode<T> getRightChild() {

		// return the right child
		return right;
	}

	/**
	 * Set the left child
	 */
	@Override
	public void setLeftChild(BinaryTreeNode<T> left) {

		// set the left child
		this.left = left;
	}

	/**
	 * Set the right child
	 */
	@Override
	public void setRightChild(BinaryTreeNode<T> right) {

		// set the right child
		this.right = right;
	}

	/**
	 * Check if the node is a leaf
	 */
	@Override
	public boolean isLeaf() {

		// if the node has no children, then it is a leaf
		return ((left == null) && (right == null));
	}

}
