package datastructures;

/**
 * Implement a binary tree
 * 
 * @author Emma Nguyen
 * @version April 11, 2018
 */
public class DefaultBinaryTree<T> implements BinaryTree<T> {

	BinaryTreeNode<T> root;

	/**
	 * Construct a binary tree
	 */
	public DefaultBinaryTree() {

		// set root to null
		root = null;
	}

	/**
	 * Get the tree's root
	 */
	@Override
	public BinaryTreeNode<T> getRoot() {

		// return the root
		return root;
	}

	/**
	 * Set the tree's root
	 */
	@Override
	public void setRoot(BinaryTreeNode<T> root) {

		// set the root
		this.root = root;
	}

	/**
	 * Check if the tree is empty
	 */
	@Override
	public boolean isEmpty() {

		// if the root is null, then the tree is empty
		return (root == null);
	}

	/**
	 * Perform in-order traversal of the tree
	 */
	@Override
	public LinkedList<T> inorderTraversal() {

		// create a list
		LinkedList<T> list = new LinkedList<T>();

		// perform in-order traversal and add the tree's nodes to the list
		list = inorderTraverse(list, root);

		// return the list
		return list;
	}

	/**
	 * Recursive helper method to perform in-order traversal of the tree
	 * 
	 * @param list
	 *            a linked list to store the nodes during traversal
	 * @param node
	 *            the node to be visited
	 * @return a list of the tree's nodes after in-order traversal
	 */
	private LinkedList<T> inorderTraverse(LinkedList<T> list, BinaryTreeNode<T> node) {

		// if the node is null (we got to the end of the tree)
		if (node == null) {

			// return the list
			return list;
		}

		// traverse the left subtree of the node
		inorderTraverse(list, node.getLeftChild());

		// insert the current node to the list
		list.insertLast(node.getData());

		// traverse the right subtree of the node
		inorderTraverse(list, node.getRightChild());

		// return the list
		return list;
	}

	/**
	 * Perform pre-order traversal of the tree
	 */
	@Override
	public LinkedList<T> preorderTraversal() {

		// create a list
		LinkedList<T> list = new LinkedList<T>();

		// perform in-order traversal and add the tree's nodes to the list
		list = preorderTraverse(list, root);

		// return the list
		return list;
	}

	/**
	 * Recursive helper method to perform pre-order traversal of the tree
	 * 
	 * @param list
	 *            a linked list to store the nodes during traversal
	 * @param node
	 *            the node to be visited
	 * @return a list of the tree's nodes after pre-order traversal
	 */
	private LinkedList<T> preorderTraverse(LinkedList<T> list, BinaryTreeNode<T> node) {

		// if the node is null (we got to the end of the tree)
		if (node == null) {

			// return the list
			return list;
		}

		// insert the current node to the list
		list.insertLast(node.getData());

		// traverse the left subtree of the node
		preorderTraverse(list, node.getLeftChild());

		// traverse the right subtree of the node
		preorderTraverse(list, node.getRightChild());

		// return the list
		return list;
	}

	/**
	 * Perform post-order traversal of the tree
	 */
	@Override
	public LinkedList<T> postorderTraversal() {

		// create a list
		LinkedList<T> list = new LinkedList<T>();

		// perform in-order traversal and add the tree's nodes to the list
		list = postorderTraverse(list, root);

		// return the list
		return list;
	}

	/**
	 * Recursive helper method to perform post-order traversal of the tree
	 * 
	 * @param list
	 *            a linked list to store the nodes during traversal
	 * @param node
	 *            the node to be visited
	 * @return a list of the tree's nodes after post-order traversal
	 */
	private LinkedList<T> postorderTraverse(LinkedList<T> list, BinaryTreeNode<T> node) {

		// if the node is null (we got to the end of the tree)
		if (node == null) {

			// return the list
			return list;
		}

		// traverse the left subtree of the node
		postorderTraverse(list, node.getLeftChild());

		// traverse the right subtree of the node
		postorderTraverse(list, node.getRightChild());

		// insert the current node to the list
		list.insertLast(node.getData());

		// return the list
		return list;
	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            runtime-parameters
	 */
	public static void main(String[] args) {

		// create a tree corresponding to the Seven Dwarfs

		// create a node for each dwarf
		BinaryTreeNode<String> happy = new DefaultBinaryTreeNode<String>();
		happy.setData("Happy");
		BinaryTreeNode<String> doc = new DefaultBinaryTreeNode<String>();
		doc.setData("Doc");
		BinaryTreeNode<String> bashful = new DefaultBinaryTreeNode<String>();
		bashful.setData("Bashful");
		BinaryTreeNode<String> grumpy = new DefaultBinaryTreeNode<String>();
		grumpy.setData("Grumpy");
		BinaryTreeNode<String> dopey = new DefaultBinaryTreeNode<String>();
		dopey.setData("Dopey");
		BinaryTreeNode<String> sleepy = new DefaultBinaryTreeNode<String>();
		sleepy.setData("Sleepy");
		BinaryTreeNode<String> sneezy = new DefaultBinaryTreeNode<String>();
		sneezy.setData("Sneezy");

		// set the Seven Dwarfs tree's structure
		happy.setLeftChild(doc);
		happy.setRightChild(sleepy);
		doc.setLeftChild(bashful);
		doc.setRightChild(grumpy);
		grumpy.setRightChild(dopey);
		sleepy.setRightChild(sneezy);

		// create a binary tree
		BinaryTree<String> tree = new DefaultBinaryTree<String>();

		// set the tree's root to point to the drawf Happy
		tree.setRoot(happy);

		// perform in-order traversal of the tree
		LinkedList<String> inorderList = tree.inorderTraversal();

		// print out the tree after in-order traversal
		System.out.println("Inorder traversal: " + inorderList.toString());

		// perform pre-order traversal of the tree
		LinkedList<String> preorderList = tree.preorderTraversal();

		// print out the tree after pre-order traversal
		System.out.println("Preorder traversal: " + preorderList.toString());

		// perform post-order traversal of the tree
		LinkedList<String> postorderList = tree.postorderTraversal();

		// print out the tree after post-order traversal
		System.out.println("Postorder traversal: " + postorderList.toString());
	}

}
