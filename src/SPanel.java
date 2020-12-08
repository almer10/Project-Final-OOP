
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;

public class SPanel extends Panel // create border panels for the display
{
    private static final long serialVersionUID = 1L;
    Color LB = new Color(0xAD, 0xD8, 0xE6); // Background biru cerah

    public SPanel(Dimension set) {
        super(); // construct panel
        this.setBackground(LB);
        this.setPreferredSize(set);
    }// constructor berakhir
}// end of SPane
