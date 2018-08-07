package maze;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Maze extends JPanel {
	int width, height;
	int rows;
	int cols;
	int obs;
	int[] obsx;
	int[] obsy;
	int ix;
	int iy;
	int rowHt;
	int rowWid;
	Agente a = new Agente();
	Elemento[][] tablero;

	Maze(int r, int c) {
		//setSize(width = w, height = h);
		rows = r;
		cols = c;
		System.out.println(c);
		obs = (int) (r * r * 0.05);
		obsx = new int[obs];
		obsy = new int[obs];
		int zz = 0;
		while (zz < obs) {
			int x = 0;
			while (x == 0) {
				x = (int) (Math.random() * cols);
			}
			obsx[zz] = x;
			zz++;
		}
		zz = 0;
		while (zz < obs) {
			int x = (int) (Math.random() * cols);
			obsy[zz] = x;
			zz++;
		}
		tablero = new Elemento[r][c];
	}

	public void paint(Graphics g) {
		rowHt = height / (rows - 2);
		rowWid = width / (cols);
		width = getSize().width;
		height = getSize().height;
		int init = (int) Math.floor(Math.random() * (rows - 2));
		int end = (int) Math.floor(Math.random() * (rows - 2));
		System.out.println(init + ", " + end);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (j == init && i == 0) {
					ix = i;
					iy = j;
					g.setColor(Color.RED);
					g.drawRect(10 + i * rowHt / 2, 10 + j * rowWid / 2, rowHt / 2, rowWid / 2);
					g.setColor(Color.BLACK);
					g.fillOval(10 + i * rowHt / 2, 10 + j * rowWid / 2, rowHt / 2, rowWid / 2);
					a.setx(i);
					a.sety(j);
					tablero[i][j] = a;
				}
				if (j == end && i == rows - 1) {
					g.setColor(Color.RED);
					g.drawRect(10 + i * rowHt / 2, 10 + j * rowWid / 2, rowHt / 2, rowWid / 2);
					g.fillRect(10 + i * rowHt / 2, 10 + j * rowWid / 2, rowHt / 2, rowWid / 2);
					g.setColor(Color.BLACK);
					Meta m = new Meta();
					m.setx(i);
					m.sety(j);
					tablero[i][j] = m;
				}
				if (i > 0 && i < rows - 1) {
					boolean err = true;
					// here is the "actual" grid
					g.drawRect(10 + i * rowHt / 2, 10 + j * rowWid / 2, rowHt / 2, rowWid / 2);
					for (int k = 0; k < obs; k++) {
						if (obsx[k] == i && obsy[k] == j) {
							System.out.println("obs in : " + obsx[k] + ", " + obsy[k]);
							g.fillRect(10 + i * rowHt / 2, 10 + j * rowWid / 2, rowHt / 2, rowWid / 2);
							Obstaculo o = new Obstaculo();
							o.setx(i);
							o.sety(j);
							tablero[i][j] = o;
							err = false;
							break;
						}
					}
					if (err) {
						Elemento e = new Elemento();
						tablero[i][j] = e;
					}
				}
			}
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//mover();
	}

	public void mover() {
		int side = 0;
		int posx = a.getx();
		int posy = a.gety();
		Graphics g = getGraphics();
		System.out.println(cols);
		while (!(tablero[posx][posy] instanceof Meta)) {
			System.out.println("En : (" + posx + ", " + posy + ")");
			// System.out.println("side: " + side);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// System.out.print("moviendo a ");
			/*
			 * if (tablero[posx][posy].getv() >= 10) {
			 * System.out.println("No puedo acabar"); break; }
			 */
			switch (side) {
			case 0:
				// derecha
				System.out.println("derecha.");
				if (posx < cols) {
					if (!(tablero[posx + 1][posy] instanceof Obstaculo)) {
						g.clearRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						g.drawRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						tablero[posx][posy].visitar();
						if (posx < rows - 2)
							posx++;
						g.clearRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						g.drawOval(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
					}
				}
				break;
			case 1:
				// abajo
				System.out.println("abajo.");
				if (posy < cols - 1) {
					if (!(tablero[posx][posy + 1] instanceof Obstaculo)) {
						g.clearRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						g.drawRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						tablero[posx][posy].visitar();
						if (posy < cols - 1)
							posy++;
						g.clearRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						g.drawOval(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
					}
				}
				break;
			case 2:
				// izquierda
				System.out.println("izquierda.");
				if (posx > 1) {
					if (!(tablero[posx - 1][posy] instanceof Obstaculo)) {
						g.clearRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						g.drawRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						tablero[posx][posy].visitar();
						if (posx > 1)
							posx--;
						g.clearRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						g.drawOval(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
					}
				}
				break;
			default:
				// arriba
				System.out.println("arriba.");
				if (posy > 0) {
					if (!(tablero[posx][posy - 1] instanceof Obstaculo)) {
						g.clearRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						g.drawRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						tablero[posx][posy].visitar();
						if (posy > 0)
							posy--;
						g.clearRect(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
						g.drawOval(10 + posx * rowHt / 2, 10 + posy * rowWid / 2, rowHt / 2, rowWid / 2);
					}
				}
				break;
			}
			if (posx == 0) {
				posx++;
			}
			System.out.println("new pos: (" + posx + ", " + posy + ")");
			side = (int) (Math.random() * 4);
		}
		System.out.println("Fin");
	}
}
