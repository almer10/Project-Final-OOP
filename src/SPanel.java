import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;

public class SPanel extends Panel 
{
    private static final long serialVersionUID = 1L;
    Color LB = new Color(0xAD, 0xD8, 0xE6); 

    public SPanel(Dimension set) {
        super(); 
        this.setBackground(LB);
        this.setPreferredSize(set);
    }
}
