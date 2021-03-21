import java.io.*;
import java.util.*;

public class A176607_MorseCode1 {

	public class treeNode {
		private char letter;
		private String morseCode;
		private treeNode leftChildNode, rightChildNode;

		public treeNode() {
			letter = '\0';
			morseCode = null;
			leftChildNode = rightChildNode = null;
		}

	}

	private treeNode root;
	private int countLine, countWord, countCharacter, countSymbol, countNumber;
	protected String lineNumber, wordNumber, characterNumber, symbolNumber, numberNumber;

	public A176607_MorseCode1() {
		root = new treeNode();
		countLine = countWord = countCharacter = countSymbol = countNumber = 0;
		lineNumber = wordNumber = characterNumber = symbolNumber = numberNumber = null;
		try {
			Scanner fileIn = new Scanner(new File("A176607_File2.dat"));
			while (fileIn.hasNext()) {
				char letter = fileIn.next().charAt(0);
				String morseCode = fileIn.next();
				treeNode currentNode = root;
				for (int i = 0; i < morseCode.length(); i++) {
					char symbol = morseCode.charAt(i);
					if (symbol == '.') {
						if (currentNode.leftChildNode == null) {
							currentNode.leftChildNode = new treeNode();
							currentNode = currentNode.leftChildNode;
						} else {
							currentNode = currentNode.leftChildNode;
						}
					} else {
						if (currentNode.rightChildNode == null) {
							currentNode.rightChildNode = new treeNode();
							currentNode = currentNode.rightChildNode;
						} else {
							currentNode = currentNode.rightChildNode;
						}
					}
				}
				currentNode.letter = letter;
				currentNode.morseCode = morseCode;
			}
			fileIn.close();
		} catch (IOException e) {
			System.out.println("Please include a Letter-MorseCode-List File!");
		}
	}

	public void encode(String word) {
		String upperCaseWord = word.toUpperCase();
		treeNode currentNode = root;
		String[] w = word.split("\\s+");
		for (String n : w) {
			n = n.trim();
			countWord++;
		}
		for (int i = 0; i < upperCaseWord.length(); i++) {
			char letter = upperCaseWord.charAt(i);
			if (letter == '.' || letter == ',' || letter == ':' || letter == '"' || letter == '\'' || letter == '!' 
				|| letter == '?' || letter == '@' || letter == '-' || letter == ';' || letter == '(' || letter == ')' 
				|| letter == '=') {
				countSymbol++;
			} else if (Character.isDigit(letter)) {
				countNumber++;
			}
			if (letter != ' ') {
				encodeRec(currentNode, letter);
				countCharacter++;
				currentNode = root;
			} else if (letter == ' ') {
				System.out.print(" ");
			}
		}
		countLine++;
		System.out.println();
	}

	public void encodeRec(treeNode root, char letter) {
		if (root == null) {
			return;
		} else {
			encodeRec(root.leftChildNode, letter);
			if (root.letter == letter) {
				System.out.print(root.morseCode + " ");
			}
			encodeRec(root.rightChildNode, letter);
		}
	}

	public void transmissionSummary() {
		lineNumber = Integer.toString(countLine);
		wordNumber = Integer.toString(countWord);
		characterNumber = Integer.toString(countCharacter);
		symbolNumber = Integer.toString(countSymbol);
		numberNumber = Integer.toString(countNumber);
		encode(lineNumber);
		encode(wordNumber);
		encode(characterNumber);
		encode(symbolNumber);
		encode(numberNumber);
		encode("EOT");
		countLine = countWord = countCharacter = countSymbol = countNumber = 0;
	}

	public String decode(String morseCode) {
		String word = "";
		treeNode currentNode = root;
		for (int i = 0; i < morseCode.length(); i++) {
			char symbol = morseCode.charAt(i);
			if (symbol == '.') {
				currentNode = currentNode.leftChildNode;
			} else if (symbol == '-') {
				currentNode = currentNode.rightChildNode;
			} else if (symbol == ' ' && i != morseCode.length() - 1) {
				word += currentNode.letter;
				currentNode = root;
			}
		}
		word += currentNode.letter;
		System.out.println(word);
		return word;
	}

	public void summaryAnalysis() {
		System.out.println(lineNumber + " " + wordNumber + " " + characterNumber + " " + symbolNumber + " " + numberNumber);
	}

	public void printInorder() {
		inorderRec(root);
	}

	public void inorderRec(treeNode root) {
		if (root == null) {
			return;
		} else {
			inorderRec(root.leftChildNode);
			if (root.letter == '3' || root.letter == '2' || root.letter == '.' || root.letter == 'J' || root.letter == 'B' 
				|| root.letter == 'C' || root.letter == ')' || root.letter == ',' || root.letter == '8') {
				System.out.println();
			}
			if (root.letter != '\0' && root.morseCode != null) {
				System.out.printf("%-10s %s", root.letter + " " + root.morseCode, "\t");
			}
			inorderRec(root.rightChildNode);
		}
	}
}