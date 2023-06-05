package implementation;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Implementation for a non-selfbalancing Binary Search Tree.
 * 
 * @author King Bass
 *
 * @param <K> The data type of the key of each node of the tree.
 * @param <V> The data type of the data stored within each node of the tree.
 */
public class BinarySearchTree<K, V> {
	
	/**
	 * A node within the Binary Search Tree. Each node has a left child and a 
	 * right child. (Children can be null).
	 * @author King Bass
	 *
	 */
	private class Node {
		private K key;
		private V value;
		private Node left, right;

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Node root;
	private int treeSize, maxEntries;
	private Comparator<K> comparator;
	
	/**
	 * Constructor for the BST that assigns the comparator object and ensures
	 * valid instance variables. 
	 * 
	 * @param comparator The comparator that will compare key's of each node.
	 * @param maxEntries The maximum number of nodes that will be allowed in the
	 * 					 BST.
	 * 
	 * @throws IllegalArgumentException If the comparator object is null or 
	 * 									maxEntries < 1.
	 */
	public BinarySearchTree(Comparator<K> comparator, int maxEntries) 
			throws IllegalArgumentException {
		if (comparator == null || maxEntries < 1) {
			throw new IllegalArgumentException("Illegal Arguments");
		} else {
			this.comparator = comparator;
			this.maxEntries = maxEntries;
		}
	}
	
	/**
	 * Adds a node into the BST in the correct position.
	 * 
	 * @param key   The key of the node to be stored.
	 * @param value The value of the node to be stored.
	 * 
	 * @return The updated BST.
	 * 
	 * @throws TreeIsFullException If the treeIsFull and another node cannot be 
	 * 							   added.
	 */
	public BinarySearchTree<K, V> add(K key, V value) throws TreeIsFullException {
		if (isEmpty()) {
			root = new Node(key, value);
			treeSize++;
			return this;
		} else if (isFull()) {
			throw new TreeIsFullException("Tree is already full");
		} else {
			return this.addAux(key, value, root);
		}
	}
	
	/**
	 * The recursive algorithm behind inserting the node into the correct place
	 * by reassigning parents children. Method accounts for all insertion 
	 * location cases.
	 * 
	 * @param key	  The key of the node to be stored.
	 * @param value	  The value of the node to be stored.
	 * @param rootAux The node that will be processed and compared.
	 * @return
	 */
	private BinarySearchTree<K, V> addAux(K key, V value, Node rootAux) {
		
		int comparison = comparator.compare(key,rootAux.key);
		
		if (comparison == 0) {
			rootAux.value = value;
			return this;
		} else if (comparison < 0) {
			if (rootAux.left == null) {
				rootAux.left = new Node(key, value);
				treeSize++;
				return this;
			} else {
				return addAux(key, value, rootAux.left);
			}
		} else {
			if (rootAux.right == null) {
				rootAux.right = new Node(key, value);
				treeSize++;
				return this;
			} else {
				return addAux(key, value, rootAux.right);
			}
		}
	}
	
	/**
	 * Overriding the toString method for the bst.
	 */
	@Override
	public String toString() {
		return toStringAux(root);
	}
	
	/**
	 * Recursive processing of the BST to return the correct String. Utilizes 
	 * an inorder traversal to guarentee keys are displayed in ascending order.
	 * 
	 * @param rootAux The node to be processed.
	 * 
	 * @return The BST as a String.
	 */
	private String toStringAux(Node rootAux) {
		if (isEmpty()) {
			return "EMPTY TREE";
		} else if (rootAux == null) {
			return "";
		} else {
			return toStringAux(rootAux.left) + "{" + rootAux.key + ":" + 
					rootAux.value + "}" + toStringAux(rootAux.right);
		}
	}
	
	/**
	 * Determines whether the tree is empty or not.
	 * 
	 * @return True if BST root is null. False otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Returns the number of nodes within the BST.
	 * 
	 * @return Number of nodes in BST.
	 */
	public int size() {
		return treeSize;
	}

	/**
	 * Determines whether the BST is full or not.
	 * 
	 * @return True if treeSize equals maxEntries. False otherwise.
	 */
	public boolean isFull() {
		return treeSize == maxEntries;
	}

	/**
	 * Locates and returns the minimum node within the BST with respect to the 
	 * nodes' keys.
	 * 
	 * @return Minimum KeyValuePair.
	 * 
	 * @throws TreeIsEmptyException If the Tree is empty.
	 */
	public KeyValuePair<K, V> getMinimumKeyValue() throws TreeIsEmptyException {
			if (isEmpty()) {
				throw new TreeIsEmptyException("Tree is empty");
			}
			return minKeyValueAux(root);
	}
	
	/**
	 * Recursive processing of the BST to fulfill requirements of the 
	 * getMinimumKeyValue method.
	 * 
	 * @param rootAux The node to be processed.
	 * 
	 * @return The Minimum KeyValuePair with respect to the nodes' keys.
	 */
	private KeyValuePair<K, V> minKeyValueAux(Node rootAux) {
		if (rootAux.left == null) {
			KeyValuePair<K, V> answer = 
					new KeyValuePair<K, V>(rootAux.key, rootAux.value);
			return answer;
		} else {
			return minKeyValueAux(rootAux.left);
		}
	}

	/**
	 * Locates and returns the maximum node within the BST with respect to the 
	 * nodes' keys.
	 * 
	 * @return Maximum KeyValuePair.
	 * 
	 * @throws TreeIsEmptyException If the Tree is empty.
	 */
	public KeyValuePair<K, V> getMaximumKeyValue() throws TreeIsEmptyException {
		if (isEmpty()) {
			throw new TreeIsEmptyException("Tree is empty");
		}
		return maxKeyValueAux(root);
	}
	
	/**
	 * Recursive processing of the BST to fulfill requirements of the 
	 * getMaximumKeyValue method.
	 * 
	 * @param rootAux The node to be processed.
	 * 
	 * @return The Maximum KeyValuePair with respect to the nodes' keys.
	 */
	private KeyValuePair<K, V> maxKeyValueAux(Node rootAux) {
		if (rootAux.right == null) {
			KeyValuePair<K, V> answer = 
					new KeyValuePair<K, V>(rootAux.key, rootAux.value);
			return answer;
		} else {
			return maxKeyValueAux(rootAux.right);
		}
	}

	/**
	 * Locates a node within the tree based upon the parameters key and returns
	 * the KeyValuePair.
	 * 
	 * @param key The key of the node you'd like to find.
	 * 
	 * @return The node as a KeyValuePair.
	 */
	public KeyValuePair<K, V> find(K key) {
		return findAux(key, root);
	}
	
	/**
	 * Recursively fulfills the requirements of the find method.
	 * 
	 * @param key     The key of the node you'd like to find.
	 * @param rootAux The node to be processed.
	 * 
	 * @return The node as a KeyValuePair.
	 */
	private KeyValuePair<K, V> findAux(K key, Node rootAux) {
		if (rootAux == null) {
			return null;
		}
		int comparison = comparator.compare(key, rootAux.key);
		if (comparison == 0) {
			KeyValuePair<K, V> answer = 
					new KeyValuePair<>(rootAux.key, rootAux.value);
			return answer;
		} else if (comparison < 0) {
			return findAux(key, rootAux.left);
		} else {
			return findAux(key, rootAux.right);
		}
	}

	/**
	 * Deletes a node within the BST and handles all node location cases.
	 * 
	 * @param key The key of the node you'd like to delete.
	 * 
	 * @return The updated BST if the node was deleted and null if the key 
	 * 		   key parameter is not assigned to a node within the BST.
	 * 
	 * @throws TreeIsEmptyException If the BST is empty.
	 */
	public BinarySearchTree<K, V> delete(K key) throws TreeIsEmptyException {
	    if (isEmpty()) {
	        throw new TreeIsEmptyException("Tree is empty");
	    }
	    root = deleteAux(key, root);
	    treeSize--;
	    return this;
	}

	/**
	 * Recursive method to fulfill the requirements of the delete method.
	 * 
	 * @param key     The key of the node you'd like to delete.
	 * @param rootAux The node to be processed.
	 * 
	 * @return The updated BST if the node was deleted and null if the key 
	 * 		   key parameter is not assigned to a node within the BST.
	 */
	private Node deleteAux(K key, Node rootAux) {
	    if (rootAux == null) {
	        return null;
	    }
	    int comparison = comparator.compare(key, rootAux.key);
	    if (comparison < 0) {
	        rootAux.left = deleteAux(key, rootAux.left);
	    } else if (comparison > 0) {
	        rootAux.right = deleteAux(key, rootAux.right);
	    } else {
	        if (rootAux.left == null) {
	            return rootAux.right;
	        } else if (rootAux.right == null) {
	            return rootAux.left;
	        } else {
	            Node minNode = getMinimumNode(rootAux.right);
	            rootAux.key = minNode.key;
	            rootAux.value = minNode.value;
	            rootAux.right = deleteAux(minNode.key, rootAux.right);
	        }
	    }
	    return rootAux;
	}

	/**
	 * Utilized in the delete method. Returns the minimum node with respect to 
	 * the nodes' keys.
	 * 
	 * @param node The node you'd like to begin traversal at.
	 * 
	 * @return The minimum node below the node parameter with respect to the 
	 * 		   nodes' keys.
	 */
	private Node getMinimumNode(Node node) {
		    if (node.left == null) {
		        return node;
		    }
		    return getMinimumNode(node.left);
	}
	

	/**
	 * Conducts an inorder traversal of the BST and processes each node upon 
	 * arrival.
	 * 
	 * @param callback The processing to be done to each node while traversing.
	 */
	public void processInorder(Callback<K, V> callback) {
		if (callback == null) {
			throw new IllegalArgumentException("callback is null.");
		}
		processInorderAux(callback, root);
	}
	
	/**
	 * Recursive method to fulfill the requirements of the processInOrder 
	 * method.
	 * 
	 * @param callback The processing to be done to each node while traversing.
	 * @param rootAux  The node to be processed.
	 */
	private void processInorderAux(Callback<K, V> callback, Node rootAux) {
		if (rootAux == null) {
			return;
		}
		processInorderAux(callback, rootAux.left);
		callback.process(rootAux.key, rootAux.value);
		processInorderAux(callback, rootAux.right);
	}

	/**
	 * Creates and returns a subtree based on the original tree, containing 
	 * nodes whose keys fall within the specified lower and upper limits.
	 *
	 * @param lowerLimit The lower limit of the keys for the subtree (inclusive).
	 * @param upperLimit The upper limit of the keys for the subtree (inclusive).
	 * 
	 * @return A new BinarySearchTree instance representing the subtree.
	 * 
	 * @throws IllegalArgumentException If the lowerLimit or upperLimit is null,
	 *  								or if the lowerLimit is greater than the
	 *  								upperLimit.
	 */
	public BinarySearchTree<K, V> subTree(K lowerLimit, K upperLimit) {
		if (lowerLimit == null || upperLimit == null || 
				comparator.compare(lowerLimit, upperLimit) > 0) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		BinarySearchTree<K, V> answer = new BinarySearchTree<K, V>(comparator, maxEntries);
		answer.subTreeAux(lowerLimit, upperLimit, root);
		return answer;
	}
	
	/**
	 * Recursive auxiliary method for creating a subtree based on the original tree.
	 *
	 * @param lowerLimit The lower limit of the keys for the subtree (inclusive).
	 * @param upperLimit The upper limit of the keys for the subtree (inclusive).
	 * @param rootAux The current node being processed.
	 */
	private void subTreeAux(K lowerLimit, K upperLimit, Node rootAux) {
		if (rootAux != null) {
			int comparisonUpper = comparator.compare(rootAux.key,  upperLimit);
			int comparisonLower = comparator.compare(rootAux.key, lowerLimit);
			if (comparisonUpper > 0) {
				this.subTreeAux(lowerLimit, upperLimit, rootAux.left);
			} else if (comparisonLower < 0) {
				this.subTreeAux(lowerLimit, upperLimit, rootAux.right);
			} else {
				try {
					this.add(rootAux.key, rootAux.value);
				} catch (TreeIsFullException e) {
					e.printStackTrace();
				}
				subTreeAux(lowerLimit, upperLimit, rootAux.left);
				subTreeAux(lowerLimit, upperLimit, rootAux.right);
			}
		}
	}
	
	/**
	 * Creates and returns a TreeSet containing all the leaves (nodes with no 
	 * children) of the BST.
	 * 
	 * @return A TreeSet with all the leaves of the BST.
	 */
	public TreeSet<V> getLeavesValues() {
		TreeSet<V> answer = new TreeSet<V>();
		getLeavesValuesAux(answer, root);
		return answer;
	}
	
	/**
	 * Recursive auxiliary method to add values to the TreeSet from the leaves 
	 * of the original BST.
	 * 
	 * @param answer  The TreeSet being added to.
	 * @param rootAux The node to be processed.
	 */
	private void getLeavesValuesAux(TreeSet<V> answer, Node rootAux) {
		if (rootAux != null) {
			if (rootAux.left == null && rootAux.right == null) {
				answer.add(rootAux.value);
			}
			getLeavesValuesAux(answer, rootAux.left);
			getLeavesValuesAux(answer, rootAux.right);
		}
	}

	/**
	 * Returns the current number of nodes within the BST.
	 * 
	 * @return Current number of nodes within the BST.
	 */
	public int getTreeSize() {
		return this.size();
	}
}
