package pathtracker.gui.wimp;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {
    public CustomLabel(String text) {
        super(text);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }
}
