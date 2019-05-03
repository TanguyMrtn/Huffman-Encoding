import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FrequenciesFileToMap {
	
	Map<Character,Integer> frequenciesMap = new HashMap<>();
	
	public FrequenciesFileToMap() {
		this.frequenciesMap = new HashMap<>();
	}

	public Map<Character, Integer> frequenciesFileToMapFn(String fileName) throws IOException {
		/**
		 * Making a map from a frequency file containing characters and their occurrence frequency
		 * @param fileName name of the frequency file
		 */
		BufferedReader br = new BufferedReader(new FileReader(fileName)); // Read the file line per line
		String line;
		char key;
		String[] parts;
		String value;
		Map<Character,Integer> frequencies = new HashMap<>(); // Map containing character/frequency entry
		while ((line = br.readLine()) != null) { // while not at the end of file
			if (line.length()==0) { // manage LineFeed character
					key=(char) 10; // integer value of the line feed character
					String nextLine=br.readLine();
					parts=nextLine.split(" "); // the space is the character separating the character and his frequency
					value=parts[1];
					frequencies.put(key, Integer.parseInt(value));
			}
			else {
				if (line.charAt(0)==' ' && line.charAt(1)==' ') { // manage space character
					key=line.charAt(0); // the character
					parts=line.split(" ");
					value=parts[2];  // the character frequency
					frequencies.put(key, Integer.parseInt(value));
				}
				else {
					key = line.charAt(0); // the character
					parts=line.split(" ");
					value = parts[1]; // the character frequency
					frequencies.put(key, Integer.parseInt(value));
				}
			}
		}
		br.close();
		return this.frequenciesMap=frequencies;
	}

	public Map<Character, Integer> getFrequenciesMap() {
		return frequenciesMap;
	}
}
