package pathtracker.gui.wimp;

import java.awt.*;

public interface FontStrategy {
    /**
     * The default font is the font used for most of the GUI.
     * @return The default font.
     */
    Font getDefaultFont();

    /**
     * This font is bigger than the default font, but not a title font.
     * @return The big font.
     */
    Font getBigFont();

    /**
     * This font is smaller than the default font.
     * @return The small font.
     */
    Font getSmallFont();

    /**
     * This font is to be used for titles, such a frame headers.
     * @return The title font.
     */
    Font getTitleFont();
}
