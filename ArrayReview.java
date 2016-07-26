public class ArrayReview {

	// Challenge #0
	// Print the numbers sequentially

	// Challenge #1
	// Print the elements backwards

	// Challenge #2
	// Print the largest, smallest, and average element of the array
	// Average = sum / # of elements

	// Challenge #3
	// Print the elements, sorted
	public static void main(String[] args) {
		int[] array = { 1, 2, 34, 5, 6, 7, 89 };
		// initialization - create the variable and give them values
		// condition - under what circumstance should the loop body run
		// increment - how do we change the variable when the body is done
		int largest = array[0];
		int smallest = array[0];
		int avg = 0;
		int count = 0;
		for (int i = 0; i < array.length; i++) {

			if (array[i] > largest) {
				largest = array[i];
			}

			if (array[i] < smallest) {
				smallest = array[i];
			}

			avg += array[i]; // avg = avg + array[i];
			count++;
		}
		System.out.println("Largest element: " + largest);
		System.out.println("Smallest element: " + smallest);
		System.out.println("Average element: " + 1.0 * avg / count);

	}
}
