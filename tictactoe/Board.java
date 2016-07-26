package tictactoe;

import apcs.Window;

public class Board {

	int[][] grid;
	int player; // 1 is human, 2 is computer

	public Board() {
		grid = new int[3][3];
		player = 1;
	}

	public Board(Board b, Move m) {
		grid = new int[3][3];

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				grid[x][y] = b.grid[x][y];
			}
		}

		player = b.player;
		makeMove(m);
	}

	public void makeMove(Move m) {
		if (grid[m.x][m.y] == 0) {
			grid[m.x][m.y] = player;
			player = 3 - player;
		}
	}

	public int max(int forPlayer) {
		if (winner() == forPlayer) {
			return 1;
		}
		if (winner() == 3 - forPlayer) {
			return -1;
		}
		Move[] moves = getMoves();
		if (moves.length == 0) {
			return 0;
		}
		int best = -2;
		for (Move m : moves) {
			Board b = new Board(this, m);
			int value = b.min(forPlayer);

			if (value > best) {
				best = value;
			}
		}
		return best;
	}

	public int min(int forPlayer) {
		if (winner() == forPlayer) {
			return 1;
		}
		if (winner() == 3 - forPlayer) {
			return -1;
		}
		Move[] moves = getMoves();
		if (moves.length == 0) {
			return 0;
		}
		int worst = 2;
		for (Move m : moves) {
			Board b = new Board(this, m);
			int value = b.max(forPlayer);

			if (value < worst) {
				worst = value;
			}
		}
		return worst;
	}

	public int winner() {
		// return 1 if player 1 has won, return 2 if player 2 has won
		// return 0 otherwise

		// check for a horizontal winner
		for (int y = 0; y < 3; y++) {
			if (grid[0][y] == grid[1][y] && grid[1][y] == grid[2][y]) {
				if (grid[0][y] == 1) {
					return 1;
				} else if (grid[0][y] == 2) {
					return 2;
				}
			}
		}
		// check for a vertical winner
		for (int x = 0; x < 3; x++) {
			if (grid[x][0] == grid[x][1] && grid[x][1] == grid[x][2]) {
				if (grid[x][0] == 1) {
					return 1;
				} else if (grid[x][0] == 2) {
					return 2;
				}
			}
		}

		// two ifs for two diagonals
		if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
			if (grid[1][1] == 1) {
				return 1;
			} else if (grid[1][1] == 2) {
				return 2;
			}
		}

		if (grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) {
			if (grid[1][1] == 1) {
				return 1;
			} else if (grid[1][1] == 2) {
				return 2;
			}
		}

		return 0; // no winner

	}

	public Move[] getMoves() {
		int count = 0;

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (grid[x][y] == 0) {
					count++;
				}
			}
		}

		Move[] available = new Move[count];
		int index = 0;
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (grid[x][y] == 0) {
					Move m = new Move(x, y);
					available[index] = m;
					index++;
				}
			}
		}
		return available;
	}

	public void draw() {
		int size = Window.height() / 3;

		Window.out.background("white");
		Window.out.color("black");
		// vertical lines
		Window.out.line(size, 0, size, Window.height());
		Window.out.line(size * 2, 0, size * 2, Window.height());
		// horizontal lines
		Window.out.line(0, size, Window.width(), size);
		Window.out.line(0, size * 2, Window.width(), size * 2);

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (grid[x][y] == 1) {
					Window.out.fontSize(50);
					Window.out.print("X", x * size + size / 2, y * size + size
							/ 2);
				}
				if (grid[x][y] == 2) {
					Window.out.fontSize(50);
					Window.out.print("O", x * size + size / 2, y * size + size
							/ 2);
				}
			}
		}
		Window.frame();
	}
}
