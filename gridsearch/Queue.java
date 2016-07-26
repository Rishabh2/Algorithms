package gridsearch;

import java.util.LinkedList;

public class Queue implements List {

	LinkedList<Cell> list;

	public Queue() {
		list = new LinkedList<Cell>();
	}

	@Override
	public void add(Cell c) {
		list.addLast(c);

	}

	@Override
	public Cell remove() {
		return list.removeFirst();
	}

	@Override
	public Cell peek() {
		return list.peekFirst();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
