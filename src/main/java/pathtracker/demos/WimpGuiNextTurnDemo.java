package pathtracker.demos;

import pathtracker.doubles.TrackerStub;
import pathtracker.framework.Gui;
import pathtracker.framework.Tracker;
import pathtracker.gui.wimp.WimpGui;

public class WimpGuiNextTurnDemo {
    public static void main(String[] args) {
        Tracker tracker = new TrackerStub();
        Gui gui = new WimpGui(tracker);
        gui.run();
    }
}
