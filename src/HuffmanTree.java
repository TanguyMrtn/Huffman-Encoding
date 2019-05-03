import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;



public class HuffmanTree {
	
	Node root;
	Map<Character, String> charCodeMap;
	Map<Character, Integer> charFrequenciesMap;
	
	public HuffmanTree(Map<Character, Integer> charFrequenciesMap) {
		/**
		 * Building tree
		 * @param charFrequenciesMap containing character/frequency entry
		 */
		
		this.charFrequenciesMap=charFrequenciesMap;
		
		PriorityQueue<Node> nodeQueue= new PriorityQueue<>(); // PriorityQueue is used because it allows a easy sort on the frequencies of nodes
		
		for(Entry<Character,Integer> entry:charFrequenciesMap.entrySet()) { // browse the map
			Character key = entry.getKey();
			Integer value = entry.getValue();
			nodeQueue.add(new Node(key,value)); // add a new node (it will be a leaf) in the PrirityQueue
		}
		while (nodeQueue.size() > 1) { // while we don't have one node in the PriorityQueue (this last node is the tree)
			Node lowerFrequencyNode = nodeQueue.poll(); // this node is the one with the lowest frequency, because the PriorityQueue sort on node frequencies
			Node higherFrequencyNode = nodeQueue.poll(); // the one with the higher frequency after the lowest
			int frequencySum = lowerFrequencyNode.getFrequency() + higherFrequencyNode.getFrequency(); 
			Node newNode = new Node('\u0000',frequencySum); // create a new node, which is not a leaf, with a frequency equal to the sum of his 2 children frequency
			newNode.leftChild=lowerFrequencyNode;
			newNode.rightChild=higherFrequencyNode;
			nodeQueue.add(newNode); // add this new node to the PriorityQueue
			
			}
		Node rootHuffmanTree = nodeQueue.poll(); // it's the root of the huffman tree
		this.root=rootHuffmanTree;
		this.charCodeMap=new HashMap<Character,String>();
	}
	
	public Map<Character, String> getCharCode(Node node,String charCode) {
		/**
		 * Generating a map containing characters and their binary codes in recursive way (not optimal)
		 * @param node node that we want the binary code 
		 * @param charCode binary code
		 */
		if (node.isLeaf()) { // if on a leaf, we add the character and his binary code to the map
			this.charCodeMap.put(node.getCharacter(), charCode);
		}
		else { // else, we apply the same function on the right and left child of the node
			if (node.getLeftChild() != null) { // if we go on the left child, we add 0 to the binary code
				getCharCode(node.getLeftChild(),charCode+"0");
			}
			if (node.getRightChild() != null) { // if we go on the right child, we add 1 to the binary code
				getCharCode(node.getRightChild(),charCode+"1");
			}
		}
		return charCodeMap;
	}


	public void charCodeMapToFile(Map<Character, String> charCodeMap, String charFrequencyCodeFile) throws FileNotFoundException, UnsupportedEncodingException {
		/**
		 * Write in a file characters with their frequencies and their binary codes
		 * @param charCodeMap map containing characters and their binary codes
		 * @param charFrequencyCodeFile destination file
		 */
		PrintWriter writer = new PrintWriter(charFrequencyCodeFile,"UTF-8"); // writer
		for(Entry<Character,String> entry:charCodeMap.entrySet()) { // browse the character/string map
			Character keyMap = entry.getKey();
			String valueMap = entry.getValue();
			Integer freqMap = this.getCharFrequenciesMap().get(keyMap);
			writer.println(keyMap + " " + freqMap + " " + valueMap); // write in the file
		}
		writer.close();
	}

	public Node getRoot() {
		return root;
	}

	public Map<Character, String> getCharCodeMap() {
		return charCodeMap;
	}
	
	public Map<Character, Integer> getCharFrequenciesMap() {
		return charFrequenciesMap;
	}
	
}











