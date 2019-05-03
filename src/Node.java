

public class Node implements Comparable<Node>{
	char character;
	int frequency;
	Node leftChild;
	Node rightChild;
	
	public Node(char character, int frequency) {
		/**
		 * Constructor of Node object
		 * @param character one of the character of the character/frequency map 
		 * @param frequency frequency of the character
		 */
		this.character = character;
		this.frequency = frequency;
		this.leftChild=null;
		this.rightChild=null;
		
	}
	
	public int compareTo(Node node) {
		/**
		 * comparison between frequency of 2 nodes, allowing the use of a PriorityQueue in HuffmanTree class
		 * @param node node to compare
		 */
        return this.getFrequency() - node.getFrequency();
    }
	
	
	public boolean isLeaf() {
		/**
		 * easy way to know if a node is a leaf (no right child & no left child)
		 */
		return (this.getLeftChild() == null && this.getRightChild() == null);
	}
	
	public char getCharacter() {
		return character;
	}
	public int getFrequency() {
		return frequency;
	}
	public Node getLeftChild() {
		return leftChild;
	}
	public Node getRightChild() {
		return rightChild;
	}
	
	
	
}
