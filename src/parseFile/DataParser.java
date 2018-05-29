package parseFile;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import datastructures.DefaultBinaryTree;
import datastructures.DefaultBinaryTreeNode;

/**
 * Parse a xml file and build a binary tree with the data
 * 
 * @author Emma Nguyen
 * @version April 21, 2018
 */
public class DataParser {

	/**
	 * Take in an XML file and return a binary tree built with the file's data
	 * 
	 * @param file
	 *            an XML to be parsed
	 * @return a binary tree build with the file's data
	 */
	public static DefaultBinaryTree<String> parseXML(File file) {

		// create a document builder factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// create a document builder
		DocumentBuilder builder;

		// file I/O requires exception handling
		try {

			// initialize the builder with the builder factory
			builder = factory.newDocumentBuilder();

			// get the document from the file
			Document document = builder.parse(file);

			// parse the document
			return (parseDocument(document));

		}
		// handle the possible exceptions
		catch (ParserConfigurationException pce) {

			// print out the exceptions
			pce.printStackTrace();
		} catch (SAXException e) {

			// print out the exceptions
			e.printStackTrace();
		} catch (IOException e) {

			// print out the exceptions
			e.printStackTrace();
		}

		// return the tree
		return null;
	}

	/**
	 * Parse a document
	 * 
	 * @param document
	 *            the document to be parsed
	 */
	private static DefaultBinaryTree<String> parseDocument(Document document) {

		// create a binary tree
		DefaultBinaryTree<String> tree = new DefaultBinaryTree<String>();

		// get the root node of the document
		Element docRoot = (Element) document.getDocumentElement();

		// parse the document and see the tree's root to be the document's root node
		tree.setRoot(parseQuestionElement(docRoot));

		// return the binary tree
		return tree;
	}

	/**
	 * Parse a question element
	 * 
	 * @param elem
	 *            the element to be parsed
	 * @return a binary tree node that holds the parsed element
	 */
	private static DefaultBinaryTreeNode<String> parseQuestionElement(Element elem) {

		// if the element is a question element
		if (elem.getTagName().equals("question")) {

			// create a node with the element's content
			DefaultBinaryTreeNode<String> node = new DefaultBinaryTreeNode<String>(elem.getAttribute("text"));

			// get the element's children
			NodeList children = elem.getChildNodes();

			// keep track of the current child while checking all of the children
			Element currentElt;

			// the left and right child of the node
			DefaultBinaryTreeNode<String> left = null;
			DefaultBinaryTreeNode<String> right = null;

			// loop through the children
			for (int i = 0; i < children.getLength(); i++) {

				// if the child is an element
				if (children.item(i) instanceof Element) {

					// set the current element to be the current child
					currentElt = (Element) children.item(i);

					// if the current element is a yes answer
					if (currentElt.getAttribute("useranswer").equals("yes")) {

						// parse the answer element and save the result to the left child of the node
						left = parseAnswerElement(currentElt);
					}

					// if the current element is a no answer
					else if (currentElt.getAttribute("useranswer").equals("no")) {

						// parse the answer element and save the result to the right child of the node
						right = parseAnswerElement(currentElt);
					}
				}

				// set the left child and right child of the node
				node.setLeftChild(left);
				node.setRightChild(right);
			}

			// return the node
			return node;
		}

		// if the element is not a question element, return null
		return null;
	}

	/**
	 * Parse an answer element
	 * 
	 * @param elem
	 *            the element to be parsed
	 * @return a binary tree node that holds the parsed element
	 */
	private static DefaultBinaryTreeNode<String> parseAnswerElement(Element elem) {

		// the current element
		Element currentElt;

		// a binary tree node that holds the answer
		DefaultBinaryTreeNode<String> answer;

		// if the element is answer element
		if (elem.getTagName().equals("answer")) {

			// get the element's children
			NodeList children = elem.getChildNodes();

			// loop through the children
			for (int i = 0; i < children.getLength(); i++) {

				// if a child is an Element
				if (children.item(i) instanceof Element) {

					// set the current element to be the child
					currentElt = (Element) children.item(i);

					// get the tag name of the current element
					String tagName = currentElt.getTagName();

					// if the tag name is thing
					if (tagName.equals("thing")) {

						// create a new node to hold the answer element's content
						answer = new DefaultBinaryTreeNode<String>(currentElt.getTextContent());

						// return the node
						return answer;
					}

					// if the tag name is a question
					else if (tagName.equals("question")) {

						// parse the question element
						return parseQuestionElement(currentElt);
					}
				}
			}
		}

		// if the element is not an answer element, return null
		return null;
	}

}
