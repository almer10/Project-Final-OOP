/*
Sudoku adalah program untuk menentukan problem solving dari sebuah angka

Program ini bertujuan untuk menyelsaikan bagaimana perintah sudoku bekerja
dengan study case yang ada
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Tampilan extends JPanel implements ActionListener // membuat tampilan
{
    private static final long serialVersionUID = 1L;
    private int DisplayWidth = 557; // tampilan sudoku dengan lebar 557 pixels
    private int DisplayHeight = 580; // tampilan sudoku dengan tinggi 580 pixels
    private int ButtonsWidth = 200; // tombol dengan lebar 200 pixels
    private final Color LB = new Color(0xAD, 0xD8, 0xE6); // Biru Terang
    private final Color DB = new Color(0x1E, 0x90, 0xFF); // Biru Gelap
    private final Color P = new Color(0x80, 0, 0x80); // Ungu

    public Tampilan() // construct
    {
        addMouseListener(new MouseAdapter() 
        {
            public void mousePressed(MouseEvent e) {
                selectNumber(e.getX(), e.getY()); 
            }
        });
        this.setLayout(new BorderLayout());

        JPanel pb = new JPanel(); // membuat tombol
        pb.setPreferredSize(new Dimension(ButtonsWidth, DisplayHeight));
        pb.setBackground(LB);

        FlowLayout FL = new FlowLayout();
        FL.setVgap(55);
        FL.setHgap(100); 
        pb.setLayout(FL);
        SButton EYS = new SButton("Pilihan", "EYS");
        EYS.addActionListener(this);
        pb.add(EYS);
        SButton SHS = new SButton("Hard", "SHS");
        SHS.addActionListener(this);
        pb.add(SHS);
        SButton SMS = new SButton("Medium", "SMS");
        SMS.addActionListener(this);
        pb.add(SMS);
        SButton SES = new SButton("Easy", "SES");
        SES.addActionListener(this);
        pb.add(SES);
        SButton GBS = new SButton("Mundur Satu Step", "GBS");
        GBS.addActionListener(this);
        pb.add(GBS);
        SButton STS = new SButton("Jawaban", "STS");
        STS.addActionListener(this);
        pb.add(STS);
        this.add(pb, BorderLayout.WEST); 
    }

    private void selectNumber(int x, int y) // memanggil method mouse click
    {
        int NumberPosition[] = { 3, 63, 124, 187, 248, 309, 372, 433, 494 }; 
        final byte pSNumberY = 19; 
        if (x < ButtonsWidth + NumberPosition[0])
            return; 
        x -= ButtonsWidth - NumberPosition[0]; 

        byte count;
        byte Xposition = 0; 
        for (count = 0; count < 9; count++) 
        {
            if (x > NumberPosition[count])
                Xposition = count; 
        } 

        byte Yposition = 0; 
        for (count = 0; count < 9; count++) 
        {
            if (y > NumberPosition[count])
                Yposition = count; 
        } 
        byte position = (byte) (Xposition + Yposition * 9); 

        byte Xnumber = 0; 
        x -= NumberPosition[Xposition]; 
        for (count = 0; count < 3; count++) 
        {
            if (x > pSNumberY * count)
                Xnumber = count; 
        } 

        byte Ynumber = 0; 
        y -= NumberPosition[Yposition]; 
        for (count = 0; count < 3; count++) 
        {
            if (y > pSNumberY * count)
                Ynumber = count; 
        } 
        byte number = (byte) (Xnumber + Ynumber * 3); 

        Sudoku.step = Smethods.select(Sudoku.sudoku, number, position, Sudoku.step);
        repaint(ButtonsWidth, 0, DisplayWidth, DisplayHeight); 

    }

    public Dimension getPreferredSize() 
    {
        return new Dimension(DisplayWidth + ButtonsWidth, DisplayHeight);
    }

    protected void paintComponent(Graphics g) {
        final byte Foot = 24; // tinggi dari dasar sudoku
        final byte NumberX = 11; // X offset untuk string selected display
        final byte NumberY = 54; // Y offset untuk string selected display
        final byte blanksize = 59; // ukuran dari angka yang tidak dipilih
        final byte pNumberX = 4; // X offset untuk tampilan string pencil
        final byte pNumberY = 18; // Y offset untuk tampilan string pencil
        final byte pSNumberX = 20; // X spacing untuk tampilan string pencil
        final byte pSNumberY = 19; // Y spacing untuk tampilan string pencil
        final int FootMessageX = 96; // X offset untuk pesan dibawah
        final int FootMessageY = 574; // X offset untuk pesan dibawah
        final int FootNumberX = 211; // X offset untuk langkah angka
        final int FootNumberY = 574; // Y offset langkah angka
        // Grid lines untuk tampilan
        int BigLines[] = { 0, 184, 369, 554, 577 }; // blok angka 3 x 3 dengan lebar 3 pixels
        int SmallLines[] = { 62, 123, 247, 308, 432, 493 }; // setiap angka dengan lebar 1 x 1 pixel
        int NumberPosition[] = { 3, 63, 124, 187, 248, 309, 372, 433, 494 }; // tampilan angka
        Font fontSelected = new Font("SansSerif", Font.ROMAN_BASELINE, 70); // angka yang dipilih
        Font fontFoot = new Font("SansSerif", Font.ROMAN_BASELINE, 20); // the foot text
        Font fontPencil = new Font("SansSerif", Font.ROMAN_BASELINE, 20); // pencil lines

        super.paintComponent(g); 
        g.setColor(DB);
        g.setFont(fontPencil);

        // batas horizontal
        byte count; 
        for (count = 0; count < 5; count++)
            g.fillRect(0, BigLines[count], DisplayWidth + ButtonsWidth, 3);
        for (count = 0; count < 6; count++)
            g.drawLine(0, SmallLines[count], DisplayWidth + ButtonsWidth, SmallLines[count]);
        // batas vertikal
        g.fillRect(BigLines[0] + ButtonsWidth, 0, 3, DisplayHeight);
        g.fillRect(BigLines[1] + ButtonsWidth, 0, 3, DisplayHeight - Foot);
        g.fillRect(BigLines[2] + ButtonsWidth, 0, 3, DisplayHeight - Foot);
        g.fillRect(BigLines[3] + ButtonsWidth, 0, 3, DisplayHeight);
        for (count = 0; count < 6; count++)
            g.drawLine(SmallLines[count] + ButtonsWidth, 0, SmallLines[count] + ButtonsWidth, DisplayHeight - Foot);
        // foot text
        g.setFont(fontFoot);
        g.drawString("Langkah        Solusi Sudoku", FootMessageX + ButtonsWidth, FootMessageY);
        g.drawString(String.valueOf(Sudoku.step), FootNumberX + ButtonsWidth, FootNumberY);
        byte numbercount;
        for (numbercount = 0; numbercount < 81; numbercount++) {
            g.setColor(DB); 
            byte zeros = 0; 
            byte outercount; 
            for (outercount = 0; outercount < 3; outercount++) {
                for (count = 0; count < 3; count++) {
                    byte pencilnumber = Sudoku.sudoku[count + outercount * 3 + numbercount * 9][Sudoku.step];
                    if (pencilnumber > 0) {
                        if (pencilnumber < 10) {
                            g.setFont(fontPencil);
                            g.drawString(String.valueOf(pencilnumber),
                                    NumberPosition[numbercount % 9] + (count * pSNumberX) + pNumberX + ButtonsWidth,
                                    NumberPosition[numbercount / 9] + outercount * pSNumberY + pNumberY);
                        } 
                        else {
                            g.setFont(fontSelected);
                            g.drawString(String.valueOf(pencilnumber - 10),
                                    NumberPosition[numbercount % 9] + ButtonsWidth + NumberX,
                                    NumberPosition[numbercount / 9] + NumberY);
                        } 
                    } 
                    else
                        zeros += 1;
                }
            }
            if (zeros == 9) {
                g.setColor(P);
                g.fillRect(NumberPosition[numbercount % 9] + ButtonsWidth, NumberPosition[numbercount / 9], blanksize,
                        blanksize);
            }
        } 
    }

    public void actionPerformed(ActionEvent e) // Panggil metod untuk tombol push/tekan
    {
        if (e.getActionCommand() == "EYS")
            Sudoku.step = 0; 
        else if (e.getActionCommand() == "SHS") {
            Smethods.trysudoku(Sudoku.sudoku, (byte) 0);
            Sudoku.step = 9; 
        } else if (e.getActionCommand() == "SMS") {
            Smethods.trysudoku(Sudoku.sudoku, (byte) 0);
            Sudoku.step = 18; 
        } else if (e.getActionCommand() == "SES") {
            Smethods.trysudoku(Sudoku.sudoku, (byte) 0);
            Sudoku.step = 36; 
        } else if (e.getActionCommand() == "STS") {
            Smethods.trysudoku(Sudoku.sudoku, Sudoku.step); 
        } else if (e.getActionCommand() == "GBS") {
            if (Sudoku.step > 0)
                Sudoku.step -= 1; 
        }

        repaint(ButtonsWidth, 0, DisplayWidth, DisplayHeight); // call the paint method
    }// end of button pressed method

}
