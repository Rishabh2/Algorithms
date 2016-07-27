import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class RPNCalc {
	public static void main(String[] args) {
		while (true) {
			System.out.println(calculate(convert(readExpression())));
		}
	}

	public static Queue<String> convert(Queue<String> expression) {
		Queue<String> operandQueue = new LinkedList<String>();
		Stack<String> operatorStack = new Stack<String>();
		while (expression.isEmpty() == false) {
			String token = expression.poll();
			if (isOperator(token)) {
				while (operatorStack.isEmpty() == false
						&& precedence(operatorStack.peek()) >= precedence(token)) {
					operandQueue.add(operatorStack.pop());
				}
				operatorStack.push(token);
			} else if (isParen(token)) {
				if (token.equals("(")) {
					operatorStack.push(token);
				} else {
					while (operatorStack.peek().equals("(") == false) {
						operandQueue.add(operatorStack.pop());
					}
					operatorStack.pop();
				}
			} else {
				operandQueue.add(token);
			}
		}
		while (operatorStack.isEmpty() == false) {
			operandQueue.add(operatorStack.pop());
		}
		return operandQueue;
	}

	public static double calculate(Queue<String> expression) {
		Stack<Double> values = new Stack<Double>();
		while (expression.isEmpty() == false) {
			String token = expression.poll();
			if (isOperator(token)) {
				evaluate(token, values);
			} else {
				values.push(Double.parseDouble(token));
			}
		}
		return values.pop();
	}

	public static Queue<String> readExpression() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("> ");
		String rawExpression = scanner.nextLine();
		return lex(rawExpression);
	}

	public static Queue<String> lex(String rawExpression) {
		Queue<String> expression = new LinkedList<String>();
		char[] chars = rawExpression.toCharArray();
		String value = "";
		for (char c : chars) {
			if (isParen(c+"")||isOperator(c+"")) {
				if (value.equals("") == false) {
					expression.add(value);
					value = "";
				}
				expression.add(c + "");
			} else if(Character.isDigit(c)||c=='.') {
				value += c;
			}
		}
		if (value.equals("")==false){
			expression.add(value);
		}
		return expression;
	}

	private static void evaluate(String token, Stack<Double> values) {
		if (token.equals("+")) {
			Double op2 = values.pop();
			Double op1 = values.pop();
			values.push(op1 + op2);
		} else if (token.equals("-")) {
			Double op2 = values.pop();
			Double op1 = values.pop();
			values.push(op1 - op2);
		} else if (token.equals("*")) {
			Double op2 = values.pop();
			Double op1 = values.pop();
			values.push(op1 * op2);
		} else if (token.equals("/")) {
			Double op2 = values.pop();
			Double op1 = values.pop();
			values.push(op1 / op2);
		}
	}

	private static int precedence(String token) {
		if (token.equals("+") || token.equals("-")) {
			return 1;
		} else if (token.equals("/") || token.equals("*")) {
			return 2;
		} else {
			return 0;
		}
	}

	private static boolean isOperator(String token) {
		return "+-/*".contains(token);
	}

	private static boolean isParen(String token) {
		return "()".contains(token);
	}
}
