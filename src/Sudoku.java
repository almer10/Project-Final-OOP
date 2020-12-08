import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This class contains the "public static void main(String[] args)" This class
 * generates the display window. The centre panel has the buttons and the sudoku
 * display
 * 
 * @author Carl Morey
 * @version 5.0 21/12/2011
 */

public class Sudoku {
	public static byte[][] sudoku = new byte[729][82]; // global array untuk sudoku solver
	public static byte step = 0; // global variable untuk solusi langkah

	private static final int WindowWidth = 777; // memiliki lebar 777 pixel
	private static final int WindowHeight = 636; // memiliki tinggi 636 pixel

	public static void ShowGUI() {
		Smethods.start(sudoku); // start array at step 0 has no numbers selected

		final byte border = 14; // border for display
		JFrame f = new JFrame("Sudoku");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("sudoku.png"));
		} catch (IOException e) {
		} // end of try/catch
		f.setResizable(false); // not to be resized
		f.setIconImage(image);
		f.setSize(WindowWidth, WindowHeight); // penetapan ukuran untuk display dan borders
		f.setLocation(0, 0); // start top left
		f.setLayout(new BorderLayout()); // north south east west and centre

		f.add(new SPanel(new Dimension(WindowWidth, border)), BorderLayout.NORTH);
		f.add(new SPanel(new Dimension(WindowWidth, border)), BorderLayout.SOUTH);
		f.add(new SPanel(new Dimension(border, WindowHeight)), BorderLayout.EAST);
		f.add(new SPanel(new Dimension(0, WindowHeight)), BorderLayout.WEST); // menetapkan batas

		Tampilan tp = new Tampilan();
		tp.setBackground(Color.BLACK); // ubah background sudoku display warna hitam
		f.add(tp, BorderLayout.CENTER); // tambahkan sudoku display panel

		f.setVisible(true);
	}// akhiran dari method tampilkan gui

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ShowGUI();
			}
		}); // end of run()
	}// end of main

}// end of Sudoku class
