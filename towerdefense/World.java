package towerdefense;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import apcs.Window;

public class World {
	public static final int PATH = 0;

	public static final int GRASS = 1;

	private Space[][] grid;

	private int height;

	private int width;

	private int scale;

	private Space start;

	private Space end;


	public World(String path) {
		height = 0;
		StringBuilder input = new StringBuilder();
		try {
			Scanner scanner = new Scanner(new File(path));
			while (scanner.hasNextLine()) {
				input.append(scanner.nextLine());
				height++;
			}
			width = input.length() / height;
			scale = 500 / Math.max(width, height);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (input.length() > 0) {
			grid = new Space[width][height];
			Window.size(width * scale, height * scale);
			Window.setFrameRate(100);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					Space newspace = new Space(x, y, this);
					switch (input.charAt(x + y * width)) {
					case 'P':
						newspace.setType(PATH);
						start = newspace;
						break;
					case '.':
						newspace.setType(PATH);
						end = newspace;
						break;
					case ' ':
						newspace.setType(PATH);
						break;
					case '%':
						newspace.setType(GRASS);
						break;
					}
					grid[x][y] = newspace;
				}
			}
		}
		createPath();
	}

	/**
	 * Get the start of the path
	 * 
	 * @return the start of the path
	 */
	public Space getStart() {
		return start;
	}

	/**
	 * Get the end of the path
	 * 
	 * @return the end of the path
	 */
	public Space getEnd() {
		return end;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Space getSpace(int x, int y) {
		return grid[x][y];
	}

	/**
	 * Draw the world
	 */
	public void draw() {
		Window.out.background("white");
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				Space space = grid[x][y];
				if (space.getType() == PATH) {
					Window.out.color(255, 218, 185);
				} else if (space.getType() == GRASS) {
					Window.out.color("light green");
				}

				Window.out.square(x * scale + scale / 2, y * scale + scale / 2,
						scale);
			}
		}
	}

	private void createPath() {
		Space current = start;
		while (current != end) {
			Space[] neighbors = current.neighbors();
			for (Space neighbor : neighbors) {
				if (neighbor.getNext() != current) {
					current.setNext(neighbor);
				}
			}
			current = current.getNext();
		}
	}

	public int getScale() {
		return scale;
	}
}