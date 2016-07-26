package towerdefense;

import java.util.ArrayList;

import apcs.Window;

public class TowerDefense {
	public static void main(String[] args) {
		World world = new World("defense");
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		ArrayList<Tower> towers = new ArrayList<Tower>();
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		while (true) {

			world.draw();

			for (Bullet bullet : bullets) {
				bullet.move();
				bullet.draw();
			}

			for (int i = 0; i < enemies.size(); i++) {
				Enemy enemy = enemies.get(i);
				if (enemy.move()) {
					// game over
					Window.out.background("red");
					Window.out.print("GAME OVER", Window.width() / 2,
							Window.height() / 2);
					Window.mouse.waitForClick();
					towers.clear();
					enemies.clear();
					bullets.clear();
					for (int x = 0; x < world.getWidth(); x++) {
						for (int y = 0; y < world.getHeight(); y++) {
							world.getSpace(x, y).setTower(null);
						}
					}
					continue;

				}
				enemy.draw();

				for (int j = 0; j < bullets.size(); j++) {
					Bullet bullet = bullets.get(j);
					if (bullet.isHitting(enemy)) {
						enemies.remove(i);
						bullets.remove(j);
						i--;
						break;
					}
				}
			}
			if (Window.mouse.clicked()) {
				int x = Window.mouse.getX() / world.getScale();
				int y = Window.mouse.getY() / world.getScale();
				if (x >= 0 && x < world.getWidth() && y >= 0
						&& y < world.getHeight()) {
					Space space = world.getSpace(x, y);
					String color = "orange";
					int reload = 50;
					int range = world.getScale() * 5;

					if (space.getType() == world.GRASS
							&& space.hasTower() == false) {
						Tower tower = new Tower(space, world, color, reload,
								range, null);
						tower.setComparator(new CloseComparator(tower));
						towers.add(tower);

					}
				}
			}

			for (Tower tower : towers) {
				tower.draw(world.getScale() / 2);
				Bullet bullet = tower.fire(enemies);
				if (bullet != null) {
					bullets.add(bullet);
				}
			}

			Window.frame();

			if (Window.random(-20, 20) == 0) {
				enemies.add(new Enemy(10, 3, world));
			}

		}
	}
}
