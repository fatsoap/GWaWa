package project_alpha;



import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame screen = new JFrame("GWW");
		Gameplay game = new Gameplay();
		screen.setSize(1000,1000);
		screen.setLocation(20,20);
		screen.setResizable(false);
		screen.setVisible(true);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.add(game);
	}

}
