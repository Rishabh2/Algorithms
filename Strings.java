import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Strings {

	public static ArrayList<String> words;
	public static String start;
	public static String goal;

	public static void main(String[] args) {
		words = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(new File("words"));
			while (scanner.hasNextLine()) {
				words.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		start = "crown";
		goal = "kings";

		stringChange(start);

	}

	public static void stringChange(String current) {
		ArrayList<String> path = new ArrayList<String>();
		path.add(current);
		if (stringChange(current, path)) {
			for (int i = 2; i < path.size(); i++) {
				if (stringDist(path.get(i), path.get(i - 2)) <= 1) {
					path.remove(i-- - 1);
				}
			}
			for (String s : path) {
				System.out.println(s);
			}
		} else {
			System.out.println("Impossible");
		}
	}

	private static boolean stringChange(String current, ArrayList<String> path) {
		if (current.equals(goal)) {
			return true;
		}
		PriorityQueue<String> possibilities = new PriorityQueue<String>(10,
				new Comparator<String>() {

					@Override
					public int compare(String arg0, String arg1) {
						int dist0 = stringDist(arg0, goal);
						int dist1 = stringDist(arg1, goal);

						return dist0 - dist1;
					}

				});

		for (int i = 0; i < current.length(); i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				String option = current.substring(0, i) + c
						+ current.substring(i + 1);
				if (isWord(option) && !path.contains(option)) {
					possibilities.add(option);
				}
			}
		}

		while (!possibilities.isEmpty()) {
			String option = possibilities.poll();
			path.add(option);
			if (stringChange(option, path)) {
				return true;
			} else {
				path.remove(option);
			}
		}

		return false;
	}

	public static boolean isWord(String word) {

		int high = words.size() - 1;
		int low = 0;
		int mid = (high + low) / 2;

		while (high - low > 1) {

			int cmp = word.compareTo(words.get(mid));

			if (cmp == 0) {
				return true;
			} else if (cmp > 0) {
				low = mid + 1;
				mid = (high + low) / 2;
			} else if (cmp < 0) {
				high = mid - 1;
				mid = (high + low) / 2;
			}

		}
		return word.equals(words.get(high)) || word.equals(words.get(low));
	}

	public static int stringDist(String s1, String s2) {
		int count = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				count++;
			}
		}
		return count;
	}

}
