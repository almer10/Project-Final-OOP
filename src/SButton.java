import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Class ini membuat tombol dalam game
 * 
 * 
 */

class SButton extends JButton // membuat tombol dengan tampilan yang ditentukan
{
    private static final long serialVersionUID = 1L;
    Color DB = new Color(0x1E, 0x90, 0xFF);
    Color LB = new Color(0xAD, 0xD8, 0xE6); // Background dengan biru cerah

    public SButton(String action, String command) {
        super(action); // construct button
        this.setBackground(LB);
        this.setForeground(DB);
        this.setBorder(BorderFactory.createBevelBorder(0, DB, DB));
        this.setActionCommand(command);
    }// Akhir dari constructor

    public Dimension getPreferredSize() {
        return new Dimension(130, 30); // ukuran tombol yang sudah ditentukan
    }// akhir dari get dimension
}// akhir dari SButton
