package maze;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {
	public Main() {
		int n = 10;
		int size = 5 * n;
		Maze xyz = new Maze(n + 2, n);
		add(xyz);
		pack();
		setSize(50 + (n * size), 50 + (n * size));
		setResizable(false);
		xyz.mover();
	}

	public static void main(String[] a) {
		new Main().setVisible(true);
		
	}
}