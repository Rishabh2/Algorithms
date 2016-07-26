package gridsearch;

import apcs.Window;

public class MazeSolver {

	public static void main(String[] args) {

		World w = new World("open");

		int type = 3;

		w.draw();
		List list;
		if (type == 1) {
			list = new Stack();
		} else if (type == 2) {
			list = new Queue();
		} else {
			list = new PQueue();
		}

		Cell current = w.getStart();
		current.mark();

		while (current.equals(w.getEnd()) == false) {
			Cell[] neighbors = current.neighbors();

			for (Cell c : neighbors) {
				if (c.isMarked() == false) {

					c.setPrevious(current);
					list.add(c);
					c.mark();
				}
			}
			w.draw();
			// Window.mouse.waitForClick();
			// Window.mouse.waitForRelease();
			current = list.remove();
		}

		current = w.getEnd().getPrevious();
		while (current.equals(w.getStart()) == false) {
			current.markAsPath();
			current = current.getPrevious();
		}
		w.draw();

	}

}
