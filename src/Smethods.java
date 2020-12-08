public class Smethods {
	public static void start(byte[][] sudoku) {
		int count = 0;
		for (count = 0; count < 729; count++)
			sudoku[count][0] = (byte) (1 + (count % 9));
	}

	public static void trysudoku(byte[][] sudoku, byte startstep) {
		java.util.Random generator = new java.util.Random(System.currentTimeMillis());
		byte step = startstep;
		int trys = 0; 
		do 
		{

			trys += 1; 
			boolean noblanks = true; 
			step = startstep;

			while ((step < 81) && (noblanks)) 
			{
				byte number = (byte) generator.nextInt(9); 
				byte position = (byte) generator.nextInt(81); 
				step = Smethods.select(sudoku, number, position, step);

				boolean standalone = false;
				do 
				{
					standalone = false;
					byte count; 
					byte incount; 
					for (count = 0; count < 81; count++) {
						byte nzeros = 0; 
						for (incount = 0; incount < 9; incount++) {
							if (sudoku[count * 9 + incount][step] == 0)
								nzeros += 1; 
							else
								number = (byte) (sudoku[count * 9 + incount][step] - 1); 
							if (nzeros == 9)
								noblanks = false; 
						} 
						if ((nzeros == 8) && (number < 10)) {
							step = Smethods.select(sudoku, number, count, step); 
							standalone = true;
						} 
					} 
				} 
				while (standalone);

			} 
			Sudoku.step = step;
		} 
		while ((step != 81) && (trys < 500)); 

	}

	public static byte select(byte[][] sudoku, byte number, byte position, byte step) {
		if ((sudoku[position * 9 + number][step] == 0) || (sudoku[position * 9 + number][step] > 9))
			return step;

		step += 1; 
		int count = 0;
		for (count = 0; count < 729; count++)
			sudoku[count][step] = sudoku[count][step - 1]; 
		for (count = 0; count < 9; count++)
			sudoku[position * 9 + count][step] = 0; 

		byte row = (byte) (position / 9);
		for (count = 0; count < 9; count++)
			sudoku[row * 81 + count * 9 + number][step] = 0; 

		byte column = (byte) (position % 9);
		for (count = 0; count < 9; count++)
			sudoku[column * 9 + count * 81 + number][step] = 0; 

		int brow = (position / 27) * 243; 
		column = (byte) (((position % 9) / 3) * 27);
		byte incount;
		for (incount = 0; incount < 3; incount++) {
			for (count = 0; count < 3; count++)
				sudoku[brow + column + count * 9 + incount * 81 + number][step] = 0; 
		} 

		sudoku[position * 9 + number][step] = (byte) (number + 11); 
		return step;
	}

}
