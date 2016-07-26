package luckyunlucky;

import java.util.ArrayList;

/**
 * A Cell object represents an individual cell in a 2D world.
 * 
 * @author keshav
 */
public class Cell {
	// Stores the position of the cell.
	private int x;
	private int y;
	private int type = 0;

	// Reference to the world that the cell is in.
	private World world;

	// For path finding and heuristics.
	private boolean marked = false;
	private boolean markPath = false;
	private Cell previous;
	private int cost = 0;

	public Cell(int x, int y, World world) {
		this.x = x;
		this.y = y;
		this.world = world;
	}

	/**
	 * Returns an array of the neighboring cells to this one.
	 */
	public Cell[] neighbors() {
		ArrayList<Cell> cells = new ArrayList<Cell>();

		if (x > 0 && !world.getCell(x - 1, y).isWall()) {
			cells.add(world.getCell(x - 1, y));
		}
		if (x + 1 < world.getWidth() && !world.getCell(x + 1, y).isWall()) {
			cells.add(world.getCell(x + 1, y));
		}
		if (y > 0 && !world.getCell(x, y - 1).isWall()) {
			cells.add(world.getCell(x, y - 1));
		}
		if (y + 1 < world.getHeight() && !world.getCell(x, y + 1).isWall()) {
			cells.add(world.getCell(x, y + 1));
		}

		return cells.toArray(new Cell[cells.size()]);
	}

	/**
	 * Sets the type of this cell.
	 * 
	 * @param type
	 *            - the integer describing the type of this cell
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Returns the type of this cell.
	 */
	public int getType() {
		return type;
	}

	/**
	 * Returns whether or not this cell is a wall.
	 */
	public boolean isWall() {
		return this.type == World.WALL;
	}

	/**
	 * Marks this cell.
	 */
	public void mark() {
		marked = true;
	}

	/**
	 * Marks this cell as the path.
	 */
	public void markAsPath() {
		markPath = true;
	}

	/**
	 * Unmarks this cell.
	 */
	public void unmark() {
		marked = false;
		markPath = false;
	}

	/**
	 * Returns true if this cell is marked.
	 */
	public boolean isMarked() {
		return marked;
	}

	/**
	 * Returns whether this cell is marked as a path.
	 */
	public boolean isPath() {
		return markPath;
	}

	/**
	 * Returns true if this cell is equal to another cell.
	 */
	public boolean equals(Cell other) {
		if (x == other.x && y == other.y) {
			return true;
		}
		return false;
	}

	/**
	 * Sets the previous cell.
	 */
	public void setPrevious(Cell cell) {
		previous = cell;
	}

	/**
	 * Returns the previous cell to this one.
	 */
	public Cell getPrevious() {
		return previous;
	}

	/**
	 * Returns whether or not this cell has a previous cell.
	 */
	public boolean hasPrevious() {
		return previous != null;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDistance(Cell other) {
		return getDistance(other.getX(), other.getY());
	}

	public int getDistance(int ox, int oy) {
		return Math.abs(x - ox) + Math.abs(y - oy);
	}

	public int endDistance() {
		return getDistance(world.getEnd());
	}
}