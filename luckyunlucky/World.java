package luckyunlucky;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import apcs.Window;

/**
 * The World object represents a 2D world of cells.
 * 
 * @author keshav
 * 
 */
public class World {
	// Terrain types
	public static final int WALL = 1;
	public static final int START = 2;
	public static final int END = 3;
	public static final int MARK = 4;

	// GUI constants
	private int width = 80;
	private int height = 60;
	private int scale = 10;
	private boolean showGrid = false;

	// Store a 2D array of cells and a start/end cell
	private Cell[][] grid;
	private Cell start;
	private Cell end;

	/**
	 * Constructs a world that is by default 80 x 60 cells, with a cell size of
	 * 10.
	 */
	public World() {
		this(80, 60, 10);
	}

	/**
	 * Makes a world of a custom width and height.
	 * 
	 * @param width
	 *            - the width of the world
	 * @param height
	 *            - the height of the world
	 */
	public World(int width, int height, int scale) {
		this.width = width;
		this.height = height;
		this.scale = scale;

		Window.size(width * scale, height * scale);
		Window.setFrameRate(100);

		// Create the grid and size the window accordingly
		grid = new Cell[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				grid[x][y] = new Cell(x, y, this);
			}
		}
	}

	/**
	 * Creates a world from the file description.
	 * 
	 * @param path
	 *            - the path to the world file.
	 */
	public World(String path) {
		this(path, 10);
	}

	/**
	 * Creates a world from the file description using the given scale for cell
	 * size.
	 * 
	 * @param path
	 *            - path to the world file
	 * @param scale
	 *            - the size of each cell
	 */
	public World(String path, int scale) {
		height = 0;
		StringBuilder input = new StringBuilder();
		try {
			Scanner s = new Scanner(new File(path));
			while (s.hasNextLine()) {
				input.append(s.nextLine());
				input.append("\n");
				height++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (input.length() > 0) {
			this.scale = scale;
			width = input.length() / height - 1;
			grid = new Cell[width][height];
			Window.size(width * scale, height * scale);
			Window.setFrameRate(100);

			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					grid[x][y] = new Cell(x, y, this);
					switch (input.charAt(x + y * (width + 1))) {
					case '%':
						grid[x][y].setType(WALL);
						break;
					case 'P':
						grid[x][y].setType(START);
						start = grid[x][y];
						break;
					case '.':
						grid[x][y].setType(END);
						end = grid[x][y];
						break;
					}
				}
			}
		}
	}

	/**
	 * Sets the desired route through the world.
	 * 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public void setRoute(int startX, int startY, int endX, int endY) {
		setRoute(getCell(startX, startY), getCell(endX, endY));
	}

	public void setRoute(Cell start, Cell end) {
		if (start != null && end != null) {
			this.start = start;
			this.end = end;

			start.setType(START);
			end.setType(END);
		}
	}

	public void unmark() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				grid[x][y].unmark();
			}
		}
	}

	/**
	 * Sets a wall at the given x, y position.
	 * 
	 * @param x
	 *            - x position of the wall
	 * @param y
	 *            - y position of the wall
	 */
	public void setWall(int x, int y) {
		if (getCell(x, y) != null) {
			getCell(x, y).setType(WALL);
		}
	}

	/**
	 * Returns the cell at the given x, y position, or null if no cell is at
	 * that position.
	 * 
	 * @param x
	 *            - x position of the cell
	 * @param y
	 *            - y position of the cell
	 * @return - a Cell object for the given cell
	 */
	public Cell getCell(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height)
			return null;
		return grid[x][y];
	}

	/**
	 * Returns the starting cell of this world, if there is one.
	 */
	public Cell getStart() {
		return start;
	}

	/**
	 * Returns the ending cell of this world, if there is one.
	 */
	public Cell getEnd() {
		return end;
	}

	/**
	 * Returns the width of this world.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of this world.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Draws grid lines when drawing the world.
	 */
	public void drawGrid() {
		showGrid = true;
	}

	/**
	 * Returns the width of this world.
	 */
	public void draw() {
		Window.out.background("white");

		if (showGrid) {
			Window.out.color("light gray");
			for (int x = 0; x < width * scale; x = x + scale) {
				Window.out.line(x, 0, x, height * scale);
			}
			for (int y = 0; y < height * scale; y = y + scale) {
				Window.out.line(0, y, width * scale, y);
			}
		}

		// Go to every square in the grid
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (getCell(x, y).isWall()) {
					Window.out.color("black");
				} else if (grid[x][y].getType() == START) {
					Window.out.color("green");
				} else if (grid[x][y].getType() == END) {
					Window.out.color("red");
				} else if (grid[x][y].isPath()) {
					Window.out.color("yellow");
				} else if (grid[x][y].isMarked()) {
					Window.out.color("gray");
				} else {
					Window.out.color("white");
				}
				Window.out.square(x * scale + scale / 2, y * scale + scale / 2,
						scale - (showGrid ? 1 : 0));
			}
		}
		Window.frame();
	}
}