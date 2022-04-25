import java.util.*;
import java.io.*;

public class LexicalAnalyzer {
	public static final List<String> KEYWORDS = Arrays.asList("auto", "break", "case", "char", "const", "continue",
			"default", "do", "double", "else",
			"enum", "extern", "float", "for", "goto", "if", "inline", "int", "long", "register",
			"restrict", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "union",
			"unsigned", "void", "volatile", "while");

	public static final List<Character> DELIMITERS = Arrays.asList('[', ']', '(', ')', '{', '}',
			',', '.', ';', ':', '"', '\'');

	public static final List<Character> OPERATORS = Arrays.asList('+', '-', '*', '/', '%', '&', '|', '^', '!', '=');

	public static boolean isNumber(String token) {
		for (char c : token.toCharArray())
			if (c < '0' || c > '9')
				return false;
		return true;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileReader("program.c"));

		List<String> tokens = new ArrayList<>();
		while (sc.hasNextLine())
			tokens.addAll(tokensizeLine(sc.nextLine()));

		List<String> operatorList = new ArrayList<>();
		List<String> delimiterList = new ArrayList<>();
		List<String> keywordList = new ArrayList<>();
		List<String> identiferList = new ArrayList<>();
		List<String> numberList = new ArrayList<>();

		for (String token : tokens)
			switch (identifyToken(token)) {
				case "operator":
					operatorList.add(token);
					break;
				case "delimiter":
					delimiterList.add(token);
					break;
				case "keyword":
					keywordList.add(token);
					break;
				case "number":
					numberList.add(token);
					break;
				case "identifer":
					identiferList.add(token);
					break;
			}

		System.out.println("\n");
		System.out.println("Operators : " + Arrays.toString(operatorList.toArray()) + "\n");
		System.out.println("Delimiters: " + Arrays.toString(delimiterList.toArray()) + "\n");
		System.out.println("Keywords  : " + Arrays.toString(keywordList.toArray()) + "\n");
		System.out.println("Identifer : " + Arrays.toString(identiferList.toArray()) + "\n");
		System.out.println("Digit     : " + Arrays.toString(numberList.toArray()) + "\n\n");
	}

	public static List<String> tokensizeLine(String line) {
		line += '\0';
		List<String> tokens = new ArrayList<>();
		StringBuilder tmpToken = new StringBuilder();

		for (char c : line.toCharArray())
			if (c == '\0' || Character.isWhitespace(c) || DELIMITERS.contains(c) || OPERATORS.contains(c)) {
				if (tmpToken.length() > 0)
					tokens.add(tmpToken.toString());

				tmpToken.setLength(0);
				if (c != '\0' && !Character.isWhitespace(c))
					tmpToken.append(c);
			} else
				tmpToken.append(c);

		return tokens;
	}

	public static String identifyToken(String token) {
		if (token.length() == 1) {
			if (OPERATORS.contains(token.charAt(0)))
				return "operator";
			if (DELIMITERS.contains(token.charAt(0)))
				return "delimiter";
		}

		if (KEYWORDS.contains(token))
			return "keyword";
		if (isNumber(token))
			return "number";
		return "identifer";
	}
}