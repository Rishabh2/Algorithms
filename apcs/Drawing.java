package apcs;

public class Drawing {

	public static void main(String[] args) {
		Window.size(500, 500);
		Window.out.background("light blue");

		Window.out.color("violet");
		Window.out.circle(250, 500, 250);

		Window.out.color("indigo");
		Window.out.circle(250, 500, 225);

		Window.out.color("blue");
		Window.out.circle(250, 500, 200);

		Window.out.color("green");
		Window.out.circle(250, 500, 175);

		Window.out.color("yellow");
		Window.out.circle(250, 500, 150);

		Window.out.color("orange");
		Window.out.circle(250, 500, 125);

		Window.out.color("red");
		Window.out.circle(250, 500, 100);

		Window.out.color("light blue");
		Window.out.circle(250, 500, 75);
	}

}
