package datastructures;

/***
 * Create a linked list**
 * 
 * @author Emma Nguyen*@version March 22,2018
 */
public class LinkedList<T> {

	/* declare instance variables */

	// head of the list
	private LinkedListNode<T> head;

	// tail of the list
	private LinkedListNode<T> tail;

	/**
	 * Construct a linked list
	 */
	public LinkedList() {

		// set the head to be null
		head = null;

		// set the tail to be null
		tail = null;
	}

	/**
	 * Front insertion (insert a node at the head of the list)
	 *
	 * @param data
	 *            the element to be inserted
	 */
	public void insertFirst(T data) {

		// create a new node
		LinkedListNode<T> newNode = new LinkedListNode<T>();

		// set the node's data
		newNode.setData(data);

		// set the node's link
		newNode.setNext(head);

		// if the tail is null
		if (tail == null) {

			// set the new node to be the tail
			tail = newNode;
		}

		// update the head
		head = newNode;
	}

	/**
	 * Insert a node after a specific node
	 *
	 * @param currentNode
	 *            the current node after which the element is to be inserted
	 * @param data
	 *            the element to be inserted
	 */
	public void insertAfter(LinkedListNode<T> currentNode, T data) {

		// create a new node
		LinkedListNode<T> newNode = new LinkedListNode<T>();

		// set the node's data
		newNode.setData(data);

		// link the node to the node after the current node
		newNode.setNext(currentNode.getNext());

		// link the current node to the node
		currentNode.setNext(newNode);
	}

	/**
	 * Insert an element to the end of the list
	 *
	 * @param data
	 *            the element to be inserted
	 */
	public void insertLast(T data) {

		// create a new node
		LinkedListNode<T> newNode = new LinkedListNode<T>();

		// set the node's data
		newNode.setData(data);

		// if the tail is null
		if (tail == null) {

			// set the head to be the node
			head = newNode;
		} else {

			// if the tail is not null, link the tail to the node;
			tail.setNext(newNode);
		}

		// update the tail;
		tail = newNode;
	}

	/**
	 * Delete the first element in the list
	 */
	public void deleteFirst() {

		// if the list is empty, return as there is nothing to delete
		if (isEmpty())
			return;

		// set the head to be the node after it
		head = head.getNext();

		// if after deletion, head becomes null
		if (head == null)

			// tail also becomes null
			tail = null;

	}

	/**
	 * Delete the last element in the list
	 */
	public void deleteLast() {

		// if the list is empty, return as there is nothing to delete
		if (isEmpty())
			return;

		// find the node before the last node
		LinkedListNode<T> second = getNode(size() - 2);

		// if the node before the last node does not exist
		if (second == null) {

			// then there is only one node left in the list, set both head and tail to null
			head = null;
			tail = null;

		} else {

			// if there exists a node before the last node

			// set the second to last node to null;
			second.setNext(null);

			// update the tail
			tail = second;

		}

	}

	/**
	 * Delete the node after a specific node
	 *
	 * @param currentNode
	 */
	public void deleteNext(LinkedListNode<T> currentNode) {

		// link the current node to the one after the one after it
		currentNode.setNext(currentNode.getNext().getNext());

	}

	/**
	 * Get the element at the first node
	 *
	 * @return the data the first node holds
	 */
	public T getFirst() {

		// if the list is empty, return null;
		if (isEmpty())
			return null;

		// return the data
		return (head.getData());
	}

	/**
	 * Get the first node
	 *
	 * @return the first node in the list
	 */
	public LinkedListNode<T> getFirstNode() {

		// return the head of the list
		return head;
	}

	/**
	 * Get the last element in the list
	 *
	 * @return the data at the last node
	 */
	public T getLast() {

		// if the list is empty, return null
		if (isEmpty())
			return null;

		// return the data at the last node
		return (tail.getData());
	}

	/**
	 * Get the last node of the list
	 *
	 * @return the last node in the list
	 */
	public LinkedListNode<T> getLastNode() {

		// the last node of the list
		return tail;
	}

	/**
	 * Get an element at a specific index in the linked list
	 */
	public T get(int index) {

		// if the node does not exist
		if (getNode(index) == null)

			// return null
			return null;
		else

			// if the node exists, get the node and get its data
			return (getNode(index).getData());
	}

	/**
	 * Get a node at a certain index
	 *
	 * @param index
	 *            the index of the node
	 * @return the node at the specified index
	 */
	private LinkedListNode<T> getNode(int index) {

		// set the index
		int i = 0;

		// traverse the linked list
		LinkedListNode<T> node = head;
		while (node != null) {

			// if the index of a node is equal to the index specified in the
			// parameter
			if (i == index)

				// return the node
				return node;

			// else, increase the index
			i++;

			// get to the next node
			node = node.getNext();
		}

		// If we traversed the entire list and cannot find the node, then the
		// node does not exist and return null
		return null;
	}

	/**
	 * Get the size of the list
	 *
	 * @return the list's size
	 */
	public int size() {

		// create a size variable
		int size = 0;

		// traverse the linked list
		for (LinkedListNode<T> node = head; node != null; node = node.getNext()) {

			// count the number of nodes
			size++;
		}

		// return the size
		return size;
	}

	/**
	 * Check whether the list is empty
	 *
	 * @return true if the list is empty; false otherwise
	 */
	public boolean isEmpty() {

		// if the head is null, the list is empty; if the head is not null, the
		// list is not empty
		return (head == null);
	}

	/**
	 * Get a string representation of the linked list
	 */
	public String toString() {

		// create a string to hold the list
		String list = "";

		// traverse the linked list
		LinkedListNode<T> node = head;
		while (node != null) {

			// get a string of each node and add to the list string
			list += node.toString() + " ";

			// get to the next node
			node = node.getNext();
		}

		// return the string holding the list
		return list;
	}

}
