package gridsearch;

import java.util.Comparator;

public class PQueue implements List {

	Heap heap;

	public PQueue() {
		heap = new Heap(new Comparator<Cell>() {

			// negative number if o1 comes first
			// zero if they have equal priority
			// positive number if o2 comes first
			@Override
			public int compare(Cell o1, Cell o2) {
				int dist1 = o1.getDistance(o1.getWorld().getEnd());
				int dist2 = o2.getDistance(o2.getWorld().getEnd());
				int cost1 = o1.getTotalCost();
				int cost2 = o2.getTotalCost();

				return (dist1 * cost1 * cost1) - (dist2 * cost2 * cost2);

			}

		});
		// Comparator - Compares
		// Terminator - Terminates
		// Iterator - Iterates
		// Alligator - Alligates
	}

	@Override
	public void add(Cell c) {
		heap.add(c);

	}

	@Override
	public Cell remove() {
		return heap.remove();
	}

	@Override
	public Cell peek() {
		return heap.peek();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}

}
