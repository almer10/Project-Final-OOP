public class Smethods {
	public static void start(byte[][] sudoku) {
		int count = 0;
		for (count = 0; count < 729; count++)
			sudoku[count][0] = (byte) (1 + (count % 9));
	}// end of start. 1-9 in 81 spots

	public static void trysudoku(byte[][] sudoku, byte startstep) {
		java.util.Random generator = new java.util.Random(System.currentTimeMillis());
		byte step = startstep;
		int trys = 0; // total of the tries to get a complete solution
		do // keep random try's until have a complete random sudoku
		{

			trys += 1; // count the tries
			boolean noblanks = true; // blank = 000000000 sudoku in a dead end
			step = startstep;

			while ((step < 81) && (noblanks)) // try a random selection run
			{
				byte number = (byte) generator.nextInt(9); // 0 ke 8
				byte position = (byte) generator.nextInt(81); // 0 ke 80
				step = Smethods.select(sudoku, number, position, step); // langkah -langkah

				boolean standalone = false;
				do // check to find any numbers which stand alone and must be selected
				{
					standalone = false;
					byte count; // counter for position 0 to 80
					byte incount; // counter for number 0 to 8
					for (count = 0; count < 81; count++) {
						byte nzeros = 0; // we start with no zero count
						for (incount = 0; incount < 9; incount++) {
							if (sudoku[count * 9 + incount][step] == 0)
								nzeros += 1; // count zeros
							else
								number = (byte) (sudoku[count * 9 + incount][step] - 1); // Offset to 0-8
							if (nzeros == 9)
								noblanks = false; // we have a blank a dead end
						} // end of checked number
						if ((nzeros == 8) && (number < 10)) {
							step = Smethods.select(sudoku, number, count, step); // we have a step
							standalone = true;
						} // end of select stand alone
					} // end of checked 81 numbers
				} // end of select stand alone numbers
				while (standalone);

			} // end of a random try
			Sudoku.step = step;
		} // end of try until we have a complete sudoku
		while ((step != 81) && (trys < 500)); // should never need more than 500

	}// end of try sudoku

	public static byte select(byte[][] sudoku, byte number, byte position, byte step) {
		if ((sudoku[position * 9 + number][step] == 0) || (sudoku[position * 9 + number][step] > 9))
			return step;// end of number not possible or is selected

		step += 1; // we can select so write this step to the sudoku array
		int count = 0;
		for (count = 0; count < 729; count++)
			sudoku[count][step] = sudoku[count][step - 1]; // copy existing to next step
		for (count = 0; count < 9; count++)
			sudoku[position * 9 + count][step] = 0; // gabisa dipilih yang ada di kotak

		byte row = (byte) (position / 9);
		for (count = 0; count < 9; count++)
			sudoku[row * 81 + count * 9 + number][step] = 0; // baris horizon

		byte column = (byte) (position % 9);
		for (count = 0; count < 9; count++)
			sudoku[column * 9 + count * 81 + number][step] = 0; // baris vertikal

		int brow = (position / 27) * 243; // baris block 0f 3
		column = (byte) (((position % 9) / 3) * 27); // kolom block of 3
		byte incount;
		for (incount = 0; incount < 3; incount++) {
			for (count = 0; count < 3; count++)
				sudoku[brow + column + count * 9 + incount * 81 + number][step] = 0; // box 3 x 3
		} // akhir dari box 3 x 3
			// we have selected one number
		sudoku[position * 9 + number][step] = (byte) (number + 11); // selected now 11 to 19
		return step;
	}// akhir dari pemilihan angka

}// akhir dari class
