package parseFile;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import datastructures.BinaryTree;
import datastructures.BinaryTreeNode;

/**
 * Write a binary tree to an xml file
 * 
 * @author Emma Nguyen
 * @version April 27, 2018
 */
public class ConvertTreeToXML {

	/**
	 * Write a binary tree to an xml file
	 * 
	 * @param tree
	 *            the tree from which data is extracted to be put in the file
	 * @param file
	 *            the file to hold the data of the tree
	 * @return true if the operation was successful, false otherwise
	 */
	public static boolean writeTreeToXML(BinaryTree<String> tree, String file) {

		// create a document builder factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		// build document requires exception handling
		try {

			// create a document builder
			DocumentBuilder builder = factory.newDocumentBuilder();

			// builda a document
			Document doc = builder.newDocument();

			// create a tree of the elements created with the binary tree's data
			Element root = createElementTree(tree, doc);

			// append the tree to the document
			doc.appendChild(root);

			// output to the document to a file
			output(doc, new StreamResult(new File(file)));

			// return that the operation was successful
			return true;
		} catch (Exception e) {

			// print the exception
			e.printStackTrace();
		}

		// return false as the operation failed
		return false;
	}

	/**
	 * Output the specified document to a stream result
	 * 
	 * @param doc
	 *            the document to be written to a stream result
	 * @param result
	 *            the stream result to receive the document
	 */
	private static void output(Document doc, StreamResult result) {

		// use transformer factory requires exception handling
		try {

			// create a transformer factory
			TransformerFactory factory = TransformerFactory.newInstance();

			// create a transformer
			Transformer transformer = factory.newTransformer();

			// create a dom source that holds the document
			DOMSource source = new DOMSource(doc);

			// transform the source into the specified stream result
			transformer.transform(source, result);

		} catch (TransformerConfigurationException tce) {

			// print the exception
			tce.printStackTrace();
		} catch (TransformerException te) {

			// print the exception
			te.printStackTrace();
		}
	}

	/**
	 * Build a tree of elements created with the document. The elements' data are
	 * from the specified binary tree
	 * 
	 * @param tree
	 *            the tree of data from which data is to be got
	 * @param doc
	 *            the document to create elements
	 * @return the root of the element tree
	 */
	private static Element createElementTree(BinaryTree<String> tree, Document doc) {

		// create elements starting from the binary tree's root
		return createElement(tree.getRoot(), doc);
	}

	/**
	 * Create an element from a binary tree node with a document
	 * 
	 * @param node
	 *            the binary tree node currently processed whose data is used to
	 *            create an element
	 * @param doc
	 *            the document to help with create an element
	 * @return the element built with the binary tree node and the document
	 */
	private static Element createElement(BinaryTreeNode<String> node, Document doc) {

		// if the node is a leaf node
		if (node.isLeaf()) {

			// create a thing element
			Element thing = doc.createElement("thing");

			// set the element's content
			thing.setTextContent(node.getData());

			// return the thing element
			return thing;
		}

		// if the node is not a leaf node
		else {

			// create a question element
			Element question = doc.createElement("question");

			// set the element's attribute
			question.setAttribute("text", node.getData().toString());

			// if the node has a left child
			if (node.getLeftChild() != null) {

				// create an answer element
				Element answerYes = doc.createElement("answer");

				// set the answer element's attribute
				answerYes.setAttribute("useranswer", "yes");

				// append the answer element to the question element
				question.appendChild(answerYes);

				// create an element with the left child and append it to the answer element
				answerYes.appendChild(createElement(node.getLeftChild(), doc));
			}

			// if the node has a right child
			if (node.getRightChild() != null) {

				// create an answer element
				Element answerNo = doc.createElement("answer");

				// set the answer element's attribute
				answerNo.setAttribute("useranswer", "no");

				// append the answer element to the question element
				question.appendChild(answerNo);

				// create an element with the right child and append it to the answer element
				answerNo.appendChild(createElement(node.getRightChild(), doc));
			}

			// return the question element
			return question;
		}
	}
}
