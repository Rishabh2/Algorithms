package apcs;

public class Animation {

	public static void main(String[] args) {
		Window.size(500, 500);

		int radius = 50;
		int x = 250;
		int y = radius;
		int yspeed = 0;

		while (1 < 2) {
			// drawing
			Window.out.background("white");
			Window.out.color("red");
			Window.out.circle(x, y, radius);
			Window.frame(33);

			// moving
			y = y + yspeed;
			yspeed = yspeed + 1;

			// bouncing
			// if the ball is hitting the ground, then bounce up
			if (y >= Window.height() - radius) {
				y = Window.height() - radius;
				y = y - 50;
				yspeed = yspeed * -4 / 5;
			}

		}

	}

}
