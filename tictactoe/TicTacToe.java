package tictactoe;

import apcs.Window;

public class TicTacToe {

	public static void main(String[] args) {
		Window.size(600, 600);
		Board board = new Board();
		board.draw();
		while (board.getMoves().length != 0 && board.winner() == 0) {
			if (board.player == 1) {
				humanMove(board);
			} else {
				computerMove(board);
			}
			board.draw();
		}

	}

	private static void computerMove(Board board) {
		Move[] moves = board.getMoves();
		int worst = 2;
		Move worstMove = null;
		for (Move m : moves) {
			Board b = new Board(board, m);
			int value = b.max(1);
			if (value < worst) {
				worstMove = m;
				worst = value;
			}
		}
		board.makeMove(worstMove);

	}

	private static void humanMove(Board board) {
		Window.mouse.waitForClick();
		int x = Window.mouse.getX() / 200;
		int y = Window.mouse.getY() / 200;

		board.makeMove(new Move(x, y));
	}

}
