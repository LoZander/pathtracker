package pathtracker.gui.wimp;

import java.awt.*;

public class ScalingFontStrategy implements FontStrategy {
    @Override
    public Font getDefaultFont() {
        double scale = getScale();

        String fontName = "Arial";
        int fontStyle = Font.PLAIN;
        int fontSize = (int) Math.round(10 * scale);

        return new Font(fontName, fontStyle, fontSize);
    }

    @Override
    public Font getBigFont() {
        double scale = getScale();

        String fontName = "Arial";
        int fontStyle = Font.PLAIN;
        int fontSize = (int) Math.round(16 * scale);

        return new Font(fontName, fontStyle, fontSize);
    }

    @Override
    public Font getSmallFont() {
        double scale = getScale();

        String fontName = "Arial";
        int fontStyle = Font.PLAIN;
        int fontSize = (int) Math.round(8 * scale);

        return new Font(fontName, fontStyle, fontSize);
    }

    @Override
    public Font getTitleFont() {
        double scale = getScale();

        String fontName = "Arial";
        int fontStyle = Font.PLAIN;
        int fontSize = (int) Math.round(20 * scale);
        return new Font(fontName, fontStyle, fontSize);
    }

    private double getScale() {
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        double width = resolution.getWidth();
        return width / 1080;
    }
}
