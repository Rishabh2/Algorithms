package tictactoe;

public class Move {

	int x;
	int y;
	int value;

	public Move(int x, int y) {
		this.x = x;
		this.y = y;
		value = 100;
	}

	public String toString() {
		return "X: " + x + " Y: " + y + " Value: " + value;
	}
}
