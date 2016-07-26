package luckyunlucky;

public class ArrayList<T> {
	private T[] array;
	private int nelems;

	@SuppressWarnings("unchecked")
	public ArrayList() {
		array = (T[]) new Object[10];
		nelems = 0;
	}

	// O(1)
	public void add(T object) {
		if (object == null) {
			throw new NullPointerException();
		} else {
			if (nelems == array.length) {
				sizeUp();
			}
			array[nelems] = object;
			nelems++;
		}
	}

	@SuppressWarnings("unchecked")
	private void sizeUp() {
		T[] newArray = (T[]) new Object[array.length * 2];
		for (int i = 0; i < nelems; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

	// O(n)
	public T remove(int index) {
		if (index >= nelems) {
			throw new IndexOutOfBoundsException();
		} else {
			T removed = array[index];
			for (int i = index + 1; i < nelems; i++) {
				array[i - 1] = array[i];
			}
			nelems--;
			return removed;
		}
	}

	// O(1)
	public T get(int index) {
		if (index >= nelems) {
			throw new IndexOutOfBoundsException();
		} else {
			return array[index];
		}
	}

	// O(n)
	public void insert(T object, int index) {
		if (object == null) {
			throw new NullPointerException();
		} else if (index > nelems) {
			throw new IndexOutOfBoundsException();
		} else {
			if (nelems == array.length) {
				sizeUp();
			}

			for (int i = nelems; i >= index; i--) {
				array[i + 1] = array[i];
			}

			array[index] = object;
			nelems++;
		}
	}

	public int size() {
		return nelems;
	}

	public String toString() {
		String print = "ArrayList: ";
		for (int i = 0; i < nelems; i++) {
			print += array[i] + " ";
		}
		return print;
	}
}
