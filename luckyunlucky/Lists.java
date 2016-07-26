package luckyunlucky;

public class Lists {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < 5; i++) {
			stack.add(i);
		}
		while (!stack.isEmpty()) {
			System.out.println(stack.remove());
		}
	}

}
