package pathtracker.demos;

import pathtracker.doubles.TrackerStub2;
import pathtracker.framework.Gui;
import pathtracker.framework.Tracker;
import pathtracker.gui.wimp.WimpGui;

public class WimpGuiScrollDemo {
    public static void main(String[] args) {
        Tracker tracker = new TrackerStub2();
        Gui gui = new WimpGui(tracker);
        gui.run();
    }
}
