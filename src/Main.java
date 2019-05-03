import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Scanner scanner = new Scanner( System.in );
		System.out.println("Select Huffman Encoding method by typing 1 or 2 :");
		System.out.println("1 - Static method");
		System.out.println("2 - Semi-adaptativ method");
		String encodingMethodChoice = scanner.nextLine();
		
		if (encodingMethodChoice.equals("2")) {
			System.out.print( "Enter the name of the file you want to compress (pay attention to directory path) : " );
			String fileNameInput = scanner.nextLine();
			System.out.print( "Enter the final name of the encoded file you want (pay attention to directory path) : " );
			String encodedFileName = scanner.nextLine();
			scanner.close();
			
			TextToFrequences myText = new TextToFrequences(fileNameInput ,"frequencies.txt"); // creating character/frequency file
			myText.textToFrequences();
			
			FrequenciesFileToMap frequencies = new FrequenciesFileToMap(); // getting a char/int map from character/frequency file
			frequencies.frequenciesFileToMapFn(myText.getFrequencyFile());
	
			HuffmanTree tree = new HuffmanTree(frequencies.getFrequenciesMap()); // build tree
			Map<Character, String> charCode = tree.getCharCode(tree.getRoot(),""); // get character code
			tree.charCodeMapToFile(charCode,"charFrequenciesAndBinCode.txt"); // writing character/code in a file
			
			EncodingText test = new EncodingText(fileNameInput,charCode); // encoding the text
			test.binaryStringToFile(encodedFileName+".bin"); // writing the text in final file
			System.out.println("File is compress.");
			System.out.println(encodedFileName+".bin is the encoded text.");
			System.out.println("frequencies.txt contains each character with his frequency from your file.");
			System.out.println("charFrequenciesAndBinCode.txt contains each character with his frequency and his binary code.");
		}
		
		else if (encodingMethodChoice.equals("1")) {
			
			System.out.print( "Enter the name of the file you want to compress (pay attention to directory path) : " );
			String fileNameInput = scanner.nextLine();
			System.out.print( "Enter the name of the file containing your character frequencies (pay attention to directory path) : " );
			String characterFrequenciesFile = scanner.nextLine();
			System.out.print( "Enter the final name of the encoded file you want (pay attention to directory path) : " );
			String encodedFileName = scanner.nextLine();
			scanner.close();
			
			FrequenciesFileToMap frequencies = new FrequenciesFileToMap(); // getting a char/int map from character/frequency file
			frequencies.frequenciesFileToMapFn(characterFrequenciesFile);
			
			HuffmanTree tree = new HuffmanTree(frequencies.getFrequenciesMap()); // build tree
			Map<Character, String> charCode = tree.getCharCode(tree.getRoot(),""); // get character code
			tree.charCodeMapToFile(charCode,"charFrequenciesAndBinCode.txt"); // writing character/code in a file
			
			EncodingText test = new EncodingText(fileNameInput,charCode); // encoding the text
			test.binaryStringToFile(encodedFileName+".bin"); // writing the text in final file
			
			System.out.println("File is compress.");
			System.out.println(encodedFileName+".bin is the encoded text.");
			System.out.println("charFrequenciesAndBinCode.txt contains each character with his frequency and his binary code.");
		}
		
		else {
			System.out.println("Please enter a correct answer, program will close");
			
		}

	}

}
