import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class EncodingText {
	String binaryString;
	
	public EncodingText(String textToEncode, Map<Character, String> charCodeMap) throws FileNotFoundException,IOException {
		/**
		 * Constructor of EncodingText object, we read the text we wish to encode and we change each character per his binary representation
		 * @param textToEncode text we wish to encode
		 * @param charCodeMap character/string map : characters with their binary codes
		 */
		FileReader reader;
		int iterator;
		String characterCode;
		String binaryText=""; // whole text but in binary
		reader = new FileReader(textToEncode); // Reader
		char character;
		
		while((iterator=reader.read()) != -1) { // while we aren't at the end of file
			character = (char) iterator;
			characterCode=charCodeMap.get(character); // we get the binary code associated to the character
			binaryText=binaryText+characterCode; // we add this binary code part to the binaryString
		}
		reader.close();
		this.binaryString=binaryText;
	}
	
	public void binaryStringToFile(String encodedFileName) throws FileNotFoundException, UnsupportedEncodingException {
		/**
		 * Convert the binary string into ASCII and write the ASCII string in a file
		 * @param encodedFileName file name to write in
		 */
		String binarieText=this.binaryString;
		String binariePart;
		int meter=0; // cut the whole string 8 byte per 8 byte
		PrintWriter writer = new PrintWriter(encodedFileName,"ISO-8859-1");
		while (meter<binarieText.length()) { // while the meter value is smaller than the string
			if (meter+8>binarieText.length()) { // if the string hasn't a size multiple of 8
				binariePart=binarieText.substring(meter, binarieText.length()); // take the string part from meter value to the end of string
				int dec = Integer.parseInt(binariePart,2); // convert the string of 8 characters in 1 bit
				char encodedChar = (char) dec; // convert the integer value in 1 character
				writer.print(encodedChar); // add the character to the encoded string
				meter=meter+8;
			}
			else {
				binariePart=binarieText.substring(meter, meter+8); // take the string part from meter value to meter value +8
				int dec = Integer.parseInt(binariePart,2); // convert the string of 8 characters in 1 bit
				char encodedChar = (char) dec; // convert the integer value in 1 character
				writer.print(encodedChar);; // add the character to the encoded string
				meter=meter+8;
			}
		}
		 // Writer in ISO-8859-1 (not UTF-8) because if it's in UTF-8 the final file has a bigger size than the one we wish to encode
		 // write the encoded string in the file
		writer.close();
	}
}

	