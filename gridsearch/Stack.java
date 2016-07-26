package gridsearch;

import java.util.LinkedList;

public class Stack implements List {

	LinkedList<Cell> list;

	public Stack() {
		list = new LinkedList<Cell>();
	}

	@Override
	public void add(Cell c) {
		list.addFirst(c);
	}

	@Override
	public Cell remove() {
		return list.removeFirst();
	}

	@Override
	public Cell peek() {
		return list.peekFirst(); // list.get(0)
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
