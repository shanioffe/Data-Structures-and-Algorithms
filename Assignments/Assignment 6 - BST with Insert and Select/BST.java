/*
 * This algorithm works by reading in a file, transcribing the input into an array of integers, 
 * and creating a binary search tree using those integers as keys. There is one particular function
 *  called "select" that returns the kth-smallest element in the tree, k = (n/2 + 1), by recurring
 *  down the tree comparing each node's size with k until the kth-smallest element is found.
 * 
 * Author: Shannon Ioffe, Geeks4Geeks
 * Date: April 17, 2023
 * COSC 336
 */

import java.io.*;
import java.util.*;
class BST {

	// node class
	class Node {
		int key;
		Node left, right;
		int size;

		public Node(int item)
		{
			key = item;
			left = right = null;
			size = 1;
		}
	}

	// root of BST
	Node root;

	// BST constructor
	BST() { root = null; }

	BST(int value) { root = new Node(value); }

	//	mainly calls insertRec()
	void insert(int key) { root = insertRec(root, key); }

	// recursive function to insert a new key in BST
	Node insertRec(Node root, int key)
	{

		// if the tree is empty, return a new node
		if (root == null) {
			root = new Node(key);
			return root;
		}

		// otherwise, recur down the tree
		else if (key < root.key)
			root.left = insertRec(root.left, key);
		else if (key > root.key)
			root.right = insertRec(root.right, key);
		// handles duplicate keys
		else if (key == root.key)
			root.left = insertRec(root.left, key);

		root.size = 1;
		root.size = 1;
		if (root.left != null) {
			root.size += root.left.size;
		}
		if (root.right != null) {
			root.size += root.right.size;
		}

		// return the (unchanged) node pointer
		return root;
	}

	// mainly calls InorderRec()
	void inorder() { inorderRec(root); }

	// inorder traversal of BST
	void inorderRec(Node root)
	{
		if (root != null) {
			inorderRec(root.left);
			System.out.println(root.key + " (size: " + root.size + ")");

			inorderRec(root.right);
		}
	}

	// returns the kth smallest elm in the tree rooted at t
	int select(Node t, int k){
		if (t == null) {
			return -1;
		}
		int leftSize = t.left != null ? t.left.size : 0;
		if (leftSize == k - 1) {
			return t.key;
		} else if (leftSize >= k) {
			return select(t.left, k);
		} else {
			return select(t.right, k - leftSize - 1);
		}
	}

	//reads the input and creates an array for its contents
	public static int[] readFile(String fname) throws IOException {

        Scanner scan = new Scanner(new File(fname));

        int sizeOfSequence = scan.nextInt();
        int[] sequence = new int[sizeOfSequence];

        for(int i = 0; i < sizeOfSequence; i++){
            sequence[i] = scan.nextInt();
        }

        scan.close();

        return sequence;
}
	public static void main(String[] args) throws IOException
	{
		// loop to manage several input files
		String[] inputs = {"input0.txt", "input1.txt", "input2.txt"};
		for(int i = 0; i < inputs.length; i++){
			int[] keys = readFile(inputs[i]);
			BST tree = new BST();
			// loop to populate BST tree with nodes
			for(int j = 0; j < keys.length; j++){
				tree.insert(keys[j]);
			}
			System.out.println("Output for input " + i + ": " + tree.select(tree.root, (keys.length/2 + 1)));
		}
	
	}
}
