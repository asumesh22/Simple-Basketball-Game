package basketball;

import java.io.IOException;

import javax.swing.JFrame;

public class driver {
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Basketball");
        frame.setSize(800, 800);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new panel());
        frame.setVisible(true);
	}
}
