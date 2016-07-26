package luckyunlucky;

import java.util.Comparator;

import apcs.Window;

public class MazeSolver {

	public static void main(String[] args) {
		World w = new World("big");
		w.draw();
		// Get all the possibilities
		// Pick one
		// Mark it's neighbors as possibilities

		List<Cell> cells = new Heap<Cell>(new Comparator<Cell>() {
			public int compare(Cell c1, Cell c2) {
				return c1.endDistance() - c2.endDistance();
			}
		});

		Cell current = w.getStart();
		while (!current.equals(w.getEnd())) {
			for (Cell c : current.neighbors()) {
				if (!c.isMarked()) {
					c.mark();
					c.setPrevious(current);
					cells.add(c);
				}
			}
			current = cells.remove();
			w.draw();
			// Window.mouse.waitForClick();
			// Window.mouse.waitForRelease();
		}
		while (!current.equals(w.getStart())) {
			current.markAsPath();
			current = current.getPrevious();
		}
		w.draw();
	}

}
