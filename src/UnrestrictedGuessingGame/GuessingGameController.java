package UnrestrictedGuessingGame;

import java.io.File;
import java.util.ArrayList;

import datastructures.BinaryTree;
import datastructures.BinaryTreeNode;
import datastructures.DefaultBinaryTree;
import datastructures.DefaultBinaryTreeNode;
import parseFile.ConvertTreeToXML;
import parseFile.DataParser;

public class GuessingGameController {

	// a binary tree
	private BinaryTree<String> tree;

	// another binary tree to hold the updated version of the previous tree
	private BinaryTree<String> updatedTree;

	// the current node in the tree
	private BinaryTreeNode<String> current;

	// a guessing game view
	private GuessingGameView view;

	/**
	 * Construct a guessing game controller
	 * 
	 * @param view
	 *            the view of this controller
	 */
	public GuessingGameController(GuessingGameView view) {

		// set the view
		this.view = view;

		// create a binary tree
		tree = new DefaultBinaryTree<String>();

		// parse the data from an xml file and save it in the binary tree
		tree = DataParser.parseXML(new File("support/data/SkincareData.xml"));

		// set the current node to be the tree's node
		current = tree.getRoot();

		// set the updated tree to be the tree
		updatedTree = tree;
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

		// if there is no more right child (no more question)
		if (current.getRightChild() == null) {

			// get the new thing that the player is thinking of from the player
			String thing = view.getNewThing();

			// if the player did not provide any info on the thing
			if (thing == null) {

				// view not enough info message
				view.viewNotEnoughInfoMessage();
			}

			// if the player provided info on the thing
			else {

				// get a yes-no question that would determine the thing from the player
				String question = view.getQuestionForThing();

				// if the player did not provide any info on the question
				if (question == null) {

					// view not enough info message
					view.viewNotEnoughInfoMessage();
				}

				// if the player provided info on the question
				else {

					// get the answer to the question from the player
					String answer = view.getAnswerToQuestionForThing();

					// if the player did not provide any info on the answer
					if (answer == null) {

						// view not enough info message
						view.viewNotEnoughInfoMessage();
					}

					// if the player provided info on the thing, question, and answer
					else {

						// create a new node for the new thing
						BinaryTreeNode<String> newThing = new DefaultBinaryTreeNode<String>(thing.toUpperCase());

						// create a new node for the question about the thing
						BinaryTreeNode<String> newQuestion = new DefaultBinaryTreeNode<String>(question);

						// set the question node to be right child of the current node
						current.setRightChild(newQuestion);

						// if answer to the question is yes
						if (answer.toLowerCase().equals("yes")) {

							// set the thing to be the left child of the question
							newQuestion.setLeftChild(newThing);
						}

						// if answer to the question is no
						else if (answer.toLowerCase().equals("no")) {

							// set the thing to be the right child of the question
							newQuestion.setRightChild(newThing);
						}

						// save the updated tree
						updatedTree = tree;

						// save the updated tree to the XML file for the next game (update the xml data
						// file)
						ConvertTreeToXML.writeTreeToXML(updatedTree, "support/data/SkincareData.xml");

						// view start again message
						view.viewStartAgainMessage();
					}
				}
			}
		}

		// if there is more right child
		else {

			// travel to the right child
			current = current.getRightChild();

			// set the question label to the the right child's data (question)
			view.getQuestion().setText(current.getData());
		}
	}

	/**
	 * Start a new game
	 */
	public void newGame() {

		// set the tree to be the updated tree
		tree = updatedTree;

		// set the current node to be the tree's root
		current = tree.getRoot();

		// set the updated tree to be the tree
		updatedTree = tree;
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
