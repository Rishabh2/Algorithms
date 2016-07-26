public class MyArrayList implements MyList {

	Integer[] array;
	int numElems;

	public MyArrayList() {
		array = new Integer[10];
		numElems = 0;
	}

	// O(1)
	@Override
	public void add(Integer i) {
		if (numElems >= array.length) {
			sizeUp();
		}
		array[numElems] = i;
		numElems++;
	}

	// O(n)
	@Override
	public Integer remove(int index) {
		Integer removed = array[index];
		numElems--;
		for (int i = index; i < numElems; i++) {
			array[i] = array[i + 1];
		}
		return removed;
	}

	// O(1)
	@Override
	public Integer get(int index) {
		return array[index];
	}

	// O(1)
	@Override
	public int size() {
		return numElems;
	}

	// O(n)
	@Override
	public boolean contains(Integer i) {
		for (int index = 0; index < numElems; index++) {
			if (array[index] == i) {
				return true;
			}
		}
		return false;
	}

	// insert(42, 2)
	// { 17, 18, 19, 77, 18, 52, null, null, null, null }
	// { 17, 18, __, 19, 77, 18, 52, null, null, null }
	// { 17, 18, 42, 19, 77, 18, 52, null, null, null }
	// O(n)
	@Override
	public void insert(Integer i, int index) {
		if (numElems >= array.length) {
			sizeUp();
		}
		for (int j = numElems - 1; j >= index; j--) {
			array[j + 1] = array[j];
		}
		array[index] = i;
		numElems++;
	}

	private void sizeUp() {
		Integer[] newarray = new Integer[array.length * 2];
		for (int i = 0; i < array.length; i++) {
			newarray[i] = array[i];
		}
		array = newarray;
	}

	public String toString() {
		String s = "ArrayList:";
		for (int i = 0; i < numElems; i++) {
			s = s + " " + array[i];
		}
		return s;
	}
}
