import java.util.Stack;

public class Stacks {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		Stack<String> stack2 = new Stack<String>();
		stack.push("hello");
		stack.push("mudda");
		stack.push("hello");
		stack.push("fadda");

		move(stack, stack2);
		// 1 1
		// 2 2
		// 3 3
		// 4 4
		// 5 5
		//
		//

	}

	private static void move(Stack<String> stack, Stack<String> stack2) {
		if (stack.isEmpty()) {
			return;
		}
		String saved = stack.pop();
		move(stack, stack2);
		stack2.push(saved);
	}
}
