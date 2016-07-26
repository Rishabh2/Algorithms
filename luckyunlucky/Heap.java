package luckyunlucky;

import java.util.Comparator;

public class Heap<T> implements List<T> {
	private T[] array;
	private Comparator<T> comparator;
	private int nelems;

	@SuppressWarnings("unchecked")
	public Heap(Comparator<T> comparator) {
		this.comparator = comparator;
		nelems = 0;
		array = (T[]) new Object[10];
	}

	// compare the objects at these indexes
	private int compare(int i, int j) {
		return comparator.compare(array[i], array[j]);
	}

	// result = comparator.compare(a, b)
	// a < b -> result < 0
	// a == b -> result == 0
	// a > b -> result > 0

	private void swap(int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	@Override
	public void add(T object) {
		if (nelems == array.length) {
			sizeUp();
		}
		array[nelems] = object;
		siftUp(nelems++);
	}

	private void siftUp(int index) {
		if (index > 0) {
			int parentIndex = (index - 1) / 2;
			if (compare(parentIndex, index) > 0) {
				swap(parentIndex, index);
				siftUp(parentIndex);
			}
		}
	}

	@Override
	public T remove() {
		T data = array[0];
		array[0] = array[--nelems];
		siftDown(0);
		return data;
	}

	private void siftDown(int index) {
		int child1 = index * 2 + 1;
		int child2 = index * 2 + 2;
		// CASE: No children
		if (child1 >= nelems) {
			return;
		}
		// CASE: One child
		if (child2 >= nelems) {
			if (compare(index, child1) > 0) {
				swap(index, child1);
			}
		} else {
			// CASE: 2 children
			int smallerChild = child1;
			if (compare(child1, child2) > 0) {
				smallerChild = child2;
			}
			if (compare(smallerChild, index) < 0) {
				swap(smallerChild, index);
				siftDown(smallerChild);
			}
		}
	}

	@Override
	public T peek() {
		return array[0];
	}

	@Override
	public boolean isEmpty() {
		return nelems == 0;
	}

	@SuppressWarnings("unchecked")
	private void sizeUp() {
		T[] newArray = (T[]) new Object[array.length * 2];
		for (int i = 0; i < nelems; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

}
