package datastructures;

/**
 * The list interface
 * 
 * @author Emma Nguyen
 * @version March 22, 2018
 */
public interface List<T> {

	/**
	 * Add (insert) data at a specific index in the list
	 * 
	 * @param index
	 *            the index to insert the data
	 * @param data
	 *            the data to be inserted
	 */
	public void add(int index, T data);

	/**
	 * Get data stored at specific index in list
	 * 
	 * @param index
	 *            the index to get the data
	 * @return the data at that index
	 */
	public T get(int index);

	/**
	 * Delete data at a specific index in the list
	 * 
	 * @param index
	 *            the index whose data is to be deleted
	 */
	public void delete(int index);

	/**
	 * Get the number of elements in the list
	 * 
	 * @return the number of elements in the list
	 */
	public int size();

	/**
	 * Check whether the list is empty
	 * 
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Return a string representation of the list
	 * 
	 * @return a string that represents the list
	 */
	public String toString();
}
