import pathtracker.framework.Gui;
import pathtracker.gui.wimp.WimpGui;
import pathtracker.standard.TrackerImpl;
import pathtracker.standard.factories.AlphaTrackerFactory;

public class wimpTracker {
    public static void main(String[] args) {
        Gui gui = new WimpGui(new TrackerImpl(new AlphaTrackerFactory()));
        gui.run();
    }
}
