
/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		ListIterator it = iterator(); // Constructing an iterator.
		for (int i = 0; i < index; i++) { // moves until index.
			it.next();
		}
		return it.current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (index == 0){ // inserts the node to the start of the list
			addFirst(block);
			return;
		}		
		if (index == size)	{ // inserts the node to the end of the list
			addLast(block);
			return;
		}	
		Node tmp = new Node (block);
		Node prev = first;
		Node current = first.next;
		for (int i = 1; i < index; i++) { 
			prev = current;
			current = current.next;
		} // reached the right index
		prev.next = tmp;
		tmp.next = current;
		size++;
		}


	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node tmp = new Node (block); // creating a new node
		if ( size == 0){
			first = tmp;
			last = tmp;
			size++;
		}
		else{
		  	// adding tmp to the end of the list.
			last.next = tmp; // points the last to current tmp
			last = tmp; // making tmp last Node in the list
			size ++; // update size
	}
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node tmp = new Node (block); // creating a new node
		tmp.next = first; // make the new node point to the first node in the list
		first = tmp; // make the new node the first node on the list
		if (size == 0){
			last = tmp;
		}
		size++; // updating size
	}
	

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index > size || size == 0) { // exceptions
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (index == 0) return first.block;
		else {
		Node tmp = getNode(index);
		return tmp.block;
		}
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		ListIterator it = iterator();
		int counter = 0;
		while (it.hasNext()){
			if (it.current.block.equals(block)){
				return counter;
			}
			it.next();
			counter++;
		} // if exited the loop then the block isnt in the list.
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if (first.block.equals(node.block)) { // if it's the first one
        first = first.next;
        size--;
        if (size == 0) { // if it was the only element
            last = null;
        }
        return;
    }
    Node prev = null;
    Node current = first;
    for (int i = 0; i < size; i++) {
        if (current.block.equals(node.block)) {
            if (current == last) { // if it's the last one
                last = prev;
            }
            if (prev != null) {
                prev.next = current.next; // bypass the node to be removed
            }
            size--;
            return;
        }
        prev = current;
        current = current.next;
    }
	}
	

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) { 
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		if (index > 2000) return;
		Node node = getNode(index);
		remove(node);
	}
	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int nodeIndex = indexOf(block);
		if (nodeIndex == -1) throw new IllegalArgumentException("index must be between 0 and size");
		Node node = getNode(nodeIndex);
		remove (node);
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String str = "";
		ListIterator it = iterator();
		while (it.hasNext()) {
			str += it.current.block + " ";
			it.next();
		}
		return str;
	}
}