package gridsearch;

import java.util.Comparator;

public class Heap implements List {

	Comparator<Cell> comparator;
	Cell[] cells;
	int nelems;

	public Heap(Comparator<Cell> comparator) {
		this.comparator = comparator;
		cells = new Cell[10];
		nelems = 0;
	}

	private void swap(int i, int j) {
		Cell temp = cells[i];
		cells[i] = cells[j];
		cells[j] = temp;
	}

	@Override
	public void add(Cell c) {
		if (nelems >= cells.length) {
			sizeUp();
		}
		cells[nelems] = c;
		siftUp(nelems++);
	}

	private void sizeUp() {
		Cell[] newarray = new Cell[cells.length * 2];
		for (int i = 0; i < cells.length; i++) {
			newarray[i] = cells[i];
		}
		cells = newarray;
	}

	private void siftUp(int index) {
		if (index == 0) {
			return;
		}
		int parent = (index - 1) / 2;
		if (comparator.compare(cells[parent], cells[index]) > 0) {
			// if (cells[parent] > cells[index]) {
			swap(parent, index);
			siftUp(parent);
		}
	}

	@Override
	public Cell remove() {
		Cell removed = cells[0];
		cells[0] = cells[--nelems];
		siftDown(0);
		return removed;

	}

	private void siftDown(int index) {
		int child1 = index * 2 + 1;
		int child2 = index * 2 + 2;

		// case of no children
		if (child1 >= nelems) {
			return;
		}
		// case of 1 child
		else if (child2 >= nelems) {
			if (comparator.compare(cells[child1], cells[index]) < 0) {
				// if (cells[child1] < cells[index]) {
				swap(child1, index);
				siftDown(child1);
			}
		}
		// case of two children
		else {
			int smallerchild;
			if (comparator.compare(cells[child1], cells[child2]) < 0) {
				// if (cells[child1] < cells[child2]) {
				smallerchild = child1;
			} else {
				smallerchild = child2;
			}

			if (comparator.compare(cells[smallerchild], cells[index]) < 0) {
				swap(smallerchild, index);
				siftDown(smallerchild);
			}
		}
	}

	@Override
	public Cell peek() {
		return cells[0];
	}

	@Override
	public boolean isEmpty() {
		return nelems == 0;
	}

}
