package GuessingGame;

import java.io.File;
import java.util.ArrayList;

import datastructures.BinaryTreeNode;
import datastructures.DefaultBinaryTree;
import parseFile.DataParser;

/**
 * Create a controller for the 20 questions guesing game. This class defines
 * what happens when the player chooses yes or no
 * 
 * @author Emma Nguyen
 * @version April 27, 2018
 */
public class GuessingGameController {

	// a binary tree
	private DefaultBinaryTree<String> tree;

	// a binary tree node to keep track of the current node
	private BinaryTreeNode<String> current;

	// a guessing game view
	private GuessingGameView view;

	/**
	 * Construct a guessing game controller
	 * 
	 * @param view
	 *            the view of the controller
	 */
	public GuessingGameController(GuessingGameView view) {

		// set the view
		this.view = view;

		// initiate the tree
		tree = new DefaultBinaryTree<String>();

		// create a file of the data
		File data = new File("support/data/SkincareData.xml");

		// parse the file's data and save it in the binary tree
		tree = DataParser.parseXML(data);

		// get the current node, to be the tree root
		current = tree.getRoot();
	}

	/**
	 * Get the current node
	 * 
	 * @return the current node of the tree
	 */
	public BinaryTreeNode<String> getCurrentElem() {

		// return the current node
		return current;
	}

	/**
	 * Define what happens when the player chooses yes
	 */
	public void chooseYes() {

		// if there is more left child (we got the thing)
		if (current.getLeftChild() != null) {

			// travel to the left child
			current = current.getLeftChild();

			// after traveling to the left child, if there is no more left child
			if (current.getLeftChild() == null) {

				// this means we got the thing , thus show the thing
				view.showThing("So you chose our service of " + current.getData() + " indeed!");
			}

			// there is more child, this means there's more question
			else

				// show the question
				view.getQuestion().setText(current.getData());
		}
	}

	/**
	 * Define what happens when the player chooses no
	 */
	public void chooseNo() {

		// there is no more right child (no more question)
		if (current.getRightChild() == null) {

			// show that the thing does not exist
			view.showThing(
					"Oops! We don't have the item that you're thinking of. Please play the unlimited version of me~~~");

		}

		// if there is more right child (there's more question)
		else {

			// travel to the right child
			current = current.getRightChild();

			// show the question
			view.getQuestion().setText(current.getData());
		}
	}

	/**
	 * Get all the leaf nodes of the binary tree
	 * 
	 * @return a list containing all the leaf nodes of the tree
	 */
	public ArrayList<String> getLeafNodes() {

		// create a list to hold all the leaf nodes
		ArrayList<String> leafs = new ArrayList<String>();

		// get the leaf nodes with the helper
		leafs = getLeafNodesHelper(leafs, tree.getRoot());

		// return the list of leaf nodes
		return leafs;
	}

	/**
	 * Helper method to get the leaf nodes
	 * 
	 * @param list
	 *            a list to store the leaf nodes
	 * @param node
	 *            the current node the method is working on
	 * @return a list containing all the leaf nodes
	 */
	private ArrayList<String> getLeafNodesHelper(ArrayList<String> list, BinaryTreeNode<String> node) {

		// if the current node is null
		if (node == null)

			// return null
			return null;

		// if the current node is a leaf node
		if (node.isLeaf()) {

			// add the node to the list
			list.add(node.getData());
		} else {

			// if the node has a left child
			if (node.getLeftChild() != null)

				// get all the leaf nodes of the left child
				getLeafNodesHelper(list, node.getLeftChild());

			// if the node has a right child
			if (node.getRightChild() != null)

				// get all the leaf nodes of the right child
				getLeafNodesHelper(list, node.getRightChild());
		}

		// return the list of leaf nodes
		return list;
	}

}
