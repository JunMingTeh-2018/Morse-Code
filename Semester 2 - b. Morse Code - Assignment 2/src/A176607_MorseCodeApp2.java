import java.util.*;

public class A176607_MorseCodeApp2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		A176607_MorseCode1 morseCodeTree = new A176607_MorseCode1();
		try {
			int number = sc.nextInt();
			while (number != 4) {
				if (number >= 1 && number <= 3) {
					Scanner scanner = new Scanner(System.in);
					switch (number) {
						case 1:
							String word = scanner.nextLine();
							if (word.equals("VV")) {
								while (!word.equals("EOM")) {
									morseCodeTree.encode(word);
									word = scanner.nextLine();
								}
								morseCodeTree.encode("EOM");
								morseCodeTree.transmissionSummary();
							} else {
								System.out.println("Invalid input. You should start with VV");
							}
							System.out.println();
							break;
						case 2:
							String morseCode = scanner.nextLine();
							if (morseCode.equals("...- ...-") || morseCode.equals("...- ...- ")) {
								while (!morseCode.equals(". --- --") && !morseCode.equals(". --- -- ")) {
									morseCodeTree.decode(morseCode);
									morseCode = scanner.nextLine();
								}
								morseCodeTree.decode(". --- --");
								String lineNumber = morseCodeTree.decode(scanner.nextLine());
								String wordNumber = morseCodeTree.decode(scanner.nextLine());
								String characterNumber = morseCodeTree.decode(scanner.nextLine());
								String symbolNumber = morseCodeTree.decode(scanner.nextLine());
								String numberNumber = morseCodeTree.decode(scanner.nextLine());
								morseCodeTree.decode(scanner.nextLine());
								System.out.println();
								morseCodeTree.summaryAnalysis();
								if (lineNumber.equals(morseCodeTree.lineNumber)
									&& wordNumber.equals(morseCodeTree.wordNumber)
									&& characterNumber.equals(morseCodeTree.characterNumber)
									&& symbolNumber.equals(morseCodeTree.symbolNumber)
									&& numberNumber.equals(morseCodeTree.numberNumber)) {
									System.out.println("Result: Consistent Summary");
								} else {
									System.out.println("Result: Inconsistent summary");
								}
								morseCodeTree.lineNumber = morseCodeTree.wordNumber = morseCodeTree.characterNumber = 
								morseCodeTree.symbolNumber = morseCodeTree.numberNumber = null;
							} else {
								System.out.println("Invalid input. You should start with ...- ...-");
							}
							System.out.println();
							break;
						case 3:
							morseCodeTree.printInorder();
							System.out.print("\n\n");
							break;
					}
					scanner.close();
				} else {
					System.out.println("Invalid code. Please enter code 1-4!\n");
				}
				System.out.println("Menu: " + "\n    1.  Send Morse Message" + "\n    2.  Receive Morse Message" + 
								   "\n    3.  Print Letters and Morse Code" + "\n    4.  Exit\n\n" +
								   "Input code:\n");
				number = sc.nextInt();
			}
			System.out.print("Bye dits-dahs..");
		} catch (Exception e) {
			System.out.print("Invalid input. Please restart the program and enter code 1-4! Bye dits-dahs..");
		}
		sc.close();
	}
}