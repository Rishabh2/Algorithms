package apcs;

public class Node {

	public int x;
	public int y;
	public Node next;
	public int value;

	public Node(int value, Node next) {
		this.value = value;
		this.next = next;
	}

	public Node(int value, Node next, int x, int y) {
		this(value, next);
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return ("(" + value + ", " + x + ", " + y + ")");
	}

	public boolean equals(Node b) {
		if (b == null)
			return false;
		return (this.y == b.y && this.x == b.x);
	}

	public void draw(String color) {
		border();
		Window.out.color(color);
		draw();
	}

	public void draw() {
		Window.out.rectangle(x, y, VisualList.BOX_SIZE, VisualList.BOX_SIZE);
		Window.out.color("light blue");
		Window.out.rectangle(x, y + VisualList.BOX_SIZE / 4,
				VisualList.BOX_SIZE, VisualList.BOX_SIZE / 2);
		Window.out.color("black");
		Window.out.print(value, x - VisualList.BOX_SIZE / 2, y
				+ VisualList.BOX_SIZE / 2);
		Window.out.color("red");
		if (next != null) {
			Window.out.line(x + VisualList.BOX_SIZE / 2, y, next.x, next.y);
		} else {
			Window.out.line(x + VisualList.BOX_SIZE / 2, y, x
					+ VisualList.BOX_SIZE, y);
			Window.out.color("purple");
			Window.out.print("null", x + VisualList.BOX_SIZE, y + 10);
		}
	}

	private void border() {
		Window.out.color("black");
		Window.out.rectangle(this.x, this.y, VisualList.BOX_SIZE + 2,
				VisualList.BOX_SIZE + 2);

	}
}
