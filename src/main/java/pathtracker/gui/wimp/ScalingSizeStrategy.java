package pathtracker.gui.wimp;

import java.awt.*;

public class ScalingSizeStrategy implements SizeStrategy {
    public Dimension createDimension(int width, int height) {
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        double resWidth = resolution.getWidth();
        int scale = (int) Math.round(resWidth / 1080);
        return new Dimension(scale * width, scale * height);
    }
}
