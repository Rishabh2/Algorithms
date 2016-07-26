package apcs;

public class VisualList {
	Node first;
	int numElements;
	int level;
	public static final int BOX_SIZE = 50;

	public VisualList() {
		first = null;
		numElements = 0;
		level = 0;
	}

	public VisualList(int level) {
		this();
		this.level = level;
	}

	public VisualList(Node first) {
		this();
		this.first = first;
	}

	public VisualList(Node first, int level) {
		this();
		this.level = level;
		this.first = first;
	}

	public void add(int value) {
		int[] params = new int[1];
		params[0] = value;
		draw(null, null, "add", params, 0);
		if (first == null) {
			first = new Node(value, null, VisualList.BOX_SIZE, getY());
			numElements++;
			draw(first, null, "add", params, 1);
		} else {
			Node current = first;
			draw(current, null, "add", params, 1);
			while (current.next != null) {
				current = current.next;
				draw(current, null, "add", params, 1);
			}
			current.next = new Node(value, null);
			organize();
			numElements++;
			draw(null, null, "add", params, 2);
		}
	}

	public int getNextX(Node prev) {
		return prev.x + BOX_SIZE * 3 / 2;
	}

	public int getY() {
		return level * 2 * BOX_SIZE + BOX_SIZE;
	}

	public int get(int index) {
		if (index >= numElements || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int[] params = new int[1];
		params[0] = index;
		draw(null, null, "get", params, 0);
		int i = 0;
		Node current = first;
		draw(current, null, "get", params, 1);
		while (i < index) {
			current = current.next;
			draw(current, null, "get", params, 1);
			i++;
		}
		draw(null, null, "get", params, 2);
		return current.value;
	}

	public int remove(int index) {
		if (index >= numElements || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int[] params = new int[1];
		params[0] = index;
		draw(null, null, "remove", params, 0);
		if (index == 0) {
			Node temp = first;
			draw(null, temp, "remove", params, 2);
			first = first.next;
			numElements--;
			return temp.value;
		}
		int i = 0;
		Node current = first;
		draw(current, null, "remove", params, 1);
		while (i < index - 1) {
			current = current.next;
			draw(current, null, "remove", params, 1);
			i++;
		}
		Node removeThis = current.next;
		draw(current, removeThis, "remove", params, 1);
		current.next = removeThis.next;
		removeThis.x = Window.width() / 2;
		removeThis.y = Window.height() / 2;
		draw(current, removeThis, "remove", params, 1);
		numElements--;
		organize();
		draw(null, null, "remove", params, 2);
		return removeThis.value;
	}

	private void organize() {
		if (first != null) {
			Node current = first;
			Node penultimate = null;
			while (current.next != null) {
				current.next.y = getY();
				current.next.x = getNextX(current);
				penultimate = current;
				current = current.next;
			}
			current.y = getY();
			if (current.equals(first)) {
				current.x = BOX_SIZE;
			} else {
				current.x = getNextX(penultimate);
			}
		}
	}

	public int size() {
		return numElements;
	}

	public void insert(int value, int index) {
		if (index >= numElements || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int[] params = new int[2];
		params[0] = value;
		params[1] = index;
		draw(null, null, "insert", params, 0);
		if (index == 0) {
			first = new Node(value, first, BOX_SIZE, level * BOX_SIZE
					+ BOX_SIZE);
		} else {
			Node current = first;
			draw(current, null, "insert", params, 1);
			int i = 0;
			while (i < index - 1) {
				current = current.next;
				draw(current, null, "insert", params, 1);
				i++;
			}
			current.next = new Node(value, current.next, Window.height() / 2,
					Window.width() / 2);
			draw(current, null, "insert", params, 1);
		}
		organize();
		draw(null, null, "insert", params, 2);
		numElements++;
	}

	public void draw(Node curr, Node rem, String callback, int[] params,
			int step) {
		Window.out.background("light green");
		if (rem != null) {
			rem.draw("red");
		}
		Window.out.color("white");
		Window.out.rectangle(Window.width() / 2,
				Window.height() - BOX_SIZE / 2, Window.width(), BOX_SIZE);
		Window.out.color("black");
		String callbackString = getCallbackString(callback, params);
		Window.out.print(callbackString, 10, Window.height() - BOX_SIZE + 15);
		// pause before drawing
		if (step == 0) {
			draw(null, null, callback, params, 1);
			makeButton("green", "Go!");
			pause();
		}
		// draw all elements from all lists
		for (VisualList vl : LinkedListVisualizer.lists) {
			if (vl.equals(this)) {
				drawList(vl, curr, rem);
			} else {
				drawList(vl, null, null);
			}
			vl.organize();
		}
		// pause after drawing
		if (step == 1) {
			makeButton("gray", "Step!");
			pause();
		} else if (step == 2) {
			makeButton("red", "Stop!");
			pause();
		} else {
			Window.sleep(100);
		}
	}

	/**
	 * Returns the second half of the list and modifies the first half
	 */
	public static void drawList(VisualList list, Node curr, Node rem) {
		Node current = list.first;
		while (current != null) {
			if (current.equals(curr)) {
				current.draw("yellow");
			} else {
				if (!current.equals(rem))
					current.draw("white");
			}
			current = current.next;
		}
	}

	private static void makeButton(String color, String text) {
		Window.out.color(color);
		Window.out.square(Window.width() - BOX_SIZE,
				Window.height() - BOX_SIZE, BOX_SIZE);
		Window.out.color("black");
		Window.out.print(text, Window.width() - BOX_SIZE - 20, Window.height()
				- BOX_SIZE);
	}

	private static void pause() {
		boolean clicked = Window.key.pressed("space");
		while (!clicked) {
			int x = Window.mouse.getX();
			int y = Window.mouse.getY();
			if (Math.abs(Window.width() - BOX_SIZE - x) < BOX_SIZE / 2
					&& Math.abs(Window.height() - BOX_SIZE - y) < BOX_SIZE / 2) {
				clicked = Window.mouse.clicked();
				if (clicked) {
					Window.mouse.waitForRelease();
				}
			}
			Window.sleep(10);
		}
	}

	private static String getCallbackString(String callback, int[] params) {
		String method = callback + "(";
		String param = "";
		if (params.length != 0) {
			param = params[0] + "";
			for (int i = 1; i < params.length; i++) {
				param = param + ", " + params[i];
			}
		}
		return method + param + ");";
	}
}