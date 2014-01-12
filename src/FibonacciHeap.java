/*************************************************************************
 *  
 * FibonacciHeap
 * 
 * The FibonacciHeap class holds the structure of a heap
 *
 *************************************************************************/

/**
 * The class holds the structure of the heap.
 * 
 * @author balajiiyer
 */

public class FibonacciHeap {

	/**
	 * Creates a node of Fibonacci heap.
	 * 
	 */
	public static class Entry {
		/** Holds the data object */
		private int data;
		/** Holds the key value */
		private int key;
		/** Holds the parent node */
		private Entry parent;
		/** Holds the first child node. */
		private Entry child;
		/** Holds the right sibling node. */
		private Entry right;
		/** Holds the left sibling node. */
		private Entry left;
		/** Holds the children count */
		private int degree;
		/**
		 * Marks true if child was removed since the node was added to its
		 * parent.
		 */
		private boolean mark;

		/**
		 * Create an entry with data and key. Initializes the right and left
		 * pointers. Forms a circular doubly-linked list.
		 * 
		 * @param data
		 * @param key
		 */
		public Entry(int data, int key) {
			this.data = data;
			this.key = key;
			right = this;
			left = this;
		}

		/**
		 * Returns the element of heap entry.
		 * 
		 * @return data
		 */
		public Object getValue() {
			return data;
		}
	}

	/** Holds the minimum node in the heap */
	private Entry min;

	/** Holds the node count in the heap. */
	private int count;

	/**
	 * Creates an heap object with no elements.
	 */
	public FibonacciHeap() {
		min = null;
	}

	/**
	 * Executes a cascading cut operation. Cuts the y from its parent and then
	 * repeats the same for parent and so on up the tree
	 * 
	 * @param y
	 */
	private void cascadingCut(Entry y) {
		Entry z = y.parent;
		// if parent exists
		if (z != null) {
			if (y.mark) {
				// cut it from parent,if marked
				cut(y, z);
				// cut its parent
				cascadingCut(z);
			} else {
				// Set y marked,if unmarked previously
				y.mark = true;
			}
		}
	}

	/**
	 * Removes all elements from heap.
	 */
	public void clear() {
		min = null;
		count = 0;
	}

	/**
	 * Consolidate trees of equal degree
	 * 
	 */
	private void consolidate() {
		int arraySize = count + 1;
		Entry entryArray[] = new Entry[arraySize];
		// Initialize the array
		for (int i = 0; i < arraySize; i++) {
			entryArray[i] = null;
		}
		// Count the root nodes.
		int numRoots = 0;
		Entry x = min;
		if (x != null) {
			numRoots++;
			x = x.right;
			while (x != min) {
				numRoots++;
				x = x.right;
			}
		}
		while (numRoots > 0) {
			int d = x.degree;
			Entry next = x.right;
			while (entryArray[d] != null) {
				// If nodes of equal degree,make one child of another
				Entry y = entryArray[d];
				// Decide on key value
				if (x.key > y.key) {
					Entry temp = y;
					y = x;
					x = temp;
				}
				link(y, x);
				entryArray[d] = null;
				d++;
			}
			entryArray[d] = x;
			x = next;
			numRoots--;
		}
		// Set min to null
		// Construct the root list from array entries
		min = null;
		for (int i = 0; i < arraySize; i++) {
			if (entryArray[i] != null) {
				if (min != null) {
					entryArray[i].left.right = entryArray[i].right;
					entryArray[i].right.left = entryArray[i].left;
					entryArray[i].left = min;
					entryArray[i].right = min.right;
					min.right = entryArray[i];
					entryArray[i].right.left = entryArray[i];
					if (entryArray[i].key < min.key) {
						min = entryArray[i];
					}
				} else {
					min = entryArray[i];
				}
			}
		}
	}

	/**
	 * Removes x from the child list of y.
	 * 
	 * @param x
	 * @param y
	 */
	private void cut(Entry x, Entry y) {
		x.left.right = x.right;
		x.right.left = x.left;
		y.degree--;
		if (y.child == x) {
			y.child = x.right;
		}
		if (y.degree == 0) {
			y.child = null;
		}
		x.left = min;
		x.right = min.right;
		min.right = x;
		x.right.left = x;
		x.parent = null;
		x.mark = false;
	}

	/**
	 * Decreases the key value for a heap node
	 * 
	 * @param x
	 * @param k
	 * @exception IllegalArgumentException
	 * 
	 */
	public void decreaseKey(Entry x, int k) {
		if (k > x.key) {
			throw new IllegalArgumentException(
					"The key value cannot be increased");
		}
		x.key = k;
		Entry y = x.parent;
		if (y != null && x.key < y.key) {
			cut(x, y);
			cascadingCut(y);
		}
		if (min != null && x.key < min.key) {
			min = x;
		}
	}

	/**
	 * Deletes a node from the heap
	 * 
	 * @param x
	 *            node to remove from heap.
	 */
	public void delete(Entry x) {
		decreaseKey(x, Integer.MIN_VALUE);
		removeMin();
	}

	/**
	 * Check if the heap is empty or not.
	 * 
	 * @return true if empty,else otherwise
	 */
	public boolean isEmpty() {
		return min == null;
	}

	/**
	 * Adds a new element into heap.
	 * 
	 * @param x
	 * @param key
	 * @return created node
	 */
	public Entry insert(int x, int key) {
		Entry node = new Entry(x, key);
		if (min != null) {
			node.left = min;
			node.right = min.right;
			min.right = node;
			node.right.left = node;
			if (key < min.key) {
				min = node;
			}
		} else {
			min = node;
		}
		count++;
		return node;
	}

	/**
	 * Makes node y a child of node x.
	 * 
	 * @param y
	 * @param x
	 */
	private void link(Entry y, Entry x) {
		y.left.right = y.right;
		y.right.left = y.left;
		y.parent = x;
		if (x.child == null) {
			x.child = y;
			y.right = y;
			y.left = y;
		} else {
			y.left = x.child;
			y.right = x.child.right;
			x.child.right = y;
			y.right.left = y;
		}
		x.degree++;
		y.mark = false;
	}

	/**
	 * Returns the smallest element in the heap.
	 * 
	 * @return min
	 */
	public Entry min() {
		return min;
	}

	/**
	 * Removes the smallest element from the heap.
	 * 
	 * @return data
	 */
	public Object removeMin() {
		Entry z = min;
		if (z != null) {
			int numKids = z.degree;
			Entry x = z.child;
			Entry tempRight;
			while (numKids > 0) {
				tempRight = x.right;
				x.left.right = x.right;
				x.right.left = x.left;
				x.left = min;
				x.right = min.right;
				min.right = x;
				x.right.left = x;
				x.parent = null;
				x = tempRight;
				numKids--;
			}
			z.left.right = z.right;
			z.right.left = z.left;
			if (z == z.right) {
				min = null;
			} else {
				min = z.right;
				consolidate();
			}
			count--;
			return z.data;
		}
		return null;
	}

	/**
	 * Returns the size of the heap.
	 * 
	 * @return count
	 */
	public int size() {
		return count;
	}

	/**
	 * Combines two Fibonacci heaps into a new one.
	 * 
	 * @param heap1
	 * @param heap2
	 * @return combined heap
	 */
	public static FibonacciHeap union(FibonacciHeap heap1, FibonacciHeap heap2) {
		FibonacciHeap H = new FibonacciHeap();
		if ((heap1 != null) && (heap2 != null)) {
			H.min = heap1.min;
			if (H.min != null) {
				if (heap2.min != null) {
					H.min.right.left = heap2.min.left;
					heap2.min.left.right = H.min.right;
					H.min.right = heap2.min;
					heap2.min.left = H.min;
					if (heap2.min.key < heap1.min.key) {
						H.min = heap2.min;
					}
				}
			} else {
				H.min = heap2.min;
			}
			H.count = heap1.count + heap2.count;
		}
		return H;
	}

}
