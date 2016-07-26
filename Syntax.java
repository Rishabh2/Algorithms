public class Syntax {

	public static void main(String[] args) {
		int x = 0;
		x = x + 1;
		x++;
		for (int i = 0; i < 10; i++) {
			// body
			System.out.println("Hello");
		}
		int j = 0;
		while (j < 10) {
			System.out.println("Goodbye");
			j++;
		}
		int[] array = { 1, 3, 5, 7, 9 };
		for (int index = 0; index < array.length; index++) {
			System.out.println(array[index]);
		}
		for(int i : array){
			// body of code
			System.out.println(i);
		}
	}

}
