package standard.gui.wimp;

import framework.Gui;
import framework.Tracker;

import javax.swing.*;
import java.awt.*;

public class WimpGui implements Gui {
    private Tracker tracker;
    private Frame mainFrame;

    public WimpGui(Tracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public void run() {
        mainFrame = new JFrame();

    }
}
