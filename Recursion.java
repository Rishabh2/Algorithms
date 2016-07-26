public class Recursion {

	public static void main(String[] args) {
		int[] array = { 1, 5, 7, 9, 23, 45, 67, 8 };
		foo(array);
	}

	public static void foo(int[] array) {
		foo(array, 0);
	}

	private static void foo(int[] array, int i) {
		if (i < array.length) {
			foo(array, i + 1);
			System.out.println(array[i]);
		}
	}

}
