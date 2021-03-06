import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Sudoku {
	public static byte[][] sudoku = new byte[729][82]; // global array untuk sudoku solver
	public static byte step = 0; // global variable untuk solusi langkah

	private static final int WindowWidth = 777; // memiliki lebar 777 pixel
	private static final int WindowHeight = 636; // memiliki tinggi 636 pixel

	public static void ShowGUI() {
		Smethods.start(sudoku); 

		final byte border = 14; 
		JFrame f = new JFrame("Sudoku");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false); 
		f.setSize(WindowWidth, WindowHeight); 
		f.setLocation(0, 0); 
		f.setLayout(new BorderLayout()); 

		f.add(new SPanel(new Dimension(WindowWidth, border)), BorderLayout.NORTH);
		f.add(new SPanel(new Dimension(WindowWidth, border)), BorderLayout.SOUTH);
		f.add(new SPanel(new Dimension(border, WindowHeight)), BorderLayout.EAST);
		f.add(new SPanel(new Dimension(0, WindowHeight)), BorderLayout.WEST); 

		Tampilan tp = new Tampilan();
		tp.setBackground(Color.WHITE); 
		f.add(tp, BorderLayout.CENTER); 

		f.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ShowGUI();
			}
		}); 
	}

}
