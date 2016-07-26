import apcs.Window;

public class Sorting {

	public static void main(String[] args) {
		Window.size(1500, 500);
		Window.frame();
		Window.setFrameRate(100);
		int[] array = randomArray(Window.width(), 0, Window.height());
		draw(array);
		quickSort(array);
		draw(array);
	}

	public static void binarySort(int[] array) {
		BST tree = new BST();
		for (int index = 0; index < array.length; index++) {
			tree.add(array[index]);
			int[] treeArray = tree.toArray();
			for (int treeIndex = 0; treeIndex <= index; treeIndex++) {
				array[treeIndex] = treeArray[treeIndex];
			}
			draw(array);
		}
	}

	public static void heapSort(int[] array) {
		heapify(array);
		// Window.mouse.waitForClick();
		int index = array.length - 1;
		while (index > 0) {
			swap(array, 0, index);
			index--;
			siftDown(array, 0, index);
			draw(array);
		}
	}

	private static void heapify(int[] array) {
		int index = (array.length - 2) / 2;
		while (index >= 0) {
			siftDown(array, index, array.length - 1);
			draw(array);
			index--;
		}
	}

	private static void siftDown(int[] array, int index, int end) {
		int child1 = index * 2 + 1;
		int child2 = index * 2 + 2;

		// case of no children
		if (child1 > end) {
			return;
		}
		// case of 1 child
		else if (child2 > end) {
			if (array[child1] > array[index]) {
				swap(array, index, child1);
				siftDown(array, child1, end);
			}
		}
		// case of 2 children
		else {
			int biggerchild;
			if (array[child1] > array[child2]) {
				biggerchild = child1;
			} else {
				biggerchild = child2;
			}

			if (array[biggerchild] > array[index]) {
				swap(array, index, biggerchild);
				siftDown(array, biggerchild, end);
			}
		}

	}

	public static void bogoSort(int[] array) {
		while (!isSorted(array)) {
			randomize(array);
			draw(array);
		}
	}

	private static void randomize(int[] array) {
		for (int i = 0; i < 100; i++) {
			swap(array, Window.random(0, array.length - 1),
					Window.random(0, array.length - 1));
		}

	}

	private static boolean isSorted(int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1] > array[i]) {
				return false;
			}
		}
		return true;
	}

	// sort the entire array a in-place using merge sort
	public static void mergeSort(int[] a) {
		mergeSort(a, 0, a.length);
	}

	// Sort a[lo, hi).
	public static void mergeSort(int[] a, int lo, int hi) {
		int N = hi - lo; // number of elements to sort

		// 0- or 1-element file, so we're done
		if (N <= 1)
			return;

		// recursively sort left and right halves
		int mid = lo + N / 2;
		mergeSort(a, lo, mid);
		mergeSort(a, mid, hi);

		// merge two sorted subarrays into auxiliary array aux
		int[] aux = new int[N];
		int i = lo, j = mid;
		for (int k = 0; k < N; k++) {
			if (i == mid) {
				aux[k] = a[j];
				j++;
			} else if (j == hi) {
				aux[k] = a[i];
				i++;
			} else if (a[j] < a[i]) {
				aux[k] = a[j];
				j++;
			} else {
				aux[k] = a[i];
				i++;
			}
		}

		// copy back into a
		for (int k = 0; k < N; k++) {
			a[lo + k] = aux[k];
			draw(a);
		}
	}

	public static void draw(int[] array) {
		// int count = (int) Math.sqrt(array.length);
		int count = array.length;
		int size = Window.width() / count;

		Window.out.background("white");
		for (int i = 0; i < array.length; i++) {

			// int x = (i % count) * size + size / 2;
			// int y = (i / count) * size + size / 2;
			// Window.out.color(0, 255 - array[i], array[i]);
			// Window.out.square(x, y, size);
			//
			int x = i * size + size / 2;
			int y = array[i] / 2;
			Window.out.color(0, 255 * array[i] / Window.height(), 255 - 255
					* array[i] / Window.height());
			Window.out.rectangle(x, y, size, array[i]);

		}
		Window.frame();
	}

	public static int[] randomArray(int size, int min, int max) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = Window.random(min, max);
		}
		return array;
	}

	public static void quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	// start is inclusive, end is exclusive
	public static void quickSort(int[] array, int start, int end) {
		if (start < end) {
			int p = partition(array, start, end);
			quickSort(array, start, p - 1);
			quickSort(array, p + 1, end);
		}
	}

	private static int partition(int[] array, int start, int end) {
		int pivot = array[end];
		int smallIndex = start;
		for (int j = start; j < end; j++) {
			if (array[j] < pivot) {
				swap(array, smallIndex, j);
				draw(array);
				smallIndex++;
			}
		}
		swap(array, smallIndex, end);
		draw(array);
		return smallIndex;
	}

	// O(n^2)
	public static void selectionSort(int[] array) {
		// O(n)
		for (int i = 0; i < array.length; i++) {
			int smallestIndex = i;
			// O(n)
			for (int j = i; j < array.length; j++) {
				if (array[j] < array[smallestIndex]) {
					smallestIndex = j;
				}
			}
			swap(array, i, smallestIndex);
			draw(array);
		}
	}

	public static void insertionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int j = i;
			int element = array[i];
			while (j > 0 && array[j - 1] > element) {
				array[j] = array[j - 1];
				// draw(array);
				j--;
			}
			array[j] = element;
			draw(array);
		}
	}

	public static void bubbleSort(int[] array) {
		boolean swaps = true;
		while (swaps) {
			swaps = false;
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					swap(array, i, i + 1);
					swaps = true;
				}
			}
			draw(array);
		}
	}

	private static void swap(int[] array, int i, int j) {
		// swap the elements
		// start: array[i] = 7, array[j] = 4
		// end: array[i] = 4, array[j] = 7
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;

	}

}
