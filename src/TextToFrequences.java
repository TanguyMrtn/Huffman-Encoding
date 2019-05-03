import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TextToFrequences {

	private String sourceFile;
	private String frequencyFile;

	public TextToFrequences(String sourceFile,String frequencyFile) {
		this.sourceFile=sourceFile;
		this.frequencyFile=frequencyFile;
	}
	
	public void textToFrequences() throws FileNotFoundException,IOException{
		/**
		 * Generate a txt file containing the occurrence frequency of each character in the text.
		 */
		FileReader reader;
		int iterator;
		int keyValue;
		char key;
		Map<Character,Integer> frequencies = new HashMap<>(); // Create a map which will contains a character as a key, and his frequency as value
		
		reader = new FileReader(this.getSourceFile()); // Reader
		
		while((iterator=reader.read()) != -1) { // While not at the end of the file
			key=(char) iterator; // iterator is the integer representation of a character, we convert it in character type
			if (frequencies.containsKey(key) == false) { // Add the character in the map if he isn't already
				frequencies.put(key, 1);
			}
			else { // else, add 1 to the value of the character if he is already in the map
				keyValue=frequencies.get(key)+1;
				frequencies.put(key, keyValue);
			}
		}
		PrintWriter writer = new PrintWriter(this.getFrequencyFile(),"UTF-8"); // writer
		for(Entry<Character,Integer> entry:frequencies.entrySet()) { // browse character/frequency map
			Character keyMap = entry.getKey();
			Integer valueMap = entry.getValue();
			writer.println(keyMap + " " + valueMap); // write in file
		}
		writer.close();
		reader.close();
	}
	
	public String getSourceFile() {
		return sourceFile;
	}

	public String getFrequencyFile() {
		return frequencyFile;
	}
}

