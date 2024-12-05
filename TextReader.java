import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TextReader {

	private ArrayList<String> lines = new ArrayList<String>();
	Map<String, ArrayList<Integer>> wordMap = new HashMap<>();

	public void read(String fileName, String pattern) {

		setUpWordMap(fileName);

		Set<Integer> matchedLines = matchWords(wordMap, pattern);

		printResults(matchedLines,lines);
	}


	private void setUpWordMap(String fileName) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				lines.add(data);
			}
	
			// adds all words in each line into wordList
			int index = 0;
			for (String line : lines) {
				for (String word : line.split("\\s+")) {
					if (!wordMap.containsKey(word)) {
						wordMap.put(word, new ArrayList<>());
						wordMap.get(word).add(index);
					} else {
						wordMap.get(word).add(index);
					}
				}
				index++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}

	private Set<Integer> matchWords(Map<String, ArrayList<Integer>> words, String pattern) {
    	Set<Integer> matchedLines = new HashSet<Integer>();
		for(Map.Entry<String, ArrayList<Integer>> entry : words.entrySet() ) {
			if (entry.getKey().trim().equals(pattern.trim())) {
				for (int index : entry.getValue()) {
					matchedLines.add(index);
				}
				return matchedLines;
			}
		}
		return matchedLines;
	}


	private void printResults(Set<Integer> matchedLines, ArrayList<String> lines) {
		Iterator<Integer> linesIterator = matchedLines.iterator();
		while (linesIterator.hasNext()) {
			System.out.println(lines.get(linesIterator.next() + 1));
			System.out.println();
			System.out.println();
		}
	}

  }
