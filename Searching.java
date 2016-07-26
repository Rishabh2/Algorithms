public class Searching {

	public static void main(String[] args) {
		// Step 1. Create a random array, 1,000 elements from 1 to 5,000
		// Step 2. Sort it
		// Step 3. Check it for a specific value, 2,500
		// NOTE: Do step 3 in a method
		// Step 4. Print whether or not the array contains the value
		int[] array = Sorting.randomArray(10000, 1, 5000);

		System.out.println(search(array, 2500));
		Sorting.insertionSort(array);
		System.out.println(binarySearch(array, 2500));
	}

	// O(n)
	public static boolean search(int[] array, int value) {
		int count = 0;
		for (int i : array) {
			count++;
			if (i == value) {
				System.out.println("Sequential Search: " + count);
				return true;
			}
		}
		System.out.println("Sequential Search: " + count);
		return false;
	}

	// O(log(n))
	public static boolean binarySearch(int[] array, int value) {
		int high = array.length - 1;
		int low = 0;
		int mid = (high + low) / 2;
		while (high - low > 1) {
			if (array[mid] == value) {

				return true;
			} else if (array[mid] > value) {
				high = mid - 1;
				mid = (high + low) / 2;
			} else {
				low = mid + 1;
				mid = (high + low) / 2;
			}
		}

		return array[high] == value || array[low] == value;
	}

}
