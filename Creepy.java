import apcs.Window;

public class Creepy {
	public static void main(String[] args) {
		Window.size(500, 500);
		int centerx = Window.width() / 2;
		int centery = Window.height() / 2;
		int radius = 100;
		int reyex = centerx + radius / 2;
		int reyey = centerx - radius / 2;
		int leyex = centerx - radius / 2;
		int leyey = centery - radius / 2;
		int rpupx;
		int rpupy;
		int rpupdist;
		double rpupang;
		int lpupx;
		int lpupy;
		int lpupdist;
		double lpupang;
		int pupradius = 5;
		int mousex;
		int mousey;
		int eyeradius = 15;
		while (true) {
			Window.out.background("white");
			Window.out.color("peach");
			Window.out.circle(centerx, centery, radius);
			Window.out.color("red");
			Window.out.arc(centerx, centery, 3 * radius / 4, 3 * radius / 4, 0,
					180);
			Window.out.circle(reyex, reyey, eyeradius);
			Window.out.circle(leyex, leyey, eyeradius);

			mousex = Window.mouse.getX();
			mousey = Window.mouse.getY();
			rpupx = mousex;
			rpupy = mousey;
			lpupx = mousex;
			lpupy = mousey;
			rpupdist = distance(rpupx, rpupy, reyex, reyey);
			lpupdist = distance(lpupx, lpupy, leyex, leyey);
			if (rpupdist > eyeradius - pupradius) {
				rpupang = Math.atan2(rpupy - reyey, rpupx - reyex);
				rpupx = (int) (reyex + Math.cos(rpupang)
						* (eyeradius - pupradius));
				rpupy = (int) (reyey + Math.sin(rpupang)
						* (eyeradius - pupradius));
			}
			if (lpupdist > eyeradius - pupradius) {
				lpupang = Math.atan2(lpupy - leyey, lpupx - leyex);
				lpupx = (int) (leyex + Math.cos(lpupang)
						* (eyeradius - pupradius));
				lpupy = (int) (leyey + Math.sin(lpupang)
						* (eyeradius - pupradius));
			}
			Window.out.color("white");
			Window.out.circle(rpupx, rpupy, pupradius);
			Window.out.circle(lpupx, lpupy, pupradius);

			Window.frame();
		}
	}

	public static int distance(int x1, int y1, int x2, int y2) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		return (int) Math.sqrt(dx * dx + dy * dy);
	}
}
