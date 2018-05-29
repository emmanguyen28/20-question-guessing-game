package datastructures;

/**
 * A class to make a node
 * 
 * @author Emma Nguyen
 * @version March 22, 2018
 */
public class LinkedListNode<T> {

	/* instance variables */

	// the data that a node holds
	private T data;

	// the next node
	private LinkedListNode<T> next;

	/**
	 * Construct a node
	 */
	public LinkedListNode() {
	}

	/**
	 * Set the data of that node
	 * 
	 * @param data
	 *            the data to be set
	 */
	public void setData(T data) {

		// set the data
		this.data = data;
	}

	/**
	 * Get the data of a node
	 * 
	 * @return the data of a node
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the next node of a node
	 * 
	 * @param node
	 *            the node to be set
	 */
	public void setNext(LinkedListNode<T> node) {

		// set the next node
		this.next = node;
	}

	/**
	 * Get the next node of a node
	 * 
	 * @return the next node
	 */
	public LinkedListNode<T> getNext() {
		return next;
	}

	/**
	 * Get the string value of a node
	 * 
	 * @return the string representation of the node
	 */
	public String toString() {
		return String.valueOf(data);
	}

}
